package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.Quotation;
import com.fms.core.entity.Scmcoc;
import com.fms.dao.QuotationDao;

public class QuotationDaoImpl extends BaseDaoImpl implements QuotationDao{

	public List<Quotation> findQuotations(String scmCocName, String hsCode,Date begineffectDate,Date endeffectDate, int index,int length) {
		List params = new ArrayList();
		String hql="select q from Quotation q left join fetch q.scmcoc s left join fetch q.material m left join fetch m.unit u where 1=1 ";
		if(scmCocName!=null && !"".equals(scmCocName)){
			hql+=" and s.name like ? ";
			params.add("%"+scmCocName+"%");
		}
		if(hsCode!=null && !"".equals(hsCode)){
			hql+=" and m.code like ? ";
			params.add("%"+hsCode+"%");
		}
		if(begineffectDate!=null && !"".equals(begineffectDate)){
			hql+=" and q.effectDate>= ? ";
			params.add(begineffectDate);
		}
		if(endeffectDate!=null && !"".equals(endeffectDate)){
			hql+=" and q.effectDate<= ? ";
			params.add(endeffectDate);
		}
		return this.findPageList(hql, params.toArray(), index, length);
	}

	public Integer findDataCount(String scmCocName, String hsCode, Date begineffectDate,Date endeffectDate) {
		String hql = " select count(q) from Quotation q left join q.scmcoc s left join q.material m where 1=1 ";
		List params = new ArrayList();
		if(scmCocName!=null && !"".equals(scmCocName)){
			hql+=" and s.name like ? ";
			params.add("%"+scmCocName+"%");
		}
		if(hsCode!=null && !"".equals(hsCode)){
			hql+=" and m.code like ? ";
			params.add("%"+hsCode+"%");
		}
		if(begineffectDate!=null && !"".equals(begineffectDate)){
			hql+=" and q.effectDate>= ? ";
			params.add(begineffectDate);
		}
		if(endeffectDate!=null && !"".equals(endeffectDate)){
			hql+=" and q.effectDate<= ? ";
			params.add(endeffectDate);
		}
		return this.count(hql, params.toArray());
	}
	
	public List<Scmcoc> findAll(){
		return this.find("from Scmcoc a where a.isCustom = "+Boolean.FALSE);
	}
	public Scmcoc findById(String id){
		return (Scmcoc) this.findUniqueResult("from Scmcoc a where a.id = ?", new Object[]{id});
	}
}
