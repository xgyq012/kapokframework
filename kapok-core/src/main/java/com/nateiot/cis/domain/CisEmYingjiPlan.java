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
 * 应急预案
 * @author xiewenhua
 *
 */
@Entity
@Table(name = "CIS_EM_YINGJI_PLAN")
public class CisEmYingjiPlan extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 跟进明细
	 */
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "yingjiPlan")
	private List<CisEmPlanRenyuan> planRenyuanList;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "YINGJI_PLAN_ID")
	private Integer yingjiPlanId;
	
	/**
	 * 应急事件类别ID
	 */
	@Column(name = "YJSJLX_ID")
	private Integer yjsjlxId;
	
	/**
	 * 应急事件类型名称
	 */
	@Column(name = "YJSJLX_NAME")
	private String yjsjlxName;
	
	/**
	 * 应急预案名称
	 */
	@Column(name = "NAME")
	private String name;
	
	/**
	 * 应急预案内容
	 */
	@Column(name = "PLAN_BODY")
	private String planBody;

	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	public String getYjsjlxName() {
		return yjsjlxName;
	}
	public void setYjsjlxName(String yjsjlxName) {
		this.yjsjlxName = yjsjlxName;
	}
	public Integer getYingjiPlanId() {
		return yingjiPlanId;
	}
	public void setYingjiPlanId(Integer yingjiPlanId) {
		this.yingjiPlanId = yingjiPlanId;
	}
	public Integer getYjsjlxId() {
		return yjsjlxId;
	}
	public void setYjsjlxId(Integer yjsjlxId) {
		this.yjsjlxId = yjsjlxId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlanBody() {
		return planBody;
	}
	public void setPlanBody(String planBody) {
		this.planBody = planBody;
	}
	public List<CisEmPlanRenyuan> getPlanRenyuanList() {
		return planRenyuanList;
	}
	public void setPlanRenyuanList(List<CisEmPlanRenyuan> planRenyuanList) {
		this.planRenyuanList = planRenyuanList;
	}
	public String getDelSign() {
		return delSign;
	}
	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}
	
}
