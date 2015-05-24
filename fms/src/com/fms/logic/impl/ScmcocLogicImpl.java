package com.fms.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Scmcoc;
import com.fms.core.entity.Settlement;
import com.fms.dao.ScmcocDao;
import com.fms.dao.SettlementDao;
import com.fms.logic.ScmcocLogic;
import com.fms.temp.TempScmcoc;

public class ScmcocLogicImpl implements ScmcocLogic {

	private ScmcocDao scmcocDao;
	private SettlementDao settlementDao;

	public List<Scmcoc> findAllScmcoc(AclUser loginUser, Boolean isCustom, String likeStr, Integer index, Integer length) {
		return scmcocDao.findAllScmcoc(isCustom, likeStr, index, length);
	}

	public Scmcoc findScmcocById(AclUser loginUser, String id) {
		return scmcocDao.findScmcocById(id);
	}

	public void saveScmcoc(AclUser loginUser, Scmcoc scmcoc) {
		scmcocDao.saveScmcoc(scmcoc);
	}

	public SettlementDao getSettlementDao() {
		return settlementDao;
	}

	public void setSettlementDao(SettlementDao settlementDao) {
		this.settlementDao = settlementDao;
	}

	public void betchSaveScmcoc(AclUser loginUser, List<Scmcoc> data) {
		scmcocDao.betchSaveScmcoc(data);
	}

	public void deleteScmcocById(AclUser loginUser, String id) {
		scmcocDao.deleteScmcocById(id);
	}

	public ScmcocDao getScmcocDao() {
		return scmcocDao;
	}

	public void setScmcocDao(ScmcocDao scmcocDao) {
		this.scmcocDao = scmcocDao;
	}

	public Scmcoc findScmcocByCode(AclUser loginUser, String code) {
		return this.scmcocDao.findScmcocByCode(code);
	}

	public void delete(AclUser loginUser, List<String> ids) {
		this.scmcocDao.delete(ids);
	}

	public Integer findDataCount(AclUser loginUser, String className, Boolean isCustom, String name) {
		return this.scmcocDao.findDataCount(className, isCustom, name);
	}

	private TempScmcoc setProperties(Scmcoc src, TempScmcoc tag) {
		if (null != src && null != tag) {
			tag.setCode(src.getCode());
			tag.setName(src.getName());
			tag.setLinkPhone(src.getLinkPhone());
			tag.setNetworkLink(src.getNetworkLink());
			tag.setAddress(src.getAddress());
			tag.setLinkMan(src.getLinkMan());
			tag.setEndDate(src.getEndDate());
			tag.setIsCustom(false);
			tag.setSettlement(src.getSettlement());
			tag.setNote(src.getNote());
			return tag;
		}
		return null;
	}

	public List<TempScmcoc> doValidata(AclUser loginUser, List<Scmcoc> data, Map<String, Settlement> map) {
		List<TempScmcoc> valiList = new ArrayList<TempScmcoc>();
		List<Scmcoc> scmList = this.scmcocDao.findAllScmcoc(false, null, -1, -1);
		List<Settlement> settList = this.settlementDao.findAllSettlement(null);
		Map<String, Scmcoc> scmcocCache = new HashMap<String, Scmcoc>();
		Map<String, Scmcoc> importDataCache = new HashMap<String, Scmcoc>();
		Map<String, Scmcoc> codeCache = new HashMap<String, Scmcoc>();
		Map<String, TempScmcoc> tempCodeCache = new HashMap<String, TempScmcoc>();
		Map<String, Settlement> settlementCache = new HashMap<String, Settlement>();
		for (Scmcoc s : valiList) {
			String key = s.getCode() + "/" + s.getName() + "/" + s.isCustom;
			scmcocCache.put(key, s);
			codeCache.put(s.getCode(), s);
		}
		for (Settlement seet : settList) {
			String keys = seet.getCode() + "/" + seet.getName();
			settlementCache.put(keys, seet);
		}
		try {
			// 开始验证数据
			for (Object obj : data) {
				Scmcoc impScm = (Scmcoc) obj;
				TempScmcoc temp = new TempScmcoc();
				if (null == impScm.getCode() || "".equals(impScm.getCode().trim())) {
					String mess = "编码不能为空; ";
					temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
				}
				if (null != impScm.getCode() || !"".equals(impScm.getCode().trim())) {
					if (codeCache.get(impScm.getCode()) != null) {
						String mess = "编码【" + impScm.getCode() + "】已用过; ";
						temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
					}
				}
				if (null == impScm.getName() || "".equals(impScm.getName().trim())) {
					String mess = "供应商名称不能为空; ";
					temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
				}
				if (null == impScm.getLinkMan() || "".equals(impScm.getLinkMan().trim())) {
					String mess = "联系人不能为空; ";
					temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
				}
				if (null != impScm.getEndDate() || !"".equals(impScm.getEndDate().trim())) {
					if (!isNumeric(impScm.getEndDate())) {
						String mess = "约定结算日期不是正整数或不是数字; ";
						temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
					} else {
						if (Integer.parseInt(impScm.getEndDate()) > 28 || Integer.parseInt(impScm.getEndDate()) <= 0) {
							String mess = "约定结算日期不在(1~28)之间; ";
							temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
						}
					}
				}
				String key2 = impScm.getCode() + "/" + impScm.getName();
				// 验证导入数据在系统中是否重复
				if (scmcocCache.get(key2) != null && null != impScm.getCode() && !"".equals(impScm.getCode().trim()) && null != impScm.getName() && !"".equals(impScm.getName().trim())) {
					// setProperties(impScm, temp);
					String mess = "对应编码【" + impScm.getCode() + "】、名称【 " + impScm.getName() + "】在系统中已存在; ";
					temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
				}
				// 验证导入数据自身是否有重复
				if (importDataCache.get(key2) != null && null != impScm.getCode() && !"".equals(impScm.getCode().trim()) && null != impScm.getName() && !"".equals(impScm.getName().trim())) {
					String mess = "对应编码【" + impScm.getCode() + "】、名称【 " + impScm.getName() + "】在导入文件中重复; ";
					temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
				}
				if (impScm.getSettlement().getName() == null || impScm.getSettlement().getName().equals("")) {
					String mess = "结算方式不能为空; ";
					temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
				}
				if (!"".equals(impScm.getSettlement().getName()) && map.get(impScm.getSettlement().getName()) == null) {
					String seetkey = impScm.getSettlement().getCode() + "/" + impScm.getSettlement().getName();
					if (settlementCache.get(seetkey) == null) {
						String mess = "结算方式在系统中不存在; ";
						temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
					}
				}
				if (null != impScm.getCode() && !"".equals(impScm.getCode()) && tempCodeCache.get(impScm.getCode()) != null) {
					String mess = "供应商或客户编号【" + impScm.getCode() + "】在导入的文件中有重复; ";
					temp.setErrorInfo(temp.getErrorInfo() == null ? "" + mess : temp.getErrorInfo() + mess);
				}
				tempCodeCache.put(impScm.getCode(), temp);
				importDataCache.put(key2, temp);
				valiList.add(setProperties(impScm, temp));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valiList;
	}

	private Scmcoc decProperties(AclUser loginUser, TempScmcoc src, Map<String, Settlement> map, String isScmcoc) {
		Scmcoc s = new Scmcoc();
		if (null != src) {
			s.setCode(src.getCode());
			s.setName(src.getName());
			s.setNote(src.getNote());
			s.setAddress(src.getAddress());
			s.setEndDate(src.getEndDate());
			s.setLinkMan(src.getLinkMan());
			s.setLinkPhone(src.getLinkPhone());
			s.setNetworkLink(src.getNetworkLink());
			if ("true".equals(isScmcoc)) {
				s.setIsCustom(false);
			} else {
				s.setIsCustom(true);
			}
			if (src.getSettlement() != null && src.getSettlement().getName() != null && !"".equals(src.getSettlement().getName())) {
				s.setSettlement(map.get(src.getSettlement().getName()));
			}
			return s;
		}
		return null;
	}

	public boolean doSaveExcelData(AclUser loginUser, List list, String isScmcoc) {
		List<Scmcoc> scmcockL = new ArrayList<Scmcoc>();
		List<Settlement> settList = this.settlementDao.findAllSettlement(null);
		Map<String, Settlement> map = new HashMap<String, Settlement>();
		for (Settlement sl : settList) {
			map.put(sl.getName(), sl);
		}
		// 重新验证是否有错误的数据
		for (Object obj : list) {
			TempScmcoc ts = (TempScmcoc) obj;
			if (null != ts.getErrorInfo() && !"".equals(ts.getErrorInfo().trim())) {
				return false;
			} else {
				scmcockL.add(decProperties(loginUser, ts, map, isScmcoc));
				continue;
			}
		}
		try {
			scmcocDao.batchSaveOrUpdate(scmcockL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	private boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	public Scmcoc findScmcocByName(String name) {
		return this.scmcocDao.findScmcocByName(name);
	}

}
