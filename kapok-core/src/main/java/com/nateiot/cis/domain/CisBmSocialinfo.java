package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateSerializer;
import com.nateiot.core.repository.DBUtil;

/**
 * 社保信息
 * @author xiaguangjun
 *
 */
@Entity
@Table(name = "CIS_BM_SOCIALINFO")
public class CisBmSocialinfo extends BaseEntity{

	
	/**
	 * 社保ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SOCIAL_ID")
	private Integer socialId;
	
	
	/**
	 * 人员信息ID
	 */
	@Column(name = "HOUSEHOLDER_ID")
	private Integer householderId;
	
	
	/**
	 * 社保编号
	 */
	@Column(name = "SOCIAL_CODE")
	private String socialCode;
	
	
	/**
	 * 退休时间
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "RETIREMENT_DATE")
	private Date retirementDate;
	
	
	/**
	 * 领取退休金金额
	 */
	@Column(name = "HAVE_PENSION_AMOUNT")
	private Double havePensionAmount;
	
	
	/**
	 * 参加养老保险类型
	 */
	@Column(name = "ENDOW_INSURANCE_TYPE")
	private String endowInsuranceType;
	
	
	/**
	 * 参保时间
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "PARTICIPATION_DATE")
	private Date participationDate;
	
	
	/**
	 * 参保金额
	 */
	@Column(name = "PARTICIPATION_AMOUNT")
	private Double participationAmount;
	
	
	/**
	 * 领取养老时间
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "HAVE_AGED_DATE")
	private Date haveAgedDate;
	
	
	/**
	 * 领取养老金额
	 */
	@Column(name = "HAVE_AGED_AMOUNT")
	private Double haveAgedAmount;
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	
	/*@JsonIgnore
	@OneToOne
	@JoinColumn(name = "HOUSEHOLDER_ID")
	private CisBmHouseholder cisBmHouseholder;

	
	public Integer getHouseholderId() {
		return cisBmHouseholder == null ? null : cisBmHouseholder.getHouseholderId();
	}


	public void setHouseholderId(Integer householderId) {
		this.cisBmHouseholder = DBUtil.find(CisBmHouseholder.class,householderId);
	}
	

	public CisBmHouseholder getCisBmHouseholder() {
		return cisBmHouseholder;
	}


	public void setCisBmHouseholder(CisBmHouseholder cisBmHouseholder) {
		this.cisBmHouseholder = cisBmHouseholder;
	}*/


	public Integer getSocialId() {
		return socialId;
	}


	public void setSocialId(Integer socialId) {
		this.socialId = socialId;
	}


	public String getSocialCode() {
		return socialCode;
	}


	public void setSocialCode(String socialCode) {
		this.socialCode = socialCode;
	}


	public Date getRetirementDate() {
		return retirementDate;
	}


	public void setRetirementDate(Date retirementDate) {
		this.retirementDate = retirementDate;
	}


	public Double getHavePensionAmount() {
		return havePensionAmount;
	}


	public void setHavePensionAmount(Double havePensionAmount) {
		this.havePensionAmount = havePensionAmount;
	}


	public String getEndowInsuranceType() {
		return endowInsuranceType;
	}


	public void setEndowInsuranceType(String endowInsuranceType) {
		this.endowInsuranceType = endowInsuranceType;
	}


	public Date getParticipationDate() {
		return participationDate;
	}


	public void setParticipationDate(Date participationDate) {
		this.participationDate = participationDate;
	}


	public Double getParticipationAmount() {
		return participationAmount;
	}


	public void setParticipationAmount(Double participationAmount) {
		this.participationAmount = participationAmount;
	}


	public Date getHaveAgedDate() {
		return haveAgedDate;
	}


	public void setHaveAgedDate(Date haveAgedDate) {
		this.haveAgedDate = haveAgedDate;
	}


	public Double getHaveAgedAmount() {
		return haveAgedAmount;
	}


	public void setHaveAgedAmount(Double haveAgedAmount) {
		this.haveAgedAmount = haveAgedAmount;
	}


	public Integer getHouseholderId() {
		return householderId;
	}


	public void setHouseholderId(Integer householderId) {
		this.householderId = householderId;
	}


	public String getDelSign() {
		return delSign;
	}


	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	
}
