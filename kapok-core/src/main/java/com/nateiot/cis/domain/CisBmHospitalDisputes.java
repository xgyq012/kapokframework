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
 * 公共安全--医院纠纷
 * 
 * @author guohuawen
 * 
 */
@Entity
@Table(name = "cis_bm_hospital_disputes")
public class CisBmHospitalDisputes extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DISPUTES_ID")
	private Integer disputesId;
	
	
	/**
	 * 所属机构
	 */
	@Column(name = "ORG_ID")
	private Integer orgId;
	
	
	/**
	 * 医院名称
	 */
	@Column(name = "HOSPITAL_NAME")
	private String hospitalName;
	
	/**
	 * 标题
	 */
	@Column(name = "TITLE")
	private String title;
	
	
	/**
	 * 纠纷类型
	 */
	@Column(name = "DISPUTE_TYPE")
	private String disputeType;
	
	
	/**
	 * 院长
	 */
	@Column(name = "PRESIDENT")
	private String president;
	
	
	/**
	 * 涉及人数
	 */
	@Column(name = "INVOLVE_NUMBER")
	private Integer involveNumber;
	
	
	/**
	 * 发生时间
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "OCCURRENCE_TIME")
	private Date occurrenceTime;
	
	/**
	 * 内容
	 */
	@Column(name = "REMARK")
	private String remark;
	
	/**
	 * 删除标记
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;


	public Integer getDisputesId() {
		return disputesId;
	}


	public void setDisputesId(Integer disputesId) {
		this.disputesId = disputesId;
	}


	public Integer getOrgId() {
		return orgId;
	}


	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


	public String getHospitalName() {
		return hospitalName;
	}


	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}


	public String getDisputeType() {
		return disputeType;
	}


	public void setDisputeType(String disputeType) {
		this.disputeType = disputeType;
	}


	public String getPresident() {
		return president;
	}


	public void setPresident(String president) {
		this.president = president;
	}


	public Integer getInvolveNumber() {
		return involveNumber;
	}


	public void setInvolveNumber(Integer involveNumber) {
		this.involveNumber = involveNumber;
	}


	public Date getOccurrenceTime() {
		return occurrenceTime;
	}


	public void setOccurrenceTime(Date occurrenceTime) {
		this.occurrenceTime = occurrenceTime;
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


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	

}
