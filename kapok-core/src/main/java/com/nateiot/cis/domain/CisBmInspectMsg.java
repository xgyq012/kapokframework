package com.nateiot.cis.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;


/**
 * @author xiaguangjun
 * 纪检信息
 */

@Table(name="CIS_BM_INSPECT_MSG")
@Entity
public class CisBmInspectMsg extends BaseEntity{

	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "INSPECT_ID")
	private Integer inspectId;
	
	
	/**
	 * 所属机构
	 */
	@Column(name = "ORG")
	private Integer org;
	
	
	/**
	 * 纪检组织名称
	 */
	@Column(name = "INSPECT_NAME")
	private String inspectName;
	
	
	/**
	 * 地址
	 */
	@Column(name = "ADRESS")
	private String adress;
	
	
	/**
	 * 纪检组织负责人
	 */
	@Column(name = "JJZZFZR")
	private String jjzzfzr;
	
	
	/**
	 * 联系电话
	 */
	@Column(name = "TEL")
	private String tel;
	
	
	/**
	 * 纪检委员人数
	 */
	@Column(name = "JJWYRS")
	private String jjwyrs;
	
	
	/**
	 * 党务公开
	 */
	@Column(name = "DWGK")
	private String dwgk;
	
	
	/**
	 * 政务公开
	 */
	@Column(name = "ZWGK")
	private String zwgk;
	
	
	/**
	 * 居务公开
	 */
	@Column(name = "JWGK")
	private String jwgk;
	
	
	/**
	 * 三重一大
	 */
	@Column(name = "SZYD")
	private String szyd;
	
	
	/**
	 * 风险防控
	 */
	@Column(name = "FXFK")
	private String fxfk;
	
	
	/**
	 * 廉政示范点
	 */
	@Column(name = "LZSFD")
	private String lzsfd;
	
	
	/**
	 * 案件投诉
	 */
	@Column(name = "AJTS")
	private Integer ajts;
	
	
	/**
	 * 案件处罚
	 */
	@Column(name = "AJCF")
	private String ajcf;
	
	
	/**
	 * 效能投诉
	 */
	@Column(name = "XNTS")
	private Integer xnts;
	
	
	/**
	 * 效能处罚
	 */
	@Column(name = "XNCF")
	private String xncf;
	
	
	/**
	 * 小金库治理
	 */
	@Column(name = "XJKZL")
	private String xjkzl;
	
	
	/**
	 * 公车治理
	 */
	@Column(name = "GCZL")
	private String gczl;
	
	
	/**
	 * 信访
	 */
	@Column(name = "XF")
	private String xf;
	
	
	/**
	 * 党风廉政责任书
	 */
	@Column(name = "DFLZZRS")
	private String dflzzrs;
	
	
	/**
	 * 专题教育活动
	 */
	@Column(name = "ZTJYHD")
	private String ztjyhd;


	public Integer getInspectId() {
		return inspectId;
	}


	public void setInspectId(Integer inspectId) {
		this.inspectId = inspectId;
	}


	public int getOrg() {
		return org;
	}


	public void setOrg(int org) {
		this.org = org;
	}


	public String getInspectName() {
		return inspectName;
	}


	public void setInspectName(String inspectName) {
		this.inspectName = inspectName;
	}


	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}


	public String getJjzzfzr() {
		return jjzzfzr;
	}


	public void setJjzzfzr(String jjzzfzr) {
		this.jjzzfzr = jjzzfzr;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getJjwyrs() {
		return jjwyrs;
	}


	public void setJjwyrs(String jjwyrs) {
		this.jjwyrs = jjwyrs;
	}


	public String getDwgk() {
		return dwgk;
	}


	public void setDwgk(String dwgk) {
		this.dwgk = dwgk;
	}


	public String getZwgk() {
		return zwgk;
	}


	public void setZwgk(String zwgk) {
		this.zwgk = zwgk;
	}


	public String getJwgk() {
		return jwgk;
	}


	public void setJwgk(String jwgk) {
		this.jwgk = jwgk;
	}


	public String getSzyd() {
		return szyd;
	}


	public void setSzyd(String szyd) {
		this.szyd = szyd;
	}


	public String getFxfk() {
		return fxfk;
	}


	public void setFxfk(String fxfk) {
		this.fxfk = fxfk;
	}


	public String getLzsfd() {
		return lzsfd;
	}


	public void setLzsfd(String lzsfd) {
		this.lzsfd = lzsfd;
	}


	public Integer getAjts() {
		return ajts;
	}


	public void setAjts(Integer ajts) {
		this.ajts = ajts;
	}


	public String getAjcf() {
		return ajcf;
	}


	public void setAjcf(String ajcf) {
		this.ajcf = ajcf;
	}


	public Integer getXnts() {
		return xnts;
	}


	public void setXnts(Integer xnts) {
		this.xnts = xnts;
	}


	public String getXncf() {
		return xncf;
	}


	public void setXncf(String xncf) {
		this.xncf = xncf;
	}


	public String getXjkzl() {
		return xjkzl;
	}


	public void setXjkzl(String xjkzl) {
		this.xjkzl = xjkzl;
	}


	public String getGczl() {
		return gczl;
	}


	public void setGczl(String gczl) {
		this.gczl = gczl;
	}


	public String getXf() {
		return xf;
	}


	public void setXf(String xf) {
		this.xf = xf;
	}


	public String getDflzzrs() {
		return dflzzrs;
	}


	public void setDflzzrs(String dflzzrs) {
		this.dflzzrs = dflzzrs;
	}


	public String getZtjyhd() {
		return ztjyhd;
	}


	public void setZtjyhd(String ztjyhd) {
		this.ztjyhd = ztjyhd;
	}
	
	
	
 
}
