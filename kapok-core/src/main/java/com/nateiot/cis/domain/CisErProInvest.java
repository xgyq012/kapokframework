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
 * 经济运行 -- 项目招商
 * 
 * @author guohuawen
 * 
 */
@Entity
@Table(name = "CIS_ER_PROINVEST")
public class CisErProInvest extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 项目招商主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PROINVEST_ID")
	private Integer proInvestId;
	
	/**
	 * 项目招商编码
	 */
	@Column(name = "PROINVEST_CODE")
	private String proInvestCode;
	
	/**
	 * 项目招商名称
	 */
	@Column(name = "PROINVEST_NAME")
	private String proInvestName;
	
	/**
	 * 所属机构ID 
	 */
	@Column(name="UNITS_ID")
	private Integer unitsId;
	
	/**
	 * 项目概要
	 */
	@Column(name = "PROINVEST_GENERAL")
	private String proInvestGen;
	
	/**
	 * 开工日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "START_DATE")
	private Date startDate;
	
	/**
	 * 建成日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "ACTIVATE_DATE")
	private Date activateDate;
	
	/**
	 * 招商类型
	 */
	@Column(name = "PROINVEST_TYPE")
	private String proInvestType;
	
	/**
	 * 项目进度
	 */
	@Column(name = "PROINVEST_PLAN")
	private String proInvestPlan;
	
	/**
	 * 删除标记 
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 * 负责人
	 */
	@Column(name = "PRINCIPAL")
	private String principal;
	
	/**
	 * 描述
	 */
	@Column(name = "REMARK")
	private String remark;

	public Integer getProInvestId() {
		return proInvestId;
	}

	public void setProInvestId(Integer proInvestId) {
		this.proInvestId = proInvestId;
	}

	public String getProInvestCode() {
		return proInvestCode;
	}

	public void setProInvestCode(String proInvestCode) {
		this.proInvestCode = proInvestCode;
	}

	public String getProInvestName() {
		return proInvestName;
	}

	public void setProInvestName(String proInvestName) {
		this.proInvestName = proInvestName;
	}

	public String getProInvestGen() {
		return proInvestGen;
	}

	public void setProInvestGen(String proInvestGen) {
		this.proInvestGen = proInvestGen;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getActivateDate() {
		return activateDate;
	}

	public void setActivateDate(Date activateDate) {
		this.activateDate = activateDate;
	}

	public String getProInvestType() {
		return proInvestType;
	}

	public void setProInvestType(String proInvestType) {
		this.proInvestType = proInvestType;
	}

	public String getProInvestPlan() {
		return proInvestPlan;
	}

	public void setProInvestPlan(String proInvestPlan) {
		this.proInvestPlan = proInvestPlan;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUnitsId() {
		return unitsId;
	}

	public void setUnitsId(Integer unitsId) {
		this.unitsId = unitsId;
	}
	
}
