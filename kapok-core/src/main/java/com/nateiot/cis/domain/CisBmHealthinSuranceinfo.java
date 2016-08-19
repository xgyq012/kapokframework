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
 * 医保信息
 * @author xiaguangjun
 *
 */
@Entity
@Table(name = "CIS_BM_HEALTHIN_SURANCEINFO")
public class CisBmHealthinSuranceinfo extends BaseEntity {

	
	/**
	 * 医保信息ID
	 */
	@Column(name = "HEALTH_ID")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer healthId;
	
	
	/**
	 * 人员ID
	 */
	@Column(name = "HOUSEHOLDER_ID")
	private Integer householderId;
	
	
	/**
	 * 医保类型
	 */
	@Column(name = "HEALTH_TYPE")
	private String healthType;
	
	
	/**
	 * 投保时间
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "INSURE_DATE")
	private Date insureDate;
	
	
	/**
	 * 医保卡号
	 */
	@Column(name = "HEALTH_CODE")
	private String healthCode;
	
	
	/**
	 * 缴纳金额
	 */
	@Column(name = "PAYMENT_AMOUNT")
	private Double paymentAmount;
	
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;


	public Integer getHealthId() {
		return healthId;
	}


	public void setHealthId(Integer healthId) {
		this.healthId = healthId;
	}


	public Integer getHouseholderId() {
		return householderId;
	}


	public void setHouseholderId(Integer householderId) {
		this.householderId = householderId;
	}


	public String getHealthType() {
		return healthType;
	}


	public void setHealthType(String healthType) {
		this.healthType = healthType;
	}


	public Date getInsureDate() {
		return insureDate;
	}


	public void setInsureDate(Date insureDate) {
		this.insureDate = insureDate;
	}


	public String getHealthCode() {
		return healthCode;
	}


	public void setHealthCode(String healthCode) {
		this.healthCode = healthCode;
	}


	public Double getPaymentAmount() {
		return paymentAmount;
	}


	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}


	public String getDelSign() {
		return delSign;
	}


	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}
	
 
}
