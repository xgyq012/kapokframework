package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateSerializer;
import com.nateiot.core.repository.DBUtil;

/**
 * 党务建设 -- 党员公开承诺活动登记表
 * 
 * @author Guohw
 */
@Entity
@Table(name = "CIS_PA_MEMBER_PROMISE")
public class CisPaMemberPromise extends BaseEntity{
	
private static final long serialVersionUID = 1L;
	
	/**
	 * 主键ID 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PROMISE_ID", length = 10, nullable = false)
	private Integer promiseId;
	
	/**
	 *  所属网格ID
	 */
	@Column(name = "UNITS_ID")
	private Integer unitsId;
	
	/**
	 *  表格填写日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "WRITE_DATE")
	private Date writeDate;
	
	/**
	 *  乡(镇)
	 */
	@Column(name = "VILLAGE")
	private String village;
	
	/**
	 *  党支部
	 */
	@Column(name = "BRANCH")
	private String branch;
	
	/**
	 *  承诺事项
	 */
	@Column(name = "PROMISE_ITEM")
	private String promiseItem;
	
	/**
	 *  践诺情况
	 */
	@Column(name = "SITUATION")
	private String situation;
	
	/**
	 *  支部评定意见
	 */
	@Column(name = "SUGGESTION")
	private String suggestion;
	
	/**
	 *  评定日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "REVIEW")
	private Date review;
	
	/**
	 * 删除标记 
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 *  党员ID
	 */
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "MEMBER_ID", referencedColumnName = "HOUSEHOLDER_ID")
	private CisBmHouseholder houseHolder;
	
	@Transient
	public void setMemberId(Integer memberId){
		if(memberId != null){
			this.houseHolder = DBUtil.find(CisBmHouseholder.class, memberId);
		}
	}
	
	@Transient
	public Integer getMemberId(){
		return this.houseHolder == null ? null : this.houseHolder.getHouseholderId();
	}
	
	@Transient
	public String getName(){
		return this.houseHolder == null ? null : this.houseHolder.getHouseholderName();
	}
	
	@Transient
	public String getSex(){
		return this.houseHolder == null ? null : this.houseHolder.getSex();
	}
	
	@Transient
	public Integer getAge(){
		return this.houseHolder == null ? null : this.houseHolder.getAge();
	}
	
	public CisBmHouseholder getHouseHolder() {
		return houseHolder;
	}

	public void setHouseHolder(CisBmHouseholder houseHolder) {
		this.houseHolder = houseHolder;
	}

	public Integer getPromiseId() {
		return promiseId;
	}

	public void setPromiseId(Integer promiseId) {
		this.promiseId = promiseId;
	}

	public Integer getUnitsId() {
		return unitsId;
	}

	public void setUnitsId(Integer unitsId) {
		this.unitsId = unitsId;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getPromiseItem() {
		return promiseItem;
	}

	public void setPromiseItem(String promiseItem) {
		this.promiseItem = promiseItem;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public Date getReview() {
		return review;
	}

	public void setReview(Date review) {
		this.review = review;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

}
