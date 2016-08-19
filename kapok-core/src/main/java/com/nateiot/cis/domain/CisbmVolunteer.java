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

//志愿者服务队伍

@Table(name="CIS_BM_VOLUNTEER")
@Entity
public class CisbmVolunteer extends BaseEntity {
	
	private static final long serialVersionUID = 1L;


	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "SERVICE_ID")
	private Integer serviceId;
	
	/**
	 * 机构id
	 */
	@Column(name = "ORG_ID")
	private Integer orgId;
	
	/**
	 * 队伍名称
	 */
	@Column(name = "TEAM_NAME")
	private String teamName;
	
	
	/**
	 * 志愿者人数
	 */
	@Column(name = "VOLUNT_NUM")
	private Integer voluntNum;
	
	
	/**
	 * 负责人
	 */
	@Column(name = "LOWER")
	private String lower;
	
	
	/**
	 * 联系电话
	 */
	@Column(name = "PHONE")
	private String phone;
	
	
	/**
	 * 服务时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "SER_TIME")
	private Date serTime;
	
	
	/**
	 * 服务内容
	 */
	@Column(name = "SER_CONTENT")
	private String serContent;
	
	
	/**
	 * 服务地点
	 */
	@Column(name = "SER_ADDRESS")
	private String serAddress;
	
	
	
	/**
	 * 服务效果
	 */
	@Column(name = "SER_EFFECT")
	private String serEffect;



	public Integer getServiceId() {
		return serviceId;
	}



	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getTeamName() {
		return teamName;
	}



	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}



	public Integer getVoluntNum() {
		return voluntNum;
	}



	public void setVoluntNum(Integer voluntNum) {
		this.voluntNum = voluntNum;
	}



	public String getLower() {
		return lower;
	}



	public void setLower(String lower) {
		this.lower = lower;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public Date getSerTime() {
		return serTime;
	}



	public void setSerTime(Date serTime) {
		this.serTime = serTime;
	}



	public String getSerContent() {
		return serContent;
	}



	public void setSerContent(String serContent) {
		this.serContent = serContent;
	}



	public String getSerAddress() {
		return serAddress;
	}



	public void setSerAddress(String serAddress) {
		this.serAddress = serAddress;
	}



	public String getSerEffect() {
		return serEffect;
	}



	public void setSerEffect(String serEffect) {
		this.serEffect = serEffect;
	}


	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
}
