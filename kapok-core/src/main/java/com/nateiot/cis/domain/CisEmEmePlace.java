package com.nateiot.cis.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 应急管理 -- 避难场所
 * 
 * @author guohuawen
 * 
 */
@Entity
@Table(name = "CIS_EM_EMEPLACE")
public class CisEmEmePlace extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 避难场所主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EMEPLACE_ID")
	private Integer emePlaceId;
	
	
	/**
	 * 避难场所名称
	 */
	@Column(name = "EMEPLACE_NAME")
	private String emePlaceName;
	
	/**
	 * 地址
	 */
	@Column(name = "ADDRESS")
	private String address;
	
	/**
	 * 地理坐标X
	 */
	@Column(name = "LON")
	private String lon;
	
	/**
	 * 地理坐标Y
	 */
	@Column(name = "LAT")
	private String lat;
	
	/**
	 * 面积
	 */
	@Column(name = "AREA")
	private String area;
	
	/**
	 * 容纳人数
	 */
	@Column(name = "GALLERY")
	private String gallery;
	
	/**
	 * 负责人
	 */
	@Column(name = "FUZEREN")
	private String fuzeren;
	
	/**
	 * 负责人电话
	 */
	@Column(name = "PHONE")
	private String phone;
	/**
	 * 删除标记 
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 * 描述
	 */
	@Column(name = "REMARK")
	private String remark;

	public Integer getEmePlaceId() {
		return emePlaceId;
	}

	public void setEmePlaceId(Integer emePlaceId) {
		this.emePlaceId = emePlaceId;
	}

	public String getEmePlaceName() {
		return emePlaceName;
	}

	public void setEmePlaceName(String emePlaceName) {
		this.emePlaceName = emePlaceName;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getGallery() {
		return gallery;
	}

	public void setGallery(String gallery) {
		this.gallery = gallery;
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

	public String getFuzeren() {
		return fuzeren;
	}

	public void setFuzeren(String fuzeren) {
		this.fuzeren = fuzeren;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
