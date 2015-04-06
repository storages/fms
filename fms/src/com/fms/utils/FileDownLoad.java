package com.fms.utils;

import java.io.File;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 下载文件
 * 
 * @author Administrator
 * 
 */
public class FileDownLoad extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ROOTPATH = "WEB-INF" + File.separator + "template" + File.separator;
	private String fileName;
	private String fileFlag;
	private String inputPath;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileFlag() {
		return fileFlag;
	}

	public void setFileFlag(String fileFlag) {
		this.fileFlag = fileFlag;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	// 返回一个输入流，作为一个客户端来说是一个输入流，但对于服务器端是一个 输出流
	public InputStream getDownloadFile() throws Exception {
		if ("stockTemp".equals(fileFlag)) {
			this.fileName = "仓库管理【导入】模板.xls";
			this.inputPath = ROOTPATH + "stockTemplate.xls";
		} else if ("scmcocTemp".equals(fileFlag)) {
			this.fileName = "供应商管理【导入】模板.xls";
			this.inputPath = ROOTPATH + "scmcocTemplate.xls";
		} else if ("currTemp".equals(fileFlag)) {
			this.fileName = "交易货币【导入】模板.xls";
			this.inputPath = ROOTPATH + "currTemplate.xls";
		} else if ("settlTemp".equals(fileFlag)) {
			this.fileName = "结算方式【导入】模板.xls";
			this.inputPath = ROOTPATH + "settlTemplate.xls";
		} else if ("unitTemp".equals(fileFlag)) {
			this.fileName = "计量单位【导入】模板.xls";
			this.inputPath = ROOTPATH + "unitTemplate.xls";
		} else if ("quotationTemp".equals(fileFlag)) {
			this.fileName = "报价单【导入】模板.xls";
			this.inputPath = ROOTPATH + "quotationTemplate.xls";
		} else if ("bomTemp".equals(fileFlag)) {
			this.fileName = "BOM表【导入】模板.xls";
			this.inputPath = ROOTPATH + "bomTemplate.xls";
		} else if ("materTemp".equals(fileFlag)) {
			this.fileName = "物料信息表【导入】模板.xls";
			this.inputPath = ROOTPATH + "materTemplate.xls";
		}
		// 解乱码
		this.fileName = new String(this.fileName.getBytes("UTF-8"), "ISO-8859-1");
		return ServletActionContext.getServletContext().getResourceAsStream(this.inputPath);
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
