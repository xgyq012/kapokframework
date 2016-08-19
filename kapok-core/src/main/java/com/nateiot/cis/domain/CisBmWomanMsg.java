package com.nateiot.cis.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 妇联信息
 * @author xiaguangjun
 *
 */
@Entity
@Table(name="CIS_BM_WOMAN_MSG")
public class CisBmWomanMsg extends BaseEntity {

	
	/**
	 * 主键
	 */
	@Id
	@Column(name = "WOMAN_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer womanId;

	
	/**
	 * 所属机构
	 */
	@Column(name = "ORG")
	private Integer org;
	
	
	/**
	 * 家庭类别
	 */
	@Column(name = "FAMILY_TYPE")
	private String familyType;
	
	
	/**
	 * 家庭情况
	 */
	@Column(name = "FAMILY_STATUS")
	private String familyStatus;
	
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;


	public Integer getWomanId() {
		return womanId;
	}


	public void setWomanId(Integer womanId) {
		this.womanId = womanId;
	}


	public Integer getOrg() {
		return org;
	}


	public void setOrg(Integer org) {
		this.org = org;
	}


	public String getFamilyType() {
		return familyType;
	}


	public void setFamilyType(String familyType) {
		this.familyType = familyType;
	}


	public String getFamilyStatus() {
		return familyStatus;
	}


	public void setFamilyStatus(String familyStatus) {
		this.familyStatus = familyStatus;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


 
}
