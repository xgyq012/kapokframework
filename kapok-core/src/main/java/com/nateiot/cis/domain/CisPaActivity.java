package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * 党组织活动
 * @author xiewenhua
 *
 */

@Table(name = "CIS_PA_ACTIVITY")
@Entity
public class CisPaActivity extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ACTIVITY_ID")
	private Integer activityId;
	
	/**
	 * 所属机构
	 */
	@Column(name = "MESH_ID")
	private Integer meshId;
	
	
	/**
	 * 党组织id
	 */
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "PARTY_ID")
	private CisBmPartyOrganization partyOrganization;
	
	/**
	 * 活动名称
	 */
	@Column(name = "ACTIVITY_NAME")
	private String activityName;
	
	/**
	 * 活动时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "ACTIVITY_TIME")
	private Date activityTime;
	
	/**
	 * 活动内容
	 */
	@Column(name = "ACTIVITY_BODY")
	private String activityBody;
	
	/**
	 * 摘要
	 */
	@Column(name = "REMARK")
	private String remark;
	
	/**
	 * 主持人
	 */
	@Column(name = "ZHUCHIREN")
	private String zhuchiren;
	
	/**
	 * 活动地点
	 */
	@Column(name = "ADDRESS")
	private String address;
	
	/**
	 * 参加人
	 */
	@Column(name = "CANJIAREN")
	private String canjiaren;
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getMeshId() {
		return meshId;
	}
	public void setMeshId(Integer meshId) {
		this.meshId = meshId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public Date getActivityTime() {
		return activityTime;
	}
	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}
	public String getActivityBody() {
		return activityBody;
	}
	public void setActivityBody(String activityBody) {
		this.activityBody = activityBody;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getZhuchiren() {
		return zhuchiren;
	}
	public void setZhuchiren(String zhuchiren) {
		this.zhuchiren = zhuchiren;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCanjiaren() {
		return canjiaren;
	}
	public void setCanjiaren(String canjiaren) {
		this.canjiaren = canjiaren;
	}
	public String getDelSign() {
		return delSign;
	}
	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}
	public CisBmPartyOrganization getPartyOrganization() {
		return partyOrganization;
	}
	public void setPartyOrganization(CisBmPartyOrganization partyOrganization) {
		this.partyOrganization = partyOrganization;
	}
	
	public void setPartyId(Integer partyId){
		if(partyId != null){
			this.partyOrganization = DBUtil.find(CisBmPartyOrganization.class, partyId);
		}
	}
	
	@Transient
	public Integer getPartyId(){
		return this.partyOrganization == null ? null : this.partyOrganization.getOrganizationId();
	}
	
	@Transient
	public String getPartyOrganizationName(){
		return this.partyOrganization == null ? null : this.partyOrganization.getOrganizationName();
	}
	

}
