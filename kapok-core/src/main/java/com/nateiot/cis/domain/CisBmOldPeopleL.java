package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.nateiot.core.repository.DBUtil;

/**
 * 老年人信息子表
 * @author xiaguangjun
 *
 */
@Table(name="CIS_BM_OLD_PEOPLE_L")
@Entity
public class CisBmOldPeopleL {
	
	
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "P_ID")
	private Integer pId;
	
	
	/**
	 * 老年人id
	 */
	/*@Column(name = "OP_ID")
	private Integer opId;*/
	
	
	/**
	 * 子女姓名一
	 */
	@Column(name = "CHILDREN_NAME")
	private String childrenName;
	
	
	/**
	 * 工作单位
	 */
	@Column(name = "GZDW")
	private String gzdw;
	
	
	/**
	 * 联系电话
	 */
	@Column(name = "CALL_PHONE")
	private String callPhone;
	
	
	/**
	 * 家庭详细住址
	 */
	@Column(name = "JTXX_ADDRESS")
	private String jtxxAddress;
	
	
	@ManyToOne
	@JoinColumn(name = "OP_ID")
	@JsonIgnore
	private CisBmOldPeopleH cisBmOldPeopleH;


	public Integer getpId() {
		return pId;
	}


	public void setpId(Integer pId) {
		this.pId = pId;
	}
	
	@Transient
	public Integer getOpId() {

		return cisBmOldPeopleH == null ? null : cisBmOldPeopleH.getOpId();
	}


	public void setOpId(Integer opId) {
		this.cisBmOldPeopleH = DBUtil.find(CisBmOldPeopleH.class, opId);
	}


	public CisBmOldPeopleH getCisBmOldPeopleH() {
		return cisBmOldPeopleH;
	}


	public void setCisBmOldPeopleH(CisBmOldPeopleH cisBmOldPeopleH) {
		this.cisBmOldPeopleH = cisBmOldPeopleH;
	}


	public String getChildrenName() {
		return childrenName;
	}


	public void setChildrenName(String childrenName) {
		this.childrenName = childrenName;
	}


	public String getGzdw() {
		return gzdw;
	}


	public void setGzdw(String gzdw) {
		this.gzdw = gzdw;
	}


	public String getCallPhone() {
		return callPhone;
	}


	public void setCallPhone(String callPhone) {
		this.callPhone = callPhone;
	}


	public String getJtxxAddress() {
		return jtxxAddress;
	}


	public void setJtxxAddress(String jtxxAddress) {
		this.jtxxAddress = jtxxAddress;
	}
	
	 
}

