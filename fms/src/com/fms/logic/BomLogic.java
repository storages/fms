package com.fms.logic;

import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.BomExg;
import com.fms.core.entity.BomImg;
import com.fms.core.entity.BomVersion;
import com.fms.temp.TempBom;

/**
 * Bom表接口
 * 
 * @author Administrator
 * 
 */
public interface BomLogic {
	/**
	 * 查找BOM成品
	 * 
	 * @return
	 */
	List<BomExg> findBomExg(AclUser loginUser,String hsName, String hsCode, String hsModel, Integer index, Integer length);

	/**
	 * 按条件查找记录数
	 * 
	 * @param className
	 * @param searchStr
	 * @return
	 */
	Integer findDataCount(AclUser loginUser,String className, String hsName, String hsCode, String hsModel);

	/**
	 * 保存BOM成品表
	 * 
	 * @param data
	 * @return
	 */
	List<BomExg> saveBomExg(AclUser loginUser,List<BomExg> data);

	/**
	 * 删除BOM成品表
	 * 
	 * @param idArr
	 */
	void delBomExgByIds(AclUser loginUser,String[] idArr);

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
	public List<BomImg> findBomImg(String hsName, String hsCode, String hsModel, String exgId, Integer verNo, Integer index, Integer length);

	/**
	 * 查找BOM版本号
	 * 
	 * @return
	 */
	List<BomVersion> findBomVersion(String exgBomId);

	/**
	 * 验证Excel导入Bom表数据
	 * 
	 * @param temps
	 * @return
	 */
	List<TempBom> doValidata(List<TempBom> temps, String exgId, Integer verNo);

	/**
	 * 保存导入的数据
	 * 
	 * @param data
	 * @return
	 */
	Boolean doSaveExcelData(List<TempBom> data, Integer verNo, String bomExgId);

	/**
	 * 根据成品id查找最大BOM版本
	 * 
	 * @param exgId
	 * @return
	 */
	public Integer findBomVersionNoByHead(String exgId);

	/**
	 * 保存BomVersion
	 * 
	 * @param bomVersion
	 * @return
	 */
	public BomVersion saveBomVersion(BomVersion bomVersion);

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
	 * 根据ID查询BomVerSion
	 * 
	 * @param verId
	 * @return
	 */
	public BomVersion findBomVersionById(String verId);

	/**
	 * 根据BomVersionNo查询BomVerSion
	 * 
	 * @param verNo
	 * @return
	 */
	public BomVersion findBomVersionByVerNo(Integer verNo);

	/**
	 * 批量保存BomImg
	 * 
	 * @param bomImgs
	 * @return
	 */
	public List<BomImg> saveBomImgs(List<BomImg> bomImgs);

	/**
	 * 根据id查找BomImg
	 * 
	 * @param imgId
	 * @return
	 */
	public BomImg findBomImgById(String imgId);

	/**
	 * 根据ID删除BomImg
	 */
	public void delBomImgByIds(String[] imgIds);
}
