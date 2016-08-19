package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.base.domain.GxwlSysDoc;
import com.nateiot.core.repository.DBUtil;

/**
 * 服务办事 -- 事件登记文件表
 * 
 *  @author Guohw
 */
@Entity
@Table(name = "CIS_SW_EVENT_FILE")
public class CisSwEventFile extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 文件主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FILE_ID")
	private Integer fileId;

//	/**
//	 * 事件登记主键
//	 */
//	@Column(name = "ENROLL_ID")
//	private Integer enrollId;
	
	/**
	 * 文件ID
	 */
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "DOC_ID", referencedColumnName = "DOC_ID")
	private GxwlSysDoc doc;
	
	/**
	 * 事件登记 
	 */
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "ENROLL_ID")
	private CisSwEventEnroll cisSwEventEnroll;
	
	/**
	 * 文件名称
	 */
	@Column(name = "DOC_NAME")
	private String docName;
	
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
	
	/**
	 * 事件登记ID
	 */
	@Transient
	public Integer getEnrollId() {
		return cisSwEventEnroll == null ? null : cisSwEventEnroll.getEnrollId();
	}

	@Transient
	public void setEnrollId(Integer enrollId) {
		if (enrollId != null) {
			this.cisSwEventEnroll = new CisSwEventEnroll();
			this.cisSwEventEnroll.setEnrollId(enrollId);;
		}
//		if (enrollId != null) {
//			this.cisSwEventEnroll = DBUtil.find(CisSwEventEnroll.class,enrollId);
//		}
	}

	/**
	 * 图片名
	 */
	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public GxwlSysDoc getDoc() {
		return doc;
	}

	public void setDoc(GxwlSysDoc doc) {
		this.doc = doc;
	}

	public CisSwEventEnroll getCisSwEventEnroll() {
		return cisSwEventEnroll;
	}

	public void setCisSwEventEnroll(CisSwEventEnroll cisSwEventEnroll) {
		this.cisSwEventEnroll = cisSwEventEnroll;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CisSwEventFile other = (CisSwEventFile) obj;
		if (fileId == null) {
			if (other.fileId != null)
				return false;
		} else if (!fileId.equals(other.fileId))
			return false;
		return true;
	}
	
}
