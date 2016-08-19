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

/**
 * @author xiaguangjun
 *	流动人口婚育证办理
 */
@Table(name="CIS_BM_LDRKHYZ")
@Entity
public class CisBmLdrkhyz extends BaseEntity {


	/**
	 * ID
	 */
	@Id
	@Column(name = "LD_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer ldId;
	
	
	/**
	 * 所属机构
	 */
	@Column(name = "ORG_ID")
	private Integer orgId;
	
	
	/**
	 * 持证人
	 */
	@Column(name = "CZ_NAME")
	private String czName;
	
	
	/**
	 * 户籍地
	 */
	@Column(name = "HJD")
	private String hjd;
	
	
	/**
	 * 节育状况
	 */
	@Column(name = "JYZK")
	private String jyzk;
	
	
	/**
	 * 家庭住址
	 */
	@Column(name = "ADDRESS")
	private String address;
	
	
	/**
	 * 婚育证号
	 */
	@Column(name = "HYZ_CODE")
	private String hyzCode;
	
	
	/**
	 * 办证日期
	 */
	@Column(name = "BZRQ")
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bzrq;
	
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;


	

	public Integer getLdId() {
		return ldId;
	}


	public void setLdId(Integer ldId) {
		this.ldId = ldId;
	}


	public Integer getOrgId() {
		return orgId;
	}


	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


	public String getCzName() {
		return czName;
	}


	public void setCzName(String czName) {
		this.czName = czName;
	}


	public String getHjd() {
		return hjd;
	}


	public void setHjd(String hjd) {
		this.hjd = hjd;
	}


	public String getJyzk() {
		return jyzk;
	}


	public void setJyzk(String jyzk) {
		this.jyzk = jyzk;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getHyzCode() {
		return hyzCode;
	}


	public void setHyzCode(String hyzCode) {
		this.hyzCode = hyzCode;
	}


	public Date getBzrq() {
		return bzrq;
	}


	public void setBzrq(Date bzrq) {
		this.bzrq = bzrq;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

 
}
