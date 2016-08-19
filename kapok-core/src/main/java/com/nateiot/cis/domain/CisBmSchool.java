package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

@Entity
@Table(name = "cis_bm_school")
public class CisBmSchool extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 主键 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SCHOOL_ID")
	private Integer schoolId;
	
	/**
	 * 所属机构 
	 */
	@Column(name = "SSJG")
	private Integer ssjg;
	
	/**
	 *  学校名称
	 */
	@Column(name = "XXMC")
	private String xxmc;
	
	/**
	 *  学校班额
	 */
	@Column(name = "XXBE")
	private Integer xxbe;
	
	/**
	 *  占地面积
	 */
	@Column(name = "XXZDMJ")
	private String xxzdmj;
	
	/**
	 *  地址
	 */
	@Column(name = "DZ")
	private String dz;
	
	/**
	 *  教师人数
	 */
	@Column(name = "JSRS")
	private Integer jsrs;
	
	/**
	 *  学生人数
	 */
	@Column(name = "XSRS")
	private Integer xsrs;
	
	/**
	 *  党支部书记
	 */
	@Column(name = "DZBSJ")
	private String dzbsj;
	
	/**
	 *  党支部书记联系电话
	 */
	@Column(name = "DZBSJDH")
	private String dzbsjdh;
	
	/**
	 *  党员人数
	 */
	@Column(name = "DYRS")
	private Integer dyrs;
	
	/**
	 *  工会主席
	 */
	@Column(name = "GHZX")
	private String ghzx;
	
	/**
	 *  工会主席联系电话
	 */
	@Column(name = "GHZXDH")
	private String ghzxdh;
	
	/**
	 *  工会会员人数
	 */
	@Column(name = "GHHYRS")
	private Integer ghhyrs;
	
	/**
	 *  校长姓名
	 */
	@Column(name = "XZXM")
	private String xzxm;
	
	/**
	 *  校长电话
	 */
	@Column(name = "XZDH")
	private String xzdh;
	
	/**
	 *  安全负责人姓名
	 */
	@Column(name = "AQFZRXM")
	private String aqfzrxm;
	
	/**
	 *  安全负责人电话
	 */
	@Column(name = "AQFZRDH")
	private String aqfzrdh;
	
	/**
	 *  文明单位情况
	 */
	@Column(name = "WMDWQK")
	private String wmdwqk;
	
	/**
	 * 删除标记 
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 *  防火设备
	 */
	@Column(name = "FHSB")
	private String fhsb;
	
	/**
	 *  防盗设施
	 */
	@Column(name = "FDSS")
	private String fdss;
	
	/**
	 *  值班室电话
	 */
	@Column(name = "ZBSDH")
	private String zbsdh;
	
	/**
	 *  电子监控安装情况
	 */
	@Column(name = "DZJKAZ")
	private String dzjkaz;

	/**
	 * 经度
	 */
	@Column(name = "LON")
	private String lon;
	
	/**
	 * 纬度
	 */
	@Column(name = "LAT")
	private String lat;
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public Integer getSsjg() {
		return ssjg;
	}

	public void setSsjg(Integer ssjg) {
		this.ssjg = ssjg;
	}

	public String getXxmc() {
		return xxmc;
	}

	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}

	public Integer getXxbe() {
		return xxbe;
	}

	public void setXxbe(Integer xxbe) {
		this.xxbe = xxbe;
	}
	

	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public Integer getJsrs() {
		return jsrs;
	}

	public void setJsrs(Integer jsrs) {
		this.jsrs = jsrs;
	}

	public Integer getXsrs() {
		return xsrs;
	}

	public void setXsrs(Integer xsrs) {
		this.xsrs = xsrs;
	}

	public String getDzbsj() {
		return dzbsj;
	}

	public void setDzbsj(String dzbsj) {
		this.dzbsj = dzbsj;
	}

	public String getDzbsjdh() {
		return dzbsjdh;
	}

	public void setDzbsjdh(String dzbsjdh) {
		this.dzbsjdh = dzbsjdh;
	}

	public Integer getDyrs() {
		return dyrs;
	}

	public void setDyrs(Integer dyrs) {
		this.dyrs = dyrs;
	}

	public String getGhzx() {
		return ghzx;
	}

	public void setGhzx(String ghzx) {
		this.ghzx = ghzx;
	}

	public String getGhzxdh() {
		return ghzxdh;
	}

	public void setGhzxdh(String ghzxdh) {
		this.ghzxdh = ghzxdh;
	}

	public Integer getGhhyrs() {
		return ghhyrs;
	}

	public void setGhhyrs(Integer ghhyrs) {
		this.ghhyrs = ghhyrs;
	}

	public String getXzxm() {
		return xzxm;
	}

	public void setXzxm(String xzxm) {
		this.xzxm = xzxm;
	}

	public String getXzdh() {
		return xzdh;
	}

	public void setXzdh(String xzdh) {
		this.xzdh = xzdh;
	}

	public String getAqfzrxm() {
		return aqfzrxm;
	}

	public void setAqfzrxm(String aqfzrxm) {
		this.aqfzrxm = aqfzrxm;
	}

	public String getAqfzrdh() {
		return aqfzrdh;
	}

	public void setAqfzrdh(String aqfzrdh) {
		this.aqfzrdh = aqfzrdh;
	}

	public String getWmdwqk() {
		return wmdwqk;
	}

	public void setWmdwqk(String wmdwqk) {
		this.wmdwqk = wmdwqk;
	}

	public String getFhsb() {
		return fhsb;
	}

	public void setFhsb(String fhsb) {
		this.fhsb = fhsb;
	}
	
	public String getFdss() {
		return fdss;
	}

	public void setFdss(String fdss) {
		this.fdss = fdss;
	}

	public String getZbsdh() {
		return zbsdh;
	}

	public void setZbsdh(String zbsdh) {
		this.zbsdh = zbsdh;
	}

	public String getDzjkaz() {
		return dzjkaz;
	}

	public void setDzjkaz(String dzjkaz) {
		this.dzjkaz = dzjkaz;
	}

	public String getXxzdmj() {
		return xxzdmj;
	}

	public void setXxzdmj(String xxzdmj) {
		this.xxzdmj = xxzdmj;
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
