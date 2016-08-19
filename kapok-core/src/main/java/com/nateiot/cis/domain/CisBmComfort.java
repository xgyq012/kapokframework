package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 优抚人员信息
 * @author xiaguangjun
 *
 */
@Entity
@Table(name = "Cis_Bm_Comfort")
public class CisBmComfort extends  BaseEntity {

	
	/**
	 * 优抚信息ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "COMFORT_ID")
	private Integer comfortId;
	
	
	/**
	 * 人员ID
	 */
	@Column(name = "HOUSEHOLDER_ID")
	private Integer householderId;
	
	
	/**
	 * 优抚类别
	 */
	@Column(name = "COMFORT_TYPE")
	private String comfortType;
	
	
	/**
	 * 优抚金额
	 */
	@Column(name = "COMFORT_AMOUNT")
	private Double comfortAmount;
	
	
	/**
	 * 工作状态
	 */
	@Column(name = "WORK_STATUS")
	private String workStatus;
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;

	
	public Integer getComfortId() {
		return comfortId;
	}

	public void setComfortId(Integer comfortId) {
		this.comfortId = comfortId;
	}

	public Integer getHouseholderId() {
		return householderId;
	}

	public void setHouseholderId(Integer householderId) {
		this.householderId = householderId;
	}

	public String getComfortType() {
		return comfortType;
	}

	public void setComfortType(String comfortType) {
		this.comfortType = comfortType;
	}

	public Double getComfortAmount() {
		return comfortAmount;
	}

	public void setComfortAmount(Double comfortAmount) {
		this.comfortAmount = comfortAmount;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}
	

	
}
