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
 * 案发情况
 * 
 * @author huo
 * 
 */
@Entity
@Table(name = "CIS_BM_CASE_INFO")
public class CisBmCaseInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CASE_ID")
	private Integer caseId;

	
	/**
	 * 所属机构
	 */
	@Column(name = "ORG_ID")
	private Integer orgId;
	
	
	/**
	 * 案发时间
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "CASE_TIME")
	private Date caseTime;
	
	
	/**
	 * 案发地点
	 */
	@Column(name = "CASE_ADDRESS")
	private String caseAddress;
	
	
	/**
	 * 案件类型
	 */
	@Column(name = "CASE_TYPE")
	private String caseType;
	
	
	/**
	 * 案件概述
	 */
	@Column(name = "CASE_DESC")
	private String caseDesc;
	
	
	/**
	 * 案件处理情况
	 */
	@Column(name = "CASE_HANDLING")
	private String caseHandling;
	
	
	/**
	 * 删除标记
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	
	/**
	 * 案件回访情况
	 */
	@Column(name = "CASE_RETURN_VISIT")
	private String caseReturnVisit;


	public Integer getCaseId() {
		return caseId;
	}


	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}


	public Integer getOrgId() {
		return orgId;
	}


	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


	public Date getCaseTime() {
		return caseTime;
	}


	public void setCaseTime(Date caseTime) {
		this.caseTime = caseTime;
	}


	public String getCaseAddress() {
		return caseAddress;
	}


	public void setCaseAddress(String caseAddress) {
		this.caseAddress = caseAddress;
	}


	public String getCaseType() {
		return caseType;
	}


	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}


	public String getCaseDesc() {
		return caseDesc;
	}


	public void setCaseDesc(String caseDesc) {
		this.caseDesc = caseDesc;
	}


	public String getCaseHandling() {
		return caseHandling;
	}


	public void setCaseHandling(String caseHandling) {
		this.caseHandling = caseHandling;
	}


	public String getDelSign() {
		return delSign;
	}


	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}


	public String getCaseReturnVisit() {
		return caseReturnVisit;
	}


	public void setCaseReturnVisit(String caseReturnVisit) {
		this.caseReturnVisit = caseReturnVisit;
	}
	
 
}
