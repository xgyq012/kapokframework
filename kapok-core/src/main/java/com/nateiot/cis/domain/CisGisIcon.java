package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;


/**
 * @author Lan
 * 标注类型
 */
@Table(name="CIS_GIS_ICON")
@Entity
public class CisGisIcon extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 图标主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ICON_ID")
	private Integer iconId;
	
	/**
	 * 图标ID
	 */
	@Column(name = "IMG_ID")
	private String imgId;
	
	/**
	 * 名称
	 */
	@Column(name = "ICON_NAME")
	private String iconName;
	
	
	/**
	 * 路径
	 */
	@Column(name = "ICON_URL")
	private String iconUrl;
	
	/**
	 * 图标大小
	 */
	@Column(name = "PIC_SIZE")
	private String picSize;
	
	
	/**
	 * 描述
	 */
	@Column(name = "PIC_DESCRIBE")
	private String picDescribe;


	public Integer getIconId() {
		return iconId;
	}


	public void setIconId(Integer iconId) {
		this.iconId = iconId;
	}


	public String getIconName() {
		return iconName;
	}


	public void setIconName(String iconName) {
		this.iconName = iconName;
	}


	public String getIconUrl() {
		return iconUrl;
	}


	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}


	public String getPicSize() {
		return picSize;
	}


	public void setPicSize(String picSize) {
		this.picSize = picSize;
	}


	public String getPicDescribe() {
		return picDescribe;
	}


	public void setPicDescribe(String picDescribe) {
		this.picDescribe = picDescribe;
	}


	public String getImgId() {
		return imgId;
	}


	public void setImgId(String imgId) {
		this.imgId = imgId;
	}
	
}
