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
import com.nateiot.core.common.json.JsonDateTimeSerializer;


/**
 *  考核督办 -- 积分明细记录
 * 
 * @author Guohw
 * 
 */
@Entity
@Table(name = "CIS_ES_INTEGAL_DETAIL")
public class CisEsIntegalDetail extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 积分明细主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DETAIL_ID")
	private Integer detailId;
	
	/**
	 * 用户ID
	 */
	@Column(name = "USER_ID")
	private Integer userId;
	
	/**
	 * 用户账号
	 */
	@Column(name = "USER_NAME")
	private String userName;
	
	/**
	 * 用户名称
	 */
	@Column(name = "REAL_NAME")
	private String realName;
	
	/**
	 * 积分类型
	 */
	@Column(name = "DETAIL_TYPE")
	private String detailType;
	
	/**
	 * 分值 
	 */
	@Column(name = "SCORE")
	private Integer score;
	
	/**
	 * 计分时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "DETAIL_TIME")
	private Date detailTime;
	
	/**
	 * 单据ID 
	 */
	@Column(name = "VOUCHER_ID")
	private Integer voucherId;
	
	/**
	 * 单据类型
	 */
	@Column(name = "VOUCHER_TYPE")
	private String voucherType;
	
	/**
	 * 描述
	 */
	@Column(name = "REMARK")
	private String remark;

	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	public Date getDetailTime() {
		return detailTime;
	}

	public void setDetailTime(Date detailTime) {
		this.detailTime = detailTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
	}

	public String getVoucherType() {
		return voucherType;
	}

	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}
	
}
