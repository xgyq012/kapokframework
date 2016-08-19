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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateTimeSerializer;
import com.nateiot.core.repository.DBUtil;

@Entity
@Table(name = "CIS_PA_FINAL_DANGRI")
public class CisPaFinalDangri extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 跟进明细
	 */
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "finalDangri")
	private List<CisPaDangriMingxi> dangriMingxiList;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "FINAL_DANGRI_ID")
	private Integer finalDangriId;
	
	/**
	 * 所属机构
	 */
	@Column(name = "MESH_ID")
	private Integer meshId;

	
	/**
	 * 党日计划编号
	 */
	@Column(name = "PLAN_NUMBER")
	private String planNumber;
	
	/**
	 * 党组织id
	 */
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "PARTY_ID")
	private CisBmPartyOrganization partyOrganization;

	
	/**
	 * 年度
	 */
	@Column(name = "YEAR")
	private String year;
	
	/**
	 * 党日计划名称
	 */
	@Column(name = "PLAN_NAME")
	private String planName;
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;
	
	/**
	 * 党日计划制定人
	 */
	@Column(name = "AUTHOR")
	private String author;
	
	/**
	 * 制定时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "ZHIDIN_TIME")
	private Date zhidinTime;
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;

	
	public Integer getFinalDangriId() {
		return finalDangriId;
	}
	public void setFinalDangriId(Integer finalDangriId) {
		this.finalDangriId = finalDangriId;
	}
	public Integer getMeshId() {
		return meshId;
	}
	public void setMeshId(Integer meshId) {
		this.meshId = meshId;
	}
	public String getPlanNumber() {
		return planNumber;
	}
	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getZhidinTime() {
		return zhidinTime;
	}
	public void setZhidinTime(Date zhidinTime) {
		this.zhidinTime = zhidinTime;
	}
	public String getDelSign() {
		return delSign;
	}
	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}
	public CisBmPartyOrganization getPartyOrganization() {
		return partyOrganization;
	}
	public void setPartyOrganization(CisBmPartyOrganization partyOrganization) {
		this.partyOrganization = partyOrganization;
	}
	
	public void setPartyId(Integer partyId){
		if(partyId != null){
			this.partyOrganization = DBUtil.find(CisBmPartyOrganization.class, partyId);
		}
	}
	
	@Transient
	public Integer getPartyId(){
		return this.partyOrganization == null ? null : this.partyOrganization.getOrganizationId();
	}
	
	@Transient
	public String getPartyOrganizationName(){
		return this.partyOrganization == null ? null : this.partyOrganization.getOrganizationName();
	}
	
	public List<CisPaDangriMingxi> getDangriMingxiList() {
		return dangriMingxiList;
	}
	public void setDangriMingxiList(List<CisPaDangriMingxi> dangriMingxiList) {
		this.dangriMingxiList = dangriMingxiList;
	}
	
}
