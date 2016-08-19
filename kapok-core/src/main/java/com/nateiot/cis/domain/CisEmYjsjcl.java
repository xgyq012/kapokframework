package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 应急事件处理
 * @author xiewenhua
 *
 */
@Entity
@Table(name = "CIS_EM_YJSJCL")
public class CisEmYjsjcl extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "YJSJCL_ID")
	private Integer yjsjclId;
	
	/**
	 * 应急事件ID
	 */
	@Column(name = "YINGJI_SHIJIAN_ID")
	private Integer yingjiShijianId;
	
	/**
	 * 应急方案ID
	 */
	@Column(name = "YINGJI_PLAN_ID")
	private Integer yingjiPlanId;
	
	/**
	 * 应急人员ID（被派遣人）
	 */
	@Column(name = "YINGJI_RENYUAN_ID")
	private Integer yingjiRenyuanId;
	
	/**
	 * 派遣意见
	 */
	@Column(name = "PAIQIAN_YIJIAN")
	private String paiqianYijian;
	
	/**
	 * 派遣人ID
	 */
	@Column(name = "PAIQIAN_REN_ID")
	private Integer paiqianRenId;
	
	/**
	 * 派遣时间
	 */
	@Column(name = "PAIQIAN_TIME")
	private Date paiqianTime;

	public Integer getYjsjclId() {
		return yjsjclId;
	}

	public void setYjsjclId(Integer yjsjclId) {
		this.yjsjclId = yjsjclId;
	}

	public Integer getYingjiShijianId() {
		return yingjiShijianId;
	}

	public void setYingjiShijianId(Integer yingjiShijianId) {
		this.yingjiShijianId = yingjiShijianId;
	}

	public Integer getYingjiPlanId() {
		return yingjiPlanId;
	}

	public void setYingjiPlanId(Integer yingjiPlanId) {
		this.yingjiPlanId = yingjiPlanId;
	}

	public Integer getYingjiRenyuanId() {
		return yingjiRenyuanId;
	}

	public void setYingjiRenyuanId(Integer yingjiRenyuanId) {
		this.yingjiRenyuanId = yingjiRenyuanId;
	}

	public String getPaiqianYijian() {
		return paiqianYijian;
	}

	public void setPaiqianYijian(String paiqianYijian) {
		this.paiqianYijian = paiqianYijian;
	}

	public Integer getPaiqianRenId() {
		return paiqianRenId;
	}

	public void setPaiqianRenId(Integer paiqianRenId) {
		this.paiqianRenId = paiqianRenId;
	}

	public Date getPaiqianTime() {
		return paiqianTime;
	}

	public void setPaiqianTime(Date paiqianTime) {
		this.paiqianTime = paiqianTime;
	}
}
