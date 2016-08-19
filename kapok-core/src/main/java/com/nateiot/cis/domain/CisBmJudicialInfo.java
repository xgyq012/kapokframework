package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateSerializer;

/**
 * 司法信息
 * 
 * @author huo
 * 
 */
@Entity
@Table(name = "CIS_BM_JUDICIAL_INFO")
public class CisBmJudicialInfo extends BaseEntity {


	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "JUDICIAL_ID")
	private Integer judicialId;

	/**
	 * 所属机构
	 */
	@Column(name = "ORG_ID")
	private String orgId;

	/**
	 * 姓名
	 */
	@Column(name = "JUD_NAME")
	private String judName;
	
	/**
	 * 录入人姓名
	 */
	@Column(name = "RECORD_NAME")
	private String recordName;
	
	/**
	 * 事件标题
	 */
	@Column(name = "INCIDENT_TITLE")
	private String incidentTitle;

	/**
	 * 联系电话
	 */
	@Column(name = "CONTACT_NUMBER")
	private String contactNumber;

	/**
	 * 事件描述
	 */
	@Column(name = "EVENT_DESCRIPTION")
	private String eventDescription;

	/**
	 * 录入时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "ENTRY_TIME")
	private Date entryTime;
	

	/**
	 * 结束时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "OVER_TIME")
	private Date overTime;

	/**
	 * 受理情况
	 */
	@Column(name = "ACCEPT_SITUATION")
	private String acceptSituation;

	/**
	 * 调解类型
	 */
	@Column(name = "MEDIATION_TYPE")
	private String mediationType;

	/**
	 * 结案描述
	 */
	@Column(name = "CASE_DESCRIPTION")
	private String caseDescription;
	

	/**
	 * 删除标记 
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;


	public Integer getJudicialId() {
		return judicialId;
	}


	public void setJudicialId(Integer judicialId) {
		this.judicialId = judicialId;
	}


	public String getOrgId() {
		return orgId;
	}


	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public String getJudName() {
		return judName;
	}


	public void setJudName(String judName) {
		this.judName = judName;
	}


	public String getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}


	public String getEventDescription() {
		return eventDescription;
	}


	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}


	public Date getEntryTime() {
		return entryTime;
	}


	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}


	public Date getOverTime() {
		return overTime;
	}


	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}


	public String getAcceptSituation() {
		return acceptSituation;
	}


	public void setAcceptSituation(String acceptSituation) {
		this.acceptSituation = acceptSituation;
	}


	public String getMediationType() {
		return mediationType;
	}


	public void setMediationType(String mediationType) {
		this.mediationType = mediationType;
	}


	public String getCaseDescription() {
		return caseDescription;
	}


	public void setCaseDescription(String caseDescription) {
		this.caseDescription = caseDescription;
	}


	public String getDelSign() {
		return delSign;
	}


	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}


	public String getRecordName() {
		return recordName;
	}


	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}


	public String getIncidentTitle() {
		return incidentTitle;
	}


	public void setIncidentTitle(String incidentTitle) {
		this.incidentTitle = incidentTitle;
	}
	
	
}
