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
 *  项目风险状态
 *  
 *  @author Guohw
 */
@Entity
@Table(name = "CIS_ER_RISK_STATUS")
public class CisErRiskStatus extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 项目风险状态主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STATUS_ID")
	private Integer statusId;
	
	/**
	 *  风险评估主键
	 */
	@Column(name = "RISK_ID")
	private Integer riskId;
	
	/**
	 *  描述
	 */
	@Column(name = "REMARK")
	private String remark;
	
	/**
	 *  评估人ID
	 */
	@Column(name = "PRINCIPAL_ID")
	private Integer principalId;
	
	/**
	 * 评估人姓名 
	 */
	@Column(name = "PRINCIPAL_NAME")
	private String principalName;
	
	/**
	 *  项目评估状态
	 */
	@Column(name = "RISK_STATUS")
	private String riskStatus;
	
	/**
	 * 删除标记 
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 * 参与人 
	 */
	@Column(name = "PLAYER")
	private String player;
	
	/**
	 * 操作人
	 */
	@Column(name = "OPERATOR")
	private String operator;
	
	/**
	 * 初评日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "INITIALE_DATE")
	private Date initialeDate;
	
	/**
	 * 复评日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "REVIEW_DATE")
	private Date reviewDate;
	
	/**
	 * 审核日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "AUDIT_DATE")
	private Date auditDate;
	
	/**
	 * 处理结果 
	 */
	@Column(name = "STATUS_RESULT")
	private String statusResult;

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Integer getRiskId() {
		return riskId;
	}

	public void setRiskId(Integer riskId) {
		this.riskId = riskId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPrincipalId() {
		return principalId;
	}

	public void setPrincipalId(Integer principalId) {
		this.principalId = principalId;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public Date getInitialeDate() {
		return initialeDate;
	}

	public void setInitialeDate(Date initialeDate) {
		this.initialeDate = initialeDate;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getStatusResult() {
		return statusResult;
	}

	public void setStatusResult(String statusResult) {
		this.statusResult = statusResult;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getRiskStatus() {
		return riskStatus;
	}

	public void setRiskStatus(String riskStatus) {
		this.riskStatus = riskStatus;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
}
