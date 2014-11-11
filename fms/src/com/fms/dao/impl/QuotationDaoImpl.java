package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.Quotation;
import com.fms.dao.QuotationDao;

public class QuotationDaoImpl extends BaseDaoImpl implements QuotationDao{

	public List<Quotation> findQuotations(String scmCocName, String hsCode,Date effectDate, int index,int length) {
		List params = new ArrayList();
		String hql="select q from Quotation q left join fetch q.scmcoc s left join fetch q.material m where 1=1 ";
		if(scmCocName!=null && !"".equals(scmCocName)){
			hql+=" and s.name = ? ";
			params.add(scmCocName);
		}
		if(hsCode!=null && !"".equals(hsCode)){
			hql+=" and m.code= ? ";
			params.add(hsCode);
		}
		if(effectDate!=null && !"".equals(effectDate)){
			hql+=" and q.effectDate= ? ";
			params.add(effectDate);
		}
		return this.findPageList(hql, params.toArray(), index, length);
	}
}
