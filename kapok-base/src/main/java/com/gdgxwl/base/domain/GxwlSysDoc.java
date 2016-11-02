package com.gdgxwl.base.domain;

import com.gdgxwl.base.common.BaseUtil;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Will WM. Zhang
 * 
 */
@Entity
@Table(name = "GXWL_SYS_DOC")
public class GxwlSysDoc implements Serializable {

	private static final long serialVersionUID = 772473279115008521L;

	/**
	 * 文档ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DOC_ID", nullable = false)
	private Integer docId;

	/**
	 * 文档名称
	 */
	@Column(name = "DOC_SHOWNAME", nullable = false, length = 200)
	private String docShowname;

	/**
	 * 文档全名称
	 */
	@Column(name = "DOC_FULLNAME", nullable = false, length = 200)
	private String docFullname;

	/**
	 * 文档扩展名
	 */
	@Column(name = "DOC_EXTENSION", length = 50)
	private String docExtension;

	/**
	 * 文档URI地址
	 */
	@Column(name = "DOC_URI", nullable = false, length = 4000)
	private String docUri;

	/**
	 * 是否临时文件
	 */
	@Column(name = "IS_TEMP", nullable = false, length = 1)
	private String isTemp;

	/**
	 * 文档类容类型
	 */
	@Column(name = "DOC_CONTENT_TYPE")
	private String docContentType;

	/**
	 * 备注
	 */
	@Column(name = "REMARK", length = 4000)
	private String remark;

	/**
	 * 创建人ID
	 */
	@Column(name = "CREATE_BY", nullable = false)
	private Integer createBy;

	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", nullable = false)
	private Date createTime;

	/**
	 * 最后修改人ID
	 */
	@Column(name = "LAST_UPDATE_BY", nullable = false)
	private Integer lastUpdateBy;

	/**
	 * 最后修改时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATE_TIME", nullable = false)
	private Date lastUpdateTime;

	@Transient
	public File getDocFile() {
		return new File(BaseUtil.getSysRootPath() + this.getDocUri());
	}

	@Transient
	public Long getDocSize() {
		return this.getDocFile().length();
	}

	public Integer getDocId() {
		return docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public String getDocShowname() {
		return docShowname;
	}

	public void setDocShowname(String docShowname) {
		this.docShowname = docShowname;
	}

	public String getDocFullname() {
		return docFullname;
	}

	public void setDocFullname(String docFullname) {
		this.docFullname = docFullname;
	}

	public String getDocExtension() {
		return docExtension;
	}

	public void setDocExtension(String docExtension) {
		this.docExtension = docExtension;
	}

	public String getDocUri() {
		return docUri;
	}

	public void setDocUri(String docUri) {
		this.docUri = docUri;
	}

	public String getIsTemp() {
		return isTemp;
	}

	public void setIsTemp(String isTemp) {
		this.isTemp = isTemp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(Integer lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getDocContentType() {
		return docContentType;
	}

	public void setDocContentType(String docContentType) {
		this.docContentType = docContentType;
	}

}
