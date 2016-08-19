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
import com.nateiot.core.repository.DBUtil;

/**
 * 楼栋信息
 * 
 * @author xiaguangjun
 *
 */
@Entity
@Table(name="CIS_BM_BUILDING_MSG")
public class CisBmBuildingMsg extends BaseEntity {

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "BUILD_ID")
	private Integer buildId;

	
	/**
	 * 机构
	 */
	@Column(name = "ORG")
	private Integer org;
	
	
	/**
	 * 小区
	 */
	/*@Column(name = "COM_ID")
	private Integer comId;*/
	
	
	/**
	 * 楼栋号
	 */
	@Column(name = "LD_CODE")
	private String ldCode;
	
	
	/**
	 * 居民数
	 */
	@Column(name = "JMS")
	private Integer jms;
	
	
	/**
	 * 住户数
	 */
	@Column(name = "ZHS")
	private Integer zhs;
	
	
	/**
	 * 防火措施
	 */
	@Column(name = "FHCS")
	private String fhcs;
	
	
	/**
	 * 防盗措施
	 */
	@Column(name = "FDCS")
	private String fdcs;
	
	
	/**
	 * 清扫人员
	 */
	@Column(name = "QSRY")
	private String qsry;
	
	
	/**
	 * 卫生设施情况
	 */
	@Column(name = "WSSSQK")
	private String wsssqk;
	
	
	/**
	 * 电话报警
	 */
	@Column(name = "DHBJ")
	private String dhbj;
	
	
	/**
	 * 电子监控
	 */
	@Column(name = "DZJK")
	private String dzjk;
	
	
	/**
	 * 封闭情况
	 */
	@Column(name = "FBQK")
	private String fbqk;
	
	
	/**
	 * 楼栋负责人
	 */
	@Column(name = "LD_NAME")
	private String ldName;
	
	
	/**
	 * 负责人电话
	 */
	@Column(name = "PHONE")
	private String phone;
	
	
	/**
	 * 经度
	 */
	@Column(name = "LON")
	private String lon;
	
	
	/**
	 * 维度
	 */
	@Column(name = "LAT")
	private String lat;
	
	
	/**
	 * 是否删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="COM_ID",nullable=true)
	private CisBmCommunityMsg cisBmCommunityMsg;
	
	
	@Transient
	public Integer getComId() {
		return this.cisBmCommunityMsg ==null ? null : getCisBmCommunityMsg().getComId();
	}

	//小区名称
	@Transient
	public  String getCommunityName(){
		
		return this.cisBmCommunityMsg ==null ? "" : getCisBmCommunityMsg().getCommunityName();
	}
	
	public void setComId(Integer comId) {
		if(comId!=null){
			setCisBmCommunityMsg(DBUtil.find(CisBmCommunityMsg.class, comId));
		}
	}
	

	public CisBmCommunityMsg getCisBmCommunityMsg() {
		return cisBmCommunityMsg;
	}


	public void setCisBmCommunityMsg(CisBmCommunityMsg cisBmCommunityMsg) {
		this.cisBmCommunityMsg = cisBmCommunityMsg;
	}


	public Integer getBuildId() {
		return buildId;
	}


	public void setBuildId(Integer buildId) {
		this.buildId = buildId;
	}


	public Integer getOrg() {
		return org;
	}


	public void setOrg(Integer org) {
		this.org = org;
	}


	public String getLdCode() {
		return ldCode;
	}


	public void setLdCode(String ldCode) {
		this.ldCode = ldCode;
	}


	public Integer getJms() {
		return jms;
	}


	public void setJms(Integer jms) {
		this.jms = jms;
	}


	public Integer getZhs() {
		return zhs;
	}


	public void setZhs(Integer zhs) {
		this.zhs = zhs;
	}


	public String getFhcs() {
		return fhcs;
	}


	public void setFhcs(String fhcs) {
		this.fhcs = fhcs;
	}


	public String getFdcs() {
		return fdcs;
	}


	public void setFdcs(String fdcs) {
		this.fdcs = fdcs;
	}


	public String getQsry() {
		return qsry;
	}


	public void setQsry(String qsry) {
		this.qsry = qsry;
	}


	public String getWsssqk() {
		return wsssqk;
	}


	public void setWsssqk(String wsssqk) {
		this.wsssqk = wsssqk;
	}


	public String getDhbj() {
		return dhbj;
	}


	public void setDhbj(String dhbj) {
		this.dhbj = dhbj;
	}


	public String getDzjk() {
		return dzjk;
	}


	public void setDzjk(String dzjk) {
		this.dzjk = dzjk;
	}


	public String getFbqk() {
		return fbqk;
	}


	public void setFbqk(String fbqk) {
		this.fbqk = fbqk;
	}


	public String getLdName() {
		return ldName;
	}


	public void setLdName(String ldName) {
		this.ldName = ldName;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getLon() {
		return lon;
	}


	public void setLon(String lon) {
		this.lon = lon;
	}


	public String getLat() {
		return lat;
	}


	public void setLat(String lat) {
		this.lat = lat;
	}


	public String getDelSign() {
		return delSign;
	}


	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}
	
	
}
