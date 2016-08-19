package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 违法青少年 
 * @author xiaguangjun
 *
 */
@Entity
@Table(name="CIS_BM_WFJL")
public class CisBmWfjl extends BaseEntity {
	
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "W_ID")
	private Integer wId;
	
	
	/**
	 * 人员id
	 */
	@Column(name = "HOUSEHOLDER_ID")
	private Integer householderId;
	
	
	/**
	 * 违法记录
	 */
	@Column(name = "WFJL")
	private String wfjl;
	
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;


	public Integer getwId() {
		return wId;
	}


	public void setwId(Integer wId) {
		this.wId = wId;
	}


	public Integer getHouseholderId() {
		return householderId;
	}


	public void setHouseholderId(Integer householderId) {
		this.householderId = householderId;
	}


	public String getWfjl() {
		return wfjl;
	}


	public void setWfjl(String wfjl) {
		this.wfjl = wfjl;
	}


	public String getDelSign() {
		return delSign;
	}


	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	
}
