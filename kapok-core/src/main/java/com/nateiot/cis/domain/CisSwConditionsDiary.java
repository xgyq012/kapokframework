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
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.base.domain.GxwlSysUser;
import com.nateiot.core.common.json.JsonDateSerializer;
import com.nateiot.core.repository.DBUtil;

/**
 * 服务办事 -- 民情日记
 * 
 *  @author Guohw
 */
@Entity
@Table(name = "CIS_SW_CONDITIONS_DIARY")
public class CisSwConditionsDiary extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 日记主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DIARY_ID")
	private Integer diaryId;
	
	/**
	 *  日记标题
	 */
	@Column(name = "DIARY_TITLE")
	private String diaryTitle;
	
	/**
	 *  日记内容
	 */
	@Column(name = "DIARY_CONTENT")
	private String diaryContent;
	
	/**
	 *  日记日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "DIARY_DATE")
	private Date diaryDate;
	
	/**
	 * 所属网格ID 
	 */
	@Column(name = "UNITS_ID")
	private Integer unitsId;
	
	/**
	 * 编写人员名称 
	 */
	@Transient
	private String writeror;
	
	/**
	 *  状态
	 */
	@Column(name = "DIARY_STATUS")
	private String diaryStatus;
	
	/**
	 *  提交时间
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "SUBMIT_TIME")
	private Date submitTime;
	
	/**
	 *  评价内容
	 */
	@Column(name = "ESTIMATE_CONTENT")
	private String estimateContent;
	
	/**
	 *  评定等级
	 */
	@Column(name = "ESTIMATE_LEVEL")
	private String estimateLevel;
	
	/**
	 *  评价时间
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "ESTIMATE_TIME")
	private Date estimateTime;
	
	/**
	 *  删除标记
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 *  编写人员
	 */
	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "writer", referencedColumnName = "USER_ID")
	private GxwlSysUser user1;
	
	/**
	 *  评价人
	 */
	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "ESTIMATE_PERSON", referencedColumnName = "USER_ID")
	private GxwlSysUser user2;
	
	public GxwlSysUser getUser1() {
		return user1;
	}

	public void setUser1(GxwlSysUser user1) {
		this.user1 = user1;
	}

	public GxwlSysUser getUser2() {
		return user2;
	}

	public void setUser2(GxwlSysUser user2) {
		this.user2 = user2;
	}
	
	@Transient
	public Integer getWriter(){
		return user1 == null ? null : user1.getUserId();
	}
	
	@Transient
	public String getWriterName(){
		return user1 == null ? "" : user1.getRealname();
	}
	
	@Transient
	public void setWriter(Integer writer) {
		if (writer != null) {
			this.user1 = DBUtil.find(GxwlSysUser.class, writer);
		}
	}

	@Transient
	public Integer getEstimatePerson(){
		return user2 == null ? null : user2.getUserId();
	}
	
	@Transient
	public String getEstimatePersonName(){
		return user2 == null ? "" : user2.getRealname();
	}
	
	@Transient
	public void setEstimatePerson(Integer estimatePerson){
		if (estimatePerson != null) {
			this.user2 = DBUtil.find(GxwlSysUser.class, estimatePerson);
		}
	}
	
	public Integer getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(Integer diaryId) {
		this.diaryId = diaryId;
	}

	public String getDiaryTitle() {
		return diaryTitle;
	}

	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}

	public String getDiaryContent() {
		return diaryContent;
	}

	public void setDiaryContent(String diaryContent) {
		this.diaryContent = diaryContent;
	}

	public Date getDiaryDate() {
		return diaryDate;
	}

	public void setDiaryDate(Date diaryDate) {
		this.diaryDate = diaryDate;
	}

	public String getDiaryStatus() {
		return diaryStatus;
	}

	public void setDiaryStatus(String diaryStatus) {
		this.diaryStatus = diaryStatus;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public String getEstimateContent() {
		return estimateContent;
	}

	public void setEstimateContent(String estimateContent) {
		this.estimateContent = estimateContent;
	}

	public String getEstimateLevel() {
		return estimateLevel;
	}

	public void setEstimateLevel(String estimateLevel) {
		this.estimateLevel = estimateLevel;
	}

	public Date getEstimateTime() {
		return estimateTime;
	}

	public void setEstimateTime(Date estimateTime) {
		this.estimateTime = estimateTime;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public String getWriteror() {
		return writeror;
	}

	public void setWriteror(String writeror) {
		this.writeror = writeror;
	}

	public Integer getUnitsId() {
		return unitsId;
	}

	public void setUnitsId(Integer unitsId) {
		this.unitsId = unitsId;
	}
	
}
