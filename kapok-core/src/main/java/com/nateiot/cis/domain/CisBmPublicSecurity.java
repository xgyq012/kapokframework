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
 * 公共安全--治安信息
 * 
 * @author guohuawen
 * 
 */
@Entity
@Table(name = "cis_bm_public_security")
public class CisBmPublicSecurity extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "PUSECURITY_ID")
	private Integer pusecurityId;
	
	
	/**
	 * 所属机构
	 */
	@Column(name = "ORG_ID")
	private Integer orgId;
	
	
	/**
	 * 单位名称
	 */
	@Column(name = "UNITS_NAME")
	private String unitsName;
	
	
	/**
	 * 单位地址
	 */
	@Column(name = "UNITS_ADDRESS")
	private String unitsAddress;
	
	
	/**
	 * 治安负责人
	 */
	@Column(name = "SECURITY_DIRECTOR")
	private String securityDirector;
	
	
	/**
	 * 单位治安负责人电话
	 */
	@Column(name = "DIRECTOR_PHONE")
	private String directorPhone;
	
	
	/**
	 * 治安员
	 */
	@Column(name = "PEACE_OFFICER")
	private String peaceOfficer;
	
	
	/**
	 * 治安员电话
	 */
	@Column(name = "OFFICER_PHONE")
	private String officerPhone;
	
	
	/**
	 * 治安隐患
	 */
	@Column(name = "SECURITY_HIDDEN")
	private String securityHidden;
	
	
	/**
	 * 整改情况
	 */
	@Column(name = "SITUATION")
	private String situation;
	
	
	/**
	 * 检查人
	 */
	@Column(name = "RUMMAGER")
	private String rummager;
	
	
	/**
	 * 检查人联系电话
	 */
	@Column(name = "RUMMAGER_PHONE")
	private String rummagerPhone;
	
	
	/**
	 * 检查日期
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "EDTTIME")
	private Date edttime;
	
	
	/**
	 * 删除标记
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;


	public Integer getPusecurityId() {
		return pusecurityId;
	}


	public void setPusecurityId(Integer pusecurityId) {
		this.pusecurityId = pusecurityId;
	}


	public Integer getOrgId() {
		return orgId;
	}


	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


	public String getUnitsName() {
		return unitsName;
	}


	public void setUnitsName(String unitsName) {
		this.unitsName = unitsName;
	}


	public String getUnitsAddress() {
		return unitsAddress;
	}


	public void setUnitsAddress(String unitsAddress) {
		this.unitsAddress = unitsAddress;
	}


	public String getSecurityDirector() {
		return securityDirector;
	}


	public void setSecurityDirector(String securityDirector) {
		this.securityDirector = securityDirector;
	}


	public String getDirectorPhone() {
		return directorPhone;
	}


	public void setDirectorPhone(String directorPhone) {
		this.directorPhone = directorPhone;
	}


	public String getPeaceOfficer() {
		return peaceOfficer;
	}


	public void setPeaceOfficer(String peaceOfficer) {
		this.peaceOfficer = peaceOfficer;
	}


	public String getOfficerPhone() {
		return officerPhone;
	}


	public void setOfficerPhone(String officerPhone) {
		this.officerPhone = officerPhone;
	}


	public String getSecurityHidden() {
		return securityHidden;
	}


	public void setSecurityHidden(String securityHidden) {
		this.securityHidden = securityHidden;
	}


	public String getSituation() {
		return situation;
	}


	public void setSituation(String situation) {
		this.situation = situation;
	}


	public String getRummager() {
		return rummager;
	}


	public void setRummager(String rummager) {
		this.rummager = rummager;
	}


	public String getRummagerPhone() {
		return rummagerPhone;
	}


	public void setRummagerPhone(String rummagerPhone) {
		this.rummagerPhone = rummagerPhone;
	}


	public Date getEdttime() {
		return edttime;
	}


	public void setEdttime(Date edttime) {
		this.edttime = edttime;
	}


	public String getDelSign() {
		return delSign;
	}


	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

}
