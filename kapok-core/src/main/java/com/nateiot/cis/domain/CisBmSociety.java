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
import com.nateiot.core.common.json.JsonDateSerializer;

@Entity
@Table(name = "cis_bm_society")
public class CisBmSociety extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "M_ID")
	private Integer mId;

	/**
	 *  所属机构
	 */
	@Column(name = "SSJG")
	private String ssjg;
	
	/**
	 *  单位名称
	 */
	@Column(name = "DWMC")
	private String dwmc;
	
	/**
	 *  单位性质
	 */
	@Column(name = "DWXZ")
	private String dwxz;
	
	/**
	 *  员工人数
	 */
	@Column(name = "YGRS")
	private Integer ygrs;
	
	/**
	 *  占地面积
	 */
	@Column(name = "ZDMJ")
	private String zdmj;
	
	/**
	 *  法人代表
	 */
	@Column(name = "FRDB")
	private String frdb;
	
	/**
	 *  法人联系电话
	 */
	@Column(name = "FRDH")
	private String frdh;
	
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
	private Integer ghyrs;
	
	/**
	 *  安全负责人
	 */
	@Column(name = "AQFZR")
	private String aqfzr;
	
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
	 *  党员人数
	 */
	@Column(name = "DYRS")
	private String dyrs;
	
	/**
	 *  防盗门安装情况
	 */
	@Column(name = "FDMAZ")
	private String fdmaz;
	
	/**
	 *  电子监控安装情况
	 */
	@Column(name = "DZJKAZ")
	private String dzjkaz;
	
	/**
	 *  电话报警安装情况
	 */
	@Column(name = "DHBJAZ")
	private String dhbjaz;
	
	/**
	 *  单位详细地址
	 */
	@Column(name = "DWXXDZ")
	private String dwxxdz;

	public Integer getmId() {
		return mId;
	}

	public void setmId(Integer mId) {
		this.mId = mId;
	}

	public String getSsjg() {
		return ssjg;
	}

	public void setSsjg(String ssjg) {
		this.ssjg = ssjg;
	}

	public String getDwmc() {
		return dwmc;
	}

	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}

	public String getDwxz() {
		return dwxz;
	}

	public void setDwxz(String dwxz) {
		this.dwxz = dwxz;
	}

	public Integer getYgrs() {
		return ygrs;
	}

	public void setYgrs(Integer ygrs) {
		this.ygrs = ygrs;
	}

	public String getZdmj() {
		return zdmj;
	}

	public void setZdmj(String zdmj) {
		this.zdmj = zdmj;
	}

	public String getFrdb() {
		return frdb;
	}

	public void setFrdb(String frdb) {
		this.frdb = frdb;
	}

	public String getFrdh() {
		return frdh;
	}

	public void setFrdh(String frdh) {
		this.frdh = frdh;
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

	public Integer getGhyrs() {
		return ghyrs;
	}

	public void setGhyrs(Integer ghyrs) {
		this.ghyrs = ghyrs;
	}

	public String getAqfzr() {
		return aqfzr;
	}

	public void setAqfzr(String aqfzr) {
		this.aqfzr = aqfzr;
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

	public String getDyrs() {
		return dyrs;
	}

	public void setDyrs(String dyrs) {
		this.dyrs = dyrs;
	}

	public String getFdmaz() {
		return fdmaz;
	}

	public void setFdmaz(String fdmaz) {
		this.fdmaz = fdmaz;
	}

	public String getDzjkaz() {
		return dzjkaz;
	}

	public void setDzjkaz(String dzjkaz) {
		this.dzjkaz = dzjkaz;
	}

	public String getDhbjaz() {
		return dhbjaz;
	}

	public void setDhbjaz(String dhbjaz) {
		this.dhbjaz = dhbjaz;
	}

	public String getDwxxdz() {
		return dwxxdz;
	}

	public void setDwxxdz(String dwxxdz) {
		this.dwxxdz = dwxxdz;
	}


}
