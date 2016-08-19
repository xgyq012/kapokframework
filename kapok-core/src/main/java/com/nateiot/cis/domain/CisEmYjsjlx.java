package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

@Entity
@Table(name="CIS_EM_YJSJLX")
public class CisEmYjsjlx extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "YJSJLX_ID")
	private Integer yjsjlxId;
	
	
	/**
	 * 应急事件类型名称
	 */
	@Column(name = "LEIXING_NAME")
	private String leixingName;
	
	
	/**
	 * 应急事件类型的描述
	 */
	@Column(name = "LEIXING_PS")
	private String leixingPs;
	
	/**
	 * 代表色
	 */
	@Column(name = "COLOR")
	private String color;
	
	
	/**
	 * 全名
	 */
	@Column(name = "FULL_NAME")
	private String fullName;

	/**
	 * 继承关系
	 */
	@Column(name = "FULL_PATH")
	private String fullPath;
	
	/**
	 * 类型等级 
	 */
	@Column(name = "PARENT_ID")
	private Integer parentId;
	
	/**
	 * 代表色
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;

	public Integer getYjsjlxId() {
		return yjsjlxId;
	}

	public void setYjsjlxId(Integer yjsjlxId) {
		this.yjsjlxId = yjsjlxId;
	}

	public String getLeixingName() {
		return leixingName;
	}

	public void setLeixingName(String leixingName) {
		this.leixingName = leixingName;
	}

	public String getLeixingPs() {
		return leixingPs;
	}

	public void setLeixingPs(String leixingPs) {
		this.leixingPs = leixingPs;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	
}
