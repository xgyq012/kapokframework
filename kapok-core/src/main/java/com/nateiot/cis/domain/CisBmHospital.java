package com.nateiot.cis.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import com.nateiot.base.domain.BaseEntity;

/**
 * 医院信息
 * @author guohuawen
 *
 */
@Entity
@Table(name = "CIS_BM_HOSPITAL")
public class CisBmHospital extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "HOSPITAL_ID")
	private Integer hospitalId;
	
	/**
	 *  所属机构
	 */
	@Column(name = "SSJG")
	private Integer ssjg;
	
	/**
	 *  医院名称
	 */
	@Column(name = "YYMC")
	private String yymc;
	
	/**
	 *  医院等级
	 */
	@Column(name = "YYDJ")
	private String yydj;
	
	/**
	 *  医院床位
	 */
	@Column(name = "YYCW")
	private Integer yycw;
	
	/**
	 *  占地面积
	 */
	@Column(name = "ZDMJ")
	private String zdmj;
	
	/**
	 *  地址
	 */
	@Column(name = "DZ")
	private String dz;
	
	/**
	 *  法人
	 */
	@Column(name = "FR")
	private String fr;
	
	/**
	 *  法人联系电话
	 */
	@Column(name = "FRDH")
	private String frdh;
	
	/**
	 *  党组织负责人
	 */
	@Column(name = "DZZFZR")
	private String dzzfzr;
	
	/**
	 *  党组织负责人联系电话
	 */
	@Column(name = "DZZFZRDH")
	private String dzzfzrdh;
	
	/**
	 *  党员人数
	 */
	@Column(name = "DYRS")
	private Integer dyrs;
	
	/**
	 *  工会组织负责人
	 */
	@Column(name = "GHZZFZR")
	private String ghzzfzr;
	
	/**
	 *  工会组织负责人联系电话
	 */
	@Column(name = "GHZZFZRDH")
	private String ghzzfzrdh;
	
	/**
	 *  工会会员人数
	 */
	@Column(name = "GHHYRS")
	private Integer ghhyrs;
	
	/**
	 *  保卫处负责人
	 */
	@Column(name = "BWCFZR")
	private String bwcfzr;
	
	/**
	 * 保卫处联系电话
	 */
	@Column(name = "BWCDH")
	private String bwcdh;
	
	/**
	 *  医政处负责人
	 */
	@Column(name = "YZCFZR")
	private String yzcfzr;
	
	/**
	 *  医政处负责人联系电话
	 */
	@Column(name = "YZCFZRDH")
	private String yzcfzrdh;
	
	/**
	 *  急诊室电话
	 */
	@Column(name = "JZSDH")
	private String jzsdh;
	
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
	 *  防盗措施
	 */
	@Column(name = "FDSS")
	private String fdss;
	
	/**
	 *  电子监控安装情况
	 */
	@Column(name = "DZJKAZ")
	private String dzjkaz;
	
	/**
	 * 删除标记 
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Integer getSsjg() {
		return ssjg;
	}

	public void setSsjg(Integer ssjg) {
		this.ssjg = ssjg;
	}

	public String getYymc() {
		return yymc;
	}

	public void setYymc(String yymc) {
		this.yymc = yymc;
	}

	public String getYydj() {
		return yydj;
	}

	public void setYydj(String yydj) {
		this.yydj = yydj;
	}

	public Integer getYycw() {
		return yycw;
	}

	public void setYycw(Integer yycw) {
		this.yycw = yycw;
	}
	
	public String getZdmj() {
		return zdmj;
	}

	public void setZdmj(String zdmj) {
		this.zdmj = zdmj;
	}

	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public String getFr() {
		return fr;
	}

	public void setFr(String fr) {
		this.fr = fr;
	}

	public String getFrdh() {
		return frdh;
	}

	public void setFrdh(String frdh) {
		this.frdh = frdh;
	}

	public String getDzzfzr() {
		return dzzfzr;
	}

	public void setDzzfzr(String dzzfzr) {
		this.dzzfzr = dzzfzr;
	}

	public String getDzzfzrdh() {
		return dzzfzrdh;
	}

	public void setDzzfzrdh(String dzzfzrdh) {
		this.dzzfzrdh = dzzfzrdh;
	}

	public Integer getDyrs() {
		return dyrs;
	}

	public void setDyrs(Integer dyrs) {
		this.dyrs = dyrs;
	}

	public String getGhzzfzr() {
		return ghzzfzr;
	}

	public void setGhzzfzr(String ghzzfzr) {
		this.ghzzfzr = ghzzfzr;
	}

	public String getGhzzfzrdh() {
		return ghzzfzrdh;
	}

	public void setGhzzfzrdh(String ghzzfzrdh) {
		this.ghzzfzrdh = ghzzfzrdh;
	}

	public Integer getGhhyrs() {
		return ghhyrs;
	}

	public void setGhhyrs(Integer ghhyrs) {
		this.ghhyrs = ghhyrs;
	}

	public String getBwcfzr() {
		return bwcfzr;
	}

	public void setBwcfzr(String bwcfzr) {
		this.bwcfzr = bwcfzr;
	}

	public String getBwcdh() {
		return bwcdh;
	}

	public void setBwcdh(String bwcdh) {
		this.bwcdh = bwcdh;
	}

	public String getYzcfzr() {
		return yzcfzr;
	}

	public void setYzcfzr(String yzcfzr) {
		this.yzcfzr = yzcfzr;
	}

	public String getYzcfzrdh() {
		return yzcfzrdh;
	}

	public void setYzcfzrdh(String yzcfzrdh) {
		this.yzcfzrdh = yzcfzrdh;
	}

	public String getJzsdh() {
		return jzsdh;
	}

	public void setJzsdh(String jzsdh) {
		this.jzsdh = jzsdh;
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

	public String getDzjkaz() {
		return dzjkaz;
	}

	public void setDzjkaz(String dzjkaz) {
		this.dzjkaz = dzjkaz;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}
	
}
