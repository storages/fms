package com.fms.core.vo.entity;

/**
 * 临时转换类
 * 
 * @author Administrator
 * 
 */
public class TempEntity {
	private int code;
	private String name;

	public TempEntity() {
		super();
	}

	public int getCode() {
		return code;
	}

	public TempEntity(int code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
