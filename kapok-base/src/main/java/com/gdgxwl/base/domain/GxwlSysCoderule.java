package com.gdgxwl.base.domain;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Will WM. Zhang
 * 
 */
@Entity
@Table(name = "GXWL_SYS_CODERULE")
public class GxwlSysCoderule extends BaseEntity {

	private static final long serialVersionUID = 2815764244751463983L;

	/**
	 * 编号ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CODERULE_ID")
	private Integer coderuleId;

	/**
	 * 编号编码
	 */
	@Column(name = "CODERULE_CODE", length = 50, nullable = false)
	private String coderuleCode;

	/**
	 * 编号名称
	 */
	@Column(name = "CODERULE_NAME", length = 200)
	private String coderuleName;

	/**
	 * 编号前缀
	 */
	@Column(name = "PREFIX", length = 50)
	private String prefix;

	/**
	 * 编号后缀
	 */
	@Column(name = "SUFFIX", length = 50)
	private String suffix;

	/**
	 * 是否随机串
	 */
	@Column(name = "IS_RANDOM", length = 1)
	private String isRandom;

	/**
	 * 随机串长度
	 */
	@Column(name = "RANDOM_NUMBER", length = 11)
	private Integer randomNumber;

	/**
	 * 循环周期
	 */
	@Column(name = "CYC_TYPE", length = 128)
	private String cycType;

	/**
	 * 序号格式
	 */
	@Column(name = "CYC_FORMAT", length = 128)
	private String cycFormat;

	/**
	 * 流水号长度
	 */
	@Column(name = "SERIAL_NUMBER", length = 11)
	private Integer serialNumber;

	/**
	 * 流水号最小值
	 */
	@Column(name = "MIN_VALUE", length = 11)
	private Integer minValue;

	/**
	 * 流水号最大值
	 */
	@Column(name = "MAX_VALUE", length = 11)
	private Integer maxValue;

	/**
	 * 当前流水值
	 */
	@Column(name = "CURRENT_VALUE", length = 11)
	private Integer currentValue;

	/**
	 * 流水号增量
	 */
	@Column(name = "SERIAL_STEP", length = 11)
	private Integer serialStep;

	/**
	 * 生效日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "VALID_DATE")
	private Date validDate;

	/**
	 * 失效日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "INVALID_DATE")
	private Date invalidDate;

	/**
	 * 备注
	 */
	@Column(name = "REMARK", length = 4000)
	private String remark;

	public Integer getCoderuleId() {
		return coderuleId;
	}

	public void setCoderuleId(Integer coderuleId) {
		this.coderuleId = coderuleId;
	}

	public String getCoderuleCode() {
		return coderuleCode;
	}

	public void setCoderuleCode(String coderuleCode) {
		this.coderuleCode = coderuleCode;
	}

	public String getCoderuleName() {
		return coderuleName;
	}

	public void setCoderuleName(String coderuleName) {
		this.coderuleName = coderuleName;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getIsRandom() {
		return isRandom;
	}

	public void setIsRandom(String isRandom) {
		this.isRandom = isRandom;
	}

	public Integer getRandomNumber() {
		return randomNumber;
	}

	public void setRandomNumber(Integer randomNumber) {
		this.randomNumber = randomNumber;
	}

	public String getCycType() {
		return cycType;
	}

	public void setCycType(String cycType) {
		this.cycType = cycType;
	}

	public String getCycFormat() {
		return cycFormat;
	}

	public void setCycFormat(String cycFormat) {
		this.cycFormat = cycFormat;
	}

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Integer getMinValue() {
		return minValue;
	}

	public void setMinValue(Integer minValue) {
		this.minValue = minValue;
	}

	public Integer getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}

	public Integer getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(Integer currentValue) {
		this.currentValue = currentValue;
	}

	public Integer getSerialStep() {
		return serialStep;
	}

	public void setSerialStep(Integer serialStep) {
		this.serialStep = serialStep;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
		
}
