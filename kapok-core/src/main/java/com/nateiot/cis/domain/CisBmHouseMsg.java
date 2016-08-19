package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.repository.DBUtil;


/**
 * @author xiaguangjun
 * 房屋信息
 */
@Entity
@Table(name = "CIS_BM_HOUSE_MSG")
public class CisBmHouseMsg extends BaseEntity{
	

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "HOUSE_ID")
	private Integer houseId;
	
	/**
	 * 单元号
	 */
	@Column(name = "DY_CODE")
	private String dyCode;
	
	
	/**
	 * 所属机构
	 */
	@Column(name = "ORG")
	private Integer org;
	
	
	/**
	 * 楼栋号
	 */
	/*@Column(name = "BUILD_ID")
	private Integer buildId;*/
	
	
	/**
	 * 小区号
	 */
	/*@Column(name = "COM_ID")
	private Integer comId;*/
	
	
	
	/**
	 * 房主姓名
	 */
	@Column(name = "HOUSE_USERNAME")
	private String houseUsername;
	
	
	/**
	 * 房主身份证号
	 */
	@Column(name = "CARD_ID")
	private String cardId;
	
	
	/**
	 * 联系电话
	 */
	@Column(name = "PHONE")
	private String phone;
	
	
	/**
	 * 共有人姓名
	 */
	@Column(name = "GYR_NAME")
	private String gyrName;
	
	
	/**
	 * 现居住人姓名
	 */
	@Column(name = "XZ_NAME")
	private String xzName;
	
	
	/**
	 * 联系电话2
	 */
	@Column(name = "TEL")
	private String tel;
	
	
	/**
	 * 居民人数
	 */
	@Column(name = "JMRS")
	private Integer jmrs;
	
	
	/**
	 * 住房面积
	 */
	@Column(name = "ZFMJ")
	private String zfmj;
	
	
	/**
	 * 房证号码
	 */
	@Column(name = "FCZ_CODE")
	private String fczCode;
	
	
	/**
	 * 是否一房多户
	 */
	@Column(name = "IS_YFSH")
	private String isYfsh;
	
	
	/**
	 * 房屋性质
	 */
	@Column(name = "FWXZ")
	private String fwxz;
	
	
	/**
	 * 是否自用或出租
	 */
	@Column(name = "IS_ZJ")
	private String isZj;
	
	
	/**
	 * 电子监控
	 */
	@Column(name = "DZJK")
	private String dzjk;
	
	
	/**
	 * 电话报警
	 */
	@Column(name = "DHBJ")
	private String dhbj;
	
	
	/**
	 * 防盗门
	 */
	@Column(name = "FDM")
	private String fdm;
	
	/**
	 * 经度
	 */
	@Column(name = "LON")
	private String lon;
	
	
	/**
	 * 维度
	 */
	@Column(name = "LAT")
	private String lat;
	
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="BUILD_ID",nullable=true,unique=false)
	private CisBmBuildingMsg cisBmBuildingMsg;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="COM_ID",nullable=true,unique=false)
	private CisBmCommunityMsg cisBmCommunityMsg;
	
	
	public void setComId(Integer comId) {
		if(comId!=null){
			this.setCisBmCommunityMsg(DBUtil.find(CisBmCommunityMsg.class, comId));
		}
	}
	
	public void setBuildId(Integer buildId) {
		if(buildId!=null){
			this.setCisBmBuildingMsg(DBUtil.find(CisBmBuildingMsg.class, buildId));
		}
	}
	
	
	@Transient
	public Integer getComId() {
		
		return this.cisBmCommunityMsg ==null ? null : getCisBmCommunityMsg().getComId();
	}
	
	@Transient
	public Integer getBuildId() {
		
		return this.cisBmBuildingMsg == null ? null : getCisBmBuildingMsg().getBuildId();
	}
	
	@Transient
	public String getLdCode() {
		
		return getCisBmBuildingMsg() ==null ? "" : getCisBmBuildingMsg().getLdCode();
	}
	
	@Transient
	public String getCommunityName() {
		
		return getCisBmCommunityMsg() ==null ? "" : getCisBmCommunityMsg().getCommunityName();
	}


	public CisBmBuildingMsg getCisBmBuildingMsg() {
		return cisBmBuildingMsg;
	}


	public void setCisBmBuildingMsg(CisBmBuildingMsg cisBmBuildingMsg) {
		this.cisBmBuildingMsg = cisBmBuildingMsg;
	}


	public CisBmCommunityMsg getCisBmCommunityMsg() {
		return cisBmCommunityMsg;
	}


	public void setCisBmCommunityMsg(CisBmCommunityMsg cisBmCommunityMsg) {
		this.cisBmCommunityMsg = cisBmCommunityMsg;
	}


	public Integer getHouseId() {
		return houseId;
	}


	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}


	public String getDyCode() {
		return dyCode;
	}


	public void setDyCode(String dyCode) {
		this.dyCode = dyCode;
	}


	public Integer getOrg() {
		return org;
	}


	public void setOrg(Integer org) {
		this.org = org;
	}


	public String getHouseUsername() {
		return houseUsername;
	}


	public void setHouseUsername(String houseUsername) {
		this.houseUsername = houseUsername;
	}


	public String getCardId() {
		return cardId;
	}


	public void setCardId(String cardId) {
		this.cardId = cardId;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getGyrName() {
		return gyrName;
	}


	public void setGyrName(String gyrName) {
		this.gyrName = gyrName;
	}


	public String getXzName() {
		return xzName;
	}


	public void setXzName(String xzName) {
		this.xzName = xzName;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public Integer getJmrs() {
		return jmrs;
	}


	public void setJmrs(Integer jmrs) {
		this.jmrs = jmrs;
	}


	public String getZfmj() {
		return zfmj;
	}


	public void setZfmj(String zfmj) {
		this.zfmj = zfmj;
	}


	public String getFczCode() {
		return fczCode;
	}


	public void setFczCode(String fczCode) {
		this.fczCode = fczCode;
	}


	public String getIsYfsh() {
		return isYfsh;
	}


	public void setIsYfsh(String isYfsh) {
		this.isYfsh = isYfsh;
	}


	public String getFwxz() {
		return fwxz;
	}


	public void setFwxz(String fwxz) {
		this.fwxz = fwxz;
	}


	public String getIsZj() {
		return isZj;
	}


	public void setIsZj(String isZj) {
		this.isZj = isZj;
	}


	public String getDzjk() {
		return dzjk;
	}


	public void setDzjk(String dzjk) {
		this.dzjk = dzjk;
	}


	public String getDhbj() {
		return dhbj;
	}


	public void setDhbj(String dhbj) {
		this.dhbj = dhbj;
	}


	public String getFdm() {
		return fdm;
	}


	public void setFdm(String fdm) {
		this.fdm = fdm;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
	
	

}
