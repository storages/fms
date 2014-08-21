package com.fms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fms.base.action.BaseAction;
import com.fms.core.entity.Currencies;
import com.fms.logic.CurrenciesLogic;
import com.fms.utils.AjaxResult;
import com.url.ajax.json.JSONObject;

public class CurrenciesAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CurrenciesLogic currenciesLogic;
	private Currencies currencies;
	
	/****货币属性*****/
	private String ids;
	private String code;
	private String name;
	private Date createDate;
	private Date modifyDate;
	private String note;
	
	/*********分页用的属性***********/
	private Integer dataTotal;//总记录数
	private String currIndex;//当前页码
	private String maxIndex;//每页显示最多条数
	private Integer pageNums;//共有多少页
	private String className="Currencies";//表名称
	private String searchStr;//搜索条件
	private static final Integer DEFAULT_PAGESIZE = 11; 

	/**
	 * 查询所有货币
	 * 
	 * @return
	 */
	public String findAllCurrencies() {
		Integer curr = (null==currIndex || "".equals(currIndex))?1:Integer.parseInt(currIndex);//当前第几页
		Integer max = (null==maxIndex || "".equals(maxIndex))?1:Integer.parseInt(currIndex);//每页最多显示条数
		dataTotal = this.currenciesLogic.findDataCount(className,parse(searchStr));
		List<Currencies> currencies = this.currenciesLogic.findAllCurrencies(parse(searchStr),(curr-1)*DEFAULT_PAGESIZE,DEFAULT_PAGESIZE);
		this.request.put("currencies", currencies);
		this.request.put("currIndex", curr);
		this.request.put("maxIndex", max);
		this.request.put("pageNums", pageCount(max, dataTotal));
		this.request.put("searchStr", parse(searchStr));
		return this.SUCCESS;//是货币请求
	}

	/**
	 * 保存货币
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveCurrencies(){
		try{
			this.currenciesLogic.saveCurrencies(this.setProperty(new Currencies()));
		}catch(Exception e){
			e.printStackTrace();
		}
		return "save";
	}
	/**
	 * 根据编码来查询货币
	 * 
	 * @return
	 */
	public void findCurrenciesByCode() {
		
		
		PrintWriter out = null;
		AjaxResult  result=new AjaxResult();
		try {
			out = response.getWriter();
			response.setContentType("application/text");
			response.setCharacterEncoding("UTF-8");
			String findCode = this.currenciesLogic.findCurrenciesByCode(code);
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
	 * 填充对象
	 * 
	 * @param scmcoc
	 * @return
	 */
	private Currencies setProperty(Currencies currencies) {
		if(null!=ids && !"".equals(ids)){
			currencies.setId(ids);
		}
		currencies.setCode(parse(code));
		currencies.setName(parse(name));
/*		currencies.setCreateDate(createDate); 兄弟，在这里不用手动去设置这两个时间，
		currencies.setModifyDate(modifyDate); 因为，在BaseDao中已经帮你做了，你可以看一下SavaOrUpdate方法
*/		currencies.setNote(parse(note));
		return currencies;
	}

	/**
	 * 删除货币
	 * 
	 * @return
	 * @throws Exception
	 */
	public void del() throws Exception {
		if (null != ids && !"".equals(ids)) {
			String [] idArr = ids.split(",");
			if(idArr!=null && idArr.length>0){
				PrintWriter out = null;
				AjaxResult  result=new AjaxResult();
				try {
					out = response.getWriter();
					response.setContentType("application/text");
					response.setCharacterEncoding("UTF-8");
					this.currenciesLogic.deleteCurrenciesById(idArr);
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

	/**
	 * 根据id来查询货币
	 * @return
	 * @throws Exception
	 */
	public String findCurrenciesById() throws Exception {
		if (null != ids && !"".equals(ids)) {
			String[] arrIds = ids.split(",");
			if (null != arrIds && arrIds.length > 0) {
				String id = arrIds[0];
				Currencies curr = this.currenciesLogic.findCurrenciesById(id);
				if (null != curr) {
					this.request.put("curr", curr);
				}
			}
		}
		
		return "find";//是货币请求
	}
	
	
	private Integer pageCount(Integer maxIndex,Integer dataTotal){
		pageNums = (dataTotal / DEFAULT_PAGESIZE) + (dataTotal % DEFAULT_PAGESIZE > 0 ? 1 : 0); // 总页数
		if(pageNums==0){
			pageNums+=1;
		}
		return pageNums;
	}
	/*********Getter and Setter method*********/
	public CurrenciesLogic getCurrenciesLogic() {
		return currenciesLogic;
	}

	public void setCurrenciesLogic(CurrenciesLogic currenciesLogic) {
		this.currenciesLogic = currenciesLogic;
	}

	public Currencies getCurrencies() {
		return currencies;
	}

	public void setCurrencies(Currencies currencies) {
		this.currencies = currencies;
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Integer getDefaultPagesize() {
		return DEFAULT_PAGESIZE;
	}
	
	
	



}
