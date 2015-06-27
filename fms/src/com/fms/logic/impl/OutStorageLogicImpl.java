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
import com.fms.core.entity.Material;
import com.fms.core.entity.OrderItem;
import com.fms.core.entity.OutStorage;
import com.fms.core.entity.PurchaseItem;
import com.fms.core.entity.Scmcoc;
import com.fms.core.entity.Stock;
import com.fms.dao.MaterialDao;
import com.fms.dao.OrderDao;
import com.fms.dao.OutStorageDao;
import com.fms.dao.PurchaseBillDao;
import com.fms.dao.ScmcocDao;
import com.fms.dao.StockDao;
import com.fms.logic.OutStorageLogic;
import com.fms.temp.TempOutStorage;
import com.fms.utils.MathUtils;

public class OutStorageLogicImpl implements OutStorageLogic {
	protected OutStorageDao outStorageDao;
	protected MaterialDao materialDao;
	protected OrderDao orderDao;
	protected PurchaseBillDao purchaseBillDao;
	protected StockDao stockDao;
	protected ScmcocDao scmcocDao;

	public OutStorageDao getOutStorageDao() {
		return outStorageDao;
	}

	public void setOutStorageDao(OutStorageDao outStorageDao) {
		this.outStorageDao = outStorageDao;
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

	public List findStorage(AclUser user, String entityName, Date startDate, Date endDate, String scmcocName, String hsName, String flag, int index, int length) {
		return this.outStorageDao.findStorage(entityName, startDate, endDate, scmcocName, hsName, flag, index, length);
	}

	public Integer findDataCount(String entityName, Date startDate, Date endDate, String scmcocName, String hsName, String flag) {
		try {
			return this.outStorageDao.findDataCount(entityName, startDate, endDate, scmcocName, hsName, flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Integer findMaxSerialNo(AclUser user, String entityName, String imgExgFlag) {
		return this.outStorageDao.findMaxSerialNo(entityName, imgExgFlag);
	}

	public Object findStorageById(Class clazz, String id) {
		return this.outStorageDao.findStorageById(clazz, id);
	}

	public String saveStorage(AclUser user, OutStorage storage) {
		String mess = "";
		if (null != storage) {
			// 判断出库数量是否超量
			mess = checkImgQty(storage);
			if (StringUtils.isBlank(mess)) {
				this.outStorageDao.saveOrUpdate(storage);
			}
		}
		return mess;
	}

	/**
	 * 检查数量
	 * 
	 * @param storage
	 * @return
	 */
	private String checkImgQty(OutStorage storage) {
		String mess = "";
		Double total = 0d;// 出库总数
		Double total2 = 0d;// 采购单或订单表体某项物料的数量
		Double total3 = 0d;// 出库总数
		Double yuliang = 0d;// 可出库总数
		total = caluateQty(storage);// 原料或成品出库汇总
		// total3 = this.outStorageDao.countQtyByPurchaseNo(storage)
		if (StringUtils.isNotBlank(storage.getPurchaseNo())) {
			if (ImgExgFlag.IMG.equals(storage.getImgExgFlag())) {
				// 原料出库汇总
				total2 = (Double) this.outStorageDao.countPurchaseItemQty(storage);
			}
			if (null != total && null != total2) {
				yuliang = total2 - total + total3;
				OutStorage _storage = (OutStorage) this.outStorageDao.getObjectById("OutStorage", storage.getId());
				if (StringUtils.isNotBlank(storage.getId()) && _storage.getPurchaseNo().equals(storage.getPurchaseNo())) {
					if ((yuliang + _storage.getExpQty() - storage.getExpQty()) < 0) {
						mess = "超量" + MathUtils.abs((yuliang + _storage.getExpQty() - storage.getExpQty()));
					}
				} else {
					if ((yuliang - storage.getExpQty()) < 0) {
						mess = "超量" + MathUtils.abs((yuliang - storage.getExpQty()));
					}
				}
			}
		} else if (StringUtils.isNotBlank(storage.getOrderNo())) {
			if (ImgExgFlag.EXG.equals(storage.getImgExgFlag())) {
				// 成品出库汇总
				// total2 = (Double)
				// this.outStorageDao.countOutQtyByOrderNo(storage);
			}

		}
		return mess;
	}

	/**
	 * 原料或成品出库汇总
	 * 
	 * @param storage
	 * @return
	 */
	public Double caluateQty(OutStorage storage) {
		Double total = 0d;
		if (storage != null && StringUtils.isNotBlank(storage.getExpFlag()) && StringUtils.isNotBlank(storage.getImgExgFlag())) {
			total = (Double) this.outStorageDao.countQtyByPurchaseNo(storage);
		}
		return total;
	}

	public void deleteStoragesByIds(AclUser user, String entityName, String[] ids) {
		if (null != ids && ids.length > 0) {
			this.outStorageDao.deleteStoragesByIds(entityName, ids);
		}
	}

	public List doValidata(List<TempOutStorage> tempOutStorages) {
		if (!tempOutStorages.isEmpty()) {
			Map<String, Material> matMap = new HashMap<String, Material>();
			// Map<String, Scmcoc> scmMap = new HashMap<String, Scmcoc>();
			Map<String, Stock> stockMap = new HashMap<String, Stock>();
			Map<String, OutStorage> sysMap = new HashMap<String, OutStorage>();
			Map<String, TempOutStorage> selfMap = new HashMap<String, TempOutStorage>();
			Map<String, OrderItem> orderMap = new HashMap<String, OrderItem>();
			Map<String, PurchaseItem> purchaseMap = new HashMap<String, PurchaseItem>();
			List<Material> matList = this.materialDao.findAllMaterialInfo(null, null, null, -1, -1);
			List<OutStorage> outStoraList = this.outStorageDao.findStorage("OutStorage", null, null, null, null, null, -1, -1);
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
			for (OutStorage is : outStoraList) {
				String key = is.getImgExgFlag() + "~@~" + is.getMaterial().getHsCode() + "~@~" + is.getOutStorageNo();
				sysMap.put(key, is);
			}

			for (TempOutStorage tis : tempOutStorages) {
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
				if (StringUtils.isBlank(tis.getExpFlag())) {
					builder.append("出库类型不能为空!");
					tis.setErrorInfo(builder.toString());
					continue;
				} else if (null == ImpExpType.parseVal(tis.getExpFlag().trim())) {
					builder.append("出库类型不存在，请参照Excel导入模板中的【出库类型】备注!");
					tis.setErrorInfo(builder.toString());
					continue;
				}
				if (StringUtils.isBlank(tis.getOutStorageNo().trim())) {
					builder.append("出库单号不能为空!");
					tis.setErrorInfo(builder.toString());
					continue;
				}
				if (StringUtils.isBlank(tis.getExpQty().trim())) {
					builder.append("出库数量不能为空!");
					tis.setErrorInfo(builder.toString());
					continue;
				} else if (!MathUtils.isNumeric(tis.getExpQty().trim())) {
					builder.append("出库数量只能是数字!");
				} else if (Double.valueOf(tis.getExpQty().trim()) <= 0) {
					builder.append("出库数量必须大于零!");
				}
				if (StringUtils.isNotBlank(tis.getStockName()) && !stockMap.containsKey(tis.getStockName())) {
					builder.append("仓库名称在系统中不存在!");
				}
				// 判断物料编码是否有效
				if (StringUtils.isBlank(tis.getHsCode().trim())) {
					builder.append("物料编码不能为空!");
					// 如果出库类型是【原料其它出库】、【成品其它出库】，就检查物料编码在【物料清单】中否存在
				} else if (ImpExpType.getImpExpType(4).equals(tis.getExpFlag().trim()) || ImpExpType.getImpExpType(10).equals(tis.getExpFlag().trim())) {
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
					// 如果出库类型不是【原料其它出库】、【成品其它出库】
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
				String key = ImgExgFlag.parseValue(tis.getImgExgFlag()) + "~@~" + tis.getHsCode() + "~@~" + tis.getOutStorageNo();
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
		return tempOutStorages;
	}

	public Boolean doSaveExcelData(AclUser aclUser, List<TempOutStorage> list) {
		if (!list.isEmpty()) {
			for (TempOutStorage tb : list) {
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
			List<OutStorage> outStorages = new ArrayList<OutStorage>();
			Integer serailNo = this.outStorageDao.getSerialNo("OutStorage");
			for (TempOutStorage storage : list) {
				outStorages.add(convertData(aclUser, storage, serailNo, matMap, stockMap, scmMap, orderMap, purchaseMap));
			}
			this.outStorageDao.batchSaveOrUpdate(outStorages);
		}
		return true;

	}

	/**
	 * 把临时类转换成实体类
	 * 
	 * @param tis
	 * @return
	 */
	private OutStorage convertData(AclUser aclUser, TempOutStorage tis, Integer serailNo,//
			Map<String, Material> matMap, Map<String, Stock> stockMap,//
			Map<String, Scmcoc> scmMap, Map<String, OrderItem> orderMap,//
			Map<String, PurchaseItem> purchaseMap) {
		if (null != tis) {
			OutStorage outStorage = new OutStorage();
			outStorage.setSerialNo(serailNo == null ? 1 : serailNo++);// 流水号
			outStorage.setOutStorageNo(tis.getOutStorageNo().trim());// 出库单号
			outStorage.setPurchaseNo(tis.getPurchaseNo().trim());// 采购单号
			outStorage.setOrderNo(tis.getOrderNo());// 订单号
			outStorage.setMaterial(matMap.get(tis.getHsCode() + "~@~" + ImgExgFlag.parseValue(tis.getImgExgFlag())));// 物料清单对象
			outStorage.setExpQty(Double.valueOf(tis.getExpQty()));// 出库数量
			outStorage.setSpecQty(StringUtils.isBlank(tis.getSpecQty()) ? null : Double.valueOf(tis.getSpecQty()));// 数量/(包装)
			Integer pcs = MathUtils.round((StringUtils.isBlank(tis.getExpQty()) || StringUtils.isBlank(tis.getSpecQty())) ? null : MathUtils.dividend(outStorage.getExpQty(), outStorage.getSpecQty()),
					"up");
			outStorage.setPkgs(null == pcs ? null : Double.valueOf(pcs));// 件数(向上取整)
			outStorage.setImgExgFlag(ImgExgFlag.parseValue(tis.getImgExgFlag()));// 物料标志
			outStorage.setExpFlag(ImpExpType.parseVal(tis.getExpFlag()) + "");// 出库类型
			if (StringUtils.isNotBlank(tis.getStockName().trim())) {
				outStorage.setStock(stockMap.get(tis.getStockName().trim()));
			}
			if (ImgExgFlag.EXG.equals(outStorage.getImgExgFlag())) {
				if (StringUtils.isNotBlank(outStorage.getOrderNo())) {
					outStorage.setScmcoc(orderMap.get(outStorage.getOrderNo()) == null ? null : orderMap.get(outStorage.getOrderNo()).getOrderHead().getScmcoc());
				}
			} else if (ImgExgFlag.IMG.equals(outStorage.getImgExgFlag())) {
				if (StringUtils.isNotBlank(outStorage.getPurchaseNo())) {
					outStorage.setScmcoc(purchaseMap.get(outStorage.getPurchaseNo()) == null ? null : purchaseMap.get(outStorage.getPurchaseNo()).getPurchaseBill().getScmcoc());
				}
			}
			outStorage.setNote(tis.getNote());// 备注
			outStorage.setExpDate(new Date());
			outStorage.setHandling(aclUser.getLoginName());
			return outStorage;
		}
		return null;
	}

	public Object countExpQty(String imgExgFlag, String purachseNo, String orderNo, String hsCode) {
		return this.outStorageDao.countExpQty(imgExgFlag, purachseNo, orderNo, hsCode);
	}
}
