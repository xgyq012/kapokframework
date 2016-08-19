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
 * 巡逻队信息
 * 
 * @author huo
 * 
 */
@Entity
@Table(name = "CIS_BM_PATROL_INFO")
public class CisBmPatrolInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PATROL_ID")
	private Integer patrolId;

	/**
	 * 所属机构
	 */
	@Column(name = "ORG_ID")
	private String orgId;

	/**
	 * 巡防队名称
	 */
	@Column(name = "PATROL_NAME")
	private String patrolName;

	/**
	 * 巡防队人数
	 */
	@Column(name = "PARTOL_NUMBER")
	private Integer partolNumber;

	/**
	 * 巡防路线
	 */
	@Column(name = "PARTOL_ROUTE")
	private String partolRoute;

	/**
	 * 开始时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "BEGIN_TIME")
	private Date beginTime;

	/**
	 * 结束时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "OVER_TIME")
	private Date overTime;
	
	/**
	 * 巡防内容
	 */
	@Column(name = "PATROL_CONTENT")
	private String patrolContent;

	/**
	 * 巡防效果
	 */
	@Column(name = "PATROL_EFFECT")
	private String patrolEffect;

	/**
	 * 负责人姓名
	 */
	@Column(name = "LEADING_OFFICIAL_NAME")
	private String leadingOfficialName;

	/**
	 * 负责人电话
	 */
	@Column(name = "LEADING_OFFICIAL_PHONE")
	private String leadingOfficialPhone;
	
	/**
	 * 删除标记 
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;

	public Integer getPatrolId() {
		return patrolId;
	}

	public void setPatrolId(Integer patrolId) {
		this.patrolId = patrolId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getPatrolName() {
		return patrolName;
	}

	public void setPatrolName(String patrolName) {
		this.patrolName = patrolName;
	}

	public Integer getPartolNumber() {
		return partolNumber;
	}

	public void setPartolNumber(Integer partolNumber) {
		this.partolNumber = partolNumber;
	}

	public String getPartolRoute() {
		return partolRoute;
	}

	public void setPartolRoute(String partolRoute) {
		this.partolRoute = partolRoute;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getOverTime() {
		return overTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

	public String getPatrolContent() {
		return patrolContent;
	}

	public void setPatrolContent(String patrolContent) {
		this.patrolContent = patrolContent;
	}

	public String getPatrolEffect() {
		return patrolEffect;
	}

	public void setPatrolEffect(String patrolEffect) {
		this.patrolEffect = patrolEffect;
	}

	public String getLeadingOfficialName() {
		return leadingOfficialName;
	}

	public void setLeadingOfficialName(String leadingOfficialName) {
		this.leadingOfficialName = leadingOfficialName;
	}

	public String getLeadingOfficialPhone() {
		return leadingOfficialPhone;
	}

	public void setLeadingOfficialPhone(String leadingOfficialPhone) {
		this.leadingOfficialPhone = leadingOfficialPhone;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	
	
}
