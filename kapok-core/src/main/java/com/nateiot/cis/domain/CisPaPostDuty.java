package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.base.domain.GxwlSysDoc;
import com.nateiot.core.common.json.JsonDateSerializer;
import com.nateiot.core.repository.DBUtil;

/**
 * 党务建设 -- 党员设岗定责登记表
 * 
 * @author Guohw
 */
@Entity
@Table(name = "CIS_PA_POST_DUTY")
public class CisPaPostDuty extends BaseEntity{

private static final long serialVersionUID = 1L;
	
	/**
	 * 主键ID 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DUTY_ID ", length = 10, nullable = false)
	private Integer dutyId;
	
	/**
	 * 公开日期(始)
	 */
//	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@JsonSerialize(using = JsonDateSerializer.class)
//	@Column(name = "BEGIN_DATE")
//	private Date beginDate;
	
	/**
	 *  所属网格
	 */
	@Column(name = "UNITS_ID")
	private Integer unitsId;
	
	/**
	 *  个人特长
	 */
	@Column(name = "SPECIALITY")
	private String speciality;
	
	/**
	 *  岗位名称
	 */
	@Column(name = "POST_NAME")
	private String postName;
	
	/**
	 *  工作职责
	 */
	@Column(name = "WORK_DUTY")
	private String workDuty;
	
	/**
	 *  删除标记
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 *  履行职责情况
	 */
	@Column(name = "SITUATION")
	private String situation;
	
	/**
	 *  群众评议意见
	 */
	@Column(name = "GRASSROOTS")
	private String grassroots;
	
	/**
	 *  支部考核意见
	 */
	@Column(name = "BRANCH")
	private String branch;
	
	/**
	 *  备注
	 */
	@Column(name = "REMARK")
	private String remark;
	
	/**
	 *  党员
	 */
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "MEMBER_ID", referencedColumnName = "HOUSEHOLDER_ID")
	private CisBmHouseholder houseHolder;

	@Transient
	public void setMemberId(Integer memberId){
		if (memberId != null) {
			this.houseHolder = DBUtil.find(CisBmHouseholder.class, memberId);
		}
	}
	
	@Transient
	public Integer getMemberId(){
		return this.houseHolder == null ? null : this.houseHolder.getHouseholderId();
	}
	
	@Transient
	public String getName(){
		return this.houseHolder == null ? null : this.houseHolder.getHouseholderName();
	}
	
	@Transient
	public String getSex(){
		return this.houseHolder == null ? null : this.houseHolder.getSex();
	}
	
	@Transient
	public String getEduLevel(){
		return this.houseHolder == null ? null : this.houseHolder.getEduLevel();
	}
	
	@Transient
	public String getDutyName(){
		return this.houseHolder == null ? null : this.houseHolder.getDutyName();
	}
	
	@Transient
	public Integer getAge(){
		return this.houseHolder == null ? null : this.houseHolder.getAge();
	}
	
	public CisBmHouseholder getHouseHolder() {
		return houseHolder;
	}

	public void setHouseHolder(CisBmHouseholder houseHolder) {
		this.houseHolder = houseHolder;
	}

	public Integer getDutyId() {
		return dutyId;
	}

	public void setDutyId(Integer dutyId) {
		this.dutyId = dutyId;
	}

	public Integer getUnitsId() {
		return unitsId;
	}

	public void setUnitsId(Integer unitsId) {
		this.unitsId = unitsId;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getWorkDuty() {
		return workDuty;
	}

	public void setWorkDuty(String workDuty) {
		this.workDuty = workDuty;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public String getGrassroots() {
		return grassroots;
	}

	public void setGrassroots(String grassroots) {
		this.grassroots = grassroots;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
