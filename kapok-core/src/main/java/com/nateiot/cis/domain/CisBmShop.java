package com.nateiot.cis.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import com.nateiot.base.domain.BaseEntity;

/**
 * 门店信息
 * @author xiaguangjun
 */

@Entity
@Table(name = "cis_bm_shop")
public class CisBmShop extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SHOP_ID")
	private Integer shopId;
	
	/**
	 *  所属机构
	 */
	@Column(name = "SSJG")
	private Integer ssjg;
	
	/**
	 *  门店名称
	 */
	@Column(name = "MDMC")
	private String mdmc;
	
	/**
	 *  门店地址
	 */
	@Column(name = "MDDZ")
    private String mddz;
	
	/**
	 *  经营范围
	 */
	@Column(name = "JYFW")
	private String jyfw;
	
	/**
	 * 删除标记 
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 * 用工人数
	 */
	@Column(name = "YGRS")
	private Integer ygrs;
	
	/**
	 *  法人
	 */
	@Column(name = "FR")
	private String fr;
	
	/**
	 *  联系电话
	 */
	@Column(name = "DH")
	private String dh;
	
	/**
	 *  备注
	 */
	@Column(name = "BZ")
	private String bz;
	
	/**
	 * 经度
	 */
	@Column(name = "LON")
	private String lon;
	
	/**
	 * 纬度
	 */
	@Column(name = "LAT")
	private String lat;
	
	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getSsjg() {
		return ssjg;
	}

	public void setSsjg(Integer ssjg) {
		this.ssjg = ssjg;
	}

	public String getMdmc() {
		return mdmc;
	}

	public void setMdmc(String mdmc) {
		this.mdmc = mdmc;
	}

	public String getMddz() {
		return mddz;
	}

	public void setMddz(String mddz) {
		this.mddz = mddz;
	}

	public String getJyfw() {
		return jyfw;
	}

	public void setJyfw(String jyfw) {
		this.jyfw = jyfw;
	}

	public Integer getYgrs() {
		return ygrs;
	}

	public void setYgrs(Integer ygrs) {
		this.ygrs = ygrs;
	}

	public String getFr() {
		return fr;
	}

	public void setFr(String fr) {
		this.fr = fr;
	}

	public String getDh() {
		return dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
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
