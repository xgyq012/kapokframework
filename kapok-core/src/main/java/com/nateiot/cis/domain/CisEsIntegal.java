package com.nateiot.cis.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;


/**
 *  考核督办 -- 积分管理
 * 
 * @author guohuawen
 * 
 */
@Entity
@Table(name = "CIS_ES_INTEGAL")
public class CisEsIntegal extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 积分管理主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "INTEGRAL_ID")
	private Integer integalId;
	
	/**
	 * 积分编码 
	 */
	@Column(name = "INTEGRAL_CODE")
	private String integalCode;
	
	/**
	 * 积分名称
	 */
	@Column(name = "INTEGRAL_NAME")
	private String integalName;
	
	/**
	 * 是否有效
	 */
	@Column(name = "ENABLE")
	private String enable;
	
	/**
	 * 分值
	 */
	@Column(name = "SCORE")
	private Integer score;

	/**
	 * 删除标记 
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 * 描述
	 */
	@Column(name = "REMARK")
	private String remark;

	public Integer getIntegalId() {
		return integalId;
	}

	public void setIntegalId(Integer integalId) {
		this.integalId = integalId;
	}

	public String getIntegalCode() {
		return integalCode;
	}

	public void setIntegalCode(String integalCode) {
		this.integalCode = integalCode;
	}

	public String getIntegalName() {
		return integalName;
	}

	public void setIntegalName(String integalName) {
		this.integalName = integalName;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
