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
 * 失业证办理
 * 
 * @author guohuawen
 * 
 */
@Entity
@Table(name = "CIS_BM_UNEMPLOYMENT")
public class CisBmUnemployment extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UNEMPLOYMENT_ID")
	private Integer unemploymentId;

	/**
	 * 所属机构
	 */
	@Column(name = "ORG_ID")
	private String orgId;

	/**
	 * 失业姓名
	 */
	@Column(name = "UNEMPLOYMENT_NAME")
	private String unemploymentName;
	
	/**
	 * 性别
	 */
	@Column(name = "SEX")
	private String sex;
	
	/**
	 * 身份证号
	 */
	@Column(name = "ID_NUM")
	private String idNum;
	
	/**
	 * 联系电话
	 */
	@Column(name = "PHONE")
	private String phone;

	/**
	 * 居住地
	 */
	@Column(name = "ADDRESS")
	private String  address;

	/**
	 * 失业证号
	 */
	@Column(name = "UNEMPLOYMENT_NUM")
	private String unemploymentNum;
	
	/**
	 * 发证日期
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "CERTIFICATE_DATE")
	private Date certificateDate;

	/**
	 * 失业日期
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "UNEMPLOYMENT_DATE")
	private Date unemploymentDate;
	
	/**
	 * 上一家公司
	 */
	@Column(name = "COMPANY")
	private String  company;
	
	/**
	 * 上一家公司联系电话
	 */
	@Column(name = "COMPANY_PHONE")
	private String  companyPhone;
	
	/**
	 * 上一家公司地址
	 */
	@Column(name = "COMPANY_ADDRESS")
	private String  companyAddress;
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String  remank;
	

	public Integer getUnemploymentId() {
		return unemploymentId;
	}

	public void setUnemploymentId(Integer unemploymentId) {
		this.unemploymentId = unemploymentId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUnemploymentName() {
		return unemploymentName;
	}

	public void setUnemploymentName(String unemploymentName) {
		this.unemploymentName = unemploymentName;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getUnemploymentNum() {
		return unemploymentNum;
	}

	public void setUnemploymentNum(String unemploymentNum) {
		this.unemploymentNum = unemploymentNum;
	}

	public Date getCertificateDate() {
		return certificateDate;
	}

	public void setCertificateDate(Date certificateDate) {
		this.certificateDate = certificateDate;
	}

	public Date getUnemploymentDate() {
		return unemploymentDate;
	}

	public void setUnemploymentDate(Date unemploymentDate) {
		this.unemploymentDate = unemploymentDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getRemank() {
		return remank;
	}

	public void setRemank(String remank) {
		this.remank = remank;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
