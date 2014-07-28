package com.fms.core.entity;

import java.util.Date;

import com.fms.base.entity.BaseEntity;
import com.fms.commons.EmployeeGender;
import com.fms.commons.JobStatus;

/**
 * 员工类
 * 
 * @author Administrator
 * 
 */
public class Employee extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 别名
	 */
	private String nickName;
	/**
	 * 年龄
	 */
	private Integer age = 18;
	/**
	 * 性别【常量类：EmployeeGender】
	 */
	private String gender = EmployeeGender.FEMALE;
	/**
	 * 出生日期【缩写】
	 */
	private Date DoB;
	/**
	 * 身份证
	 */
	private String identityCard;
	/**
	 * 民族
	 */
	private String nation;

	/**
	 * 籍贯
	 */
	private String origo;
	/**
	 * 学历
	 */
	private String diploma;
	/**
	 * 家庭住址
	 */
	private String address;
	/**
	 * 现住地
	 */
	private String currentResidence;
	/**
	 * 联系手机
	 */
	private String linkPhone;
	/**
	 * 网络联系方式
	 */
	private String networkLink;
	/**
	 * 照片、头像【存储照片路径】
	 */
	private String photo;
	/**
	 * 入职日期
	 */
	private Date entryDate;
	/**
	 * 工作状态【常量类：JobStatus】
	 */
	private String jobStatus = JobStatus.PROBATION;
	/**
	 * 所属部门【对象】
	 */
	private Department department;
	/**
	 * 职位名称
	 */
	private String positionName;

	/**
	 * 行为记录【对象】
	 */
	private BehaviorRecord behaviorRecord;
	/**
	 * 备注
	 */
	private String note;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getAge() {
		return age;
	}

	/**
	 * 默认是18
	 * @param age
	 */
	public void setAge(Integer age) {
		if (age < 18 || age > 80) {
			this.age = 18;
		} else {
			this.age = age;
		}
	}

	public String getGender() {
		return gender;
	}

	/**
	 * 默认是男性
	 * @return
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDoB() {
		return DoB;
	}

	public void setDoB(Date doB) {
		DoB = doB;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getOrigo() {
		return origo;
	}

	public void setOrigo(String origo) {
		this.origo = origo;
	}

	public String getDiploma() {
		return diploma;
	}

	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCurrentResidence() {
		return currentResidence;
	}

	public void setCurrentResidence(String currentResidence) {
		this.currentResidence = currentResidence;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getNetworkLink() {
		return networkLink;
	}

	public void setNetworkLink(String networkLink) {
		this.networkLink = networkLink;
	}

	public String getPhoto() {
		return photo;
	}

	/**
	 * 照片、头像【存储照片路径】
	 * @param photo
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public BehaviorRecord getBehaviorRecord() {
		return behaviorRecord;
	}

	public void setBehaviorRecord(BehaviorRecord behaviorRecord) {
		this.behaviorRecord = behaviorRecord;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
