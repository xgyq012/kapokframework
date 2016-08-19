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
 * 公共安全--消防信息
 * 
 * @author guohuawen
 * 
 */
@Entity
@Table(name = "CIS_BM_FIRE_CONTROL")
public class CisBmFireControl extends BaseEntity{

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COFIRE_ID")
	private Integer cofireId;
	
	
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
	 * 检查人
	 */
	@Column(name = "RUMMAGER")
	private String rummager;
	
	
	/**
	 * 电话
	 */
	@Column(name = "RUMMAGER_PHONE")
	private String rummagerPhone;
	
	
	/**
	 * 是否圈占、堵塞消防车通道
	 */
	@Column(name = "CONGESTED")
	private String congested;
	
	
	/**
	 * 是否在公共区域乱拉乱接电线，超负荷用电
	 */
	@Column(name = "OVERLOAD")
	private String overload;
	
	
	/**
	 * 是否在住宅内、地下室储存易燃易爆物品
	 */
	@Column(name = "EXPLOSIVES")
	private String explosives;
	
	
	/**
	 * 共用消防设施、器材是否丢失、损坏，是否有埋压、圈占、损坏消火栓等公共消防设施的现象
	 */
	@Column(name = "DAMAGE")
	private String damage;
	
	
	/**
	 * 住宅楼内疏散楼梯是否堆放杂物
	 */
	@Column(name = "STAIRS")
	private String stairs;
	
	
	/**
	 * 相关图片
	 */
	@Column(name = "PICTURE_ID")
	private Integer pictureId;
	
	
	/**
	 * 其他
	 */
	@Column(name = "OTHERS")
	private String others;
	
	
	/**
	 * 检查日期
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "EDTTIME")
	private Date edttime;
	
	
	/**
	 * 人数
	 */
	@Column(name = "PEOPLES")
	private Integer peoples;
	
	
	/**
	 * 行业类型
	 */
	@Column(name = "INDUSTRY_TYPES")
	private String industryTypes;
	
	
	/**
	 * 法人
	 */
	@Column(name = "CORP")
	private String corp;
	
	
	/**
	 * 法人联系电话
	 */
	@Column(name = "CORP_PHONE")
	private String corpPhone;
	
	
	/**
	 * 消防负责人
	 */
	@Column(name = "FIRE_PRINCIPAL")
	private String firePrincipal;
	
	
	/**
	 * 删除标记
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;


	public Integer getCofireId() {
		return cofireId;
	}


	public void setCofireId(Integer cofireId) {
		this.cofireId = cofireId;
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


	public String getCongested() {
		return congested;
	}


	public void setCongested(String congested) {
		this.congested = congested;
	}


	public String getOverload() {
		return overload;
	}


	public void setOverload(String overload) {
		this.overload = overload;
	}


	public String getExplosives() {
		return explosives;
	}


	public void setExplosives(String explosives) {
		this.explosives = explosives;
	}


	public String getDamage() {
		return damage;
	}


	public void setDamage(String damage) {
		this.damage = damage;
	}


	public String getStairs() {
		return stairs;
	}


	public void setStairs(String stairs) {
		this.stairs = stairs;
	}


	public Integer getPictureId() {
		return pictureId;
	}


	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}


	public String getOthers() {
		return others;
	}


	public void setOthers(String others) {
		this.others = others;
	}


	public Date getEdttime() {
		return edttime;
	}


	public void setEdttime(Date edttime) {
		this.edttime = edttime;
	}


	public Integer getPeoples() {
		return peoples;
	}


	public void setPeoples(Integer peoples) {
		this.peoples = peoples;
	}


	public String getIndustryTypes() {
		return industryTypes;
	}


	public void setIndustryTypes(String industryTypes) {
		this.industryTypes = industryTypes;
	}


	public String getCorp() {
		return corp;
	}


	public void setCorp(String corp) {
		this.corp = corp;
	}


	public String getCorpPhone() {
		return corpPhone;
	}


	public void setCorpPhone(String corpPhone) {
		this.corpPhone = corpPhone;
	}


	public String getFirePrincipal() {
		return firePrincipal;
	}


	public void setFirePrincipal(String firePrincipal) {
		this.firePrincipal = firePrincipal;
	}


	public String getDelSign() {
		return delSign;
	}


	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}


 
}
