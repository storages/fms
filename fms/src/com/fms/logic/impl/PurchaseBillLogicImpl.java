package com.fms.logic.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import template.Test;

import com.fms.commons.PurchaseBillStatus;
import com.fms.core.entity.AclUser;
import com.fms.core.entity.PurchaseBill;
import com.fms.core.entity.PurchaseItem;
import com.fms.core.vo.entity.ExportPurchaseVo;
import com.fms.dao.PurchaseBillDao;
import com.fms.logic.PurchaseBillLogic;
import com.fms.utils.ExcelUtil;

public class PurchaseBillLogicImpl implements PurchaseBillLogic {

	protected PurchaseBillDao purchaseBillDao;

	private String rootPath = System.getProperty("user.dir") + "template" + File.separator;

	public PurchaseBillDao getPurchaseBillDao() {
		return purchaseBillDao;
	}

	public void setPurchaseBillDao(PurchaseBillDao purchaseBillDao) {
		this.purchaseBillDao = purchaseBillDao;
	}

	public PurchaseBill saveOrUpdatePurchaseBill(AclUser loginUser, PurchaseBill head) {
		return this.purchaseBillDao.saveOrUpdatePurchaseBill(head);
	}

	public List<PurchaseItem> findBillItemByAppNo(AclUser loginUser, String[] appNos) {
		return this.purchaseBillDao.findBillItemByAppNo(appNos);
	}

	public void betchDelPurchase(AclUser loginUser, List<PurchaseItem> items) {
		Set<PurchaseBill> headLs = new HashSet<PurchaseBill>();
		for (PurchaseItem item : items) {
			headLs.add(item.getPurchaseBill());
		}
		// 删除采购单的表体数据
		this.purchaseBillDao.deletePurchaseItem(items);
		// 删除采购单的表头数据
		if (!headLs.isEmpty()) {
			List<PurchaseBill> list = new ArrayList<PurchaseBill>(headLs);
			this.purchaseBillDao.deletePurchaseHead(list);
		}
	}

	public int getDataCount(AclUser loginUser, String appBillNo, Date startDate, Date endDate) {
		return this.purchaseBillDao.getDataCount(appBillNo, startDate, endDate);
	}

	public List<PurchaseBill> findPurchaseHeads(AclUser loginUser, String appNo, Date beginappDate, Date endappDate, int index, int length) {
		return this.purchaseBillDao.findPurchaseHeads(appNo, beginappDate, endappDate, index, length);
	}

	public PurchaseBill findPurchaseById(AclUser loginUser, String id) {
		return this.purchaseBillDao.findPurchaseById(id);
	}

	public void saveOrUpdate(AclUser loginUser, List<PurchaseBill> heads) {
		this.purchaseBillDao.batchSaveOrUpdate(heads);
	}

	public List<PurchaseItem> findItemByHid(AclUser loginUser, String hid, String hsCode) {
		return this.purchaseBillDao.findItemById(hid, hsCode);
	}

	public List<PurchaseItem> findItemByHids(AclUser loginUser, String[] hid) {
		return this.purchaseBillDao.findItemByHids(hid);
	}

	public Boolean purchEffect(AclUser loginUser, String[] hid, Boolean flag) {
		List<PurchaseItem> items = this.findItemByHids(loginUser, hid);
		for (PurchaseItem item : items) {
			item.setIsBuy(flag);
			item.setPurchaseDate(new Date());
		}
		this.purchaseBillDao.batchSaveOrUpdate(items);
		List<PurchaseBill> pbills = this.purchaseBillDao.findPurchaseBillByIds(hid);
		if (pbills != null && pbills.size() > 0) {
			for (PurchaseBill pb : pbills) {
				pb.setPurchStatus(flag ? PurchaseBillStatus.EFFECTED : PurchaseBillStatus.UNEFFECT);
				pb = this.purchaseBillDao.saveOrUpdatePurchaseBill(pb);
				items = this.findItemByHid(loginUser, pb.getId(), null);
				for (PurchaseItem it : items) {
					it.setPurchaseBill(pb);
				}
				this.purchaseBillDao.batchSaveOrUpdate(items);
			}
			return Boolean.TRUE;
		}
		return null;
	}

	public PurchaseItem findPurchaseItemById(String id) {
		return (PurchaseItem) this.purchaseBillDao.findPurchaseItemById(id);
	}

	public List<PurchaseItem> betchSavePurchaseItems(AclUser loginUser, List<PurchaseItem> items) {
		return this.purchaseBillDao.batchSaveOrUpdate(items);
	}

	public List<PurchaseBill> findPurchaseBillByIds(String[] hid) {
		return this.purchaseBillDao.findPurchaseBillByIds(hid);
	}

	public String exportPurchase(String[] hid) {
		List<PurchaseItem> items = this.purchaseBillDao.findItemsByHeads(hid);
		List<ExportPurchaseVo> exportData = this.convertDataToVo(items);
		writeDataToExcel(exportData);
		return null;
	}

	/**
	 * 把数据转换成vo类
	 * 
	 * @param items
	 * @return
	 */
	private List<ExportPurchaseVo> convertDataToVo(List<PurchaseItem> items) {
		List<ExportPurchaseVo> epv = new ArrayList<ExportPurchaseVo>();
		if (!items.isEmpty()) {
			for (PurchaseItem item : items) {
				ExportPurchaseVo vo = new ExportPurchaseVo();
				vo.setMaterial(item.getMaterial());
				vo.setNote(item.getPurchaseBill().getSpecialNote());
				vo.setPrice(item.getPrice());
				vo.setQty(item.getQty());
				vo.setPurchaseNo(item.getPurchaseBill().getPurchaseNo());
				vo.setScmcoc(item.getPurchaseBill().getScmcoc());
				vo.setDeliveryDate(item.getDeliveryDate());
				vo.setPurchaseDate(item.getPurchaseDate());
				epv.add(vo);
			}
			return epv;
		}
		return null;
	}

	private String writeDataToExcel(List<ExportPurchaseVo> exportData) {
		String mess = "";
		if (exportData.isEmpty()) {
			return "没有数据导出!";
		} else {
			Map<String, List<ExportPurchaseVo>> dataMap = new HashMap<String, List<ExportPurchaseVo>>();
			for (ExportPurchaseVo vo : exportData) {
				if (null != dataMap.get(vo.getScmcoc().getName())) {
					List<ExportPurchaseVo> voList = dataMap.get(vo.getScmcoc().getName());
					voList.add(vo);
				} else {
					List<ExportPurchaseVo> newList = new ArrayList<ExportPurchaseVo>();
					newList.add(vo);
					dataMap.put(vo.getScmcoc().getName(), newList);
				}
			}
			for (Entry<String, List<ExportPurchaseVo>> entry : dataMap.entrySet()) {
				List<ExportPurchaseVo> groupByScmList = entry.getValue();
				String path = Test.class.getResource("/").getPath() + File.separator + "template" + File.separator + "purchaseTemp.xls";
				mess = exportDataToTemplate(new File(path), groupByScmList);
			}
		}
		return mess;
	}

	/**
	 * 导出数据到系统模板
	 */
	private String exportDataToTemplate(File excelTemplate, List<ExportPurchaseVo> exportData) {
		String mess = "";
		byte[] templateData = ExcelUtil.downloadTemplate(excelTemplate);
		if (templateData == null || templateData.length <= 0) {
			// System.out.println("模板不存在!");
			mess = "模板不存在!";
		}
		try {
			ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
			Workbook wb = new HSSFWorkbook(new ByteArrayInputStream(templateData));
			Sheet sheet = wb.getSheet("For Check");
			if (sheet == null) {
				// System.out.println("模板不正确!");
				mess = "模板不正确!";
			} else {
				Row row = null;
				int no = 1;
				for (int i = 0; i < exportData.size(); i++) {
					row = sheet.createRow(i + 1);
					ExportPurchaseVo vo = exportData.get(i);
					ExcelUtil.writeCell(row, no + i, 0);// 序号
					ExcelUtil.writeCell(row, vo.getScmcoc().getName(), 1);// 供应商名称
					ExcelUtil.writeCell(row, vo.getPurchaseDate(), 2);// 采购日期
					ExcelUtil.writeCell(row, vo.getDeliveryDate(), 3);// 交货日期
					ExcelUtil.writeCell(row, vo.getMaterial().getHsCode(), 4);// 物料编码
					ExcelUtil.writeCell(row, vo.getMaterial().getHsName(), 5);// 物料名称
					ExcelUtil.writeCell(row, vo.getMaterial().getModel(), 6);// 物料规格
					ExcelUtil.writeCell(row, vo.getQty(), 7);// 数量
					ExcelUtil.writeCell(row, vo.getMaterial().getUnit().getName(), 8);// 计量单位
					ExcelUtil.writeCell(row, vo.getMaterial().getColor(), 9);// 颜色
					ExcelUtil.writeCell(row, vo.getPrice(), 10);// 单价
					ExcelUtil.writeCell(row, vo.getNote(), 11);// 备注
					ExcelUtil.writeCell(row, vo.getPurchaseNo(), 12);// 采购单号
				}
				wb.setForceFormulaRecalculation(true);
				wb.write(outBuffer);
				FileUtils.writeByteArrayToFile(excelTemplate, outBuffer.toByteArray());
				Runtime.getRuntime().exec("cmd  /c start " + excelTemplate.getAbsolutePath() + "");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mess;
	}
}
