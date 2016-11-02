package com.gdgxwl.base.domain;

import javax.persistence.*;

/**
 * @author Will WM. Zhang
 * 
 */
@Entity
@Table(name = "GXWL_SYS_CONFIG")
public class GxwlSysConfig extends BaseEntity {

	private static final long serialVersionUID = -2052406991142813061L;

	/**
	 * 系统配置ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CONFIG_ID", nullable = false)
	private Integer configId;

	/**
	 * 配置类型
	 */
	@Column(name = "CONFIG_TYPE", length = 128)
	private String configType;

	/**
	 * 配置编码
	 */
	@Column(name = "CONFIG_CODE", length = 128)
	private String configCode;

	/**
	 * 配置名称
	 */
	@Column(name = "CONFIG_NAME", length = 200)
	private String configName;

	/**
	 * 配置值
	 */
	@Column(name = "CONFIG_VALUE", length = 128)
	private String configValue;

	/**
	 * 是否有效
	 */
	@Column(name = "ENABLE", length = 1)
	private String enable;

	/**
	 * 备注
	 */
	@Column(name = "REMARK", length = 4000)
	private String remark;

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public String getConfigType() {
		return configType;
	}

	public void setConfigType(String configType) {
		this.configType = configType;
	}

	public String getConfigCode() {
		return configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
