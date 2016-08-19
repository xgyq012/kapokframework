package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.base.domain.GxwlSysDoc;
import com.nateiot.core.repository.DBUtil;

/**
 * 党务建设
 * 
 * @author  Guohw
 * 
 */

@Entity
@Table(name = "CIS_PA_PARTY_REGIME")
public class CisPaPartyRegime extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键ID 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "REGIME_ID", length = 10, nullable = false)
	private Integer regimeId;
	
	/**
	 *  编码 
	 */
	@Column(name = "REGIME_CODE")
	private String regimeCode;
	
	/**
	 *  标题
	 */
	@Column(name = "REGIME_TITLE")
	private String regimeTitle;

	/**
	 *  内容
	 */
	@Column(name = "REMARK")
	private String remark;

	/**
	 *  类型
	 */
	@Column(name = "REGIME_TYPE")
	private String regimeType;

	/**
	 *  是否有效
	 */
	@Column(name = "IS_ENABLE")
	private String isEnable;

	/**
	 *  删除标记
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 * 文件ID
	 */
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "DOC_ID", referencedColumnName = "DOC_ID")
	private GxwlSysDoc doc;
	
//	/**
//	 * 文件名称
//	 */
//	@Column(name = "DOC_NAME")
//	private String docName;
	
	/**
	 * 文件ID
	 * @return
	 */
	@Transient
	public Integer getDocId() {
		return doc == null ? null : doc.getDocId();
	}

	@Transient
	public void setDocId(Integer docId) {
		if (docId != null) {
			this.doc = DBUtil.find(GxwlSysDoc.class, docId);
		}
	}
	
	@Transient
	public String getDocName() {
		return this.doc == null ? null : this.doc.getDocShowname();
	}

	public Integer getRegimeId() {
		return regimeId;
	}

	public void setRegimeId(Integer regimeId) {
		this.regimeId = regimeId;
	}

	public String getRegimeCode() {
		return regimeCode;
	}

	public void setRegimeCode(String regimeCode) {
		this.regimeCode = regimeCode;
	}

	public String getRegimeTitle() {
		return regimeTitle;
	}

	public void setRegimeTitle(String regimeTitle) {
		this.regimeTitle = regimeTitle;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRegimeType() {
		return regimeType;
	}

	public void setRegimeType(String regimeType) {
		this.regimeType = regimeType;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public GxwlSysDoc getDoc() {
		return doc;
	}

	public void setDoc(GxwlSysDoc doc) {
		this.doc = doc;
	}

//	public void setDocName(String docName) {
//		this.docName = docName;
//	}

}
