package com.nateiot.cis.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.repository.DBUtil;

/**
 * 服务办事 -- 服务团队行表
 * 
 *  @author Guohw
 */
@Entity
@Table(name = "CIS_SW_TEAM_MEMBER")
public class CisSwTeamMember extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 团队成员ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MEMBER_ID")
	private Integer memberId;
	
	/**
	 *  服务团队主键
	 */
//	@Column(name = "TEAM_ID")
//	private Integer teamId;
	
	/**
	 *  成员编号
	 */
	@Column(name = "MEMBER_CODE")
	private String memberCode;
	
	/**
	 *  成员名称
	 */
	@Column(name = "MEMBER_NAME")
	private String memberName;
	
	/**
	 *  成员性别
	 */
	@Column(name = "MEMBER_GENDER")
	private String memberGender;
	
	/**
	 *  成员联系电话
	 */
	@Column(name = "PHONE")
	private String phone;
	
	/**
	 * 成员住址
	 */
	@Column(name = "ADDRESS")
	private String address;
	
	/**
	 *  成员年龄
	 */
	@Column(name = "AGE")
	private Integer age;
	
	/**
	 *  紧急联系人名称
	 */
	@Column(name = "CONTACTS_NAME")
	private String contactsName;
	
	/**
	 *  紧急联系人电话
	 */
	@Column(name = "CONTACTS_PHONE")
	private String contactsPhone;
	
	/**
	 *  删除标记
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 * 成员
	 */
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "TEAM_ID")
	private CisSwServiceTeam cisSwServiceTeam;
	
	/**
	 * 服务团队主键
	 */
	@Transient
	public Integer getTeamId() {
		return cisSwServiceTeam == null ? null : cisSwServiceTeam.getTeamId();
	}

	@Transient
	public void setTeamId(Integer teamId) {
		if (teamId != null) {
			this.cisSwServiceTeam = new CisSwServiceTeam();
			this.cisSwServiceTeam.setTeamId(teamId);
		}
//		this.cisSwServiceTeam = DBUtil.find(CisSwServiceTeam.class, teamId);
	}
	
	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getContactsName() {
		return contactsName;
	}

	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public CisSwServiceTeam getCisSwServiceTeam() {
		return cisSwServiceTeam;
	}

	public void setCisSwServiceTeam(CisSwServiceTeam cisSwServiceTeam) {
		this.cisSwServiceTeam = cisSwServiceTeam;
	}


}
