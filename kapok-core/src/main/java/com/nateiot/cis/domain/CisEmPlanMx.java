package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;
/**
 * 应急预案明细
 * @author xiewenhua
 *
 */
@Entity
@Table(name="CIS_EM_PLAN_MX")
public class CisEmPlanMx extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PLAN_MX_ID")
	private Integer planMxId;
	
	/**
	 * 应急预案id
	 */
	@Column(name = "YINGJI_PLAN_ID")
	private Integer yingjiPlanId;
	
	/**
	 * 动作名称
	 */
	@Column(name = "ACTION_NAME")
	private String actionName;
	
	/**
	 * 关于动作的说明
	 */
	@Column(name = "ACTION_PS")
	private String actionPs;
	
	/**
	 * 系统用户id列表，用,分割
	 */
	@Column(name = "SYS_USER_IDS")
	private String sysUserIds;
	
	/**
	 * 其他非系统用户的id
	 */
	@Column(name = "QITAREN_IDS")
	private String qitarenIds;
	
	/**
	 * 编号
	 */
	@Column(name = "NUMBER")
	private Integer number;

	public Integer getPlanMxId() {
		return planMxId;
	}

	public void setPlanMxId(Integer planMxId) {
		this.planMxId = planMxId;
	}

	public Integer getYingjiPlanId() {
		return yingjiPlanId;
	}

	public void setYingjiPlanId(Integer yingjiPlanId) {
		this.yingjiPlanId = yingjiPlanId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionPs() {
		return actionPs;
	}

	public void setActionPs(String actionPs) {
		this.actionPs = actionPs;
	}

	public String getSysUserIds() {
		return sysUserIds;
	}

	public void setSysUserIds(String sysUserIds) {
		this.sysUserIds = sysUserIds;
	}

	public String getQitarenIds() {
		return qitarenIds;
	}

	public void setQitarenIds(String qitarenIds) {
		this.qitarenIds = qitarenIds;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
}
