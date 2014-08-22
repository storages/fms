package com.fms.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.Department;
import com.fms.core.entity.Stock;
import com.fms.logic.StockLogic;
import com.fms.utils.AjaxResult;
import com.url.ajax.json.JSONObject;

public class StockAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected StockLogic stockLogic;
	
	private String ids;
	
	/*********部门实体类属性*************/
	private String code;
	private String name;
	private String note;
	
	
	/*********分页用的属性***********/
	private Integer dataTotal;//总记录数
	private String currIndex;//当前页码
	private String maxIndex;//每页显示最多条数
	private Integer pageNums;//共有多少页
	private String className="Stock";//表名称
	private String searchStr;//搜索条件
	private static final Integer DEFAULT_PAGESIZE = 11; 

	/*********获取前台选择的文件***********/
	private String fileName;
	private String fileContentType;
	private File file;
	
	/**
	 * 查询所有仓库列表【分页】
	 * @return
	 */
	public String findAllStock(){
		Integer curr = (null==currIndex || "".equals(currIndex))?1:Integer.parseInt(currIndex);//当前第几页
		Integer max = (null==maxIndex || "".equals(maxIndex))?1:Integer.parseInt(currIndex);//每页最多显示条数
		dataTotal = this.stockLogic.findDataCount(className,parse(searchStr));
		List<Stock> stocks = this.stockLogic.findAllStock(parse(searchStr),(curr-1)*DEFAULT_PAGESIZE,DEFAULT_PAGESIZE);
		request.put("stocks", stocks);
		this.request.put("currIndex", curr);
		this.request.put("maxIndex", max);
		this.request.put("pageNums", pageCount(max, dataTotal));
		this.request.put("searchStr", parse(searchStr));
		return this.SUCCESS;
	}
	
	
	
	private Integer pageCount(Integer maxIndex,Integer dataTotal){
		pageNums = (dataTotal / DEFAULT_PAGESIZE) + (dataTotal % DEFAULT_PAGESIZE > 0 ? 1 : 0); // 总页数
		if(pageNums==0){
			pageNums+=1;
		}
		return pageNums;
	}
	
	public String findStockById() throws Exception{
		if (null != ids && !"".equals(ids)) {
			String[] arrIds = ids.split(",");
			if (null != arrIds && arrIds.length > 0) {
				String id = arrIds[0];
				Stock stock = this.stockLogic.findStockById(id);
				if (null != stock) {
					this.request.put("stock", stock);
				}
			}
		}
		return "find";
	}
	
	
	/**
	 * 保存仓库信息
	 * @return
	 * @throws Exception
	 */
	public String saveStock() throws Exception{
		this.stockLogic.saveStock(this.setProperty(new Stock()));
		return "save";
	}
	
	
	/**
	 * 填充对象
	 * 
	 * @param scmcoc
	 * @return
	 */
	private Stock setProperty(Stock stock) {
		if(null!=ids && !"".equals(ids)){
			stock.setId(ids);
		}
		stock.setCode(parse(code));
		stock.setName(parse(name));
		stock.setNote(parse(note));
		return stock;
	}
	
	
	/**
	 * 验证仓库编码是否重复
	 */
	public void findStockByCode(){
		PrintWriter out = null;
		AjaxResult  result=new AjaxResult();
		try {
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			String findCode = this.stockLogic.findStockByCode(code);
			if(null!=findCode){
				result.setSuccess(false);
				result.setMsg("编码已使用过了！");
			}else{
				result.setSuccess(true);
			}			
			JSONObject json=new JSONObject(result);
			out.println(json.toString());
			out.flush();
		} catch (IOException e) {
			result.setMsg("对不起出错了：/n"+e.getMessage());
		}finally{
			if(out!=null){
				out.close();
			}
		}
	}
	
	/**
	 * 删除仓库信息
	 * @return
	 */
	public void deleteStock(){
		
		if (null != ids && !"".equals(ids)) {
			String [] idArr = ids.split(",");
			if(idArr!=null && idArr.length>0){
				PrintWriter out = null;
				AjaxResult  result=new AjaxResult();
				try {
					out = response.getWriter();
					response.setContentType("application/text");
					response.setCharacterEncoding("UTF-8");
					this.stockLogic.delStockById(idArr);
					result.setSuccess(true);
					result.setMsg("删除成功！");
					JSONObject json=new JSONObject(result);
					out.println(json.toString());
					out.flush();
					out.close();
				} catch (Exception e) {
					result.setSuccess(false);
					result.setMsg("数据被其它地方引用，不能删除！");
					JSONObject json=new JSONObject(result);
					out.println(json.toString());
					out.flush();
					out.close();
				}
			}
		}
	}
	
	
	public String importData(){
		
		return "";
	}
	
	public StockLogic getStockLogic() {
		return stockLogic;
	}

	public void setStockLogic(StockLogic stockLogic) {
		this.stockLogic = stockLogic;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getDataTotal() {
		return dataTotal;
	}

	public void setDataTotal(Integer dataTotal) {
		this.dataTotal = dataTotal;
	}

	public String getCurrIndex() {
		return currIndex;
	}

	public void setCurrIndex(String currIndex) {
		this.currIndex = currIndex;
	}

	public String getMaxIndex() {
		return maxIndex;
	}

	public void setMaxIndex(String maxIndex) {
		this.maxIndex = maxIndex;
	}

	public Integer getPageNums() {
		return pageNums;
	}

	public void setPageNums(Integer pageNums) {
		this.pageNums = pageNums;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public String getFileContentType() {
		return fileContentType;
	}



	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}



	public File getFile() {
		return file;
	}



	public void setFile(File file) {
		this.file = file;
	}

	 
}
