package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateSerializer;
import com.nateiot.core.repository.DBUtil;

/**
 * 党组织财产信息明细
 * @author xiewenhua
 *
 */
@Entity
@Table(name = "CIS_PA_PROPERTY_DETAILS")
public class CisPaPropertyDetails extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PROPERTY_DETAILS_ID")
	private Integer propertyDetailsId;
	
	/**
	 * 党组织财产登记表id
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "PROPERTY_ID")
	private CisPaProperty property;
	
	/**
	 * 财产类型
	 */
	@Column(name = "PROPERTY_TYPE")
	private String propertyType;
	
	/**
	 * 财产名称
	 */
	@Column(name = "PROPERTY_NAME")
	private String propertyName;
	
	/**
	 * 财产编号
	 */
	@Column(name = "PROPERTY_CODE")
	private String propertyCode;
	
	/**
	 * 财产数量
	 */
	@Column(name = "PROPERTY_NUMBER")
	private Integer propertyNumber;
	
	/**
	 * 财产价值
	 */
	@Column(name = "PROPERTY_COST")
	private Double propertyCost;
	
	/**
	 * 采购日期
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "CAIGOU_DATE")
	private Date caigouDate;

	
	public Integer getPropertyDetailsId() {
		return propertyDetailsId;
	}

	public void setPropertyDetailsId(Integer propertyDetailsId) {
		this.propertyDetailsId = propertyDetailsId;
	}

	@Transient
	public Integer getPropertyId() {
		return this.property == null ? null : this.property.getPropertyId();
	}

	public void setPropertyId(Integer propertyId) {
		if(propertyId != null){
			this.property = new CisPaProperty();	
			this.property.setPropertyId(propertyId);
		}
	}

	public CisPaProperty getProperty() {
		return property;
	}

	public void setProperty(CisPaProperty property) {
		this.property = property;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyCode() {
		return propertyCode;
	}

	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}

	public Integer getPropertyNumber() {
		return propertyNumber;
	}

	public void setPropertyNumber(Integer propertyNumber) {
		this.propertyNumber = propertyNumber;
	}

	public Double getPropertyCost() {
		return propertyCost;
	}

	public void setPropertyCost(Double propertyCost) {
		this.propertyCost = propertyCost;
	}

	public Date getCaigouDate() {
		return caigouDate;
	}

	public void setCaigouDate(Date caigouDate) {
		this.caigouDate = caigouDate;
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 照片id
	 */
	@Column(name = "IMAGE_ID")
	private Integer imageId;
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;
	

}
