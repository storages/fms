package com.fms.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.fms.base.action.BaseAction;
import com.opensymphony.xwork2.ActionSupport;
import com.url.ajax.json.JSONObject;
/**
 * 文件上传工具
 * @author Administrator
 *
 */
public class MyUploadUtils extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private File     uploadFile;         //上传的文件    名称是Form 对应的name 
    private String   uploadFileContentType;   //文件的类型
    private String   uploadFileFileName;    //文件的名称
    private String   path;      //封装上传目录 用于动态的设置上传目录
    
    
    
    public  void  uploadAjax() {
    	HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response= ServletActionContext.getResponse();
    	AjaxResult result=new AjaxResult();
    	Writer writer=null; 
     try{
    	 writer= response.getWriter();
    	String scheme= request.getSession().getServletContext().getRealPath("/");
    	String projectname= request.getContextPath().replace("/", "\\");
    	scheme=scheme.replace(projectname, "")+"photo\\";
    	String  uploadPath="";//文件上传目录
    	String  imageName=UUID.randomUUID().toString()+".jpg";
    	File  tofile=new File(scheme,imageName);
    	FileUtils.copyFile(uploadFile, tofile);
    	result.setSuccess(true);
    	result.setMsg(imageName);
            }catch(Exception e){
            	result.setSuccess(false);
            	result.setMsg(e.getMessage());
	      System.out.println(e.getMessage());
             }  
     JSONObject json=new JSONObject(result);
     try {
		writer.write(json.toString());
		writer.flush();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
    
    

	

}
