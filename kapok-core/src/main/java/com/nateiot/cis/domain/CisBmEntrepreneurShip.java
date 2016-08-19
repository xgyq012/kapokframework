package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 创业商户
 * 
 * @author guohuawen
 * 
 */
@Entity
@Table(name = "CIS_BM_ENTREPRENEUR_SHIP")
public class CisBmEntrepreneurShip extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ENTREPRENEURSHIP_ID")
	private Integer entrepreneurShipId;

	/**
	 * 所属机构
	 */
	@Column(name = "ORG_ID")
	private String orgId;

	/**
	 * 门店名称
	 */
	@Column(name = "SHOP_NAME")
	private String shopName;

	/**
	 * 联系电话
	 */
	@Column(name = "LINK_PHONE")
	private String linkPhone;
	
	/**
	 * 法人代表
	 */
	@Column(name = "CORP")
	private String corp;

	/**
	 * 经营范围
	 */
	@Column(name = "BUSINESS_SCOPE")
	private String businessScope;
	
	/**
	 * 享受优惠政策情况
	 */
	@Column(name = "PRE_POLICIES")
	private String prePolicies;

	/**
	 * 已享受政策
	 */
	@Column(name = "ENJOY_POLICY")
	private String enjoyPolicy;

	/**
	 * 可享受政策
	 */
	@Column(name = "CAN_ENJOY_POLICY")
	private String canEnjoyPolicy;

	public Integer getEntrepreneurShipId() {
		return entrepreneurShipId;
	}

	public void setEntrepreneurShipId(Integer entrepreneurShipId) {
		this.entrepreneurShipId = entrepreneurShipId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getCorp() {
		return corp;
	}

	public void setCorp(String corp) {
		this.corp = corp;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getPrePolicies() {
		return prePolicies;
	}

	public void setPrePolicies(String prePolicies) {
		this.prePolicies = prePolicies;
	}

	public String getEnjoyPolicy() {
		return enjoyPolicy;
	}

	public void setEnjoyPolicy(String enjoyPolicy) {
		this.enjoyPolicy = enjoyPolicy;
	}

	public String getCanEnjoyPolicy() {
		return canEnjoyPolicy;
	}

	public void setCanEnjoyPolicy(String canEnjoyPolicy) {
		this.canEnjoyPolicy = canEnjoyPolicy;
	}
	
	
}
