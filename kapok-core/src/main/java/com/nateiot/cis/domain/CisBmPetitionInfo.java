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
 * 信访信息
 * 
 * @author guohuawen
 * 
 */
@Entity
@Table(name = "CIS_BM_PETITION_INFO")
public class CisBmPetitionInfo extends BaseEntity {


	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PETITION_ID")
	private Integer petitionId;
	
	
	/**
	 * 所属机构
	 */
	@Column(name = "ORG_ID")
	private Integer orgId;
	
	
	/**
	 * 上访人员姓名
	 */
	@Column(name = "PETITION_NAME")
	private String petitionName;
	
	
	/**
	 * 反应主要问题
	 */
	@Column(name = "MAIN_PROBLEM")
	private String mainProblem;
	
	
	/**
	 * 责任单位
	 */
	@Column(name = "ACCOUNT_UNIT")
	private String accountUnit;
	
	
	/**
	 * 责任人
	 */
	@Column(name = "ACCOUNT_PER")
	private String accountPer;
	
	
	/**
	 * 分包领导
	 */
	@Column(name = "LEADERSHIP")
	private String leadership;
	
	
	/**
	 * 处理类型
	 */
	@Column(name = "HANDLE_TYPE")
	private String handleType;
	
	
	/**
	 * 时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "TIME")
	private Date time;
	
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;
	
	
	/**
	 * 删除标记
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;


	public Integer getPetitionId() {
		return petitionId;
	}


	public void setPetitionId(Integer petitionId) {
		this.petitionId = petitionId;
	}


	public Integer getOrgId() {
		return orgId;
	}


	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


	public String getPetitionName() {
		return petitionName;
	}


	public void setPetitionName(String petitionName) {
		this.petitionName = petitionName;
	}


	public String getMainProblem() {
		return mainProblem;
	}


	public void setMainProblem(String mainProblem) {
		this.mainProblem = mainProblem;
	}


	public String getAccountUnit() {
		return accountUnit;
	}


	public void setAccountUnit(String accountUnit) {
		this.accountUnit = accountUnit;
	}


	public String getAccountPer() {
		return accountPer;
	}


	public void setAccountPer(String accountPer) {
		this.accountPer = accountPer;
	}


	public String getLeadership() {
		return leadership;
	}


	public void setLeadership(String leadership) {
		this.leadership = leadership;
	}


	public String getHandleType() {
		return handleType;
	}


	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}


	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getDelSign() {
		return delSign;
	}


	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}


 
}
