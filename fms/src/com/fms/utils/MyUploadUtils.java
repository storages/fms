package com.fms.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.fms.base.action.BaseAction;
import com.opensymphony.xwork2.ActionSupport;
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
    
    
    
    public  void  uploadAjax() throws Exception{
    	//InputStream  is=new FileInputStream(uploadFile);
    	HttpServletRequest request = ServletActionContext.getRequest();
   //	 String path = scheme+"://"+request.getServerName()+":"+request.getServerPort()+"/photo/";

    	String scheme= request.getSession().getServletContext().getRealPath("/");
    	String projectname= request.getContextPath();
    	scheme.replace(projectname, "webapps");
    	String  uploadPath="";//文件上传目录
    	String  imageName=scheme+"photo/"+UUID.randomUUID().toString()+".jpg";
    	File  tofile=new File(uploadPath,imageName);
    	OutputStream os = new FileOutputStream(tofile);// 创建一个输出流
    	//byte []buffer=new byte[1024];//设置缓存
    	//int length=0;
    	FileUtils.copyDirectory(uploadFile, tofile);
    	
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
