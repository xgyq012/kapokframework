package com.gdgxwl.base.domain;

import com.gdgxwl.base.common.Constant;

import javax.persistence.*;

/**
 * @author Will WM. Zhang
 *
 */
@Entity
@Table(name = "GXWL_SYS_CLIENT_VERSION")
public class GxwlSysClientVersion extends BaseEntity {

	private static final long serialVersionUID = -8720278265000932054L;

	/**
	 * 主键ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "VERSION_ID", nullable = false)
	private Integer versionId;
	
	/**
	 * 文件号
	 */
	@Column(name = "FILE_NUMBER", length = 200)
	private String fileNumber;
	
	/**
	 * 客户端类型
	 */
	@Column(name = "CLIENT_TYPE", length = 128)
	private String clientType;

	/**
	 * 更新说明
	 */
	@Column(name = "VERSION_DESC", length = 65535)
	private String versionDesc;
	
	/**
	 * 文件位置
	 */
	@Column(name = "CLIENT_URL", length = 200)
	private String clientUrl;
	
	/**
	 * 文件名称
	 */
	@Column(name = "FILE_NAME", length = 200)
	private String fileName;
	
	/**
	 * 版本号
	 */
	@Column(name = "VERSION_NUMBER", length = 50)
	private String versionNumber;
	
	/**
	 * 是否当前版本
	 */
	@Column(name = "IS_LAST", length = 1)
	private String isLast;
	
	@Column(name = "REMARK", length = 4000)
	private String remark;

	@Transient
	public String getIsLastName() {
		return Constant.YES_OR_NO_Y.equals(this.isLast) ? "是" : "";
	}
	
	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public String getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getVersionDesc() {
		return versionDesc;
	}

	public void setVersionDesc(String versionDesc) {
		this.versionDesc = versionDesc;
	}

	public String getClientUrl() {
		return clientUrl;
	}

	public void setClientUrl(String clientUrl) {
		this.clientUrl = clientUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getIsLast() {
		return isLast;
	}

	public void setIsLast(String isLast) {
		this.isLast = isLast;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
