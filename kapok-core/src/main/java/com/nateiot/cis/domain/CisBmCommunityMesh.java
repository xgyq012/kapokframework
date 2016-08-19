package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.nateiot.base.domain.BaseEntity;

/**
 * @author zhangweiming 社区网格
 * 
 */
@Table(name = "CIS_BM_COMMUNITY_MESH")
@Entity
public class CisBmCommunityMesh extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -468576863656151809L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "MESH_ID")
	private Integer meshId;

	/**
	 * 网格编码
	 */
	@Column(name = "MESH_CODE")
	private String meshCode;

	/**
	 * 网格名称
	 */
	@Column(name = "MESH_NAME")
	private String meshName;

	/**
	 * 网格全称
	 */
	@Column(name = "MESH_FULL_NAME")
	private String meshFullName;

	/**
	 * 网格类型
	 */
	@Column(name = "MESH_TYPE")
	private String meshType;

	/**
	 * 网格概况
	 */
	@Column(name = "MESH_DESC")
	private String meshDesc;

	/**
	 * 缩放级别
	 */
	@Column(name = "SUO_FANG_JI_BIE")
	private Integer suoFangJiBie;

	/**
	 * 范围坐标
	 */
	@Column(name = "FAN_WEI_ZUO_BIAO")
	private String fanWeiZuoBiao;

	/**
	 * 经度坐标
	 */
	@Column(name = "LON")
	private String lon;

	/**
	 * 纬度坐标
	 */
	@Column(name = "LAT")
	private String lat;

	/**
	 * 上级网格ID
	 */
	@Column(name = "PARENT_MESH_ID")
	private Integer parentMeshId;

	/**
	 * 删除标志
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;

	/**
	 * ID全路径
	 */
	@Column(name = "FULL_PATH")
	private String fullPath;

	/**
	 * 是否叶子
	 */
	@Column(name = "IS_LEAF")
	private String isLeaf;
	
	@Transient
	public String getState() {
		return "N".equalsIgnoreCase(getIsLeaf()) ? "closed" : "open";
	}


	public Integer getMeshId() {
		return meshId;
	}

	public void setMeshId(Integer meshId) {
		this.meshId = meshId;
	}

	public String getMeshCode() {
		return meshCode;
	}

	public void setMeshCode(String meshCode) {
		this.meshCode = meshCode;
	}

	public String getMeshName() {
		return meshName;
	}

	public void setMeshName(String meshName) {
		this.meshName = meshName;
	}

	public String getMeshFullName() {
		return meshFullName;
	}

	public void setMeshFullName(String meshFullName) {
		this.meshFullName = meshFullName;
	}

	public String getMeshType() {
		return meshType;
	}

	public void setMeshType(String meshType) {
		this.meshType = meshType;
	}

	public String getMeshDesc() {
		return meshDesc;
	}

	public void setMeshDesc(String meshDesc) {
		this.meshDesc = meshDesc;
	}

	public Integer getSuoFangJiBie() {
		return suoFangJiBie;
	}

	public void setSuoFangJiBie(Integer suoFangJiBie) {
		this.suoFangJiBie = suoFangJiBie;
	}

	public String getFanWeiZuoBiao() {
		return fanWeiZuoBiao;
	}

	public void setFanWeiZuoBiao(String fanWeiZuoBiao) {
		this.fanWeiZuoBiao = fanWeiZuoBiao;
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

	public Integer getParentMeshId() {
		return parentMeshId;
	}

	public void setParentMeshId(Integer parentMeshId) {
		this.parentMeshId = parentMeshId;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

}
