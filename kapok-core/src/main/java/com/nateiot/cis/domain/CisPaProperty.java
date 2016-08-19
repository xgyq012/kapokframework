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
import com.nateiot.base.domain.GxwlSysUser;
import com.nateiot.core.common.json.JsonDateSerializer;
import com.nateiot.core.repository.DBUtil;

@Entity
@Table(name = "CIS_PA_PROPERTY")
public class CisPaProperty extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 跟进明细
	 */
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "property")
	private List<CisPaPropertyDetails> proDetList;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PROPERTY_ID")
	private Integer propertyId;
	
	/**
	 * 所属机构
	 */
	@Column(name = "MESH_ID")
	private Integer meshId;
	
	/**
	 * 编号
	 */
	@Column(name = "CODE")
	private String code;
	
	/**
	 * 党组织id
	 */
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "PARTY_ID")
	private CisBmPartyOrganization partyOrganization;

	/**
	 * 登记人
	 */
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "DENGJIREN_ID")
	private GxwlSysUser dengjiren;
	/**
	 * 负责人名称
	 */
	@Column(name = "FUZEREN")
	private String fuzeren;
	
	/**
	 * 负责人电话
	 */
	@Column(name = "FUZEREN_PHONE")
	private String fuzerenPhone;
	
	/**
	 * 增减说明
	 */
	@Column(name = "ZENGJIAN_REMARK")
	private String zengjianRemark;
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;
	
	/**
	 * 登记时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "DENGJI_DATE")
	private Date dengjiDate;
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	
	public Integer getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}
	public Integer getMeshId() {
		return meshId;
	}
	public void setMeshId(Integer meshId) {
		this.meshId = meshId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	
	
	
	
	
	public GxwlSysUser getDengjiren() {
		return dengjiren;
	}
	public void setDengjiren(GxwlSysUser dengjiren) {
		this.dengjiren = dengjiren;
	}
	
	public void setDengjirenId(Integer dengjirenId){
		if(dengjirenId != null){
			this.dengjiren = DBUtil.find(GxwlSysUser.class, dengjirenId);
		}
	}
	
	@Transient
	public Integer getDengjirenId(){
		return this.dengjiren == null ? null : this.dengjiren.getUserId();
	}
	
	@Transient
	public String getDengjirenName(){
		return this.dengjiren == null ? null : this.dengjiren.getRealname();
	}
	
	public String getFuzeren() {
		return fuzeren;
	}
	public void setFuzeren(String fuzeren) {
		this.fuzeren = fuzeren;
	}
	public String getFuzerenPhone() {
		return fuzerenPhone;
	}
	public void setFuzerenPhone(String fuzerenPhone) {
		this.fuzerenPhone = fuzerenPhone;
	}
	public String getZengjianRemark() {
		return zengjianRemark;
	}
	public void setZengjianRemark(String zengjianRemark) {
		this.zengjianRemark = zengjianRemark;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getDengjiDate() {
		return dengjiDate;
	}
	public void setDengjiDate(Date dengjiDate) {
		this.dengjiDate = dengjiDate;
	}
	public String getDelSign() {
		return delSign;
	}
	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public List<CisPaPropertyDetails> getProDetList() {
		return proDetList;
	}
	public void setProDetList(List<CisPaPropertyDetails> proDetList) {
		this.proDetList = proDetList;
	}
	
	
}
