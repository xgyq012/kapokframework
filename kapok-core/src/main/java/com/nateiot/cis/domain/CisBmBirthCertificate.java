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
 * @author xiaguangjun
 * 生育证
 */
@Table(name="CIS_BM_BIRTH_CERTIFICATE")
@Entity
public class CisBmBirthCertificate extends BaseEntity {
	
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "BIRTH_ID")
	private Integer birthId;

	/**
	 * 所属机构
	 */
	@Column(name = "ORG_ID")
	private Integer orgId;
	
	
	/**
	 * 男方姓名
	 */
	@Column(name = "MAN_NAME")
	private String manName;
	
	
	/**
	 * 男方工作单位
	 */
	@Column(name = "MAN_UNIT")
	private String manUnit;
	
	
	/**
	 * 女方姓名
	 */
	@Column(name = "WOMAN_NAME")
	private String womanName;
	
	
	/**
	 * 女方工作单位
	 */
	@Column(name = "WOMAN_ADDRESS")
	private String womanAddress;
	
	
	/**
	 * 家庭住址
	 */
	@Column(name = "ADDRESS")
	private String address;
	
	
	/**
	 * 准生证类别
	 */
	@Column(name = "ZSZ_TYPE")
	private String zszType;
	
	
	/**
	 * 生育证号
	 */
	@Column(name = "SY_CODE")
	private String syCode;
	
	
	/**
	 * 办证日期
	 */
	@Column(name = "BZRQ")
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bzrq;
	
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;


	public Integer getBirthId() {
		return birthId;
	}


	public void setBirthId(Integer birthId) {
		this.birthId = birthId;
	}


	public Integer getOrgId() {
		return orgId;
	}


	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


	public String getManName() {
		return manName;
	}


	public void setManName(String manName) {
		this.manName = manName;
	}


	public String getManUnit() {
		return manUnit;
	}


	public void setManUnit(String manUnit) {
		this.manUnit = manUnit;
	}


	public String getWomanName() {
		return womanName;
	}


	public void setWomanName(String womanName) {
		this.womanName = womanName;
	}


	public String getWomanAddress() {
		return womanAddress;
	}


	public void setWomanAddress(String womanAddress) {
		this.womanAddress = womanAddress;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getZszType() {
		return zszType;
	}


	public void setZszType(String zszType) {
		this.zszType = zszType;
	}


	public String getSyCode() {
		return syCode;
	}


	public void setSyCode(String syCode) {
		this.syCode = syCode;
	}


	public Date getBzrq() {
		return bzrq;
	}


	public void setBzrq(Date bzrq) {
		this.bzrq = bzrq;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
 
}
