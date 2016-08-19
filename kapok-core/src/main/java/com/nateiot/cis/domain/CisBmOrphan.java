package com.nateiot.cis.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 孤儿
 * @author xiaguangjun
 *
 */
@Entity
@Table(name="CIS_BM_ORPHAN")
public class CisBmOrphan extends BaseEntity {
	

	/**
	 * 孤儿ID
	 */
	@Id
	@Column(name = "ORPHAN_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer orphanId;
	
	
	/**
	 * 人员信息id
	 */
	@Column(name = "HOUSEHOLDER_ID")
	private Integer householderId;
	
	
	/**
	 * 供养类别
	 */
	@Column(name = "GYLB_TYPE")
	private String gylbType;
	
	
	/**
	 * 现补贴标准
	 */
	@Column(name = "XBTBZ")
	private Double xbtbz;
	
	
	/**
	 * 监护人
	 */
	@Column(name = "JHR")
	private String jhr;
	
	
	/**
	 * 监护人电话
	 */
	@Column(name = "JHR_PHONE")
	private String jhrPhone;
	
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;


	public Integer getOrphanId() {
		return orphanId;
	}


	public void setOrphanId(Integer orphanId) {
		this.orphanId = orphanId;
	}


	public Integer getHouseholderId() {
		return householderId;
	}


	public void setHouseholderId(Integer householderId) {
		this.householderId = householderId;
	}


	public String getGylbType() {
		return gylbType;
	}


	public void setGylbType(String gylbType) {
		this.gylbType = gylbType;
	}


	public Double getXbtbz() {
		return xbtbz;
	}


	public void setXbtbz(Double xbtbz) {
		this.xbtbz = xbtbz;
	}


	public String getJhr() {
		return jhr;
	}


	public void setJhr(String jhr) {
		this.jhr = jhr;
	}


	public String getJhrPhone() {
		return jhrPhone;
	}


	public void setJhrPhone(String jhrPhone) {
		this.jhrPhone = jhrPhone;
	}


	public String getDelSign() {
		return delSign;
	}


	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}
	
	
 
	
}
