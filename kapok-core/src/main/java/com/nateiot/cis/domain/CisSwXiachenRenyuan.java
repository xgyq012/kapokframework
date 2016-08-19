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
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateTimeSerializer;

/**
 * 下沉人员信息
 * @author xiewenhua
 *
 */
@Entity
@Table(name = "CIS_SW_XIACHEN_RENYUAN")
public class CisSwXiachenRenyuan extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "XIACHEN_RENYUAN_ID")
	private Integer xiachenRenyuanId;
	
	/**
	 * 队员ID
	 */
	@Column(name = "DUIYUAN_ID")
	private Integer duiyuanId;
	
	/**
	 * 原所属组织
	 */
	@Column(name = "YUAN_ZUZHI")
	private String yuanZuzhi;
	
	/**
	 * 原职务
	 */
	@Column(name = "YUAN_JOB")
	private String yuanJob;
	
	/**
	 * 下沉后所属组织
	 */
	@Column(name = "ZUZHI")
	private String zuzhi;
	
	/**
	 * 下沉后职务
	 */
	@Column(name = "JOB")
	private String job;
	
	/**
	 * 下沉时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "XIACHEN_TIME")
	private Date xiachenTime;
	
	/**
	 * 制定人
	 */
	@Column(name = "AUTHOR")
	private Integer author;
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	public Integer getXiachenRenyuanId() {
		return xiachenRenyuanId;
	}
	public void setXiachenRenyuanId(Integer xiachenRenyuanId) {
		this.xiachenRenyuanId = xiachenRenyuanId;
	}
	public Integer getDuiyuanId() {
		return duiyuanId;
	}
	public void setDuiyuanId(Integer duiyuanId) {
		this.duiyuanId = duiyuanId;
	}
	public String getYuanZuzhi() {
		return yuanZuzhi;
	}
	public void setYuanZuzhi(String yuanZuzhi) {
		this.yuanZuzhi = yuanZuzhi;
	}
	public String getYuanJob() {
		return yuanJob;
	}
	public void setYuanJob(String yuanJob) {
		this.yuanJob = yuanJob;
	}
	public String getZuzhi() {
		return zuzhi;
	}
	public void setZuzhi(String zuzhi) {
		this.zuzhi = zuzhi;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Date getXiachenTime() {
		return xiachenTime;
	}
	public void setXiachenTime(Date xiachenTime) {
		this.xiachenTime = xiachenTime;
	}
	public Integer getAuthor() {
		return author;
	}
	public void setAuthor(Integer author) {
		this.author = author;
	}
	public String getDelSign() {
		return delSign;
	}
	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}
	
}
