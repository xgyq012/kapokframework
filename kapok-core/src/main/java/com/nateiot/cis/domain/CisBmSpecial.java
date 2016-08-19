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
 * 特殊行业
 */
@Entity
@Table(name = "cis_bm_special")
public class CisBmSpecial extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SPECIAL_ID")
	private Integer specialId;
	
	/**
	 *  所属机构
	 */
	@Column(name = "SSJG")
	private Integer ssjg;
	
	/**
	 *  企业商铺名称
	 */
	@Column(name = "QYSPMC")
	private String qyspmc;
	
	/**
	 *  行业类别
	 */
	@Column(name = "HYLB")
	private String hylb;
	
	/**
	 *  经营范围
	 */
	@Column(name = "JYFW")
	private String jyfw;
	
	/**
	 *  地址
	 */
	@Column(name = "DZ")
	private String dz;
	
	/**
	 *  注册资金
	 */
	@Column(name = "ZCZJ")
	private String zczj;
	
	/**
	 *  年缴国税
	 */
	@Column(name = "NJGS")
	private String njgs;
	
	/**
	 *  年销售收入
	 */
	@Column(name = "NXSSR")
	private String nxssr;
	
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
	 *  负责人姓名
	 */
	@Column(name = "FZRXM")
	private String fzrxm;
	
	/**
	 *  负责人电话
	 */
	@Column(name = "FZRDH")
	private String fzrdh;
	
	/**
	 * 删除标记 
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 *  安全隐患
	 */
	@Column(name = "AQYH")
	private String aqyh;
	
	/**
	 * 防火设备
	 */
	@Column(name = "FHSB")
	private String fhsb;
	
	/**
	 *  防盗措施
	 */
	@Column(name = "FDCS")
	private String fdcs;
	
	public Integer getSpecialId() {
		return specialId;
	}

	public void setSpecialId(Integer specialId) {
		this.specialId = specialId;
	}

	public Integer getSsjg() {
		return ssjg;
	}

	public void setSsjg(Integer ssjg) {
		this.ssjg = ssjg;
	}

	public String getQyspmc() {
		return qyspmc;
	}

	public void setQyspmc(String qyspmc) {
		this.qyspmc = qyspmc;
	}

	public String getHylb() {
		return hylb;
	}

	public void setHylb(String hylb) {
		this.hylb = hylb;
	}

	public String getJyfw() {
		return jyfw;
	}

	public void setJyfw(String jyfw) {
		this.jyfw = jyfw;
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

	public String getFzrxm() {
		return fzrxm;
	}

	public void setFzrxm(String fzrxm) {
		this.fzrxm = fzrxm;
	}

	public String getFzrdh() {
		return fzrdh;
	}

	public void setFzrdh(String fzrdh) {
		this.fzrdh = fzrdh;
	}

	public String getAqyh() {
		return aqyh;
	}

	public void setAqyh(String aqyh) {
		this.aqyh = aqyh;
	}

	public String getFhsb() {
		return fhsb;
	}

	public void setFhsb(String fhsb) {
		this.fhsb = fhsb;
	}

	public String getFdcs() {
		return fdcs;
	}

	public void setFdcs(String fdcs) {
		this.fdcs = fdcs;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public String getZczj() {
		return zczj;
	}

	public void setZczj(String zczj) {
		this.zczj = zczj;
	}

	public String getNjgs() {
		return njgs;
	}

	public void setNjgs(String njgs) {
		this.njgs = njgs;
	}

	public String getNxssr() {
		return nxssr;
	}

	public void setNxssr(String nxssr) {
		this.nxssr = nxssr;
	}
	

}
