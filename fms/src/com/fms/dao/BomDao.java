package com.fms.dao;

import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.BomExg;
import com.fms.core.entity.BomImg;
import com.fms.core.entity.BomVersion;

/**
 * Bom表
 * 
 * @author Administrator
 * 
 */
public interface BomDao extends BaseDao {

	/**
	 * 查找BOM成品
	 * 
	 * @return
	 */
	List<BomExg> findBomExg(String hsName, String hsCode, String hsModel, Integer index, Integer length);

	/**
	 * 按条件查找记录数
	 * 
	 * @param className
	 * @param searchStr
	 * @return
	 */
	Integer findDataCount(String className, String hsName, String hsCode, String hsModel);

	/**
	 * 分页查找成品对应原料BOM
	 * 
	 * @param hsName
	 * @param hsCode
	 * @param hsModel
	 * @param index
	 * @param length
	 * @return
	 */
	List<BomImg> findBomImg(String hsName, String hsCode, String hsModel, String exgId, Integer verNo, Integer index, Integer length);

	/**
	 * 查找BOM版本
	 * 
	 * @return
	 */
	List<BomVersion> findBomVersion(String exgBomId);

	/**
	 * 删除BOM成品
	 * 
	 * @param idArr
	 */
	void delBomExgByIds(String[] idArr);

	/**
	 * 根据成品id查找最大BOM版本
	 * 
	 * @param exgId
	 * @return
	 */
	public Integer findBomVersionNoByHead(String exgId);

	/**
	 * 根据成品id查找BomExg
	 * 
	 * @param id
	 * @return
	 */
	public BomExg findBomExgById(String id);

	/**
	 * 删除BOM版本
	 * 
	 * @param id
	 */
	public void delVersion(String id, Integer verNo);

	/**
	 * 根据BomVersionNo查询BomVerSion
	 * 
	 * @param verNo
	 * @return
	 */
	public BomVersion findBomVersionByVerNo(Integer verNo);

	/**
	 * 根据id查找
	 * 
	 * @param idBomImg
	 * @return
	 */
	public BomImg findBomImgById(String id);

	/**
	 * 根据多个ids来删除BomImg
	 * 
	 * @param imgIds
	 */
	public void delBomImgByIds(String[] imgIds);
}
