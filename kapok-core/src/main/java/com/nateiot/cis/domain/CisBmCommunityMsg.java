package com.nateiot.cis.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;


/**
 * 小区信息
 * @author xiaguangjun
 *
 */
@Entity
@Table(name="CIS_BM_COMMUNITY_MSG")
public class CisBmCommunityMsg  extends BaseEntity {

	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "COM_ID")
	private Integer comId;
	

 
	/**
	 * 编号
	 */
	@Column(name = "CODES")
	private String codes;
	
	
	/**
	 * 所属机构
	 */
	@Column(name = "ORG")
	private Integer org;
	
	
	/**
	 * 小区名称
	 */
	@Column(name = "COMMUNITY_NAME")
	private String communityName;
	
	
	/**
	 * 住户数
	 */
	@Column(name = "ZHS")
	private Integer zhs;
	
	
	/**
	 * 居民人数
	 */
	@Column(name = "JMRS")
	private Integer jmrs;
	
	
	/**
	 * 楼栋数
	 */
	@Column(name = "LDS")
	private Integer lds;
	
	
	/**
	 * 车棚数
	 */
	@Column(name = "CPS")
	private Integer cps;
	
	
	/**
	 * 文体活动场所
	 */
	@Column(name = "WTHDCS")
	private String wthdcs;
	
	
	/**
	 * 公共健身器材
	 */
	@Column(name = "GGJSQC")
	private String ggjsqc;
	
	
	/**
	 * 防火设施
	 */
	@Column(name = "FHSS")
	private String fhss;
	
	
	/**
	 * 防盗措施
	 */
	@Column(name = "FDCS")
	private String fdcs;
	
	
	/**
	 * 卫生设施情况
	 */
	@Column(name = "WSSSQK")
	private String wsssqk;
	
	
	/**
	 * 宣传设施情况
	 */
	@Column(name = "XCSSQK")
	private String xcssqk;
	
	
	/**
	 * 清扫人员
	 */
	@Column(name = "QSRY")
	private String qsry;
	
	
	/**
	 * 是否有社区警务室
	 */
	@Column(name = "SFYSQJW")
	private String sfysqjw;
	
	/**
	 * 是否有值班室
	 */
	@Column(name = "SFYZBS")
	private String sfyzbs;
	
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
	 * 治安乱点
	 */
	@Column(name = "ZALD")
	private String zald;
	
	
	/**
	 * 物业公司名称
	 */
	@Column(name = "WYGSMC")
	private String wygsmc;
	
	
	/**
	 * 物业负责人
	 */
	@Column(name = "WYFZR")
	private String wyfzr;
	
	
	/**
	 * 负责人电话
	 */
	@Column(name = "FZRDH")
	private String fzrdh;
	
	
	/**
	 * 保安人数
	 */
	@Column(name = "BARS")
	private Integer bars;
	
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
	 * 软删除标记
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	

	public Integer getComId() {
		return comId;
	}


	public void setComId(Integer comId) {
		this.comId = comId;
	}


	public String getCodes() {
		return codes;
	}


	public void setCodes(String codes) {
		this.codes = codes;
	}


	public Integer getOrg() {
		return org;
	}


	public void setOrg(Integer org) {
		this.org = org;
	}


	public String getCommunityName() {
		return communityName;
	}


	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}


	public Integer getZhs() {
		return zhs;
	}


	public void setZhs(Integer zhs) {
		this.zhs = zhs;
	}


	public Integer getJmrs() {
		return jmrs;
	}


	public void setJmrs(Integer jmrs) {
		this.jmrs = jmrs;
	}


	public Integer getLds() {
		return lds;
	}


	public void setLds(Integer lds) {
		this.lds = lds;
	}


	public Integer getCps() {
		return cps;
	}


	public void setCps(Integer cps) {
		this.cps = cps;
	}


	public String getWthdcs() {
		return wthdcs;
	}


	public void setWthdcs(String wthdcs) {
		this.wthdcs = wthdcs;
	}


	public String getGgjsqc() {
		return ggjsqc;
	}


	public void setGgjsqc(String ggjsqc) {
		this.ggjsqc = ggjsqc;
	}


	public String getFhss() {
		return fhss;
	}


	public void setFhss(String fhss) {
		this.fhss = fhss;
	}


	public String getFdcs() {
		return fdcs;
	}


	public void setFdcs(String fdcs) {
		this.fdcs = fdcs;
	}


	public String getWsssqk() {
		return wsssqk;
	}


	public void setWsssqk(String wsssqk) {
		this.wsssqk = wsssqk;
	}


	public String getXcssqk() {
		return xcssqk;
	}


	public void setXcssqk(String xcssqk) {
		this.xcssqk = xcssqk;
	}


	public String getQsry() {
		return qsry;
	}


	public void setQsry(String qsry) {
		this.qsry = qsry;
	}


	public String getSfysqjw() {
		return sfysqjw;
	}


	public void setSfysqjw(String sfysqjw) {
		this.sfysqjw = sfysqjw;
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


	public String getZald() {
		return zald;
	}


	public void setZald(String zald) {
		this.zald = zald;
	}


	public String getWygsmc() {
		return wygsmc;
	}


	public void setWygsmc(String wygsmc) {
		this.wygsmc = wygsmc;
	}


	public String getWyfzr() {
		return wyfzr;
	}


	public void setWyfzr(String wyfzr) {
		this.wyfzr = wyfzr;
	}


	public String getFzrdh() {
		return fzrdh;
	}


	public void setFzrdh(String fzrdh) {
		this.fzrdh = fzrdh;
	}


	public Integer getBars() {
		return bars;
	}


	public void setBars(Integer bars) {
		this.bars = bars;
	}


	public String getSfyzbs() {
		return sfyzbs;
	}


	public void setSfyzbs(String sfyzbs) {
		this.sfyzbs = sfyzbs;
	}


	public String getDelSign() {
		return delSign;
	}


	public void setDelSign(String delSign) {
		this.delSign = delSign;
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

	
}
