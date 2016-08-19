package com.nateiot.cis.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateSerializer;
import com.nateiot.core.common.json.JsonDateTimeSerializer;

/**
 * @author hezhenxian
 * 
 */
@Entity
@Table(name = "cis_gis_public_facilities")
public class CisGisPublicFacilities extends BaseEntity {

	private static final long serialVersionUID = 8393122560093861179L;

	/**
	 * 数据字典类型ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FACILITIES_ID", nullable = false)
	private Integer facilitiesId;

	/**
	 * 所属机构ID
	 */
	@Column(name = "MESH_ID" , length = 11)
	private Integer meshId;

	/**
	 * 设施类型名称
	 */
	@Column(name = "FACILITY_TYPE_NAME", length = 100)
	private String facilityTypeName;

	/**
	 * 设施编码
	 */
	@Column(name = "FACILITY_CODE", length = 100)
	private String facilityCode;

	/**
	 * 设施名称
	 */
	@Column(name = "FACILITY_NAME", length = 100)
	private String facilityName;

	/**
	 * 备注
	 */
	@Column(name = "remarks",length = 4000)
	private String remarks;
	
	/**
	 * 设施安装时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "ANZHUANG_TIME")
	private Date anzhuangTime;
	
	/**
	 * 备注
	 */
	@Column(name = "ADDRESS",length = 64)
	private String address;

	/**
	 * 经度坐标
	 */
	@Column(name = "LON", length = 200)
	private String lon;

	/**
	 * 纬度坐标
	 */
	@Column(name = "LAT", length = 200)
	private String lat;

	/**
	 * 数据字典
	 */
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "gxwlSysPublicFacilities")
	private List<CisGisFacilityLabel> gxwlSysFacilityLabel;

	public Integer getFacilitiesId() {
		return facilitiesId;
	}

	public void setFacilitiesId(Integer facilitiesId) {
		this.facilitiesId = facilitiesId;
	}

	public Integer getMeshId() {
		return meshId;
	}

	public void setMeshId(Integer meshId) {
		this.meshId = meshId;
	}

	public String getFacilityTypeName() {
		return facilityTypeName;
	}

	public void setFacilityTypeName(String facilityTypeName) {
		this.facilityTypeName = facilityTypeName;
	}

	public String getFacilityCode() {
		return facilityCode;
	}

	public void setFacilityCode(String facilityCode) {
		this.facilityCode = facilityCode;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public List<CisGisFacilityLabel> getGxwlSysFacilityLabel() {
		return gxwlSysFacilityLabel;
	}

	public void setGxwlSysFacilityLabel(
			List<CisGisFacilityLabel> gxwlSysFacilityLabel) {
		this.gxwlSysFacilityLabel = gxwlSysFacilityLabel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getAnzhuangTime() {
		return anzhuangTime;
	}

	public void setAnzhuangTime(Date anzhuangTime) {
		this.anzhuangTime = anzhuangTime;
	}
	
	

}
