package com.fms.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.core.entity.Scmcoc;
import com.fms.core.entity.Settlement;
import com.fms.dao.ScmcocDao;
import com.fms.dao.SettlementDao;
import com.fms.logic.ScmcocLogic;
import com.fms.temp.TempScmcoc;


public class ScmcocLogicImpl implements ScmcocLogic {

	private ScmcocDao scmcocDao;
	private SettlementDao settDao;
	 
	public List<Scmcoc> findAllScmcoc(Boolean isCustom,String likeStr,Integer index,Integer length) {
		return scmcocDao.findAllScmcoc(isCustom,likeStr,index,length);
	}

	public Scmcoc findScmcocById(String id) {
		return scmcocDao.findScmcocById(id);
	}

	public void saveScmcoc(Scmcoc scmcoc) {
		scmcocDao.saveScmcoc(scmcoc);
	}

	public SettlementDao getSettDao() {
		return settDao;
	}

	public void setSettDao(SettlementDao settDao) {
		this.settDao = settDao;
	}

	public void betchSaveScmcoc(List<Scmcoc> data) {
		scmcocDao.betchSaveScmcoc(data);
	}

	public void deleteScmcocById(String id) {
		scmcocDao.deleteScmcocById(id);
	}

	public ScmcocDao getScmcocDao() {
		return scmcocDao;
	}

	public void setScmcocDao(ScmcocDao scmcocDao) {
		this.scmcocDao = scmcocDao;
	}

	public Scmcoc findScmcocByCode(String code) {
		return this.scmcocDao.findScmcocByCode(code);
	}

	public void delete(List<String> ids) {
		this.scmcocDao.delete(ids);
	}

	public Integer findDataCount(String className,Boolean isCustom,String name){
		return this.scmcocDao.findDataCount(className,isCustom,name);
	}

	public List doValidata(List dataList) {
		List<TempScmcoc> valiList= new ArrayList<TempScmcoc>();
		List<Scmcoc> scmList = this.scmcocDao.findAllScmcoc(false, null, -1, -1);
		List<Settlement> settList = this.settDao.findAllSettlement(null);
		Map<String,Scmcoc> scmcocCache = new HashMap<String,Scmcoc>(); 
		Map<String,Scmcoc> importDataCache = new HashMap<String,Scmcoc>(); 
		Map<String,Scmcoc> codeCache = new HashMap<String,Scmcoc>(); 
		Map<String,Settlement> settlementCache = new HashMap<String,Settlement>(); 
		for(Scmcoc s:valiList){
			String key = s.getCode()+"/"+s.getName()+"/"+s.isCustom;
			scmcocCache.put(key,s);
			codeCache.put(s.getCode(), s);
		}
		for(Settlement seet:settList){
			String keys = seet.getCode()+"/"+seet.getName();
			settlementCache.put(keys, seet);
		}
		//开始验证数据
		for(Object obj:dataList){
			Scmcoc impScm = (Scmcoc) obj;
			TempScmcoc temp = new TempScmcoc();
			if(null==impScm.getCode() || "".equals(impScm.getCode().trim())){
				String mess = "编码不能为空; ";
				temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
			}
			if(null!=impScm.getCode() || !"".equals(impScm.getCode().trim())){
				if(codeCache.get(impScm.getCode())!=null){
					String mess = "编码【"+impScm.getCode()+"】已用过; ";
					temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
				}
			}
			if(null==impScm.getName() || "".equals(impScm.getName().trim())){
				String mess = "供应商名称不能为空; ";
				temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
			}
			String key2 = impScm.getCode()+"/"+impScm.getName();
			//验证导入数据在系统中是否重复
			if(scmcocCache.get(key2)!=null && null!=impScm.getCode() && !"".equals(impScm.getCode().trim()) && null!=impScm.getName() && !"".equals(impScm.getName().trim())){
				//setProperties(impScm, temp);
				String mess = "对应编码【"+impScm.getCode()+"】、名称【 "+impScm.getName()+"】在系统中已存在; ";
				temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
			}
			//验证导入数据自身是否有重复
			if(importDataCache.get(key2)!=null && null!=impScm.getCode() && !"".equals(impScm.getCode().trim()) && null!=impScm.getName() && !"".equals(impScm.getName().trim())){
				String mess = "对应编码【"+impScm.getCode()+"】、名称【 "+impScm.getName()+"】在导入文件中重复; ";
				temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
			}
			if(impScm.getSettlement()==null){
				String mess = "结算方式不能为空; ";
				temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
			}
			if(impScm.getSettlement()!=null){
				String seetkey = impScm.getCode()+"/"+impScm.getName();
				if(settlementCache.get(seetkey)==null){
					String mess = "结算方式在系统中不存在; ";
					temp.setErrorInfo(temp.getErrorInfo()==null?""+mess:temp.getErrorInfo()+mess);
				}
			}
			importDataCache.put(key2, temp);
			valiList.add(setProperties(impScm, temp));
		}
		return valiList;
	}
	
	
	private TempScmcoc setProperties(Scmcoc src,TempScmcoc tag){
		if(null!=src && null!=tag){
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
}
