package com.nateiot.cis.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
 * 党务建设 -- 两委分工
 * 
 *  @author Guohw
 */
@Entity
@Table(name = "CIS_PA_DIVISION_LABOR")
public class CisPaDivisionLabor extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 两委分工主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LABOR_ID")
	private Integer laborId;
	
	/**
	 *  编码
	 */
	@Column(name = "LABOR_CODE")
	private String laborCode;
	
	/**
	 *  所属网格
	 */
	@Column(name = "UNITS_ID")
	private Integer unitsId;
	
	/**
	 *  类型
	 */
	@Column(name = "LABOR_TYPE")
	private String laborType;
	
	/**
	 *  编制人
	 */
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "COMPILE_ID", referencedColumnName = "HOUSEHOLDER_ID")
	private CisBmHouseholder houseHolder;
	
	/**
	 *  编制日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "COMPILE_DATE")
	private Date compileDate;
	
	/**
	 *  社区名称
	 */
	@Column(name = "COMMUNITY_NAME")
	private String communityName;
	
	/**
	 *  删除标记
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 *  备注
	 */
	@Column(name = "REMARK")
	private String remark;
	
	/**
	 * 职务 
	 */
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "cisPaDivisionLabor")
	private List<CisPaDivisionRowtable> cisPaDivisionRowtable;
	
	@Transient
	public void setCompileId(Integer compileId){
		if(compileId != null){
			this.houseHolder = DBUtil.find(CisBmHouseholder.class, compileId);
		}
	}
	
	@Transient
	public Integer getCompileId(){
		return this.houseHolder == null ? null : this.houseHolder.getHouseholderId();
	}
	
	@Transient
	public String getCompileName(){
		return this.houseHolder == null ? null : this.houseHolder.getHouseholderName();
	}

	public CisBmHouseholder getHouseHolder() {
		return houseHolder;
	}

	public void setHouseHolder(CisBmHouseholder houseHolder) {
		this.houseHolder = houseHolder;
	}

	public Integer getLaborId() {
		return laborId;
	}

	public void setLaborId(Integer laborId) {
		this.laborId = laborId;
	}

	public String getLaborCode() {
		return laborCode;
	}

	public void setLaborCode(String laborCode) {
		this.laborCode = laborCode;
	}

	public Integer getUnitsId() {
		return unitsId;
	}

	public void setUnitsId(Integer unitsId) {
		this.unitsId = unitsId;
	}

	public String getLaborType() {
		return laborType;
	}

	public void setLaborType(String laborType) {
		this.laborType = laborType;
	}

	public Date getCompileDate() {
		return compileDate;
	}

	public void setCompileDate(Date compileDate) {
		this.compileDate = compileDate;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
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

	public List<CisPaDivisionRowtable> getCisPaDivisionRowtable() {
		return cisPaDivisionRowtable;
	}

	public void setCisPaDivisionRowtable(
			List<CisPaDivisionRowtable> cisPaDivisionRowtable) {
		this.cisPaDivisionRowtable = cisPaDivisionRowtable;
	}
	
}
