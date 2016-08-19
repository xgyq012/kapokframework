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

@Entity
@Table(name = "CIS_PA_DANGRI_MINGXI")
public class CisPaDangriMingxi extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "DANGRI_MINGXI_ID")
	private Integer dangriMingxiId;

	
	/**
	 * 固定党日计划id
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "FINAL_DANGRI_ID")
	private CisPaFinalDangri finalDangri;
	
	/**
	 * 月份
	 */
	@Column(name = "MONTH")
	private String month;
	
	/**
	 * 地点
	 */
	@Column(name = "ADDRESS")
	private String address;
	
	/**
	 * 内容
	 */
	@Column(name = "CONTENT")
	private String content;
	
	/**
	 * 内容
	 */
	@Column(name = "CANJIAREN")
	private String canjiaren;
	
	/**
	 * 主持人
	 */
	@Column(name = "ZHUCHIREN")
	private String zhuchiren;
	
	/**
	 * 主持人电话
	 */
	@Column(name = "ZHUCHIREN_PHONE")
	private String zhuchirenPhone;
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;

	public Integer getDangriMingxiId() {
		return dangriMingxiId;
	}

	public void setDangriMingxiId(Integer dangriMingxiId) {
		this.dangriMingxiId = dangriMingxiId;
	}

	public CisPaFinalDangri getFinalDangri() {
		return finalDangri;
	}

	public void setFinalDangri(CisPaFinalDangri finalDangri) {
		this.finalDangri = finalDangri;
	}
	
	@Transient
	public Integer getFinalDangriId(){
		return this.finalDangri == null ? null : this.finalDangri.getFinalDangriId();
	}
	
	public void setFinalDangriId(Integer finalDangriId){
		if(finalDangriId != null){
			this.finalDangri = new CisPaFinalDangri();
			this.finalDangri.setFinalDangriId(finalDangriId);
		}
	}
	

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getZhuchiren() {
		return zhuchiren;
	}

	public void setZhuchiren(String zhuchiren) {
		this.zhuchiren = zhuchiren;
	}

	public String getZhuchirenPhone() {
		return zhuchirenPhone;
	}

	public void setZhuchirenPhone(String zhuchirenPhone) {
		this.zhuchirenPhone = zhuchirenPhone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCanjiaren() {
		return canjiaren;
	}

	public void setCanjiaren(String canjiaren) {
		this.canjiaren = canjiaren;
	}
	
}
