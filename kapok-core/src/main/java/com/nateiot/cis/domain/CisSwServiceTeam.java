package com.nateiot.cis.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 服务办事 -- 服务团队
 * 
 *  @author Guohw
 */
@Entity
@Table(name = "cis_sw_service_team")
public class CisSwServiceTeam extends BaseEntity{
	

	private static final long serialVersionUID = 1L;
	
	/**
	 * 服务团队主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TEAM_ID")
	private Integer teamId;
	
	/**
	 *  团队编码
	 */
	@Column(name = "TEAM_CODE")
	private String teamCode;
	
	/**
	 *  团队名称
	 */
	@Column(name = "TEAM_NAME")
	private String teamName;
	
	/**
	 *  所属机构ID
	 */
	@Column(name = "UNITS_ID")
	private Integer unitsId;
	
	/**
	 *  负责区域
	 */
	@Column(name = "REGION")
	private String region;
	
	/**
	 * 负责人 
	 */
	@Column(name = "PRINCIPAL")
	private String principal;
	
	/**
	 *  联系方式
	 */
	@Column(name = "PHONE")
	private String phone;
	
	/**
	 *  在职状态
	 */
	@Column(name = "JOB_STATUS")
	private String jobStatus;
	
	/**
	 *  职责
	 */
	@Column(name = "RESPONSIBLE")
	private String responsible;
	
	/**
	 *  删除标记
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 * 成员 
	 */
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "cisSwServiceTeam")
	private List<CisSwTeamMember> cisSwTeamMember;


	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getUnitsId() {
		return unitsId;
	}

	public void setUnitsId(Integer unitsId) {
		this.unitsId = unitsId;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public List<CisSwTeamMember> getCisSwTeamMember() {
		return cisSwTeamMember;
	}

	public void setCisSwTeamMember(List<CisSwTeamMember> cisSwTeamMember) {
		this.cisSwTeamMember = cisSwTeamMember;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	
}
