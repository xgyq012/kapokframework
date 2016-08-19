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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateTimeSerializer;

@Entity
@Table(name = "CIS_EM_SHENCHA")
public class CisEmShencha extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SHIJIAN_SHENCHA_ID")
	private Integer shijianShenchaId;
	
	/**
	 * 应急事件的id
	 */
	@Column(name = "YINGJI_SHIJIAN_ID")
	private Integer yingjiShijianId;
	
	/**
	 * 审查人id
	 */
	@Column(name = "SHENCHAREN_ID")
	private Integer shencharenId;
	
	/**
	 * 审查人名称
	 */
	@Column(name = "SHENCHAREN_NAME")
	private String shencharenName;
	
	/**
	 * 审查意见
	 */
	@Column(name = "SHENCHA_YIJIAN")
	private String shenchaYijian;
	
	/**
	 * 是否预报状态
	 */
	@Column(name = "YUBAO_STATUS")
	private String yubaoStatus;
	
	/**
	 * 是否启动应急状态
	 */
	@Column(name = "YINGJI_STATUS")
	private String yingjiStatus;

	/**
	 * 应急预案id
	 */
	@Column(name = "YINGJI_PLAN_ID")
	private Integer yingjiPlanId;
	
	/**
	 * 应急预案名称
	 */
	@Column(name = "YINGJI_PLAN_NAME")
	private String yingjiPlanName;
	
	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "SHENCHA_TIME")
	private Date shenchaTime;

	public Integer getShijianShenchaId() {
		return shijianShenchaId;
	}

	public void setShijianShenchaId(Integer shijianShenchaId) {
		this.shijianShenchaId = shijianShenchaId;
	}

	public Integer getYingjiShijianId() {
		return yingjiShijianId;
	}

	public void setYingjiShijianId(Integer yingjiShijianId) {
		this.yingjiShijianId = yingjiShijianId;
	}

	public Integer getShencharenId() {
		return shencharenId;
	}

	public void setShencharenId(Integer shencharenId) {
		this.shencharenId = shencharenId;
	}

	public String getShencharenName() {
		return shencharenName;
	}

	public void setShencharenName(String shencharenName) {
		this.shencharenName = shencharenName;
	}

	public String getShenchaYijian() {
		return shenchaYijian;
	}

	public void setShenchaYijian(String shenchaYijian) {
		this.shenchaYijian = shenchaYijian;
	}

	public String getYubaoStatus() {
		return yubaoStatus;
	}

	public void setYubaoStatus(String yubaoStatus) {
		this.yubaoStatus = yubaoStatus;
	}

	public Integer getYingjiPlanId() {
		return yingjiPlanId;
	}

	public void setYingjiPlanId(Integer yingjiPlanId) {
		this.yingjiPlanId = yingjiPlanId;
	}

	public String getYingjiStatus() {
		return yingjiStatus;
	}

	public void setYingjiStatus(String yingjiStatus) {
		this.yingjiStatus = yingjiStatus;
	}

	public Date getShenchaTime() {
		return shenchaTime;
	}

	public void setShenchaTime(Date shenchaTime) {
		this.shenchaTime = shenchaTime;
	}

	public String getYingjiPlanName() {
		return yingjiPlanName;
	}

	public void setYingjiPlanName(String yingjiPlanName) {
		this.yingjiPlanName = yingjiPlanName;
	}
	
	
}
