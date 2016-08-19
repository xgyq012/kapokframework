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
 * @author xiaguangjun
 * 独生子女证
 */

@Table(name="CIS_BM_ONLY_CHILD_CERTIFICATE")
@Entity
public class CisBmOnlyChildCertificate extends BaseEntity  {
	

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "OC_ID")
	private Integer ocId;
	
	
	/**
	 * 所属机构
	 */
	@Column(name = "ORG_ID")
	private Integer orgId;
	
	
	/**
	 * 男方姓名
	 */
	@Column(name = "M_NAME")
	private String mName;
	
	
	/**
	 * 男方工作单位
	 */
	@Column(name = "M_UNIT")
	private String mUnit;
	
	
	/**
	 * 女方姓名
	 */
	@Column(name = "W_NAME")
	private String wName;
	
	
	/**
	 * 女方工作单位
	 */
	@Column(name = "W_ADDRESS")
	private String wAddress;
	
	
	/**
	 * 家庭住址
	 */
	@Column(name = "F_ADDRESS")
	private String fAddress;
	
	
	/**
	 * 独生子女证号
	 */
	@Column(name = "DSZN_CODE")
	private String dsznCode;
	
	
	/**
	 * 办证日期
	 */
	@Column(name = "BZRQ")
	private Date bzrq;
	
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;


	public Integer getOcId() {
		return ocId;
	}


	public void setOcId(Integer ocId) {
		this.ocId = ocId;
	}


	public Integer getOrgId() {
		return orgId;
	}


	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


	public String getmName() {
		return mName;
	}


	public void setmName(String mName) {
		this.mName = mName;
	}


	public String getmUnit() {
		return mUnit;
	}


	public void setmUnit(String mUnit) {
		this.mUnit = mUnit;
	}


	public String getwName() {
		return wName;
	}


	public void setwName(String wName) {
		this.wName = wName;
	}


	public String getwAddress() {
		return wAddress;
	}


	public void setwAddress(String wAddress) {
		this.wAddress = wAddress;
	}


	public String getfAddress() {
		return fAddress;
	}


	public void setfAddress(String fAddress) {
		this.fAddress = fAddress;
	}


	public String getDsznCode() {
		return dsznCode;
	}


	public void setDsznCode(String dsznCode) {
		this.dsznCode = dsznCode;
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
