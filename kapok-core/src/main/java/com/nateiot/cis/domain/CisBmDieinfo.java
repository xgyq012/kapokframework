package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateSerializer;

/**
 * 死亡人員信息
 * @author xiaguangjun
 *
 */
@Entity
@Table(name = "Cis_Bm_Dieinfo")
public class CisBmDieinfo extends  BaseEntity {
	
	/**
	 * 死亡信息ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "DIE_ID")
	private Integer dieId;
	
	
	/**
	 * 人员信息ID
	 */
	@Column(name = "HOUSEHOLDER_ID")
	private Integer householderId;
	
	
	/**
	 * 死亡时间
	 */
	
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DIE_DATE")
	private Date dieDate;
	
	
	/**
	 * 死亡原因
	 */
	@Column(name = "DIE_REASON")
	private String dieReason;
	
	
	/**
	 * 更新年月
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "UPDATE_YEARM")
	private Date updateYearm;
	
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;


	public Integer getDieId() {
		return dieId;
	}


	public void setDieId(Integer dieId) {
		this.dieId = dieId;
	}


	public Integer getHouseholderId() {
		return householderId;
	}


	public void setHouseholderId(Integer householderId) {
		this.householderId = householderId;
	}


	public Date getDieDate() {
		return dieDate;
	}


	public void setDieDate(Date dieDate) {
		this.dieDate = dieDate;
	}


	public String getDieReason() {
		return dieReason;
	}


	public void setDieReason(String dieReason) {
		this.dieReason = dieReason;
	}


	public Date getUpdateYearm() {
		return updateYearm;
	}


	public void setUpdateYearm(Date updateYearm) {
		this.updateYearm = updateYearm;
	}


	public String getDelSign() {
		return delSign;
	}


	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}
	
	
	
}
