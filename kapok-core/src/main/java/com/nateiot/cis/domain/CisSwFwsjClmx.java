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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateSerializer;
import com.nateiot.core.common.json.JsonDateTimeSerializer;

/**
 * 服务事件处理明细
 * @author xiewenhua
 *
 */
@Entity
@Table(name = "CIS_SW_FWSJ_CLMX")
public class CisSwFwsjClmx extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "FWSJ_CLMX_ID")
	private Integer fwsjClmxId;
	
	/**
	 * 服务事件ID
	 */
	@Column(name = "FUWU_SHIJIAN_ID")
	private Integer fuwuShijianId;
	
	/**
	 * 处理方式
	 */
	@Column(name = "CHULI_FANGSHI")
	private Integer chuliFangshi;
	
	/**
	 * 签收状态
	 */
	@Column(name = "QIANSHOU_STATUS")
	private Integer qianshouStatus;
	
	/**
	 * 处理意见
	 */
	@Column(name = "CHULI_YIJIAN")
	private String chuliYijian;
	
	/**
	 * 上传相关文件保存路径
	 */
	@Column(name = "FILE_URL")
	private String fileUrl;
	
	/**
	 * 处理人id 
	 */
	@Column(name = "CHULIREN_ID")
	private String chulirenId;
	
	/**
	 * 处理人名字
	 */
	@Column(name = "CHULIREN_NAME")
	private String chulirenName;
	
	/**
	 * 处理时间
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "CHULI_TIME")
	private Date chuliTime;
	
	public Integer getFwsjClmxId() {
		return fwsjClmxId;
	}
	public void setFwsjClmxId(Integer fwsjClmxId) {
		this.fwsjClmxId = fwsjClmxId;
	}
	public Integer getFuwuShijianId() {
		return fuwuShijianId;
	}
	public void setFuwuShijianId(Integer fuwuShijianId) {
		this.fuwuShijianId = fuwuShijianId;
	}
	
	public Integer getChuliFangshi() {
		return chuliFangshi;
	}
	public void setChuliFangshi(Integer chuliFangshi) {
		this.chuliFangshi = chuliFangshi;
	}
	public Integer getQianshouStatus() {
		return qianshouStatus;
	}
	public void setQianshouStatus(Integer qianshouStatus) {
		this.qianshouStatus = qianshouStatus;
	}
	public String getChuliYijian() {
		return chuliYijian;
	}
	public void setChuliYijian(String chuliYijian) {
		this.chuliYijian = chuliYijian;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public Date getChuliTime() {
		return chuliTime;
	}
	public void setChuliTime(Date chuliTime) {
		this.chuliTime = chuliTime;
	}
	public String getChulirenName() {
		return chulirenName;
	}
	public void setChulirenName(String chulirenName) {
		this.chulirenName = chulirenName;
	}
	public String getChulirenId() {
		return chulirenId;
	}
	public void setChulirenId(String chulirenId) {
		this.chulirenId = chulirenId;
	}
	
}
