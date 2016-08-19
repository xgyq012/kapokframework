package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateSerializer;

/**
 * 技防信息
 * 
 * @author huo
 * 
 */
@Entity
@Table(name = "CIS_BM_SAFETY_INFO")
public class CisBmSafetyInfo extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SAFETY_ID")
	private Integer safetyId;

	/**
	 * 所属机构
	 */
	@Column(name = "ORG_ID")
	private Integer orgId;

	/**
	 * 摄像头位置
	 */
	@Column(name = "CAMER_POS")
	private String camerPos;

	/**
	 * 摄像头个数
	 */
	@Column(name = "CAMER_NUM")
	private Integer camerNum;
	

	/**
	 * 时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "TIME")
	private Date time;

	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;
	
	/**
	 * 删除标记 
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;

	public Integer getSafetyId() {
		return safetyId;
	}

	public void setSafetyId(Integer safetyId) {
		this.safetyId = safetyId;
	}


	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getCamerPos() {
		return camerPos;
	}

	public void setCamerPos(String camerPos) {
		this.camerPos = camerPos;
	}

	public Integer getCamerNum() {
		return camerNum;
	}

	public void setCamerNum(Integer camerNum) {
		this.camerNum = camerNum;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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
