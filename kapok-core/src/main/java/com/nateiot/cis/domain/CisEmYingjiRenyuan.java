package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 应急人员信息
 * @author xiewenhua
 *
 */
@Entity
@Table(name = "CIS_EM_YINGJI_RENYUAN")
public class CisEmYingjiRenyuan extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "YINGJI_RENYUAN_ID")
	private Integer yingjiRenyuanId;
	
	/**
	 * 应急事件类型id
	 */
	@Column(name = "YJSJLX_ID")
	private Integer yjsjlxId;
	
	
	/**
	 * 应急事件类型名称
	 */
	@Column(name = "YJSJLX_NAME")
	private String yjsjlxName;
	
	/**
	 * 应急人员名称
	 */
	@Column(name = "YINGJIREN_NAME")
	private String yingjirenName;
	
	/**
	 * 应急人员性别
	 */
	@Column(name = "YINGJIREN_SEX")
	private String yingjirenSex;
	
	/**
	 * 照片ID
	 */
	@Column(name = "PHOTOFILE_ID")
	private Integer photofileID;
	
	
	@Column(name = "DOC_SHOWNAME")
	private String  docShowname;
	
	/**
	 * 应急人员身份证号
	 */
	@Column(name = "YINGJIREN_IDCARD")
	private String yingjirenIdcard;
	
	/**
	 * 应急人员电话
	 */
	@Column(name = "PHONE")
	private String phone;
	
	/**
	 * 应急人员邮箱
	 */
	@Column(name = "EMAIL")
	private String email;
	
	/**
	 * 应急人员邮箱
	 */
	@Column(name = "PS")
	private String ps;
	
	/**
	 * 应急人员电话
	 */
	@Column(name = "ZHIWEI")
	private String zhiwei;
	
	/**
	 * 在职状态
	 */
	@Column(name = "ZAIZHI_STATUS")
	private Integer zaizhiStatus;
	
	/**
	 * 应急人员常住地址
	 */
	@Column(name = "ADDRESS")
	private String address;
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;

	public Integer getYingjiRenyuanId() {
		return yingjiRenyuanId;
	}

	public void setYingjiRenyuanId(Integer yingjiRenyuanId) {
		this.yingjiRenyuanId = yingjiRenyuanId;
	}

	public Integer getYjsjlxId() {
		return yjsjlxId;
	}

	public void setYjsjlxId(Integer yjsjlxId) {
		this.yjsjlxId = yjsjlxId;
	}

	public String getYjsjlxName() {
		return yjsjlxName;
	}

	public void setYjsjlxName(String yjsjlxName) {
		this.yjsjlxName = yjsjlxName;
	}

	public String getYingjirenName() {
		return yingjirenName;
	}

	public void setYingjirenName(String yingjirenName) {
		this.yingjirenName = yingjirenName;
	}

	public String getYingjirenSex() {
		return yingjirenSex;
	}

	public void setYingjirenSex(String yingjirenSex) {
		this.yingjirenSex = yingjirenSex;
	}

	public String getYingjirenIdcard() {
		return yingjirenIdcard;
	}

	public void setYingjirenIdcard(String yingjirenIdcard) {
		this.yingjirenIdcard = yingjirenIdcard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}



	public Integer getPhotofileID() {
		return photofileID;
	}

	public void setPhotofileID(Integer photofileID) {
		this.photofileID = photofileID;
	}

	public String getDocShowname() {
		return docShowname;
	}

	public void setDocShowname(String docShowname) {
		this.docShowname = docShowname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getZaizhiStatus() {
		return zaizhiStatus;
	}

	public void setZaizhiStatus(Integer zaizhiStatus) {
		this.zaizhiStatus = zaizhiStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public String getZhiwei() {
		return zhiwei;
	}

	public void setZhiwei(String zhiwei) {
		this.zhiwei = zhiwei;
	}

}
