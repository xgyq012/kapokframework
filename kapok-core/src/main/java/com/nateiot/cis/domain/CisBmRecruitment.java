package com.nateiot.cis.domain;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 单位招聘
 * 
 * @author XIAJIANGJUN
 * 
 */
@Entity
@Table(name = "CIS_BM_RECRUITMENT")
public class CisBmRecruitment extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RECRUITMENT_ID")
	private Integer recruitmentId;


	/**
	 * 所属机构
	 */
	@Column(name = "MESH_ID")
	private Integer meshId;


	/**
	 * 类型
	 */
	@Column(name = "RECRUITMENT_TYPE")
	private String recruitmentType;


	/**
	 * 编号
	 */
	@Column(name = "CODE")
	private String code;


	/**
	 * 招聘单位名称
	 */
	@Column(name = "UNITS_NAME")
	private String unitsName;


	/**
	 * 单位地址
	 */
	@Column(name = "UNITS_ADDR")
	private String unitsAddr;


	/**
	 * 联系电话
	 */
	@Column(name = "LINK_PHONE")
	private String linkPhone;


	/**
	 * 单位简介
	 */
	@Column(name = "UNIT_INTRO")
	private String unitIntro;


	/**
	 * 单位福利
	 */
	@Column(name = "LINK_WELFARE")
	private String linkWelfare;


	/**
	 * 招聘日期
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "HIRING_TIME")
	private Date hiringTime;


	/**
	 * 有效期
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "VALIDITY_DATE")
	private Date validityDate;


	/**
	 * 招工简介
	 */
	@Column(name = "JOB_DESCRIPTION")
	private String jobDescription;



	public Integer getRecruitmentId() {
		return recruitmentId;
	}

	public void setRecruitmentId(Integer recruitmentId) {
		this.recruitmentId = recruitmentId;
	}

	public Integer getMeshId() {
		return meshId;
	}

	public void setMeshId(Integer meshId) {
		this.meshId = meshId;
	}

	public String getRecruitmentType() {
		return recruitmentType;
	}

	public void setRecruitmentType(String recruitmentType) {
		this.recruitmentType = recruitmentType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUnitsName() {
		return unitsName;
	}

	public void setUnitsName(String unitsName) {
		this.unitsName = unitsName;
	}

	public String getUnitsAddr() {
		return unitsAddr;
	}

	public void setUnitsAddr(String unitsAddr) {
		this.unitsAddr = unitsAddr;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getUnitIntro() {
		return unitIntro;
	}

	public void setUnitIntro(String unitIntro) {
		this.unitIntro = unitIntro;
	}

	public String getLinkWelfare() {
		return linkWelfare;
	}

	public void setLinkWelfare(String linkWelfare) {
		this.linkWelfare = linkWelfare;
	}

	public Date getHiringTime() {
		return hiringTime;
	}

	public void setHiringTime(Date hiringTime) {
		this.hiringTime = hiringTime;
	}

	public Date getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
}
