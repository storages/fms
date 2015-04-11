package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.BomExg;
import com.fms.core.entity.BomImg;
import com.fms.core.entity.BomVersion;
import com.fms.dao.BomDao;

public class BomDaoImpl extends BaseDaoImpl implements BomDao {

	public List<BomExg> findBomExg(String hsName, String hsCode, String hsModel, Integer index, Integer length) {
		List params = new ArrayList();
		StringBuilder builder = new StringBuilder();
		builder.append(" select exg from BomExg exg ");
		builder.append(" left join fetch exg.material mat ");
		builder.append(" left join fetch mat.materialType tp ");
		builder.append(" left join fetch mat.unit un where 1=1 ");
		if (hsName != null && !"".equals(hsName) && !"undefined".equals(hsName)) {
			builder.append(" and mat.hsName like ? ");
			params.add("%" + hsName + "%");
		}
		if (hsCode != null && !"".equals(hsCode) && !"undefined".equals(hsName)) {
			builder.append(" and mat.hsCode like ? ");
			params.add("%" + hsCode + "%");
		}
		if (hsModel != null && !"".equals(hsModel) && !"undefined".equals(hsModel)) {
			builder.append(" and mat.model like ? ");
			params.add("%" + hsModel + "%");
		}
		return this.findPageList(builder.toString(), params.toArray(), index, length);
	}

	public Integer findDataCount(String className, String hsName, String hsCode, String hsModel) {
		try {
			List params = new ArrayList();
			StringBuilder builder = new StringBuilder();
			builder.append("select count(exg.id) from BomExg exg ");
			builder.append(" left join exg.material mat where 1=1 ");
			if (hsName != null && !"".equals(hsName) && !"undefined".equals(hsName)) {
				builder.append(" and mat.hsName like ? ");
				params.add("%" + hsName + "%");
			}
			if (hsCode != null && !"".equals(hsCode) && !"undefined".equals(hsName)) {
				builder.append(" and mat.hsCode like ? ");
				params.add("%" + hsCode + "%");
			}
			if (hsModel != null && !"".equals(hsModel) && !"undefined".equals(hsModel)) {
				builder.append(" and mat.model like ? ");
				params.add("%" + hsModel + "%");
			}
			return this.count(builder.toString(), params.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void delBomExgByIds(String[] idArr) {
		if (idArr != null && idArr.length > 0) {
			List param = new ArrayList();
			String hql = "DELETE FROM BomExg a where a.id = ? ";
			param.add(idArr[0]);
			for (int i = 1; i < idArr.length; i++) {
				hql += " or a.id = ? ";
				param.add(idArr[i]);
			}
			this.batchUpdateOrDelete(hql, param.toArray());
		}
	}

	public List<BomImg> findBomImg(String hsName, String hsCode, String hsModel, String exgId, Integer verNo, Integer index, Integer length) {
		List params = new ArrayList();
		StringBuilder builder = new StringBuilder();
		builder.append(" select img from BomImg img ");
		builder.append(" left join fetch img.material mat ");
		builder.append(" left join fetch mat.materialType tp ");
		builder.append(" left join fetch img.bomVersion ver ");
		builder.append(" left join fetch ver.bomExg exg ");
		builder.append(" left join fetch mat.unit un where 1=1 ");
		if (hsName != null && !"".equals(hsName) && !"undefined".equals(hsName)) {
			builder.append(" and mat.hsName like ? ");
			params.add("%" + hsName + "%");
		}
		if (hsCode != null && !"".equals(hsCode) && !"undefined".equals(hsName)) {
			builder.append(" and mat.hsCode like ? ");
			params.add("%" + hsCode + "%");
		}
		if (hsModel != null && !"".equals(hsModel) && !"undefined".equals(hsModel)) {
			builder.append(" and mat.model like ? ");
			params.add("%" + hsModel + "%");
		}
		if (exgId != null && !"".equals(exgId) && !"undefined".equals(exgId)) {
			builder.append(" and exg.id =? ");
			params.add(exgId);
		}
		if (verNo != null && !"undefined".equals(verNo)) {
			builder.append(" and ver.versionNo =? ");
			params.add(verNo);
		}
		return this.findPageList(builder.toString(), params.toArray(), index, length);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Integer findDataImgCount(String className, String hsName, String hsCode, String hsModel) {
		try {
			List params = new ArrayList();
			StringBuilder builder = new StringBuilder();
			builder.append("select count(exg.id) from BomImg img ");
			builder.append(" left join img.material mat where 1=1 ");
			if (hsName != null && !"".equals(hsName) && !"undefined".equals(hsName)) {
				builder.append(" and mat.hsName like ? ");
				params.add("%" + hsName + "%");
			}
			if (hsCode != null && !"".equals(hsCode) && !"undefined".equals(hsName)) {
				builder.append(" and mat.hsCode like ? ");
				params.add("%" + hsCode + "%");
			}
			if (hsModel != null && !"".equals(hsModel) && !"undefined".equals(hsModel)) {
				builder.append(" and mat.model like ? ");
				params.add("%" + hsModel + "%");
			}
			return this.count(builder.toString(), params.toArray());
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public List<BomVersion> findBomVersion(String exgBomId) {
		return this.find("FROM BomVersion a " + " LEFT JOIN FETCH a.bomExg b " + " LEFT JOIN FETCH b.material c " + " WHERE b.id =? ", new Object[] { exgBomId });
	}

	/**
	 * 根据成品id查找BOM版本
	 * 
	 * @param exgId
	 * @return
	 */
	public Integer findBomVersionNoByHead(String exgId) {
		String hql = "select max(a.versionNo) from BomVersion a left join a.bomExg b where b.id = ? ";
		return (Integer) this.uniqueResult(hql, new Object[] { exgId });
	}

	/**
	 * 根据成品id查找BomExg
	 * 
	 * @param id
	 * @return
	 */
	public BomExg findBomExgById(String id) {
		return (BomExg) this.findUniqueResult("FROM BomExg a LEFT JOIN FETCH a.material b WHERE a.id =?", new Object[] { id });
	}

	/**
	 * 删除BOM版本
	 * 
	 * @param id
	 */
	public void delVersion(String id, Integer verNo) {
		String hql = "SELECT a FROM BomVersion a LEFT JOIN FETCH a.bomExg b WHERE b.id =? AND a.versionNo =? ";
		this.delete(this.uniqueResult(hql, new Object[] { id, verNo }));
	}

	/**
	 * 根据BomVersionNo查询BomVerSion
	 * 
	 * @param verNo
	 * @return
	 */
	public BomVersion findBomVersionByVerNo(Integer verNo) {
		String hql = "from BomVersion a left join fetch a.bomExg b left join fetch b.material c where a.versionNo =? ";
		return (BomVersion) this.uniqueResult(hql, new Object[] { verNo });
	}

	public BomImg findBomImgById(String id) {
		List params = new ArrayList();
		StringBuilder builder = new StringBuilder();
		builder.append(" select img from BomImg img ");
		builder.append(" left join fetch img.material mat ");
		builder.append(" left join fetch mat.materialType tp ");
		builder.append(" left join fetch img.bomVersion ver ");
		builder.append(" left join fetch ver.bomExg exg ");
		builder.append(" left join fetch mat.unit un where 1=1 ");
		if (id != null && !"".equals(id) && !"undefined".equals(id)) {
			builder.append(" and img.id =? ");
			params.add(id);
		}
		return (BomImg) this.uniqueResult(builder.toString(), params.toArray());
	}

	public void delBomImgByIds(String[] ids) {
		String hql = "DELETE FROM BomImg a where a.id = ? ";
		List param = new ArrayList();
		param.add(ids[0]);
		for (int i = 1; i < ids.length; i++) {
			hql += " or a.id = ? ";
			param.add(ids[i]);
		}
		this.batchUpdateOrDelete(hql, param.toArray());
	}

	public Integer findImgCount(String entityName, Integer verNo, String bomExgId) {
		String hql = "select count(img.id) from " + entityName.trim() + " img " + " left join img.bomVersion ver " + " left join ver.bomExg exg " + " where ver.versionNo =? and exg.id =? ";
		List list = new ArrayList();
		list.add(verNo);
		list.add(bomExgId);
		return this.count(hql, list.toArray());
	}

	public List<BomExg> findBomExgByIds(String[] ids) {
		List list = new ArrayList();
		if (null != ids && ids.length > 0) {
			String hql = "select a from BomExg a where a.id =? ";
			list.add(ids[0]);
			for (int i = 1; i < ids.length; i++) {
				hql += " or a.id =?";
				list.add(ids[i]);
			}
			return this.find(hql, list.toArray());
		}
		return null;
	}

	public List<BomImg> findBomImgByExgIds(String[] ids) {
		List list = new ArrayList();
		if (null != ids && ids.length > 0) {
			String hql = "select a from BomImg a left join fetch a.bomVersion b " + " left join fetch b.bomExg c where c.id =? ";
			list.add(ids[0]);
			for (int i = 1; i < ids.length; i++) {
				hql += " or c.id =?";
				list.add(ids[i]);
			}
			return this.find(hql, list.toArray());
		}
		return null;
	}

	public List<BomVersion> findBomVerSionByExgBomIds(String[] ids) {
		List list = new ArrayList();
		if (null != ids && ids.length > 0) {
			String hql = "select a from BomVersion a left join fetch a.bomExg b where b.id =? ";
			list.add(ids[0]);
			for (int i = 1; i < ids.length; i++) {
				hql += " or b.id =?";
				list.add(ids[i]);
			}
			return this.find(hql, list.toArray());
		}
		return null;
	}
}
