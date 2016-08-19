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
 * @author xiaguangjun
 * 党组织信息
 */
@Entity
@Table(name="CIS_BM_PARTY_ORGANIZATION")
public class CisBmPartyOrganization extends BaseEntity {
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ORGANIZATION_ID")
	private Integer organizationId;
	
	
	/**
	 * 所属机构
	 */
	@Column(name = "ORG")
	private Integer org;
	
	
	/**
	 * 党组织名称
	 */
	@Column(name = "ORGANIZATION_NAME")
	private String organizationName;
	
	
	/**
	 * 地址
	 */
	@Column(name = "ADDRESS")
	private String address;
	
	
	/**
	 * 党组织负责人
	 */
	@Column(name = "RESPONSIBLE_PERSON")
	private String responsiblePerson;
	
	
	/**
	 * 联系电话
	 */
	@Column(name = "CONTACT_NUMBER")
	private String contactNumber;
	
	
	/**
	 * 党员人数
	 */
	@Column(name = "PARTY_NUMBER")
	private Integer partyNumber;
	
	
	/**
	 * 党组织类型
	 */
	@Column(name = "ORGANIZATION_TYPE")
	private String organizationType;
	
	
	/**
	 * 换届时间
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "CHANGE_TIME")
	private Date changeTime;
	
	
	/**
	 * 选举方式
	 */
	@Column(name = "ELECTION_METHOD")
	private String electionMethod;
	
	
	/**
	 * 班子成员
	 */
	@Column(name = "TEAM_MEMBERS")
	private String teamMembers;
	
	
	/**
	 * 发展党员
	 */
	@Column(name = "DEVELOPMENT_PARTY")
	private String developmentParty;
	
	
	/**
	 * 党费收缴
	 */
	@Column(name = "PARTY_MEMBERSHIP_DUES")
	private Double partyMembershipDues;
	
	
	/**
	 * 民主评议
	 */
	@Column(name = "DELIBERATION")
	private String deliberation;
	
	
	/**
	 * 民主生活会
	 */
	@Column(name = "LIFE_WILL")
	private String lifeWill;
	
	
	/**
	 * 奖惩情况
	 */
	@Column(name = "REWARDS_AND_PUNISHMENT")
	private String rewardsAndPunishment;


	public Integer getOrganizationId() {
		return organizationId;
	}


	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}


	public Integer getOrg() {
		return org;
	}


	public void setOrg(Integer org) {
		this.org = org;
	}


	public String getOrganizationName() {
		return organizationName;
	}


	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getResponsiblePerson() {
		return responsiblePerson;
	}


	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}


	public String getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}


	public Integer getPartyNumber() {
		return partyNumber;
	}


	public void setPartyNumber(Integer partyNumber) {
		this.partyNumber = partyNumber;
	}


	public String getOrganizationType() {
		return organizationType;
	}


	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}


	public Date getChangeTime() {
		return changeTime;
	}


	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}


	public String getElectionMethod() {
		return electionMethod;
	}


	public void setElectionMethod(String electionMethod) {
		this.electionMethod = electionMethod;
	}


	public String getTeamMembers() {
		return teamMembers;
	}


	public void setTeamMembers(String teamMembers) {
		this.teamMembers = teamMembers;
	}


	public String getDevelopmentParty() {
		return developmentParty;
	}


	public void setDevelopmentParty(String developmentParty) {
		this.developmentParty = developmentParty;
	}


	public Double getPartyMembershipDues() {
		return partyMembershipDues;
	}


	public void setPartyMembershipDues(Double partyMembershipDues) {
		this.partyMembershipDues = partyMembershipDues;
	}


	public String getDeliberation() {
		return deliberation;
	}


	public void setDeliberation(String deliberation) {
		this.deliberation = deliberation;
	}


	public String getLifeWill() {
		return lifeWill;
	}


	public void setLifeWill(String lifeWill) {
		this.lifeWill = lifeWill;
	}


	public String getRewardsAndPunishment() {
		return rewardsAndPunishment;
	}


	public void setRewardsAndPunishment(String rewardsAndPunishment) {
		this.rewardsAndPunishment = rewardsAndPunishment;
	}


 
 
	
}
