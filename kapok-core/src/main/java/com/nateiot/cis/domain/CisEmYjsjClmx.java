package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateTimeSerializer;

/**
 * 应急事件处理明细
 * @author xiewenhua
 *
 */
@Entity
@Table(name = "CIS_EM_YJSJ_CLMX")
public class CisEmYjsjClmx extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "YJSJ_CLMX_ID")
	private Integer yjsjClmxId;
	
	/**
	 * 应急事件处理ID
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "YINGJI_SHIJIAN_ID")
	private CisEmYingjiShijian cisEmYingjiShijian;
	
	/**
	 * 描述的标题
	 */
	@Column(name = "TITLE")
	private String title;
	
	/**
	 * 现场跟进情况描述
	 */
	@Column(name = "BODY")
	private String body;
	
	/**
	 * 上传资料的保存路径
	 */
	@Column(name = "FILE_ID")
	private Integer fileId;
	
	/**
	 * 上传资料的保存路径
	 */
	@Column(name = "FILE_NAME")
	private String fileName;
	
	/**
	 * 跟进人id
	 */
	@Column(name = "GENJINREN_ID")
	private Integer genjinrenId;
	
	/**
	 * 跟进人名称
	 */
	@Column(name = "GENJINREN_NAME")
	private String genjinrenName;
	
	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "GENJIN_TIME")
	private Date genjinTime;
	
	public Integer getYjsjClmxId() {
		return yjsjClmxId;
	}
	public void setYjsjClmxId(Integer yjsjClmxId) {
		this.yjsjClmxId = yjsjClmxId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
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
	
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getGenjinrenId() {
		return genjinrenId;
	}
	public void setGenjinrenId(Integer genjinrenId) {
		this.genjinrenId = genjinrenId;
	}
	public String getGenjinrenName() {
		return genjinrenName;
	}
	public void setGenjinrenName(String genjinrenName) {
		this.genjinrenName = genjinrenName;
	}
	public Date getGenjinTime() {
		return genjinTime;
	}
	public void setGenjinTime(Date genjinTime) {
		this.genjinTime = genjinTime;
	}
	
}
