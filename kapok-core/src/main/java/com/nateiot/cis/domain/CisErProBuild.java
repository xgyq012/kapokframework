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
 * 经济运行 -- 项目建设
 * 
 * @author guohuawen
 * 
 */
@Entity
@Table(name = "CIS_ER_PROBUILD")
public class CisErProBuild extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 项目建设主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PROBUILD_ID")
	private Integer proBuildId;
	
	/**
	 * 项目建设名称
	 */
	@Column(name = "PROBUILD_NAME")
	private String proBuildName;
	
	/**
	 * 项目建设编码
	 */
	@Column(name = "PROBUILD_CODE")
	private String proBuildCode;
	
	/**
	 * 所属机构ID 
	 */
	@Column(name = "UNITS_ID")
	private Integer unitsId;
	
	/**
	 * 建设内容
	 */
	@Column(name = "REMARK")
	private String remark;
	
	/**
	 * 建设性质
	 */
	@Column(name = "PROBUILD_NATURE")
	private String proBuildNature;
	
	/**
	 * 开始日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "START_DATE")
	private Date startDate;
	
	/**
	 * 结束日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "END_DATE")
	private Date endDate;
	
	/**
	 * 总投资(万元)
	 */
	@Column(name = "GI")
	private String gi;
	
	/**
	 * 年计划投资
	 */
	@Column(name = "YEAR_PLAN")
	private String yearPlan;
	
	/**
	 * 删除标记 
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 * 截至目前完成投资
	 */
	@Column(name = "ETF")
	private String etf;
	
	/**
	 * 项目类型
	 */
	@Column(name = "PROBUILD_TYPE")
	private String proBuildType;
	
	/**
	 * 项目进度
	 */
	@Column(name = "PROBUILD_PLAN")
	private String proBuildPlan;
	
	/**
	 * 图片id
	 */
	@Column(name = "PICTURE_ID")
	private Integer pictureId;
	
	/**
	 * 图片名
	 */
	@Column(name = "PICTURE_NAME")
	private String pictureName;
	
	/**
	 * 负责单位
	 */
	@Column(name = "UNIT")
	private String unit;

	public Integer getProBuildId() {
		return proBuildId;
	}

	public void setProBuildId(Integer proBuildId) {
		this.proBuildId = proBuildId;
	}

	public String getProBuildName() {
		return proBuildName;
	}

	public void setProBuildName(String proBuildName) {
		this.proBuildName = proBuildName;
	}

	public String getProBuildCode() {
		return proBuildCode;
	}

	public void setProBuildCode(String proBuildCode) {
		this.proBuildCode = proBuildCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProBuildNature() {
		return proBuildNature;
	}

	public void setProBuildNature(String proBuildNature) {
		this.proBuildNature = proBuildNature;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public String getProBuildType() {
		return proBuildType;
	}

	public void setProBuildType(String proBuildType) {
		this.proBuildType = proBuildType;
	}

	public String getProBuildPlan() {
		return proBuildPlan;
	}

	public void setProBuildPlan(String proBuildPlan) {
		this.proBuildPlan = proBuildPlan;
	}

	public Integer getPictureId() {
		return pictureId;
	}

	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getGi() {
		return gi;
	}

	public void setGi(String gi) {
		this.gi = gi;
	}

	public String getYearPlan() {
		return yearPlan;
	}

	public void setYearPlan(String yearPlan) {
		this.yearPlan = yearPlan;
	}

	public String getEtf() {
		return etf;
	}

	public void setEtf(String etf) {
		this.etf = etf;
	}

	public Integer getUnitsId() {
		return unitsId;
	}

	public void setUnitsId(Integer unitsId) {
		this.unitsId = unitsId;
	}
	
}
