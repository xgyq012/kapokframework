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
 * 
 * @author guohuawen
 * 
 */
@Entity
@Table(name = "CIS_BM_HEALTH_INSURANCE")
public class CisBmHealthInsurance extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "HEALTHINSURANCE_ID")
	private Integer healthInsuranceId;

	/**
	 * 缴纳医保类型
	 */
	@Column(name = "INSURANCE_TYPE")
	private String insuranceType;

	/**
	 * 投保时间
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "INSURANCE_TIME")
	private Date insuranceTime;

	/**
	 * 医保卡号
	 */
	@Column(name = "INSURANCE_CARD")
	private Integer insuranceCard;

	/**
	 * 缴纳金额
	 */
	@Column(name = "PAYMENTNUM")
	private Integer paymentNum;

	public Integer getHealthInsuranceId() {
		return healthInsuranceId;
	}

	public void setHealthInsuranceId(Integer healthInsuranceId) {
		this.healthInsuranceId = healthInsuranceId;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public Date getInsuranceTime() {
		return insuranceTime;
	}

	public void setInsuranceTime(Date insuranceTime) {
		this.insuranceTime = insuranceTime;
	}

	public Integer getInsuranceCard() {
		return insuranceCard;
	}

	public void setInsuranceCard(Integer insuranceCard) {
		this.insuranceCard = insuranceCard;
	}

	public Integer getPaymentNum() {
		return paymentNum;
	}

	public void setPaymentNum(Integer paymentNum) {
		this.paymentNum = paymentNum;
	}

}
