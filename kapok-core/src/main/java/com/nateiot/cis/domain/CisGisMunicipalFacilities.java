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
 * @author xiaguangjun
 *
 */
@Table(name="CIS_GIS_MUNICIPAL_FACILITIES")
@Entity
public class CisGisMunicipalFacilities extends BaseEntity {
	
	
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "MF_ID")
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
	 * 坐标X
	 */
	@Column(name = "FC_X")
	private String fcX;
	
	
	/**
	 * 坐标Y
	 */
	@Column(name = "FC_Y")
	private String fcY;


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


	public String getFcX() {
		return fcX;
	}


	public void setFcX(String fcX) {
		this.fcX = fcX;
	}


	public String getFcY() {
		return fcY;
	}


	public void setFcY(String fcY) {
		this.fcY = fcY;
	}


}
