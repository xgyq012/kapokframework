package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;


/**
 * @author xiaguangjun
 * 社会组织
 */
@Entity
@Table(name="CIS_BM_SOCIAL_ORGAN")
public class CisBmSocialOrgan extends BaseEntity {

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ORGANIZATION_ID")
	private Integer organizationId;
	
	
	/**
	 * 所属机构
	 */
	@Column(name = "INSTITUTIONS")
	private String institutions;
	
	
	/**
	 * 组织名称
	 */
	@Column(name = "ORGAN_NAME")
	private String organName;
	
	
	/**
	 * 登记证号
	 */
	@Column(name = "REGIST_NO")
	private Integer registNo;
	
	
	/**
	 * 地址
	 */
	@Column(name = "ADDRESS")
	private String address;
	
	
	/**
	 * 业务主管单位
	 */
	@Column(name = "EXECUT_UNIT")
	private String executUnit;
	
	
	/**
	 * 社会组织类型
	 */
	@Column(name = "ORGAN_TYPE")
	private String organType;
	
	
	/**
	 * 人数
	 */
	@Column(name = "PEOPLE_NUM")
	private Integer peopleNum;
	
	
	/**
	 * 法人代表
	 */
	@Column(name = "LEGAL_REPRE")
	private String legalRepre;
	
	
	/**
	 * 法人电话
	 */
	@Column(name = "LEGAL_PHONE")
	private String legalPhone;
	
	
	/**
	 * 成立时间
	 */
	@Column(name = "FOUNT_TIME")
	private Date fountTime;
	
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;


	public Integer getOrganizationId() {
		return organizationId;
	}


	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}


	public String getInstitutions() {
		return institutions;
	}


	public void setInstitutions(String institutions) {
		this.institutions = institutions;
	}


	public String getOrganName() {
		return organName;
	}


	public void setOrganName(String organName) {
		this.organName = organName;
	}


	public Integer getRegistNo() {
		return registNo;
	}


	public void setRegistNo(Integer registNo) {
		this.registNo = registNo;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getExecutUnit() {
		return executUnit;
	}


	public void setExecutUnit(String executUnit) {
		this.executUnit = executUnit;
	}


	public String getOrganType() {
		return organType;
	}


	public void setOrganType(String organType) {
		this.organType = organType;
	}


	public Integer getPeopleNum() {
		return peopleNum;
	}


	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}


	public String getLegalRepre() {
		return legalRepre;
	}


	public void setLegalRepre(String legalRepre) {
		this.legalRepre = legalRepre;
	}


	public String getLegalPhone() {
		return legalPhone;
	}


	public void setLegalPhone(String legalPhone) {
		this.legalPhone = legalPhone;
	}


	public Date getFountTime() {
		return fountTime;
	}


	public void setFountTime(Date fountTime) {
		this.fountTime = fountTime;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
