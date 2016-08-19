package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;


/**
 * 经济运行 -- 风险项目评估
 * 
 *  @author Guohw
 */
@Entity
@Table(name = "CIS_ER_RISK")
public class CisErRisk extends BaseEntity{
	
private static final long serialVersionUID = 1L;
	
	/**
	 * 风险项目评估主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RISK_ID")
	private Integer riskId;
	
	/**
	 * 所属机构 
	 */
	@Column(name = "UNITS_ID")
	private String unitsId;
	
	/**
	 * 所属机构 
	 */
	@Column(name = "UNIT")
	private String unit;
	
	/**
	 * 风险评估名称
	 */
	@Column(name = "RISK_NAME")
	private String riskName;
	
	/**
	 * 项目介绍
	 */
	@Column(name = "REMARK")
	private String remark;
	
	/**
	 *  项目类型
	 */
	@Column(name = "RISK_TYPE")
	private String riskType;
	
	/**
	 *  删除标记
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 *  项目评估状态
	 */
	@Column(name = "RISK_STATUS")
	private String riskStatus;
	
	/**
	 *  实施单位
	 */
	@Column(name = "CARRY_OUT")
	private String carryOut;
	
	/**
	 * 评估人id 
	 */
	@Column(name = "PRINCIPAL_ID")
	private Integer principalId;
	
	/**
	 *  评估人姓名
	 */
	@Column(name = "PRINCIPAL_NAME")
	private String principalName;

	public Integer getRiskId() {
		return riskId;
	}

	public void setRiskId(Integer riskId) {
		this.riskId = riskId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public String getRiskStatus() {
		return riskStatus;
	}

	public void setRiskStatus(String riskStatus) {
		this.riskStatus = riskStatus;
	}

	public String getCarryOut() {
		return carryOut;
	}

	public void setCarryOut(String carryOut) {
		this.carryOut = carryOut;
	}

	public Integer getPrincipalId() {
		return principalId;
	}

	public void setPrincipalId(Integer principalId) {
		this.principalId = principalId;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public String getUnitsId() {
		return unitsId;
	}

	public void setUnitsId(String unitsId) {
		this.unitsId = unitsId;
	}
	
}
