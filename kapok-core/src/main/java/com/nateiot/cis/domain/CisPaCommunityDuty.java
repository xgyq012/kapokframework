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
 * 党务建设 -- 社区党务公开表
 * 
 * @author Guohw
 */
@Entity
@Table(name = "CIS_PA_COMMUNITY_DUTY")
public class CisPaCommunityDuty extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 社区党务公开主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MEETING_ID")
	private Integer meetingId;
	
	/**
	 *  公开地点
	 */
	@Column(name = "PUPLACE")
	private String puPlace;
	
	/**
	 * 公开日期(始)
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "BEGIN_DATE")
	private Date beginDate;
	
	/**
	 * 公开日期(止)
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "END_DATE")
	private Date endDate;
	
	/**
	 * 所属网格ID
	 */
	@Column(name = "UNITS_ID")
	private Integer unitsId;
	
	/**
	 * 会议形式
	 */
	@Column(name = "MEETING_FORM")
	private String meetingForm;
	
	/**
	 * 会议日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "MEETING_DATE")
	private Date meetingDate;
	
	/**
	 * 会议地点
	 */
	@Column(name = "MEETING_PLACE")
	private String meetingPlace;
	
	/**
	 * 参加会议人员签名
	 */
	@Column(name = "JOIN_SIGN")
	private String joinSign;
	
	/**
	 * 公开内容
	 */
	@Column(name = "PUCONTENT")
	private String puContent;
	
	/**
	 * 领导小组成员签名
	 */
	@Column(name = "LEAD_SIGN")
	private String leadSign;
	
	/**
	 * 监督委员会签名
	 */
	@Column(name = "MONITOR_SIGN")
	private String monitorSign;
	
	/**
	 * 删除标记
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 * 构造函数 
	 */
	public CisPaCommunityDuty(){
		
	}

	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getUnitsId() {
		return unitsId;
	}

	public void setUnitsId(Integer unitsId) {
		this.unitsId = unitsId;
	}

	public String getMeetingForm() {
		return meetingForm;
	}

	public void setMeetingForm(String meetingForm) {
		this.meetingForm = meetingForm;
	}

	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getMeetingPlace() {
		return meetingPlace;
	}

	public void setMeetingPlace(String meetingPlace) {
		this.meetingPlace = meetingPlace;
	}

	public String getJoinSign() {
		return joinSign;
	}

	public void setJoinSign(String joinSign) {
		this.joinSign = joinSign;
	}

	public String getPuContent() {
		return puContent;
	}

	public void setPuContent(String puContent) {
		this.puContent = puContent;
	}

	public String getLeadSign() {
		return leadSign;
	}

	public void setLeadSign(String leadSign) {
		this.leadSign = leadSign;
	}

	public String getMonitorSign() {
		return monitorSign;
	}

	public void setMonitorSign(String monitorSign) {
		this.monitorSign = monitorSign;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public String getPuPlace() {
		return puPlace;
	}

	public void setPuPlace(String puPlace) {
		this.puPlace = puPlace;
	}
	
}
