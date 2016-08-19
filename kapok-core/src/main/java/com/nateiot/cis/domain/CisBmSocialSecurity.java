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
 * 社保信息
 * 
 * @author guohuawen
 * 
 */
@Entity
@Table(name = "CIS_BM_SOCIAL_SECURITY")
public class CisBmSocialSecurity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SOCIALSECURITY_ID")
	private Integer socialSecurityId;

	/**
	 * 社保编号
	 */
	@Column(name = "SOCIALSECURITY_CODE")
	private String socialSecurityCode;

	/**
	 * 退休时间
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "RETIREMENT_TIME")
	private Date retirementTime;

	/**
	 * 领取退休金全额
	 */
	@Column(name = "DRAW_PENSION")
	private Integer drawPension;

	/**
	 * 参加养老保险类型
	 */
	@Column(name = "INSURED_TYPE")
	private String insuredType;
	
	/**
	 * 删除标记 
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;

	/**
	 * 参保时间 
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "INSURED_TIME")
	private Date insuredTime;

	/**
	 * 参保金额
	 */
	@Column(name = "INSURED_MONEY")
	private Integer insuredMoney;

	/**
	 * 领取养老金时间
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DRAWPENSION_TIME")
	private Date drawPensionTime;

	/**
	 * 领取养老金全额
	 */
	@Column(name = "DRAWPENSION_MONEY")
	private String drawPensionMoney;

	public Integer getSocialSecurityId() {
		return socialSecurityId;
	}

	public void setSocialSecurityId(Integer socialSecurityId) {
		this.socialSecurityId = socialSecurityId;
	}

	public String getSocialSecurityCode() {
		return socialSecurityCode;
	}

	public void setSocialSecurityCode(String socialSecurityCode) {
		this.socialSecurityCode = socialSecurityCode;
	}

	public Date getRetirementTime() {
		return retirementTime;
	}

	public void setRetirementTime(Date retirementTime) {
		this.retirementTime = retirementTime;
	}

	public Integer getDrawPension() {
		return drawPension;
	}

	public void setDrawPension(Integer drawPension) {
		this.drawPension = drawPension;
	}

	public String getInsuredType() {
		return insuredType;
	}

	public void setInsuredType(String insuredType) {
		this.insuredType = insuredType;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public Date getInsuredTime() {
		return insuredTime;
	}

	public void setInsuredTime(Date insuredTime) {
		this.insuredTime = insuredTime;
	}

	public Integer getInsuredMoney() {
		return insuredMoney;
	}

	public void setInsuredMoney(Integer insuredMoney) {
		this.insuredMoney = insuredMoney;
	}

	public Date getDrawPensionTime() {
		return drawPensionTime;
	}

	public void setDrawPensionTime(Date drawPensionTime) {
		this.drawPensionTime = drawPensionTime;
	}

	public String getDrawPensionMoney() {
		return drawPensionMoney;
	}

	public void setDrawPensionMoney(String drawPensionMoney) {
		this.drawPensionMoney = drawPensionMoney;
	}

}
