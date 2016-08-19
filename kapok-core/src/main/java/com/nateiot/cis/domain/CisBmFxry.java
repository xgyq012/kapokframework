package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 服刑人员
 * @author xiaguangjun
 *
 */
@Table(name="CIS_BM_FXRY")
@Entity
public class CisBmFxry extends BaseEntity{
	
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "FX_ID")
	private Integer fxId;
	
	
	/**
	 * 人员id
	 */
	@Column(name = "HOUSEHOLDER_ID")
	private Integer householderId;
	
	
	/**
	 * 审判机关
	 */
	@Column(name = "SPJG")
	private String spjg;
	
	
	/**
	 * 罪名
	 */
	@Column(name = "ZM")
	private String zm;
	
	
	/**
	 * 刑期
	 */
	@Column(name = "XQ")
	private String xq;
	
	
	/**
	 * 服刑地址
	 */
	@Column(name = "FXDZ")
	private String fxdz;
	
	
	/**
	 * 矫正期限
	 */
	@Column(name = "JZQX")
	private String jzqx;
	
	
	/**
	 * 类别
	 */
	@Column(name = "LB_TYPE")
	private String lbType;
	
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;


	public Integer getFxId() {
		return fxId;
	}


	public void setFxId(Integer fxId) {
		this.fxId = fxId;
	}


	public Integer getHouseholderId() {
		return householderId;
	}


	public void setHouseholderId(Integer householderId) {
		this.householderId = householderId;
	}


	public String getSpjg() {
		return spjg;
	}


	public void setSpjg(String spjg) {
		this.spjg = spjg;
	}


	public String getZm() {
		return zm;
	}


	public void setZm(String zm) {
		this.zm = zm;
	}


	public String getXq() {
		return xq;
	}


	public void setXq(String xq) {
		this.xq = xq;
	}


	public String getFxdz() {
		return fxdz;
	}


	public void setFxdz(String fxdz) {
		this.fxdz = fxdz;
	}


	public String getJzqx() {
		return jzqx;
	}


	public void setJzqx(String jzqx) {
		this.jzqx = jzqx;
	}


	public String getLbType() {
		return lbType;
	}


	public void setLbType(String lbType) {
		this.lbType = lbType;
	}


	public String getDelSign() {
		return delSign;
	}


	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}


}
