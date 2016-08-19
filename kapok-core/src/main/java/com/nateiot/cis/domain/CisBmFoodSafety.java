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
 * 公共安全--食品安全
 * 
 * @author guohuawen
 * 
 */
@Entity
@Table(name = "CIS_BM_FOOD_SAFETY")
public class CisBmFoodSafety extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "SAFOODS_ID")
	private Integer safoodsId;
	
	
	/**
	 * 所属机构
	 */
	@Column(name = "ORG_ID")
	private Integer orgId;
	
	
	/**
	 * 商品名称
	 */
	@Column(name = "GOODS_NAME")
	private String goodsName;
	
	
	/**
	 * 商品类型
	 */
	@Column(name = "GOODS_TYPE")
	private String goodsType;
	
	
	/**
	 * 生产企业名称
	 */
	@Column(name = "ENTERPRISE_NAME")
	private String enterpriseName;
	
	
	/**
	 * 经销商名称
	 */
	@Column(name = "DEALER_NAME")
	private String dealerName;
	
	
	/**
	 * 经销商营业执照
	 */
	@Column(name = "DEALER_PERMIT")
	private String dealerPermit;
	
	
	/**
	 * 商品保质期
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "EXPIRATION_DATE")
	private Date expirationDate;
	
	
	/**
	 * 删除标记
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;


	public Integer getSafoodsId() {
		return safoodsId;
	}


	public void setSafoodsId(Integer safoodsId) {
		this.safoodsId = safoodsId;
	}


	public Integer getOrgId() {
		return orgId;
	}


	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


	public String getGoodsName() {
		return goodsName;
	}


	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	public String getGoodsType() {
		return goodsType;
	}


	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}


	public String getEnterpriseName() {
		return enterpriseName;
	}


	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}


	public String getDealerName() {
		return dealerName;
	}


	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}


	public String getDealerPermit() {
		return dealerPermit;
	}


	public void setDealerPermit(String dealerPermit) {
		this.dealerPermit = dealerPermit;
	}


	public Date getExpirationDate() {
		return expirationDate;
	}


	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}


	public String getDelSign() {
		return delSign;
	}


	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	
}
