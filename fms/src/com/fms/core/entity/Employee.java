package com.fms.core.entity;

import java.util.Date;

import com.fms.base.entity.BaseEntity;
import com.fms.commons.EmployeeGender;
import com.fms.commons.JobStatus;

/**
 * Ա����
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
	 * ����
	 */
	private String name;

	/**
	 * ����
	 */
	private String nickName;
	/**
	 * ����
	 */
	private Integer age = 18;
	/**
	 * �Ա𡾳����ࣺEmployeeGender��
	 */
	private String gender = EmployeeGender.FEMALE;
	/**
	 * �������ڡ���д��
	 */
	private Date DoB;
	/**
	 * ���֤
	 */
	private String identityCard;
	/**
	 * ����
	 */
	private String nation;

	/**
	 * ����
	 */
	private String origo;
	/**
	 * ѧ��
	 */
	private String diploma;
	/**
	 * ��ͥסַ
	 */
	private String address;
	/**
	 * ��ס��
	 */
	private String currentResidence;
	/**
	 * ��ϵ�ֻ�
	 */
	private String linkPhone;
	/**
	 * ������ϵ��ʽ
	 */
	private String networkLink;
	/**
	 * ��Ƭ��ͷ�񡾴洢��Ƭ·����
	 */
	private String photo;
	/**
	 * ��ְ����
	 */
	private Date entryDate;
	/**
	 * ����״̬�������ࣺJobStatus��
	 */
	private String jobStatus = JobStatus.PROBATION;
	/**
	 * �������š�����
	 */
	private Department department;
	/**
	 * ְλ����
	 */
	private String positionName;

	/**
	 * ��Ϊ��¼������
	 */
	private BehaviorRecord behaviorRecord;
	/**
	 * ��ע
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
	 * Ĭ����18
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
	 * Ĭ��������
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
	 * ��Ƭ��ͷ�񡾴洢��Ƭ·����
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
