package com.nateiot.cis.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 市政设施
 * @author Administrator
 *
 */
@Entity
@Table(name="CIS_BM_MUNICIPAL_FACILITE")
public class CisBmMunicipalFacilite extends BaseEntity {
	
	
	/**
	 * ID
	 */
	@Id
	@Column(name = "MF_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer mfId;
	
	
	/**
	 * 名称
	 */
	@Column(name = "MF_NAME")
	private String mfName;
	
	
	/**
	 * 地址
	 */
	@Column(name = "MF_ADDRESS")
	private String mfAddress;
	
	
	/**
	 * 标注类型
	 */
	@Column(name = "LABEL_TYPE")
	private String labelType;
	
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;
	
	
	/**
	 * 经度
	 */
	@Column(name = "LON")
	private String lon;
	
	
	/**
	 * 维度
	 */
	@Column(name = "LAT")
	private String lat;


	public Integer getMfId() {
		return mfId;
	}


	public void setMfId(Integer mfId) {
		this.mfId = mfId;
	}


	public String getMfName() {
		return mfName;
	}


	public void setMfName(String mfName) {
		this.mfName = mfName;
	}


	public String getMfAddress() {
		return mfAddress;
	}


	public void setMfAddress(String mfAddress) {
		this.mfAddress = mfAddress;
	}


	public String getLabelType() {
		return labelType;
	}


	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getLon() {
		return lon;
	}


	public void setLon(String lon) {
		this.lon = lon;
	}


	public String getLat() {
		return lat;
	}


	public void setLat(String lat) {
		this.lat = lat;
	}
	

	
}
