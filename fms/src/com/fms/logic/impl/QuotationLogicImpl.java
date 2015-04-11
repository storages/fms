package com.fms.logic.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.commons.ImgExgFlag;
import com.fms.commons.PurchaseBillStatus;
import com.fms.core.entity.AclUser;
import com.fms.core.entity.AppBillHead;
import com.fms.core.entity.AppBillItem;
import com.fms.core.entity.Material;
import com.fms.core.entity.PurchaseBill;
import com.fms.core.entity.PurchaseItem;
import com.fms.core.entity.Quotation;
import com.fms.core.entity.Scmcoc;
import com.fms.dao.AppBillDao;
import com.fms.dao.PurchaseBillDao;
import com.fms.dao.QuotationDao;
import com.fms.logic.MaterialLogic;
import com.fms.logic.QuotationLogic;
import com.fms.logic.ScmcocLogic;
import com.fms.temp.TempQuotation;
import com.fms.utils.FmsDateUtils;
import com.fms.utils.ServerUtils;

public class QuotationLogicImpl implements QuotationLogic {

	protected QuotationDao quotationDao;
	protected AppBillDao appBillDao;
	protected PurchaseBillDao purchaseBillDao;
	protected MaterialLogic materLogic;
	protected ScmcocLogic scmcocLogic;

	public List<Quotation> findQuotations(AclUser loginUser, String scmCocName, String hsCode, Date begineffectDate, Date endeffectDate, int index, int length) {
		return quotationDao.findQuotations(scmCocName, hsCode, begineffectDate, endeffectDate, index, length);
	}

	public Integer findDataCount(AclUser loginUser, String scmCocName, String hsCode, Date begineffectDate, Date endeffectDate) {
		return quotationDao.findDataCount(scmCocName, hsCode, begineffectDate, endeffectDate);
	}

	public QuotationDao getQuotationDao() {
		return quotationDao;
	}

	public void setQuotationDao(QuotationDao quotationDao) {
		this.quotationDao = quotationDao;
	}

	public List<Quotation> saveQuotations(AclUser loginUser, List<Quotation> list) {
		Integer serialNo = quotationDao.getSerialNo("Quotation");
		for (Quotation q : list) {
			if (q.getSerialNo() == null) {
				if (null != serialNo && serialNo < 0) {
					throw new RuntimeException("获取流水号时，可能给定的类名不正确!");
				} else if (serialNo == null) {
					serialNo = 1;
				} else {
					serialNo += 1;
				}
				q.setSerialNo(serialNo);
			}
		}
		return this.quotationDao.batchSaveOrUpdate(list);
	}

	public List<Scmcoc> findAll(AclUser loginUser) {
		return quotationDao.findAll();
	}

	public Scmcoc findById(AclUser loginUser, String id) {
		return quotationDao.findById(id);
	}

	public List<Quotation> findQuotationByIds(AclUser loginUser, String[] ids) {
		return this.quotationDao.findQuotationByIds(ids);
	}

	public Quotation findQuotationById(AclUser loginUser, String entityName, String id) {
		return (Quotation) this.quotationDao.findEntityById(entityName, id);
	}

	public void delQuotationById(AclUser loginUser, String[] ids) {
		this.quotationDao.delQuotationById(ids);
	}

	public int updatePrice(AclUser loginUser, String[] ids) {
		int count = 0;
		if (ids != null && ids.length > 0) {
			List<Quotation> quoList = this.findQuotationByIds(loginUser, ids);
			for (Quotation q : quoList) {
				if (q.getPrice() == null || q.getPrice() == 0d || q.getEffectDate() == null) {
					return -1;
				}
			}
			for (Quotation q : quoList) {
				// 更新申请单
				count += updateAppBillItem(loginUser, q);
				// 更新采购单
				count += updatePurchaseBill(loginUser, q);
			}
		}
		return count;
	}

	/**
	 * 根据报价单的生效日期来查找申请单
	 * 
	 * @param appDate
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<AppBillItem> findAppBillItem(AclUser loginUser, Quotation q) {
		return this.appBillDao.findAppBillItem(q);
	}

	/**
	 * 根据报价单的生效日期来更新采购单单价
	 * 
	 * @param appDate
	 * @return
	 */
	@SuppressWarnings("unused")
	private int updatePurchaseBill(AclUser loginUser, Quotation q) {
		try {
			List<PurchaseBill> list = this.purchaseBillDao.findPurchaseBill(q);
			if (null != list && list.size() > 0) {
				for (PurchaseBill bill : list) {
					// 必须是未生效的采购单才能更新单价
					if (bill.getPurchStatus().equals(PurchaseBillStatus.UNEFFECT)) {
						// 根据采购单表头id来查询所有表体
						List<PurchaseItem> items = this.purchaseBillDao.findItemById(bill.getId());
						Double amount = 0d;
						for (PurchaseItem temp : items) {
							if (!temp.getIsBuy()) {
								temp.setPrice(q.getPrice());
								temp.setAmount(temp.getPrice() * temp.getQty());
							}
							amount += temp.getAmount();
						}
						bill.setTotalAmount(amount);
						this.purchaseBillDao.batchSaveOrUpdate(list);
						this.purchaseBillDao.batchSaveOrUpdate(items);
					}
				}
				return list.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 更新申请单表体单价
	 * 
	 * @param q
	 * @return
	 */
	private int updateAppBillItem(AclUser loginUser, Quotation q) {
		try {
			List<AppBillItem> itemList = this.findAppBillItem(loginUser, q);
			List<AppBillItem> newItemList = new ArrayList<AppBillItem>();
			List<AppBillHead> headList = new ArrayList<AppBillHead>();
			if (null != itemList && itemList.size() > 0) {
				for (AppBillItem item : itemList) {
					// 这里更新申请单单价时，是否只能允许【未申请、申请不通过】的状态才能更新？现状是全部都更新
					// if(item.getAppStatus().equals(AppBillStatus.UNAPPLY)||item.getAppStatus().equals(AppBillStatus.APPROVEDNOT)){
					// }
					item.setPrice(q.getPrice());// 改变单价
					item.setAmount(item.getTotalQty() * item.getPrice());// 重新计算金额
					headList.add(item.getHead());
				}
				List<AppBillItem> list = this.appBillDao.betchSaveAppBillItem(itemList);
				// 更新申请单表头总金额
				for (AppBillHead head : headList) {
					newItemList = this.appBillDao.findAppBillItemByHead(head);
					if (null != newItemList && newItemList.size() > 0) {
						Double amount = 0d;
						for (AppBillItem item : newItemList) {
							amount += item.getAmount();
						}
						head.setTotalAmount(amount);
					}
				}
				this.appBillDao.batchSaveOrUpdate(headList);
				return itemList.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public AppBillDao getAppBillDao() {
		return appBillDao;
	}

	public void setAppBillDao(AppBillDao appBillDao) {
		this.appBillDao = appBillDao;
	}

	public PurchaseBillDao getPurchaseBillDao() {
		return purchaseBillDao;
	}

	public void setPurchaseBillDao(PurchaseBillDao purchaseBillDao) {
		this.purchaseBillDao = purchaseBillDao;
	}

	public MaterialLogic getMaterLogic() {
		return materLogic;
	}

	public void setMaterLogic(MaterialLogic materLogic) {
		this.materLogic = materLogic;
	}

	public ScmcocLogic getScmcocLogic() {
		return scmcocLogic;
	}

	public void setScmcocLogic(ScmcocLogic scmcocLogic) {
		this.scmcocLogic = scmcocLogic;
	}

	public List<?> doValidata(AclUser loginUser, List<?> dataList) {
		List<TempQuotation> tempList = new ArrayList<TempQuotation>();
		List<Quotation> quoList = this.quotationDao.findQuotations(null, null, null, null, -1, -1);
		List<Scmcoc> scmcocList = this.scmcocLogic.findAllScmcoc(loginUser, false, null, -1, -1);
		List<Material> materList = this.materLogic.findAllMaterialInfo(loginUser, null, ImgExgFlag.IMG, -1, -1);
		Map<String, Scmcoc> scmCache = new HashMap<String, Scmcoc>();
		Map<String, Quotation> mapCache = new HashMap<String, Quotation>();
		Map<String, Material> marCache = new HashMap<String, Material>();
		Map<String, TempQuotation> selfMap = new HashMap<String, TempQuotation>();
		for (Quotation q : quoList) {
			String key = q.getScmcoc().getCode() + "~@~" + q.getMaterial().getHsCode();
			mapCache.put(key, q);
		}
		for (Scmcoc scm : scmcocList) {
			scmCache.put(scm.getCode(), scm);
		}
		for (Material m : materList) {
			String key = m.getHsCode();
			marCache.put(key, m);
		}
		for (Object obj : dataList) {
			TempQuotation temp = (TempQuotation) obj;
			if (temp.getScmcocCode() == null || "".equals(temp.getScmcocCode())) {
				String mess = "供应商编码不能为空; ";
				temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
			}
			if (temp.getHsCode() == null || "".equals(temp.getHsCode())) {
				String mess = "物料编码不能为空; ";
				temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
			}
			if (temp.getPrice() == null || "".equals(temp.getPrice().trim())) {
				String mess = "单价不能为空; ";
				temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
			} else if (!ServerUtils.isNumeric(temp.getPrice().trim())) {
				String mess = "单价必须是数字; ";
				temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
			}
			Scmcoc scmcoc = scmCache.get(temp.getScmcocCode());
			if (scmcoc == null) {
				String mess = "供应商编码在系统中不存在; ";
				temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
			} else if ((scmcoc.getName() != null && !"".equals(scmcoc.getName())) && !scmcoc.getName().equals(temp.getScmcocName())) {
				String mess = "供应商编码对应的名称在系统中不存在; ";
				temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
			}
			Material m = marCache.get(temp.getHsCode());
			if (m == null) {
				String mess = "物料编码在系统中不存在; ";
				temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
			} else if ((m.getHsName() != null && !"".equals(m.getHsName())) && !m.getHsName().equals(temp.getHsName())) {
				String mess = "物料编码对应的名称在系统中不存在; ";
				temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
			}
			if (scmcoc != null && m != null) {
				String key = temp.getScmcocCode() + "~@~" + temp.getHsCode();
				Quotation q = mapCache.get(key);
				if (null != q && q.getEffectDate() != null) {
					String date1 = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
					String date2 = new SimpleDateFormat("yyyy-MM-dd").format(q.getEffectDate());
					if (date1.equals(date2) && scmcoc.getCode().equals(temp.getScmcocCode()) && m.getHsCode().equals(temp.getHsCode())) {
						String mess = "导入的报价单在系统中已存在; ";
						temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
					}
				}
			}
			String mykey = temp.getScmcocCode() + "~@~" + temp.getHsCode();
			if (selfMap.get(mykey) != null) {
				String mess = "该报价单在导入文件中重复; ";
				temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
			}
			selfMap.put(mykey, temp);
			tempList.add(temp);
		}
		return tempList;
	}

	public Boolean doSaveExcelData(AclUser loginUser, List<?> dataList) {
		List<Quotation> quoL = new ArrayList<Quotation>();
		// 重新验证是否有错误的数据
		for (Object obj : dataList) {
			TempQuotation tq = (TempQuotation) obj;
			if (null != tq.getErrorInfo() && !"".equals(tq.getErrorInfo().trim())) {
				return false;
			} else {
				Quotation q = new Quotation();
				quoL.add(decProperties(loginUser, tq, q));
				continue;
			}
		}
		try {
			this.quotationDao.batchSaveOrUpdate(quoL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 复制属性
	 * 
	 * @param src
	 * @param tag
	 * @return
	 */
	private Quotation decProperties(AclUser loginUser, TempQuotation src, Quotation tag) {
		if (null != src && null != tag) {
			Material m = this.materLogic.findMaterialByHsCode(loginUser, src.getHsCode());
			tag.setMaterial(m);
			Scmcoc scm = this.scmcocLogic.findScmcocByCode(loginUser, src.getScmcocCode());
			tag.setScmcoc(scm);
			tag.setPrice(src.getPrice() == null ? 0d : Double.parseDouble(src.getPrice()));
			tag.setEffectDate(FmsDateUtils.getCurrentFormatDate());
			Integer serialNo = quotationDao.getSerialNo("Quotation");
			tag.setSerialNo(serialNo == null ? 1 : serialNo + 1);
			return tag;
		}
		return null;
	}

	/**
	 * 根据物料、供应商、和日期来查询报价单
	 * 
	 * @param m
	 * @param date
	 * @return
	 */
	public Quotation findQuotationByCondention(AclUser loginUser, Material m, Scmcoc scm) {
		return this.quotationDao.findQuotationByCondention(m, scm);

	}
}
