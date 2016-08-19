package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.nateiot.base.domain.BaseEntity;


/**
 * 党务建设 -- 两委分工行表
 * 
 *  @author Guohw
 */
@Entity
@Table(name = "CIS_PA_DIVISION_ROWTABLE")
public class CisPaDivisionRowtable extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 两委分工行表主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TABLE_ID")
	private Integer tableId;
	
//	/**
//	 * 两委分工主键 
//	 */
//	@Column(name = "LABOR_ID")
//	private Integer laborId;
	
	/**
	 *  职务
	 */
	@Column(name = "JOB")
	private String job;
	
	/**
	 *  分管工作
	 */
	@Column(name = "DIVISION_WORK")
	private String divisionWork;
	
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
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "LABOR_ID")
	private CisPaDivisionLabor cisPaDivisionLabor;
	
	@Transient
	public void setLaborId(Integer laborId){
		if(laborId != null){
			this.cisPaDivisionLabor = new CisPaDivisionLabor();
			this.cisPaDivisionLabor.setLaborId(laborId);
		}
	}
	
	@Transient
	public Integer getLaborId(Integer laborId){
		return this.cisPaDivisionLabor == null ? null : this.cisPaDivisionLabor.getLaborId();
	}

	public Integer getTableId() {
		return tableId;
	}

	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getDivisionWork() {
		return divisionWork;
	}

	public void setDivisionWork(String divisionWork) {
		this.divisionWork = divisionWork;
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

	public CisPaDivisionLabor getCisPaDivisionLabor() {
		return cisPaDivisionLabor;
	}

	public void setCisPaDivisionLabor(CisPaDivisionLabor cisPaDivisionLabor) {
		this.cisPaDivisionLabor = cisPaDivisionLabor;
	}
	
}
