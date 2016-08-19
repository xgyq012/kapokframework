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
 * 考核督办 -- 民情日记统计
 * 
 * @author guohuawen
 * 
 */
@Entity
@Table(name = "CIS_ES_MODIARY")
public class CisEsModiary extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 民情日记主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MODIARY_ID")
	private Integer moDiaryId;
	
	/**
	 * 编码
	 */
	@Column(name = "MODIARY_CODE")
	private String moDiaryCode;
	
	/**
	 * 所属机构
	 */
	@Column(name = "MODIARY_UNIT")
	private String moDiaryUnit;
	
	/**
	 * 姓名
	 */
	@Column(name = "MODIARY_NAME")
	private String moDiaryName;
	
	/**
	 * 优
	 */
	@Column(name = "ACTOR")
	private String actor;
	
	/**
	 * 良
	 */
	@Column(name = "FINE")
	private String fine;
	
	/**
	 * 中
	 */
	@Column(name = "MIDDLE")
	private String middel;
	
	/**
	 * 差
	 */
	@Column(name = "BAD")
	private String bad;
	
	/**
	 * 核计分值
	 */
	@Column(name = "TOTAL")
	private String total;

	public Integer getMoDiaryId() {
		return moDiaryId;
	}

	public void setMoDiaryId(Integer moDiaryId) {
		this.moDiaryId = moDiaryId;
	}

	public String getMoDiaryCode() {
		return moDiaryCode;
	}

	public void setMoDiaryCode(String moDiaryCode) {
		this.moDiaryCode = moDiaryCode;
	}

	public String getMoDiaryUnit() {
		return moDiaryUnit;
	}

	public void setMoDiaryUnit(String moDiaryUnit) {
		this.moDiaryUnit = moDiaryUnit;
	}

	public String getMoDiaryName() {
		return moDiaryName;
	}

	public void setMoDiaryName(String moDiaryName) {
		this.moDiaryName = moDiaryName;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getFine() {
		return fine;
	}

	public void setFine(String fine) {
		this.fine = fine;
	}

	public String getMiddel() {
		return middel;
	}

	public void setMiddel(String middel) {
		this.middel = middel;
	}

	public String getBad() {
		return bad;
	}

	public void setBad(String bad) {
		this.bad = bad;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
}
