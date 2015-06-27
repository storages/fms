package com.fms.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.fms.commons.ImgExgFlag;
import com.fms.commons.ImpExpType;
import com.fms.core.entity.AclUser;
import com.fms.core.entity.InStorage;
import com.fms.core.entity.Material;
import com.fms.core.entity.OrderItem;
import com.fms.core.entity.PurchaseItem;
import com.fms.core.entity.Scmcoc;
import com.fms.core.entity.Stock;
import com.fms.dao.MaterialDao;
import com.fms.dao.OrderDao;
import com.fms.dao.OutStorageDao;
import com.fms.dao.PurchaseBillDao;
import com.fms.dao.ScmcocDao;
import com.fms.dao.StockDao;
import com.fms.dao.StorageDao;
import com.fms.logic.StorageLogic;
import com.fms.temp.TempInStorage;
import com.fms.utils.MathUtils;

public class StorageLogicImpl implements StorageLogic {
	protected StorageDao storageDao;
	protected MaterialDao materialDao;
	protected OrderDao orderDao;
	protected PurchaseBillDao purchaseBillDao;
	protected StockDao stockDao;
	protected ScmcocDao scmcocDao;
	protected OutStorageDao outStorageDao;

	public void setStorageDao(StorageDao storageDao) {
		this.storageDao = storageDao;
	}

	public MaterialDao getMaterialDao() {
		return materialDao;
	}

	public void setMaterialDao(MaterialDao materialDao) {
		this.materialDao = materialDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public PurchaseBillDao getPurchaseBillDao() {
		return purchaseBillDao;
	}

	public void setPurchaseBillDao(PurchaseBillDao purchaseBillDao) {
		this.purchaseBillDao = purchaseBillDao;
	}

	public StockDao getStockDao() {
		return stockDao;
	}

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

	public ScmcocDao getScmcocDao() {
		return scmcocDao;
	}

	public void setScmcocDao(ScmcocDao scmcocDao) {
		this.scmcocDao = scmcocDao;
	}

	public OutStorageDao getOutStorageDao() {
		return outStorageDao;
	}

	public void setOutStorageDao(OutStorageDao outStorageDao) {
		this.outStorageDao = outStorageDao;
	}

	public StorageDao getStorageDao() {
		return storageDao;
	}

	public List findStorage(AclUser user, String entityName, Date startDate, Date endDate, String scmcocName, String hsName, String flag, int index, int length) {
		return this.storageDao.findStorage(entityName, startDate, endDate, scmcocName, hsName, flag, index, length);
	}

	public Integer findDataCount(String entityName, Date startDate, Date endDate, String scmcocName, String hsName, String flag) {
		try {
			return this.storageDao.findDataCount(entityName, startDate, endDate, scmcocName, hsName, flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Integer findMaxSerialNo(AclUser user, String entityName, String imgExgFlag) {
		return this.storageDao.findMaxSerialNo(entityName, imgExgFlag);
	}

	public Object findStorageById(Class clazz, String id) {
		return this.storageDao.findStorageById(clazz, id);
	}

	public String checkQty(InStorage storage) {
		String mess = "";
		if (null != storage) {
			// 判断入库数量是否超量
			mess = checkImpQty(storage);
		}
		return mess;
	}

	public String saveStorage(AclUser user, InStorage storage) {
		if (null != storage) {
			this.storageDao.saveOrUpdate(storage);
			// 反写采购单是否完结(入库数量>=采购单数量，即为完结)
			// 入库总数
			Double total = (Double) this.storageDao.countQtyByPurchaseNo(storage);
			// 采购单总数
			Double total2 = (Double) this.storageDao.countPurchaseItemQty(storage);
			if (total2 != null && total != null && total >= total2) {
				List<PurchaseItem> items = this.purchaseBillDao.getPurchaseBill(storage.getPurchaseNo());
				Boolean isFlag = false;
				if (items != null && items.size() > 0) {
					for (int i = 0; i < items.size(); i++) {
						PurchaseItem item = items.get(i);
						if (item.getMaterial().getHsCode().equals(storage.getMaterial().getHsCode())) {
							item.setIsCompalte(true);
							this.purchaseBillDao.saveOrUpdate(item.getPurchaseBill());
						}
						if (null != item.getIsCompalte() && item.getIsCompalte()) {
							isFlag = true;
						} else {
							isFlag = false;
						}
					}
					if (isFlag) {
						items.get(0).getPurchaseBill().setIsComplete(true);
						this.purchaseBillDao.saveOrUpdate(items.get(0).getPurchaseBill());
					}
				}
			} else {
				List<PurchaseItem> items = this.purchaseBillDao.getPurchaseBill(storage.getPurchaseNo());
				if (items != null && items.size() > 0) {
					for (int i = 0; i < items.size(); i++) {
						PurchaseItem item = items.get(i);
						if (item.getMaterial().getHsCode().equals(storage.getMaterial().getHsCode())) {
							item.setIsCompalte(false);
							this.purchaseBillDao.saveOrUpdate(item.getPurchaseBill());
						}

					}
					items.get(0).getPurchaseBill().setIsComplete(false);
					this.purchaseBillDao.saveOrUpdate(items.get(0).getPurchaseBill());
				}
			}
		}
		return null;
	}

	/**
	 * 入库检查数量
	 * 
	 * @param storage
	 * @return
	 */
	private String checkImpQty(InStorage storage) {
		String mess = "";
		Double inToyalQty = 0d;// 入库总数
		Double outToyalQty = 0d;// 出库总数
		Double materialQty = 0d;// 采购单或订单表体某项物料的数量
		Double yuliang = 0d;// 可入库总数
		if (null != storage) {
			// 原料入库
			if (ImgExgFlag.IMG.equals(storage.getImgExgFlag())) {
				// 根据入库信息查询已入库总数(已经入库数)
				inToyalQty = (Double) this.storageDao.countQtyByPurchaseNo(storage);
				// 根据采购单来查找原料出库汇总(已经出库数)
				outToyalQty = (Double) this.storageDao.countImgExpStorageQty(storage.getPurchaseNo(), storage.getMaterial().getHsCode());
				// 根据入库信息查询采购单表体某项物料的总数(应该入库数)
				materialQty = (Double) this.storageDao.findInStorageQty(storage.getPurchaseNo(), storage.getMaterial().getHsCode());
				if (null != materialQty && null != inToyalQty && null != outToyalQty) {
					// 计算可入库数量
					yuliang = materialQty - inToyalQty + outToyalQty;
				}
				// 如果是修改
				if (StringUtils.isNotBlank(storage.getId())) {
					InStorage _storage = (InStorage) this.storageDao.getObjectById("InStorage", storage.getId());
					if (_storage.getPurchaseNo().equals(storage.getPurchaseNo())) {
						if ((yuliang + _storage.getInQty() - storage.getInQty()) < 0) {
							mess = "超量" + MathUtils.abs((yuliang + _storage.getInQty() - storage.getInQty()));
						}
					}
					// 新增时
				} else if ((yuliang - storage.getInQty()) < 0) {
					mess = "超量" + MathUtils.abs((yuliang - storage.getInQty()));
				}
				// 成品入库
			} else if (ImgExgFlag.EXG.equals(storage.getImgExgFlag())) {
				// 根据入库信息查询已入库总数(已经入库数)
				inToyalQty = (Double) this.storageDao.countQtyByPurchaseNo(storage);
				// 根据采购单来查找成品出库汇总(已经出库数)
				outToyalQty = (Double) this.storageDao.countExgExpStorageQty(storage.getOrderNo(), storage.getMaterial().getHsCode());
				// 根据入库信息查询采购单表体某项物料的总数(应该入库数)
				materialQty = Double.parseDouble(this.storageDao.findInStorageExgQty(storage.getOrderNo(), storage.getMaterial().getHsCode()).toString());
				if (null != materialQty && null != inToyalQty && null != outToyalQty) {
					// 计算可入库数量
					yuliang = materialQty - inToyalQty + outToyalQty;
				}
				// 如果是修改
				if (StringUtils.isNotBlank(storage.getId())) {
					InStorage _storage = (InStorage) this.storageDao.getObjectById("InStorage", storage.getId());
					if (_storage.getOrderNo().equals(storage.getOrderNo())) {
						if ((yuliang + _storage.getInQty() - storage.getInQty()) < 0) {
							mess = "超量" + MathUtils.abs(yuliang + _storage.getInQty() - storage.getInQty());
						}
					}
					// 新增时
				} else if ((yuliang - storage.getInQty()) < 0) {
					mess = "超量" + MathUtils.abs((yuliang - storage.getInQty()));
				}
			}
		}

		return mess;
	}

	/**
	 * 原料或成品入库汇总
	 * 
	 * @param storage
	 * @return
	 */
	public Double caluateQty(InStorage storage) {
		Double total = 0d;
		if (storage != null && StringUtils.isNotBlank(storage.getImpFlag()) && StringUtils.isNotBlank(storage.getImgExgFlag())) {
			total = (Double) this.storageDao.countQtyByPurchaseNo(storage);
		}
		return total;
	}

	public void deleteStoragesByIds(AclUser user, String entityName, String[] ids) {
		if (null != ids && ids.length > 0) {
			this.storageDao.deleteStoragesByIds(entityName, ids);
		}
	}

	public List doValidata(List<TempInStorage> tempInStorages) {
		if (!tempInStorages.isEmpty()) {
			Map<String, Material> matMap = new HashMap<String, Material>();
			// Map<String, Scmcoc> scmMap = new HashMap<String, Scmcoc>();
			Map<String, Stock> stockMap = new HashMap<String, Stock>();
			Map<String, InStorage> sysMap = new HashMap<String, InStorage>();
			Map<String, TempInStorage> selfMap = new HashMap<String, TempInStorage>();
			Map<String, OrderItem> orderMap = new HashMap<String, OrderItem>();
			Map<String, PurchaseItem> purchaseMap = new HashMap<String, PurchaseItem>();
			List<Material> matList = this.materialDao.findAllMaterialInfo(null, null, null, -1, -1);
			List<InStorage> inStoraList = this.storageDao.findStorage("InStorage", null, null, null, null, null, -1, -1);
			List<OrderItem> orderList = this.orderDao.findOrderItems();
			List<PurchaseItem> purchaseItems = this.purchaseBillDao.findPurchaseItems();
			// List<Scmcoc> scmList = this.scmcocDao.findAllScmcoc(null, null,
			// -1, -1);
			List<Stock> stockList = this.stockDao.findAllStock(null, -1, -1);
			for (Material m : matList) {
				String key = m.getHsCode() + "~@~" + m.getImgExgFlag();
				matMap.put(key, m);
			}
			for (OrderItem item : orderList) {
				orderMap.put(item.getOrderHead().getOrderNo(), item);
			}
			for (PurchaseItem item : purchaseItems) {
				purchaseMap.put(item.getPurchaseBill().getPurchaseNo(), item);
			}
			for (Stock stock : stockList) {
				stockMap.put(stock.getName(), stock);
			}
			for (InStorage is : inStoraList) {
				String key = is.getImgExgFlag() + "~@~" + is.getMaterial().getHsCode() + "~@~" + is.getInStorageNo();
				sysMap.put(key, is);
			}

			for (TempInStorage tis : tempInStorages) {
				StringBuilder builder = new StringBuilder();
				if (StringUtils.isBlank(tis.getImgExgFlag())) {
					builder.append("物料标志不能为空!");
					tis.setErrorInfo(builder.toString());
					continue;
				} else if (null == ImgExgFlag.parseValue(tis.getImgExgFlag().trim())) {
					builder.append("物料标志只能是【成品】或【原料】!");
					tis.setErrorInfo(builder.toString());
					continue;
				}
				if (StringUtils.isBlank(tis.getImpFlag())) {
					builder.append("入库类型不能为空!");
					tis.setErrorInfo(builder.toString());
					continue;
				} else if (null == ImpExpType.parseVal(tis.getImpFlag().trim())) {
					builder.append("入库类型不存在，请参照Excel导入模板中的【入库类型】备注!");
					tis.setErrorInfo(builder.toString());
					continue;
				}
				if (StringUtils.isBlank(tis.getInStorageNo().trim())) {
					builder.append("入库单号不能为空!");
					tis.setErrorInfo(builder.toString());
					continue;
				}
				if (StringUtils.isBlank(tis.getInQty().trim())) {
					builder.append("入库数量不能为空!");
					tis.setErrorInfo(builder.toString());
					continue;
				} else if (!MathUtils.isNumeric(tis.getInQty().trim())) {
					builder.append("入库数量只能是数字!");
				} else if (Double.valueOf(tis.getInQty().trim()) <= 0) {
					builder.append("入库数量必须大于零!");
				}
				if (StringUtils.isNotBlank(tis.getStockName()) && !stockMap.containsKey(tis.getStockName())) {
					builder.append("仓库名称在系统中不存在!");
				}
				// 判断物料编码是否有效
				if (StringUtils.isBlank(tis.getHsCode().trim())) {
					builder.append("物料编码不能为空!");
					// 如果入库类型是【原料其它入库】、【成品其它入库】，就检查物料编码在【物料清单】中否存在
				} else if (ImpExpType.getImpExpType(4).equals(tis.getImpFlag().trim()) || ImpExpType.getImpExpType(10).equals(tis.getImpFlag().trim())) {
					if (!matMap.containsKey(tis.getHsCode() + "~@~" + ImgExgFlag.parseValue(tis.getImgExgFlag()))) {
						builder.append(tis.getImgExgFlag() + "对应的物料编码在系统中不存在!");
					} else {
						// 这时虽然可以为空，但如果用户还是填了相应的单号，那么还是要验证有效性
						// 如果物料标记是【成品】
						if (ImgExgFlag.EXG.equals(ImgExgFlag.parseValue(tis.getImgExgFlag()))) {
							if (StringUtils.isNotBlank(tis.getOrderNo()) && !orderMap.containsKey(tis.getOrderNo().trim())) {
								builder.append("订单号码在系统中不存在!");
							}
						} else if (ImgExgFlag.IMG.equals(ImgExgFlag.parseValue(tis.getImgExgFlag()))) {
							if (StringUtils.isNotBlank(tis.getPurchaseNo())) {
								builder.append("采购单号码在系统中不存在!");
							}
						}
					}
				} else {
					if (!matMap.containsKey(tis.getHsCode() + "~@~" + ImgExgFlag.parseValue(tis.getImgExgFlag()))) {
						String str = ImgExgFlag.parseValue(tis.getImgExgFlag());
						builder.append(tis.getImgExgFlag() + "对应的物料编码在系统中不存在!");
					}
					// 如果入库类型不是【原料其它入库】、【成品其它入库】
					// 如果物料标记是【成品】,
					if (ImgExgFlag.EXG.equals(ImgExgFlag.parseValue(tis.getImgExgFlag()))) {
						// 判断订单号是否为空
						if (StringUtils.isBlank(tis.getOrderNo().trim())) {
							builder.append("订单号码不能为空!");
							// 订单号不为空就检查物料编码在导入的订单中是否存在(根据导入订单号)
						} else if (!orderMap.containsKey(tis.getOrderNo().trim())) {
							builder.append("订单号码在系统中不存在!");
						}
					} else if (ImgExgFlag.IMG.equals(ImgExgFlag.parseValue(tis.getImgExgFlag()))) {
						// 判断采购单号是否为空
						if (StringUtils.isBlank(tis.getPurchaseNo().trim())) {
							builder.append("采购单号码不能为空!");
							// 采购单号不为空就检查物料编码在导入的订单中是否存在(根据导入采购单号)
						} else if (!purchaseMap.containsKey(tis.getPurchaseNo().trim())) {
							builder.append("采购单号码在系统中不存在!");
						}
					}
				}

				if (StringUtils.isNotBlank(tis.getSpecQty()) && !MathUtils.isNumeric(tis.getSpecQty())) {
					builder.append("数量/(包装)只能是数字!");
				}
				String key = ImgExgFlag.parseValue(tis.getImgExgFlag()) + "~@~" + tis.getHsCode() + "~@~" + tis.getInStorageNo();
				if (selfMap.containsKey(key)) {
					builder.append("在导入Excel文件中重复!");
				} else {
					selfMap.put(key, tis);
				}
				if (sysMap.containsKey(key)) {
					builder.append("在系统中已存在!");
				}
				tis.setErrorInfo(builder.toString());
			}
		}
		return tempInStorages;
	}

	public Boolean doSaveExcelData(AclUser aclUser, List<TempInStorage> list) {
		if (!list.isEmpty()) {
			for (TempInStorage tb : list) {
				if (tb.getErrorInfo() != null && !"".equals(tb.getErrorInfo())) {
					return false;
				}
			}
			Map<String, Material> matMap = new HashMap<String, Material>();
			Map<String, Scmcoc> scmMap = new HashMap<String, Scmcoc>();
			Map<String, Stock> stockMap = new HashMap<String, Stock>();
			Map<String, OrderItem> orderMap = new HashMap<String, OrderItem>();
			Map<String, PurchaseItem> purchaseMap = new HashMap<String, PurchaseItem>();
			List<Material> matList = this.materialDao.findAllMaterialInfo(null, null, null, -1, -1);
			List<Stock> stockList = this.stockDao.findAllStock(null, -1, -1);
			List<Scmcoc> scmList = this.scmcocDao.findAllScmcoc(null, null, -1, -1);
			List<OrderItem> orderList = this.orderDao.findOrderItems();
			List<PurchaseItem> purchaseItems = this.purchaseBillDao.findPurchaseItems();
			for (Material m : matList) {
				String key = m.getHsCode() + "~@~" + m.getImgExgFlag();
				matMap.put(key, m);
			}
			for (Stock stock : stockList) {
				stockMap.put(stock.getName(), stock);
			}
			for (Scmcoc sc : scmList) {
				scmMap.put(sc.getName(), sc);
			}
			for (OrderItem item : orderList) {
				orderMap.put(item.getOrderHead().getOrderNo(), item);
			}
			for (PurchaseItem item : purchaseItems) {
				purchaseMap.put(item.getPurchaseBill().getPurchaseNo(), item);
			}
			List<InStorage> inStorages = new ArrayList<InStorage>();
			Integer serailNo = this.storageDao.getSerialNo("InStorage");
			for (TempInStorage storage : list) {
				inStorages.add(convertData(aclUser, storage, serailNo, matMap, stockMap, scmMap, orderMap, purchaseMap));
			}
			this.storageDao.batchSaveOrUpdate(inStorages);
		}
		return true;

	}

	/**
	 * 把临时类转换成实体类
	 * 
	 * @param tis
	 * @return
	 */
	private InStorage convertData(AclUser aclUser, TempInStorage tis, Integer serailNo,//
			Map<String, Material> matMap, Map<String, Stock> stockMap,//
			Map<String, Scmcoc> scmMap, Map<String, OrderItem> orderMap,//
			Map<String, PurchaseItem> purchaseMap) {
		if (null != tis) {
			InStorage inStorage = new InStorage();
			inStorage.setSerialNo(serailNo == null ? 1 : serailNo++);// 流水号
			inStorage.setInStorageNo(tis.getInStorageNo().trim());// 入库单号
			inStorage.setPurchaseNo(tis.getPurchaseNo().trim());// 采购单号
			inStorage.setOrderNo(tis.getOrderNo());// 订单号
			inStorage.setMaterial(matMap.get(tis.getHsCode() + "~@~" + ImgExgFlag.parseValue(tis.getImgExgFlag())));// 物料清单对象
			inStorage.setInQty(Double.valueOf(tis.getInQty()));// 入库数量
			inStorage.setSpecQty(StringUtils.isBlank(tis.getSpecQty()) ? null : Double.valueOf(tis.getSpecQty()));// 数量/(包装)
			Integer pcs = MathUtils.round((StringUtils.isBlank(tis.getInQty()) || StringUtils.isBlank(tis.getSpecQty())) ? null : MathUtils.dividend(inStorage.getInQty(), inStorage.getSpecQty()),
					"up");
			inStorage.setPkgs(null == pcs ? null : Double.valueOf(pcs));// 件数(向上取整)
			inStorage.setImgExgFlag(ImgExgFlag.parseValue(tis.getImgExgFlag()));// 物料标志
			inStorage.setImpFlag(ImpExpType.parseVal(tis.getImpFlag()) + "");// 入库类型
			if (StringUtils.isNotBlank(tis.getStockName().trim())) {
				inStorage.setStock(stockMap.get(tis.getStockName().trim()));
			}
			if (ImgExgFlag.EXG.equals(inStorage.getImgExgFlag())) {
				if (StringUtils.isNotBlank(inStorage.getOrderNo())) {
					inStorage.setScmcoc(orderMap.get(inStorage.getOrderNo()) == null ? null : orderMap.get(inStorage.getOrderNo()).getOrderHead().getScmcoc());
				}
			} else if (ImgExgFlag.IMG.equals(inStorage.getImgExgFlag())) {
				if (StringUtils.isNotBlank(inStorage.getPurchaseNo())) {
					inStorage.setScmcoc(purchaseMap.get(inStorage.getPurchaseNo()) == null ? null : purchaseMap.get(inStorage.getPurchaseNo()).getPurchaseBill().getScmcoc());
				}
			}
			inStorage.setNote(tis.getNote());// 备注
			inStorage.setImpDate(new Date());
			inStorage.setHandling(aclUser.getLoginName());
			return inStorage;
		}
		return null;
	}

	public InStorage findInStorageById(String id) {
		return this.storageDao.findInStorageById(id);
	}
}
