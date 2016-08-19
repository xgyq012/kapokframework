package com.nateiot.cis.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateTimeSerializer;
import com.nateiot.core.repository.DBUtil;


/**
 * 服务办事 -- 事件登记
 * 
 *  @author Guohw
 */
@Entity
@Table(name = "CIS_SW_EVENT_ENROLL")
public class CisSwEventEnroll extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 事件登记主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ENROLL_ID")
	private Integer enrollId;

	/**
	 * 微信账户
     */
	@Column(name = "WX_NAME")
	private String wxName;
	
	/**
	 * 事件编号
	 */
	@Column(name = "ENROLL_CODE")
	private String enrollCode;

	/**
	 * 事件标题
	 */
	@Column(name = "ENROLL_TITLE")
	private String enrollTitle;

	/**
	 * 描述
	 */
	@Column(name = "REMARK")
	private String remark;
	
	/**
	 * 所属机构
	 */
	@Column(name = "UNITS_ID")
	private Integer unitsId;

	/**
	 * 所属机构
	 */
	@Column(name = "UNITS")
	private String units;

	/**
	 * 事件来源
	 */
	@Column(name = "ENROLL_SOURCE")
	private String enrollSource;

	/**
	 * 事发场所
	 */
	@Column(name = "ENROLL_PLACE")
	private String enrollPlace;

	/**
	 * 事发时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "ENROLL_TIME")
	private Date enrollTime;

	/**
	 * 当事人
	 */
	@Column(name = "CLIENT")
	private String client;

	/**
	 * 当事人电话
	 */
	@Column(name = "CLIENT_PHONE")
	private String clientPhone;

	/**
	 * 删除标记
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 * 积分字段 
	 */
	@Column(name = "SCORE_DETAIL")
	private String scoreDetail;

	/**
	 * 事件类型
	 */
	@Column(name = "ENROLL_TYPE")
	private String enrollType;

	/**
	 * 涉及人数
	 */
	@Column(name = "INVOLE_PEOPLE")
	private Integer involePeople;
	
	/**
	 *  事件类别
	 */
	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "ENROLL_CATEGORY", referencedColumnName = "TYPE_ID")
	private CisSwEventType cisSwEventType;

	/**
	 * 处理意见
	 */
	@Column(name = "SUGGESTION")
	private String suggestion;
	
	/**
	 * 事件状态
	 */
	@Column(name = "ENROLL_STATUS")
	private String enrollStatus;
	
	/**
	 * 提交人ID
	 */
	@Column(name = "SUBMIT_ID")
	private Integer submitId;
	
	/**
	 *  签收人ID
	 */
	@Column(name = "SIGNFOR_ID")
	private Integer signForId;
	
	/**
	 *  处理人ID
	 */
	@Column(name = "TRANSACT_ID")
	private Integer transactId;
	
	/**
	 *  评价人ID
	 */
	@Column(name = "ESTIMATE_ID")
	private Integer estimateId;
	
	/**
	 *  经度
	 */
	@Column(name = "LON")
	private String lon;
	
	/**
	 *  纬度
	 */
	@Column(name = "LAT")
	private String lat;

	/**
	 * 评价
	 */
	@Column(name = "EVALUATE")
	private String evaluate;

	/**
	 * 事件最终评价
	 */
	@Column(name = "EVALUATE_LAST")
	private String evaluateLast;

	/**
	 * 登记人姓名
	 */
	@Column(name = "CREATE_BY_NAME")
	private String createByName;
	
	/**
	 * 上传文件
	 */
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "cisSwEventEnroll")
	private List<CisSwEventFile> cisSwEventFile;
	
	/**
	 * 处理时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "TRANSACT_TIME")
	private Date transactTime;
	
	public Integer getEnrollId() {
		return enrollId;
	}

	public void setEnrollId(Integer enrollId) {
		this.enrollId = enrollId;
	}

	public String getEnrollCode() {
		return enrollCode;
	}

	public void setEnrollCode(String enrollCode) {
		this.enrollCode = enrollCode;
	}

	public String getEnrollTitle() {
		return enrollTitle;
	}

	public void setEnrollTitle(String enrollTitle) {
		this.enrollTitle = enrollTitle;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getEnrollSource() {
		return enrollSource;
	}

	public void setEnrollSource(String enrollSource) {
		this.enrollSource = enrollSource;
	}

	public String getEnrollPlace() {
		return enrollPlace;
	}

	public void setEnrollPlace(String enrollPlace) {
		this.enrollPlace = enrollPlace;
	}

	public Date getEnrollTime() {
		return enrollTime;
	}

	public void setEnrollTime(Date enrollTime) {
		this.enrollTime = enrollTime;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public String getEnrollType() {
		return enrollType;
	}

	public void setEnrollType(String enrollType) {
		this.enrollType = enrollType;
	}

	public Integer getInvolePeople() {
		return involePeople;
	}

	public void setInvolePeople(Integer involePeople) {
		this.involePeople = involePeople;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}

	public String getEvaluateLast() {
		return evaluateLast;
	}

	public void setEvaluateLast(String evaluateLast) {
		this.evaluateLast = evaluateLast;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public List<CisSwEventFile> getCisSwEventFile() {
		return cisSwEventFile;
	}

	public void setCisSwEventFile(List<CisSwEventFile> cisSwEventFile) {
		this.cisSwEventFile = cisSwEventFile;
	}

	public Integer getUnitsId() {
		return unitsId;
	}

	public void setUnitsId(Integer unitsId) {
		this.unitsId = unitsId;
	}

	public Integer getSubmitId() {
		return submitId;
	}

	public void setSubmitId(Integer submitId) {
		this.submitId = submitId;
	}

	public Integer getSignForId() {
		return signForId;
	}

	public void setSignForId(Integer signForId) {
		this.signForId = signForId;
	}

	public Integer getTransactId() {
		return transactId;
	}

	public void setTransactId(Integer transactId) {
		this.transactId = transactId;
	}

	public Integer getEstimateId() {
		return estimateId;
	}

	public void setEstimateId(Integer estimateId) {
		this.estimateId = estimateId;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public CisSwEventType getType() {
		return cisSwEventType;
	}

	public void setCisSwEventType(CisSwEventType cisSwEventType) {
		this.cisSwEventType = cisSwEventType;
	}

	@Transient
	public Integer getEnrollCateGoryId(){
		return cisSwEventType == null ? null : cisSwEventType.getTypeId();
	}
	
	@Transient
	public String getEnrollCateGoryName(){
		return cisSwEventType == null ? null : cisSwEventType.getTypeName();
	}
	
	public void setEnrollCategory(Integer enrollCategory){
		if(enrollCategory != null){
			setCisSwEventType(DBUtil.find(CisSwEventType.class, enrollCategory));
		}
	}
	
	@Transient
	public Integer getEnrollCateGory(Integer enrollCateGory){
			
		return this.cisSwEventType ==null ? null : this.cisSwEventType.getTypeId();
	}

	public String getEnrollStatus() {
		return enrollStatus;
	}

	public void setEnrollStatus(String enrollStatus) {
		this.enrollStatus = enrollStatus;
	}

	public CisSwEventType getCisSwEventType() {
		return cisSwEventType;
	}

	public Date getTransactTime() {
		return transactTime;
	}

	public void setTransactTime(Date transactTime) {
		this.transactTime = transactTime;
	}

	public String getScoreDetail() {
		return scoreDetail;
	}

	public void setScoreDetail(String scoreDetail) {
		this.scoreDetail = scoreDetail;
	}

	public String getWxName() {
		return wxName;
	}

	public void setWxName(String wxName) {
		this.wxName = wxName;
	}
}
