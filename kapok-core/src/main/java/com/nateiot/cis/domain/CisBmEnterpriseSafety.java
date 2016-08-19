package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateSerializer;


/**
 * 公共安全--企业安全
 * 
 * @author guohuawen
 * 
 */
@Entity
@Table(name = "CIS_BM_ENTERPRISE_SAFETY")
public class CisBmEnterpriseSafety extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 主键 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SAENTERPRISE_ID")
	private Integer saenterpriseId;
	
	/**
	 * 所属机构 
	 */
	@Column(name = "ORG_ID")
	private String orgId;
	
	/**
	 * 企业名称
	 */
	@Column(name = "COMPANY_NAME")
	private String companyName;
	
	/**
	 * 法人 
	 */
	@Column(name = "CORP")
	private String corp;
	
	/**
	 * 安全负责人
	 */
	@Column(name = "SAFETY_DIRECTOR")
	private String safetyDirector;
	
	/**
	 * 安全负责人联系电话
	 */
	@Column(name = "DIRECTOR_PHONE")
	private String directorPhone;
	
	/**
	 * 安全员
	 */
	@Column(name = "SAFETY_OFFICER")
	private String safetyOfficer;
	
	/**
	 * 安全员电话
	 */
	@Column(name = "OFFICER_PHONE")
	private String officerPhone;
	
	/**
	 * 安全制度是否落实
	 */
	@Column(name = "SECURITY_SYSTEM")
	private String securitySystem;
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;
	
	/**
	 * 检查日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "EDTTIME")
	private Date edttime;
	
	/**
	 * 删除标记 
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	

	public Integer getSaenterpriseId() {
		return saenterpriseId;
	}

	public void setSaenterpriseId(Integer saenterpriseId) {
		this.saenterpriseId = saenterpriseId;
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCorp() {
		return corp;
	}

	public void setCorp(String corp) {
		this.corp = corp;
	}

	public String getSafetyDirector() {
		return safetyDirector;
	}

	public void setSafetyDirector(String safetyDirector) {
		this.safetyDirector = safetyDirector;
	}

	public String getDirectorPhone() {
		return directorPhone;
	}

	public void setDirectorPhone(String directorPhone) {
		this.directorPhone = directorPhone;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public String getSafetyOfficer() {
		return safetyOfficer;
	}

	public void setSafetyOfficer(String safetyOfficer) {
		this.safetyOfficer = safetyOfficer;
	}

	public String getOfficerPhone() {
		return officerPhone;
	}

	public void setOfficerPhone(String officerPhone) {
		this.officerPhone = officerPhone;
	}

	public String getSecuritySystem() {
		return securitySystem;
	}

	public void setSecuritySystem(String securitySystem) {
		this.securitySystem = securitySystem;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getEdttime() {
		return edttime;
	}

	public void setEdttime(Date edttime) {
		this.edttime = edttime;
	}
	

}
