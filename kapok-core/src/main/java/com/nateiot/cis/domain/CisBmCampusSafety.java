package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateSerializer;


/**
 * 公共安全--校园安全
 * 
 * @author guohuawen
 * 
 */
@Entity
@Table(name = "CIS_BM_CAMPUS_SAFETY")
public class CisBmCampusSafety extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SACAMPUS_ID")
	private Integer sacampusId;
	
	
	/**
	 * 所属机构
	 */
	@Column(name = "ORG_ID")
	private Integer orgId;
	
	
	/**
	 * 学校名称
	 */
	@Column(name = "SCHOOL_NAME")
	private String schoolName;
	
	
	/**
	 * 事故类型
	 */
	@Column(name = "ACCIDENT_TYPE")
	private String accidentType;
	
	
	/**
	 * 学校面积
	 */
	@Column(name = "SCHOOL_AREA")
	private String schoolArea;
	
	
	/**
	 * 学校地址
	 */
	@Column(name = "SCHOOL_ADDRESS")
	private String schoolAddress;
	
	
	/**
	 * 校长
	 */
	@Column(name = "PRESIDENT")
	private String president;
	
	
	/**
	 * 校长联系电话
	 */
	@Column(name = "PRESIDENT_PHONE")
	private String presidentPhone;
	
	
	/**
	 * 保安人数
	 */
	@Column(name = "CUSTODY_NUMBER")
	private Integer custodyNumber;
	
	
	/**
	 * 值班室电话
	 */
	@Column(name = "DURYROOM_PHONE")
	private String duryroomPhone;
	
	
	/**
	 * 日期
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "EDTTIME")
	private Date edttime;
	
	
	/**
	 * 删除标记
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;


	public Integer getSacampusId() {
		return sacampusId;
	}


	public void setSacampusId(Integer sacampusId) {
		this.sacampusId = sacampusId;
	}


	public Integer getOrgId() {
		return orgId;
	}


	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


	public String getSchoolName() {
		return schoolName;
	}


	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}


	public String getAccidentType() {
		return accidentType;
	}


	public void setAccidentType(String accidentType) {
		this.accidentType = accidentType;
	}


	public String getSchoolArea() {
		return schoolArea;
	}


	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}


	public String getSchoolAddress() {
		return schoolAddress;
	}


	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}


	public String getPresident() {
		return president;
	}


	public void setPresident(String president) {
		this.president = president;
	}


	public String getPresidentPhone() {
		return presidentPhone;
	}


	public void setPresidentPhone(String presidentPhone) {
		this.presidentPhone = presidentPhone;
	}


	public Integer getCustodyNumber() {
		return custodyNumber;
	}


	public void setCustodyNumber(Integer custodyNumber) {
		this.custodyNumber = custodyNumber;
	}


	public String getDuryroomPhone() {
		return duryroomPhone;
	}


	public void setDuryroomPhone(String duryroomPhone) {
		this.duryroomPhone = duryroomPhone;
	}


	public Date getEdttime() {
		return edttime;
	}


	public void setEdttime(Date edttime) {
		this.edttime = edttime;
	}


	public String getDelSign() {
		return delSign;
	}


	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}


	

 
}
