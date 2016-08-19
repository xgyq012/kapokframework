package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;


/**
 * @author xiaguangjun
 * 标注类型
 */
@Table(name="CIS_GIS_LABEL_TYPE")
@Entity
public class CisGisLabelType extends BaseEntity {
	

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "LABEL_ID")
	private Integer labelId;
	
	
	/**
	 * 名称
	 */
	@Column(name = "LABEL_NAME")
	private String labelName;
	
	
	/**
	 * url
	 */
	@Column(name = "LABEL_URL")
	private String labelUrl;


	public Integer getLabelId() {
		return labelId;
	}


	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}


	public String getLabelName() {
		return labelName;
	}


	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}


	public String getLabelUrl() {
		return labelUrl;
	}


	public void setLabelUrl(String labelUrl) {
		this.labelUrl = labelUrl;
	}


}
