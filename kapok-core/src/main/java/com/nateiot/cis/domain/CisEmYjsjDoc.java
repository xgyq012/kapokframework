package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.nateiot.base.domain.BaseEntity;
@Entity()
@Table(name="CIS_EM_YJSJ_DOC")
public class CisEmYjsjDoc extends BaseEntity{
	private static final long serialVersionUID = 1L;

	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "YJSJ_DOC_ID")
	private Integer yjsjDocId;

	
	/**
	 * 应急事件处理ID
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "YINGJI_SHIJIAN_ID")
	private CisEmYingjiShijian cisEmYingjiShijian;

	
	/**
	 * 附件id
	 */
	@Column(name = "DOC_ID")
	private Integer docId;

	
	/**
	 * 附件名称
	 */
	@Column(name = "DOC_NAME")
	private String docName;

	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;
	
	public Integer getYjsjDocId() {
		return yjsjDocId;
	}

	public void setYjsjDocId(Integer yjsjDocId) {
		this.yjsjDocId = yjsjDocId;
	}

	public Integer getDocId() {
		return docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setYingjiShijianId(Integer yingjiShijianId){
		if(yingjiShijianId != null){
			this.cisEmYingjiShijian = new CisEmYingjiShijian();
			this.cisEmYingjiShijian.setYingjiShijianId(yingjiShijianId);
		}
	}
	
	@Transient
	public Integer getYingjiShijianId(){
		return this.cisEmYingjiShijian == null ? null : this.cisEmYingjiShijian.getYingjiShijianId();
	}
	
	public CisEmYingjiShijian getCisEmYingjiShijian() {
		return cisEmYingjiShijian;
	}
	public void setCisEmYingjiShijian(CisEmYingjiShijian cisEmYingjiShijian) {
		this.cisEmYingjiShijian = cisEmYingjiShijian;
	}
}
