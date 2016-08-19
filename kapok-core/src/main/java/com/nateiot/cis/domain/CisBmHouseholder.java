package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.CascadeType;
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
import com.nateiot.core.common.json.JsonDateSerializer;
import com.nateiot.core.repository.DBUtil;

/**
 * 人員基本信息
 * @author xiaguangjun
 *
 */
@Entity
@Table(name = "cis_bm_householder")
public class CisBmHouseholder extends BaseEntity{

	/**
	 * ID
	 */
	@ Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "HOUSEHOLDER_ID")
	private Integer householderId;

	/**
	 * 姓名
	 */
	@Column(name = "HOUSEHOLDER_NAME")
	private String householderName;
	
	/**
	 * 年龄
	 */
	@Column(name = "AGE")
	private Integer age;
	
	/**
	 * 楼栋号
	 */
	@Column(name = "BUILD_ID")
	private Integer buildId;
	
	
	/**
	 * 房屋号
	 */
/*	@Column(name = "HOUSE_ID")
	private Integer houseId;*/
	
	
	/**
	 * 小区号
	 */
	@Column(name = "COM_ID")
	private Integer comId;
	
	
	/**
	 * 身份证号码
	 */
	@Column(name = "CARD_CODE")
	private String cardCode;

	/**
	 * 性别
	 */
	@Column(name = "SEX")
	private String sex;

	/**
	 * 出生日期
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "BIRTH_DATE")
	private Date birthDate;
	

	/**
	 * 民族
	 */
	@Column(name = "NATIONALITY")
	private String nationality;

	/**
	 * 所属机构
	 */
	@Column(name = "ORG_ID")
	private Integer orgId;

	/**
	 * 文化程度
	 */
	@Column(name = "EDU_LEVEL")
	private String eduLevel;

	/**
	 * 管理类型
	 */
	@Column(name = "GLLX")
	private String gllx;

	/**
	 * 婚姻状况
	 */
	@Column(name = "MARITAL_STATUS")
	private String maritalStatus;

	/**
	 * 结婚时间
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "MARITAL_DATA")
	private Date maritalData;

	/**
	 * 健康状况
	 */
	@Column(name = "HEALTH_STATUS")
	private String healthStatus;

	/**
	 * 联系电话
	 */
	@Column(name = "CALL_PHONE")
	private String callPhone;

	/**
	 * 职业
	 */
	@Column(name = "JOB_NAME")
	private String jobName;

	/**
	 * 宗教信仰
	 */
	@Column(name = "RELIGION")
	private String religion;

	/**
	 * 节育措施
	 */
	@Column(name = "ZJXY")
	private String zjxy;

	/**
	 * 社会职务
	 */
	@Column(name = "SOCIAL_JOB")
	private String socialJob;

	/**
	 * 工作单位
	 */
	@Column(name = "UNIT")
	private String unit;

	/**
	 * 职务
	 */
	@Column(name = "DUTY_NAME")
	private String dutyName;

	/**
	 * 特长
	 */
	@Column(name = "SPECIALTY")
	private String specialty;

	/**
	 * 血型
	 */
	@Column(name = "BLOOD_TYPE")
	private String bloodType;

	/**
	 * 兵役状况
	 */
	@Column(name = "VETERAN_STATUS")
	private String veteranStatus;

	/**
	 * 户籍类别
	 */
	@Column(name = "HOUSEHOLD_TYPE")
	private String householdType;

	/**
	 * 户籍地
	 */
	@Column(name = "HOUSEHOLD_ADDRESS")
	private String householdAddress;

	/**
	 * 现居地
	 */
	@Column(name = "DWELL_ADDRESS")
	private String dwellAddress;

	/**
	 * 身高
	 */
	@Column(name = "SG_HEIGHT")
	private String sgHeight;

	/**
	 * 住房面积
	 */
	@Column(name = "HOUSING_AREA")
	private String housingArea;

	/**
	 * 门牌号
	 */
	@Column(name = "DOORPLATE")
	private String doorplate;

	/**
	 * 是否志愿者
	 */
	@Column(name = "IS_VOLUNTEER")
	private String isVolunteer;

	/**
	 * 政治面貌
	 */
	@Column(name = "POLITICS_STATUS")
	private String politicsStatus;

	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;

	/**
	 * 是否有住房
	 */
	@Column(name = "ISHAVE_HOUSE")
	private String ishaveHouse;

	/**
	 * 与户主关系
	 */
	@Column(name = "HOUSEHOLDER_RELATION")
	private String householderRelation;

	
	/**
	 * 照片ID
	 */
	@Column(name = "PHOTOFILE_ID")
	private Integer photofileID;
	
	
	@Column(name = "DOC_SHOWNAME")
	private String  docShowname;
	
	/**
	 * 籍贯
	 */
	@Column(name = "NATIVE_PLACE")
	private String nativePlace;
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;


	@Transient
	private String familytree;

	@Transient
	private Integer hzId;

	@Transient
	private String hzName;


	@JsonIgnore
	@OneToOne
	@JoinColumn(name="HOUSE_ID",nullable=true,unique=false)
	private CisBmHouseMsg cisBmHouseMsg;
	
	@Transient
	public String getCommunityName(){
		
		return this.cisBmHouseMsg ==null ? "" : getCisBmHouseMsg().getCommunityName();
	}
	
	@Transient
	public String getLdCode(){
			
		return this.cisBmHouseMsg ==null ? "" : getCisBmHouseMsg().getLdCode();
	}
	
	@Transient
	public String getDyCode(){
		
		return this.cisBmHouseMsg ==null ? "" : getCisBmHouseMsg().getDyCode();
	}
	
	
	@Transient
	public Integer getHouseId() {
		return this.cisBmHouseMsg ==null ? null : getCisBmHouseMsg().getHouseId();
	}

	public void setHouseId(Integer houseId) {
		if(houseId!=null){
			this.setCisBmHouseMsg(DBUtil.find(CisBmHouseMsg.class, houseId));
		}
	}

	public Integer getBuildId() {
		return buildId;
	}

	public void setBuildId(Integer buildId) {
		this.buildId = buildId;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public CisBmHouseMsg getCisBmHouseMsg() {
		return cisBmHouseMsg;
	}

	public void setCisBmHouseMsg(CisBmHouseMsg cisBmHouseMsg) {
		this.cisBmHouseMsg = cisBmHouseMsg;
	}

	public Integer getHouseholderId() {
		return householderId;
	}

	public void setHouseholderId(Integer householderId) {
		this.householderId = householderId;
	}

	public String getHouseholderName() {
		return householderName;
	}

	public void setHouseholderName(String householderName) {
		this.householderName = householderName;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getEduLevel() {
		return eduLevel;
	}

	public void setEduLevel(String eduLevel) {
		this.eduLevel = eduLevel;
	}

	public String getGllx() {
		return gllx;
	}

	public void setGllx(String gllx) {
		this.gllx = gllx;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Date getMaritalData() {
		return maritalData;
	}

	public void setMaritalData(Date maritalData) {
		this.maritalData = maritalData;
	}

	public String getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}

	public String getCallPhone() {
		return callPhone;
	}

	public void setCallPhone(String callPhone) {
		this.callPhone = callPhone;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getZjxy() {
		return zjxy;
	}

	public void setZjxy(String zjxy) {
		this.zjxy = zjxy;
	}

	public String getSocialJob() {
		return socialJob;
	}

	public void setSocialJob(String socialJob) {
		this.socialJob = socialJob;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getVeteranStatus() {
		return veteranStatus;
	}

	public void setVeteranStatus(String veteranStatus) {
		this.veteranStatus = veteranStatus;
	}

	public String getHouseholdType() {
		return householdType;
	}

	public void setHouseholdType(String householdType) {
		this.householdType = householdType;
	}

	public String getHouseholdAddress() {
		return householdAddress;
	}

	public void setHouseholdAddress(String householdAddress) {
		this.householdAddress = householdAddress;
	}

	public String getDwellAddress() {
		return dwellAddress;
	}

	public void setDwellAddress(String dwellAddress) {
		this.dwellAddress = dwellAddress;
	}

	public String getSgHeight() {
		return sgHeight;
	}

	public void setSgHeight(String sgHeight) {
		this.sgHeight = sgHeight;
	}

	public String getHousingArea() {
		return housingArea;
	}

	public void setHousingArea(String housingArea) {
		this.housingArea = housingArea;
	}

	public String getDoorplate() {
		return doorplate;
	}

	public void setDoorplate(String doorplate) {
		this.doorplate = doorplate;
	}

	public String getIsVolunteer() {
		return isVolunteer;
	}

	public void setIsVolunteer(String isVolunteer) {
		this.isVolunteer = isVolunteer;
	}

	public String getPoliticsStatus() {
		return politicsStatus;
	}

	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIshaveHouse() {
		return ishaveHouse;
	}

	public void setIshaveHouse(String ishaveHouse) {
		this.ishaveHouse = ishaveHouse;
	}

	public String getHouseholderRelation() {
		return householderRelation;
	}

	public void setHouseholderRelation(String householderRelation) {
		this.householderRelation = householderRelation;
	}


	public Integer getPhotofileID() {
		return photofileID;
	}

	public void setPhotofileID(Integer photofileID) {
		this.photofileID = photofileID;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public String getDocShowname() {
		return docShowname;
	}

	public void setDocShowname(String docShowname) {
		this.docShowname = docShowname;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getFamilytree() {
		return familytree;
	}

	public void setFamilytree(String familytree) {
		this.familytree = familytree;
	}

	public Integer getHzId() {
		return hzId;
	}

	public void setHzId(Integer hzId) {
		this.hzId = hzId;
	}

	public String getHzName() {
		return hzName;
	}

	public void setHzName(String hzName) {
		this.hzName = hzName;
	}
}
