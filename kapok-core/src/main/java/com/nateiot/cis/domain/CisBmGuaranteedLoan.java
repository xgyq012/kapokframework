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
 * 小额担保贷款
 * 
 * @author guohuawen
 * 
 */
@Entity
@Table(name = "CIS_BM_GUARANTEED_LOAN")
public class CisBmGuaranteedLoan extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GUARANTEED_ID")
	private Integer guaranteedId;

	/**
	 * 所属机构
	 */
	@Column(name = "ORG_ID")
	private String orgId;

	/**
	 * 贷款人
	 */
	@Column(name = "BORROWER")
	private String borrower;

	/**
	 * 贷款人性别
	 */
	@Column(name = "BORROWER_GENDER")
	private String borrowerGender;
	
	/**
	 * 身份证号
	 */
	@Column(name = "ID_NUM")
	private String idNum;
	
	/**
	 * 联系电话
	 */
	@Column(name = "LINK_PHONE")
	private String linkPhone;
	
	/**
	 * 居住地
	 */
	@Column(name = "ADDRESS")
	private String address;
	
	/**
	 * 担保人
	 */
	@Column(name = "BONDSMAN")
	private String bondsman;
	
	/**
	 * 债权人
	 */
	@Column(name = "CREDITOR")
	private String creditor;
	
	/**
	 * 贷款金额
	 */
	@Column(name = "LOAN_AMOUNT")
	private String loanAmount;
	
	/**
	 * 已还款金额
	 */
	@Column(name = "PAID_AMOUNT")
	private String paidAmount;
	
	/**
	 * 是否已还款
	 */
	@Column(name = "IS_REFUND")
	private String isRefund;
	
	/**
	 * 贷款日期
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "LOAN_DATE")
	private Date loanDate;
	
	/**
	 * 还款日期
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "REFUND_DATE")
	private Date refundDate;
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	private String remark;

	public Integer getGuaranteedId() {
		return guaranteedId;
	}

	public void setGuaranteedId(Integer guaranteedId) {
		this.guaranteedId = guaranteedId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public String getBorrowerGender() {
		return borrowerGender;
	}

	public void setBorrowerGender(String borrowerGender) {
		this.borrowerGender = borrowerGender;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getBondsman() {
		return bondsman;
	}

	public void setBondsman(String bondsman) {
		this.bondsman = bondsman;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Date getRefundDate() {
		return refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	public String getIsRefund() {
		return isRefund;
	}

	public void setIsRefund(String isRefund) {
		this.isRefund = isRefund;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreditor() {
		return creditor;
	}

	public void setCreditor(String creditor) {
		this.creditor = creditor;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
