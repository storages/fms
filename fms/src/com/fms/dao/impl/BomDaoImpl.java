package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.BomExg;
import com.fms.dao.BomDao;

public class BomDaoImpl extends BaseDaoImpl implements BomDao {

	public List<BomExg> findBomExg(String hsName, String hsCode, String hsModel, Integer index, Integer length) {
		List params = new ArrayList();
		StringBuilder builder = new StringBuilder();
		builder.append(" from BomExg exg ");
		builder.append(" left join fetch exg.material mat ");
		builder.append(" left join fetch mat.materialType tp ");
		builder.append(" left join fetch mat.unit un where 1=1 ");
		if (hsName != null && !"".equals(hsName)) {
			builder.append(" and mat.hsName like ? ");
			params.add(hsName);
		}
		if (hsCode != null && !"".equals(hsCode)) {
			builder.append(" and mat.hsCode like ? ");
			params.add(hsCode);
		}
		if (hsModel != null && !"".equals(hsModel)) {
			builder.append(" and mat.hsModel like ? ");
			params.add(hsModel);
		}
		return this.findPageList(builder.toString(), params.toArray(), index, length);
	}

	public Integer findDataCount(String className, String hsName, String hsCode, String hsModel) {
		try {
			List params = new ArrayList();
			StringBuilder builder = new StringBuilder();
			builder.append("select count(id) from ? exg ");
			builder.append(" left join fetch exg.material mat where 1=1 ");
			params.add(className.trim());
			if (hsName != null && !"".equals(hsName)) {
				builder.append(" and mat.hsName like ? ");
				params.add(hsName);
			}
			if (hsCode != null && !"".equals(hsCode)) {
				builder.append(" and mat.hsCode like ? ");
				params.add(hsCode);
			}
			if (hsModel != null && !"".equals(hsModel)) {
				builder.append(" and mat.hsModel like ? ");
				params.add(hsModel);
			}
			return this.count(builder.toString(), params.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
