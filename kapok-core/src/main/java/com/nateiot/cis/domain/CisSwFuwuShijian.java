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
 * 服务事件表
 * @author xiewenhua
 *
 */
@Entity
@Table(name = "CIS_SW_FUWU_SHIJIAN")
public class CisSwFuwuShijian extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "FUWU_SHIJIAN_ID")
	private Integer fuwuShijianId;
	
	/**
	 * 服务事件名称
	 */
	@Column(name = "NAME")
	private String name;
	
	/**
	 * 事件来源渠道
	 */
	@Column(name = "QUDAO")
	private Integer qudao;
	
	/**
	 * 事件发生地的经纬度值
	 */
	@Column(name = "XY_VALUE")
	private String xyValue;
	
	/**
	 * 事件的发生地
	 */
	@Column(name = "ADDRESS")
	private String address;
	
	/**
	 * 关于事件的描述
	 */
	@Column(name = "SHIJIAN_PS")
	private String shijianPs;
	
	/**
	 * 上传关于此次事件的文件资料
	 */
	@Column(name = "FILE_URL")
	private String fileUrl;
	
	/**
	 * 办结期限
	 */
//	@Temporal(TemporalType.TIMESTAMP)
//	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
//	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "LAST_TIME")
	private Date lastTime;
	
	/**
	 * 办结语
	 */
	@Column(name = "BANJIE_PS")
	private String banjiePs;
	
	/**
	 * 事件所处状态，有草稿、待处理、已处理、已办结四种，分别用数字0、1、2、9表示
	 */
	@Column(name = "SHIJIAN_STATUS")
	private Integer shijianStatus;
	
	/**
	 * 关于事件反馈人的信息
	 */
	@Column(name = "FANKUIREN")
	private String fankuiren;
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	public Integer getFuwuShijianId() {
		return fuwuShijianId;
	}
	public void setFuwuShijianId(Integer fuwuShijianId) {
		this.fuwuShijianId = fuwuShijianId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getQudao() {
		return qudao;
	}
	public void setQudao(Integer qudao) {
		this.qudao = qudao;
	}
	public String getXyValue() {
		return xyValue;
	}
	public void setXyValue(String xyValue) {
		this.xyValue = xyValue;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getShijianPs() {
		return shijianPs;
	}
	public void setShijianPs(String shijianPs) {
		this.shijianPs = shijianPs;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public String getBanjiePs() {
		return banjiePs;
	}
	public void setBanjiePs(String banjiePs) {
		this.banjiePs = banjiePs;
	}
	public Integer getShijianStatus() {
		return shijianStatus;
	}
	public void setShijianStatus(Integer shijianStatus) {
		this.shijianStatus = shijianStatus;
	}
	public String getFankuiren() {
		return fankuiren;
	}
	public void setFankuiren(String fankuiren) {
		this.fankuiren = fankuiren;
	}
	public String getDelSign() {
		return delSign;
	}
	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

}
