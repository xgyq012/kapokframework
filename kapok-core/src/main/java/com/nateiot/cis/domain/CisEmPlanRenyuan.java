package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.repository.DBUtil;

/**
 * 应急预案中指定的应急人员
 * @author 2
 *
 */
@Entity
@Table(name="CIS_EM_PLAN_RENYUAN")
public class CisEmPlanRenyuan extends BaseEntity{
	private static final long serialVersionUID = -5166903933955850635L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PLAN_RENYUAN_ID")
	private Integer planRenyuanId;
	
	/**
	 * 应急预案  id
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "YINGJI_PLAN_ID", nullable = false)
	private CisEmYingjiPlan yingjiPlan;
	
	/**
	 * 应急人员
	 */
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "YINGJI_RENYUAN_ID", nullable = false)
	private CisEmYingjiRenyuan yingjiRenyuan;
	

	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;

	public Integer getPlanRenyuanId() {
		return planRenyuanId;
	}

	public void setPlanRenyuanId(Integer planRenyuanId) {
		this.planRenyuanId = planRenyuanId;
	}
	
	
	
	

	
	
	
	public CisEmYingjiPlan getYingjiPlan() {
		return yingjiPlan;
	}

	public void setYingjiPlan(CisEmYingjiPlan yingjiPlan) {
		this.yingjiPlan = yingjiPlan;
	}

	@Transient
	public Integer getYingjiPlanId(){
		return this.yingjiPlan == null ? null : this.yingjiPlan.getYingjiPlanId();
	}
	
	public void setYingjiPlanId(Integer yingjiPlanId){
		if(yingjiPlanId != null){
			this.yingjiPlan = DBUtil.find(CisEmYingjiPlan.class, yingjiPlanId);
		}
	}
	
	
	
	
	
	
	@Transient
	public Integer getYingjiRenyuanId(){
		return this.yingjiRenyuan == null ? null : this.yingjiRenyuan.getYingjiRenyuanId();
	}
	
	public void setYingjiRenyuanId(Integer yingjiRenyuanId){
		if(yingjiRenyuanId != null){
			this.yingjiRenyuan = DBUtil.find(CisEmYingjiRenyuan.class, yingjiRenyuanId);
		}
	}
	@Transient
	public CisEmYingjiRenyuan getYingjiRenyuan() {
		return yingjiRenyuan;
	}
	@Transient
	public void setYingjiRenyuan(CisEmYingjiRenyuan yingjiRenyuan) {
		this.yingjiRenyuan = yingjiRenyuan;
	}

	@Transient
	public String getYingjirenName() {
		return this.yingjiRenyuan == null ? null : this.yingjiRenyuan.getYingjirenName();
	}

	@Transient
	public String getYingjirenSex() {
		return this.yingjiRenyuan == null ? null : this.yingjiRenyuan.getYingjirenSex();
	}

	@Transient
	public String getYingjirenIdcard() {
		return this.yingjiRenyuan == null ? null : this.yingjiRenyuan.getYingjirenIdcard();
	}
	@Transient
	public String getPhone() {
		return this.yingjiRenyuan == null ? null : this.yingjiRenyuan.getPhone();
	}
	@Transient
	public Integer getZaizhiStatus() {
		return this.yingjiRenyuan == null ? null : this.yingjiRenyuan.getZaizhiStatus();
	}
	@Transient
	public String getAddress() {
		return this.yingjiRenyuan == null ? null : this.yingjiRenyuan.getAddress();
	}
	@Transient
	public String getPs() {
		return this.yingjiRenyuan == null ? null : this.yingjiRenyuan.getPs();
	}

	@Transient
	public String getZhiwei() {
		return this.yingjiRenyuan == null ? null : this.yingjiRenyuan.getZhiwei();
	}

	
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
