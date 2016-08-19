package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 创业之星
 * 
 * @author guohuawen
 * 
 */
@Entity
@Table(name = "CIS_BM_ENTREPRENEUR_STAR")
public class CisBmEntrepreneurStar extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ENTREPRENEURSTAR_ID")
	private Integer entrepreneurStarId;

	/**
	 * 所属机构
	 */
	@Column(name = "ORG_ID")
	private String orgId;

	/**
	 * 创业者姓名
	 */
	@Column(name = "ENTSTAR_NAME")
	private String entstarName;

	/**
	 * 创业者性别
	 */
	@Column(name = "ENTSTAR_GENDER")
	private String entstarGender;
	
	/**
	 * 身份证号
	 */
	@Column(name = "ID_NUM")
	private String idNum;

	/**
	 * 联系电话
	 */
	@Column(name = "LINK_PHONE")
	private String linkPhone;
	
	/**
	 * 门店名称
	 */
	@Column(name = "SHOP_DEED")
	private String shopDeed;
	
	/**
	 * 事迹简介
	 */
	@Column(name = "DEED")
	private String deed;

	public Integer getEntrepreneurStarId() {
		return entrepreneurStarId;
	}

	public void setEntrepreneurStarId(Integer entrepreneurStarId) {
		this.entrepreneurStarId = entrepreneurStarId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getEntstarName() {
		return entstarName;
	}

	public void setEntstarName(String entstarName) {
		this.entstarName = entstarName;
	}

	public String getEntstarGender() {
		return entstarGender;
	}

	public void setEntstarGender(String entstarGender) {
		this.entstarGender = entstarGender;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getShopDeed() {
		return shopDeed;
	}

	public void setShopDeed(String shopDeed) {
		this.shopDeed = shopDeed;
	}

	public String getDeed() {
		return deed;
	}

	public void setDeed(String deed) {
		this.deed = deed;
	}
	
}
