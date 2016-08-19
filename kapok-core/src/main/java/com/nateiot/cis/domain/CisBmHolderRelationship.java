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
import com.nateiot.base.domain.GxwlSysDictL;
import com.nateiot.core.repository.DBUtil;

@Entity
@Table(name="CIS_BM_HOLDER_RELATIONSHIP")
public class CisBmHolderRelationship  {
	

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "RELATION_ID")
	private Integer relationId;
	
	
	/**
	 * 人口ID
	 */
	/*@Column(name = "HOUSEHOLDER_ID")
	private Integer householderId;*/
	
	
	/**
	 * 户主ID
	 */
	@Column(name = "FOLLOW_ID")
	private Integer followId;
	
	
	/**
	 * 关系类型
	 */
	@Column(name = "HOLDER_RELATIONSHIP")
	private String holderRelationship;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="HOUSEHOLDER_ID",nullable=true,unique=false)
	private CisBmHouseholder cisBmHouseholder;
	
	@Transient
	public String getCardCode(){
		
		return this.getCisBmHouseholder() ==null ? "" : this.cisBmHouseholder.getCardCode();
	}
	
	@Transient
	public String getHouseholderName(){
		
		return this.cisBmHouseholder.getHouseholderName();
	}
	
	@Transient
	public String getDyCode(){
		
		return this.cisBmHouseholder.getCisBmHouseMsg() ==null ? "" : this.cisBmHouseholder.getCisBmHouseMsg().getDyCode();
	}


	public Integer getRelationId() {
		return relationId;
	}


	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	@Transient
	public Integer getHouseholderId() {
		return  this.cisBmHouseholder == null ? null :  getCisBmHouseholder().getHouseholderId();
	}

	public void setHouseholderId(Integer householderId) {
		if(householderId!=null){
			this.setCisBmHouseholder(DBUtil.find(CisBmHouseholder.class, householderId));
		}
	}


	public CisBmHouseholder getCisBmHouseholder() {
		return cisBmHouseholder;
	}


	public void setCisBmHouseholder(CisBmHouseholder cisBmHouseholder) {
		this.cisBmHouseholder = cisBmHouseholder;
	}


	public Integer getFollowId() {
		return followId;
	}


	public void setFollowId(Integer followId) {
		this.followId = followId;
	}


	public String getHolderRelationship() {
		return holderRelationship;
	}


	public void setHolderRelationship(String holderRelationship) {
		this.holderRelationship = holderRelationship;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((relationId == null) ? 0 : relationId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CisBmHolderRelationship other = (CisBmHolderRelationship) obj;
		if (relationId == null) {
			if (other.relationId != null)
				return false;
		} else if (!relationId.equals(other.relationId))
			return false;
		return true;
	}




	
	
}
