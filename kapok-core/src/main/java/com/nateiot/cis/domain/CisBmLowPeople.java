package com.nateiot.cis.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 低保人员
 * @author xiaguangjun
 *
 */
@Entity
@Table(name="CIS_BM_LOW_PEOPLE")
public class CisBmLowPeople extends BaseEntity{
	
	/**
	 * 低保ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "DB_ID")
	private Integer dbId;
	
	
	/**
	 * 人员
	 */
	@Column(name = "HOUSEHOLDER_ID")
	private Integer householderId;
	
	
	/**
	 * 低保类型
	 */
	@Column(name = "DB_TYPE")
	private String dbType;
	
	
	/**
	 * 家庭人口
	 */
	@Column(name = "FAMILY_NUM")
	private Integer familyNum;
	
	
	/**
	 * 家庭月收入
	 */
	@Column(name = "FAMILY_AMOUNT")
	private Double familyAmount;
	
	
	/**
	 * 低保金额固定保障
	 */
	@Column(name = "DBJEGDB")
	private Double dbjegdb;
	
	
	/**
	 * 保障人口
	 */
	@Column(name = "BZRK")
	private Integer bzrk;
	
	
	/**
	 * 家庭月支出
	 */
	@Column(name = "JTYZC")
	private Double jtyzc;
	
	
	/**
	 * 走访记录
	 */
	@Column(name = "ZF_RECORD")
	private String zfRecord;
	
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;


	public Integer getDbId() {
		return dbId;
	}


	public void setDbId(Integer dbId) {
		this.dbId = dbId;
	}


	public Integer getHouseholderId() {
		return householderId;
	}


	public void setHouseholderId(Integer householderId) {
		this.householderId = householderId;
	}


	public String getDbType() {
		return dbType;
	}


	public void setDbType(String dbType) {
		this.dbType = dbType;
	}


	public Integer getFamilyNum() {
		return familyNum;
	}


	public void setFamilyNum(Integer familyNum) {
		this.familyNum = familyNum;
	}


	public Double getFamilyAmount() {
		return familyAmount;
	}


	public void setFamilyAmount(Double familyAmount) {
		this.familyAmount = familyAmount;
	}


	public Double getDbjegdb() {
		return dbjegdb;
	}


	public void setDbjegdb(Double dbjegdb) {
		this.dbjegdb = dbjegdb;
	}


	public Integer getBzrk() {
		return bzrk;
	}


	public void setBzrk(Integer bzrk) {
		this.bzrk = bzrk;
	}


	public Double getJtyzc() {
		return jtyzc;
	}


	public void setJtyzc(Double jtyzc) {
		this.jtyzc = jtyzc;
	}


	public String getZfRecord() {
		return zfRecord;
	}


	public void setZfRecord(String zfRecord) {
		this.zfRecord = zfRecord;
	}


	public String getDelSign() {
		return delSign;
	}


	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}
	
	
	
	
}
