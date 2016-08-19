package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 考核督办 -- 事件督办
 * 
 *  @author guohuawen
 */
@Entity
@Table(name = "CIS_ES_OVERSEE")
public class CisEsOversee extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 事件督办主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OVERSEE_ID")
	private Integer overseeId;
	
	/**
	 * 事件督办编码 
	 */
	@Column(name = "OVERSEE_CODE")
	private Integer overseeCode;
	
	/**
	 * 事件督办名称 
	 */
	@Column(name = "OVERSEE_NAME")
	private String overseeName;
	
	/**
	 * 描述 
	 */
	@Column(name = "REMARK")
	private String remark;

	public Integer getOverseeId() {
		return overseeId;
	}

	public void setOverseeId(Integer overseeId) {
		this.overseeId = overseeId;
	}

	public Integer getOverseeCode() {
		return overseeCode;
	}

	public void setOverseeCode(Integer overseeCode) {
		this.overseeCode = overseeCode;
	}

	public String getOverseeName() {
		return overseeName;
	}

	public void setOverseeName(String overseeName) {
		this.overseeName = overseeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
