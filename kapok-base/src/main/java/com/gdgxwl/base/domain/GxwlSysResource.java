package com.gdgxwl.base.domain;

import javax.persistence.*;

/**
 * @author Will WM. Zhang
 * 
 */
@Entity
@Table(name = "GXWL_SYS_RESOURCE")
public class GxwlSysResource extends BaseEntity {

	private static final long serialVersionUID = -1169669951017592443L;

	/**
	 * 资源ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RESOURCE_ID")
	private Integer id;

	/**
	 * 资源编码
	 */
	@Column(name = "RESOURCE_CODE", length = 50)
	private String resourceCode;

	/**
	 * 资源名称
	 */
	@Column(name = "RESOURCE_NAME", length = 200)
	private String resourceName;

	/**
	 * 资源标题
	 */
	@Column(name = "RESOURCE_LABEL", length = 200)
	private String resourceLabel;

	/**
	 * 父资源
	 */
	@Column(name = "PARENT_RESOURCE_ID")
	private Integer parentResourceId;

	/**
	 * 资源类型
	 */
	@Column(name = "RESOURCE_TYPE", length = 128)
	private String resourceType; // menu:菜单 button:按钮

	/**
	 * 显示顺序
	 */
	@Column(name = "SEQ")
	private Integer seq;

	/**
	 * URL
	 */
	@Column(name = "URL", length = 200)
	private String url;

	/**
	 * 图片路径
	 */
	@Column(name = "IMAGE_PATH", length = 200)
	private String imagePath;

	/**
	 * 打开方式
	 */
	@Column(name = "OPEN_MODE", length = 128)
	private String openMode;

	/**
	 * 全路径
	 */
	@Column(name = "FULLPATH", length = 200)
	private String fullpath;
	
	/**
	 * 是否分支
	 */
	@Column(name = "IS_BRANCH", nullable = false, length = 1)
	private String isBranch;

	/**
	 * 是否有效
	 */
	@Column(name = "ENABLE", length = 1)
	private String enable;
	
	/**
	 * 系统类型
	 */
	@Column(name = "SYSTEM_TYPE", length = 128)
	private String systemType;
	
	/**
	 * 资源说明
	 */
	@Column(name = "RESOURCE_DESC")
	private String resourceDesc;
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;

	@Transient
	public String getState() {
		return "Y".equalsIgnoreCase(getIsBranch()) ? "closed" : "open";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceLabel() {
		return resourceLabel;
	}

	public void setResourceLabel(String resourceLabel) {
		this.resourceLabel = resourceLabel;
	}

	public Integer getParentResourceId() {
		return parentResourceId;
	}

	public void setParentResourceId(Integer parentResourceId) {
		this.parentResourceId = parentResourceId;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getOpenMode() {
		return openMode;
	}

	public void setOpenMode(String openMode) {
		this.openMode = openMode;
	}

	public String getFullpath() {
		return fullpath;
	}

	public void setFullpath(String fullpath) {
		this.fullpath = fullpath;
	}
	
	public String getIsBranch() {
		return isBranch;
	}
	
	public void setIsBranch(String isBranch) {
		this.isBranch = isBranch;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public String getResourceDesc() {
		return resourceDesc;
	}

	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
