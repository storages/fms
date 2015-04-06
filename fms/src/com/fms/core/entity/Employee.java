package com.fms.core.entity;

import java.util.Date;

import com.fms.annotation.CnFileName;
import com.fms.base.entity.BaseEntity;
import com.fms.commons.EmployeeGender;
import com.fms.commons.JobStatus;

/**
 * 员工类
 * 
 * @author Administrator
 * 
 */
@CnFileName(name="员工")
public class Employee extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 员工号
	 */
	@CnFileName(name="员工编号")
	private String code;

	/**
	 * 姓名
	 */
	@CnFileName(name="姓名")
	private String name;
	
	/**
	 * 是否拥有登陆账户
	 */
	@CnFileName(name="是否具有登陆权限")
	private Boolean wfloginUser=false;
	

	/**
	 * 别名
	 */
	@CnFileName(name="别名")
	private String nickName;
	
	/**
	 * 年龄
	 */
	@CnFileName(name="年龄")
	private Integer age;
	/**
	 * 性别【常量类：EmployeeGender】
	 */
	@CnFileName(name="性别")
	private String gender = EmployeeGender.FEMALE;
	/**
	 * 出生日期【缩写】
	 */
	@CnFileName(name="出生日期")
	private Date birthday;  
	/**
	 * 身份证
	 */
	@CnFileName(name="身份证")
	private String identityCard;
	/**
	 * 民族
	 */
	@CnFileName(name="名族")
	private String nation;

	/**
	 * 籍贯
	 */
	@CnFileName(name="籍贯")
	private String origo;
	/**
	 * 学历
	 */
	@CnFileName(name="学历")
	private String diploma;
	/**
	 * 家庭住址
	 */
	@CnFileName(name="家庭住址")
	private String address;
	/**
	 * 现住地
	 */
	@CnFileName(name="现住址")
	private String currentResidence;
	/**
	 * 联系手机
	 */
	@CnFileName(name="手机联系号码")
	private String linkPhone;
	/**
	 * 网络联系方式
	 */
	@CnFileName(name="网络联系方式")
	private String networkLink;
	/**
	 * 照片、头像【存储照片路径】
	 */
	@CnFileName(name=" 照片")
	private String photo;
	/**
	 * 入职日期
	 */
	@CnFileName(name="入职日期")
	private Date entryDate;
	/**
	 * 工作状态【常量类：JobStatus】
	 */
	@CnFileName(name="工作状态")
	private String jobStatus = JobStatus.PROBATION;
	/**
	 * 所属部门【对象】
	 */
	private Department department;
	/**
	 * 职位名称
	 */
	@CnFileName(name="职位")
	private String positionName;

	/**
	 * 行为记录【对象】
	 */
	private BehaviorRecord behaviorRecord;
	/**
	 * 备注
	 */
	@CnFileName(name="备注")
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



	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean getWfloginUser() {
		return wfloginUser;
	}

	public void setWfloginUser(boolean wfloginUser) {
		this.wfloginUser = wfloginUser;
	}



}
