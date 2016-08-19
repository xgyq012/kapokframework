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
 * 吸毒人员
 * @author xiaguangjun
 *
 */
@Entity
@Table(name="CIS_BM_XDRY")
public class CisBmXdry extends BaseEntity {


	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "XD_ID")
	private Integer xdId;
	
	
	/**
	 * 人员id
	 */
	@Column(name = "HOUSEHOLDER_ID")
	private Integer householderId;
	
	
	/**
	 * 初吸时间
	 */
	@Column(name = "XD_DATE")
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date xdDate;
	
	
	/**
	 * 毒品种类
	 */
	@Column(name = "DP_TYPE")
	private String dpType;
	
	
	/**
	 * 处理措施
	 */
	@Column(name = "CLCS")
	private String clcs;
	
	
	/**
	 * 已纳入社区戒毒/社区康复
	 */
	@Column(name = "IS_REHABILITATION")
	private String isRehabilitation;
	
	
	/**
	 * 报到日期
	 */
	@Column(name = "BEGIN_DATE")
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginDate;
	
	
	/**
	 * 结束日期
	 */
	@Column(name = "END_DATE")
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endDate;
	
	
	/**
	 * 法律文书文号
	 */
	@Column(name = "FLWSWH")
	private String flwswh;
	
	
	/**
	 * 结束原因
	 */
	@Column(name = "END_REASON")
	private String endReason;
	
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;
	
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;

	

	public Integer getXdId() {
		return xdId;
	}


	public void setXdId(Integer xdId) {
		this.xdId = xdId;
	}


	public Integer getHouseholderId() {
		return householderId;
	}


	public void setHouseholderId(Integer householderId) {
		this.householderId = householderId;
	}


	public Date getXdDate() {
		return xdDate;
	}


	public void setXdDate(Date xdDate) {
		this.xdDate = xdDate;
	}


	public String getDpType() {
		return dpType;
	}


	public void setDpType(String dpType) {
		this.dpType = dpType;
	}


	public String getClcs() {
		return clcs;
	}


	public void setClcs(String clcs) {
		this.clcs = clcs;
	}


	public String getIsRehabilitation() {
		return isRehabilitation;
	}


	public void setIsRehabilitation(String isRehabilitation) {
		this.isRehabilitation = isRehabilitation;
	}


	public Date getBeginDate() {
		return beginDate;
	}


	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getFlwswh() {
		return flwswh;
	}


	public void setFlwswh(String flwswh) {
		this.flwswh = flwswh;
	}


	public String getEndReason() {
		return endReason;
	}


	public void setEndReason(String endReason) {
		this.endReason = endReason;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getDelSign() {
		return delSign;
	}


	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}
 
}
