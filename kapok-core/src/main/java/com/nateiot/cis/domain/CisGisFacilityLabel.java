package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.nateiot.base.domain.BaseEntity;

/**
 * @author hezhenxian
 * 
 */
@Entity
@Table(name = "cis_gis_facility_label")
public class CisGisFacilityLabel extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6819340764252883720L;

	/**
	 * 设施类型详细信息-主键ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FACILITY_TYPE_CHILD", nullable = false)
	private Integer facilityTypeChild;

	/**
	 * 设施类型父ID
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "FACILITY_TYPE_PARENT")
	private CisGisPublicFacilities gxwlSysPublicFacilities;
	
	
	/**
	 * 设施类型子名称
	 */
	@Column(name = "FACILITY_TYPE_CHILD_NAME", length = 100)
	private String facilityTypeChildName;


	public Integer getFacilityTypeChild() {
		return facilityTypeChild;
	}


	public void setFacilityTypeChild(Integer facilityTypeChild) {
		this.facilityTypeChild = facilityTypeChild;
	}


	public CisGisPublicFacilities getGxwlSysPublicFacilities() {
		return gxwlSysPublicFacilities;
	}


	public void setGxwlSysPublicFacilities(
			CisGisPublicFacilities gxwlSysPublicFacilities) {
		this.gxwlSysPublicFacilities = gxwlSysPublicFacilities;
	}


	public String getFacilityTypeChildName() {
		return facilityTypeChildName;
	}


	public void setFacilityTypeChildName(String facilityTypeChildName) {
		this.facilityTypeChildName = facilityTypeChildName;
	}

		
	
}
