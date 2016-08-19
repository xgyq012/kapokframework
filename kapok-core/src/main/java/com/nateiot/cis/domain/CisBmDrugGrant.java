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
 * 药品发放
 */
@Table(name="CIS_BM_DRUG_GRANT")
@Entity
public class CisBmDrugGrant extends BaseEntity {
	
	/**
	 * ID
	 */
	@Id
	@Column(name = "DR_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer drId;
	
	
	/**
	 * 所属机构
	 */
	@Column(name = "ORG_ID")
	private Integer orgId;
	
	
	/**
	 * 发放药具厂或单位
	 */
	@Column(name = "COMPANY")
	private String company;
	
	
	/**
	 * 药具名称
	 */
	@Column(name = "YP_NAME")
	private String ypName;
	
	
	/**
	 * 发放数量
	 */
	@Column(name = "YP_NUM")
	private Integer ypNum;
	
	
	/**
	 * 发放日期
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "YFRQ")
	private Date yfrq;
	
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;


	public Integer getDrId() {
		return drId;
	}


	public void setDrId(Integer drId) {
		this.drId = drId;
	}


	public Integer getOrgId() {
		return orgId;
	}


	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public String getYpName() {
		return ypName;
	}


	public void setYpName(String ypName) {
		this.ypName = ypName;
	}


	public Integer getYpNum() {
		return ypNum;
	}


	public void setYpNum(Integer ypNum) {
		this.ypNum = ypNum;
	}


	public Date getYfrq() {
		return yfrq;
	}


	public void setYfrq(Date yfrq) {
		this.yfrq = yfrq;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


}
