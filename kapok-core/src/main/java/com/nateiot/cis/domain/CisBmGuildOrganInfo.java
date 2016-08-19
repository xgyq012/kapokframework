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
 * @author xiaguangjun
 * 工会信息
 */
@Entity
@Table(name="CIS_BM_GUILD_ORGAN_INFO")
public class CisBmGuildOrganInfo extends BaseEntity {
	


	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ORGANIZATION_ID")
	private Integer organizationId;
	
	
	/**
	 * 所属机构
	 */
	@Column(name = "ORG")
	private Integer org;
	
	
	/**
	 * 企业名称
	 */
	@Column(name = "ENTERPRISE_NAME")
	private String enterpriseName;
	
	
	/**
	 * 地址
	 */
	@Column(name = "ADDRESS")
	private String address;
	
	
	/**
	 * 法人
	 */
	@Column(name = "LEGAL_PER")
	private String legalPer;
	
	
	/**
	 * 法人联系电话
	 */
	@Column(name = "LEGAL_PER_PHONE")
	private String legalPerPhone;
	
	
	/**
	 * 会员人数
	 */
	@Column(name = "MEMBER")
	private Integer member;
	
	
	/**
	 * 工会主席
	 */
	@Column(name = "CHAIRMAN")
	private String chairman;
	
	
	/**
	 * 工会主席联系电话
	 */
	@Column(name = "CHAIRMAN_PHONE")
	private String chairmanPhone;
	
	
	/**
	 * 集体合同
	 */
	@Column(name = "COLLECTIVE_CONTRACT")
	private String collectiveContract;
	
	
	/**
	 * 工资专项合同
	 */
	@Column(name = "PAY_SPECIAL_CONTRACT")
	private String paySpecialContract;
	
	
	/**
	 * 女职工保护合同
	 */
	@Column(name = "PROTECTION_CONTRACT")
	private String protectionContract;
	
	
	/**
	 * 安全卫生合同
	 */
	@Column(name = "HEALTH_CONTRACT")
	private String healthContract;
	
	
	/**
	 * 独立建会
	 */
	@Column(name = "INDE_CONSTRACT")
	private String indeConstract;
	
	
	/**
	 * 独立建会成立时间
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "INDE_FOUNDING_TIME")
	private Date indeFoundingTime;
	
	
	/**
	 * 工会小组
	 */
	@Column(name = "TRADE_UNION")
	private String tradeUnion;
	
	
	/**
	 * 工会成立时间
	 */
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "TRADE_FOUNDING_TIME")
	private Date tradeFoundingTime;
	
	
	/**
	 * 职工书屋
	 */
	@Column(name = "RANDOM_EMPLOYEES")
	private String randomEmployees;
	
	
	/**
	 * 职工之家
	 */
	@Column(name = "WORKERS_HOME")
	private String workersHome;
	
	
	/**
	 * 职代会
	 */
	@Column(name = "WORKERS_CONGRESS")
	private String workersCongress;
	
	
	/**
	 * 劳动争议调解
	 */
	@Column(name = "DISPUTE_MEDIATION")
	private String disputeMediation;
	
	
	/**
	 * 女工委组织
	 */
	@Column(name = "WOMEN_COMMITTEE_ORGANIZATION")
	private String womenCommitteeOrganization;
	
	
	/**
	 * 企业性质
	 */
	@Column(name = "ENTER_NATURE")
	private String enterNature;
	
	
	/**
	 * 星级工会
	 */
	@Column(name = "STAR_UNION")
	private String starUnion;
	
	
	/**
	 * 奖罚情况
	 */
	@Column(name = "REWARD_PUNISHMENT")
	private String rewardPunishment;
	
	
	/**
	 * 经审委组织
	 */
	@Column(name = "COMM_ORGAN")
	private String commOrgan;


	public Integer getOrganizationId() {
		return organizationId;
	}


	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}


	public Integer getOrg() {
		return org;
	}


	public void setOrg(Integer org) {
		this.org = org;
	}


	public String getEnterpriseName() {
		return enterpriseName;
	}


	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getLegalPer() {
		return legalPer;
	}


	public void setLegalPer(String legalPer) {
		this.legalPer = legalPer;
	}


	public String getLegalPerPhone() {
		return legalPerPhone;
	}


	public void setLegalPerPhone(String legalPerPhone) {
		this.legalPerPhone = legalPerPhone;
	}


	public Integer getMember() {
		return member;
	}


	public void setMember(Integer member) {
		this.member = member;
	}


	public String getChairman() {
		return chairman;
	}


	public void setChairman(String chairman) {
		this.chairman = chairman;
	}


	public String getChairmanPhone() {
		return chairmanPhone;
	}


	public void setChairmanPhone(String chairmanPhone) {
		this.chairmanPhone = chairmanPhone;
	}


	public String getCollectiveContract() {
		return collectiveContract;
	}


	public void setCollectiveContract(String collectiveContract) {
		this.collectiveContract = collectiveContract;
	}


	public String getPaySpecialContract() {
		return paySpecialContract;
	}


	public void setPaySpecialContract(String paySpecialContract) {
		this.paySpecialContract = paySpecialContract;
	}


	public String getProtectionContract() {
		return protectionContract;
	}


	public void setProtectionContract(String protectionContract) {
		this.protectionContract = protectionContract;
	}


	public String getHealthContract() {
		return healthContract;
	}


	public void setHealthContract(String healthContract) {
		this.healthContract = healthContract;
	}


	public String getIndeConstract() {
		return indeConstract;
	}


	public void setIndeConstract(String indeConstract) {
		this.indeConstract = indeConstract;
	}


	public Date getIndeFoundingTime() {
		return indeFoundingTime;
	}


	public void setIndeFoundingTime(Date indeFoundingTime) {
		this.indeFoundingTime = indeFoundingTime;
	}


	public String getTradeUnion() {
		return tradeUnion;
	}


	public void setTradeUnion(String tradeUnion) {
		this.tradeUnion = tradeUnion;
	}


	public Date getTradeFoundingTime() {
		return tradeFoundingTime;
	}


	public void setTradeFoundingTime(Date tradeFoundingTime) {
		this.tradeFoundingTime = tradeFoundingTime;
	}


	public String getRandomEmployees() {
		return randomEmployees;
	}


	public void setRandomEmployees(String randomEmployees) {
		this.randomEmployees = randomEmployees;
	}


	public String getWorkersHome() {
		return workersHome;
	}


	public void setWorkersHome(String workersHome) {
		this.workersHome = workersHome;
	}


	public String getWorkersCongress() {
		return workersCongress;
	}


	public void setWorkersCongress(String workersCongress) {
		this.workersCongress = workersCongress;
	}


	public String getDisputeMediation() {
		return disputeMediation;
	}


	public void setDisputeMediation(String disputeMediation) {
		this.disputeMediation = disputeMediation;
	}


	public String getWomenCommitteeOrganization() {
		return womenCommitteeOrganization;
	}


	public void setWomenCommitteeOrganization(String womenCommitteeOrganization) {
		this.womenCommitteeOrganization = womenCommitteeOrganization;
	}


	public String getEnterNature() {
		return enterNature;
	}


	public void setEnterNature(String enterNature) {
		this.enterNature = enterNature;
	}


	public String getStarUnion() {
		return starUnion;
	}


	public void setStarUnion(String starUnion) {
		this.starUnion = starUnion;
	}


	public String getRewardPunishment() {
		return rewardPunishment;
	}


	public void setRewardPunishment(String rewardPunishment) {
		this.rewardPunishment = rewardPunishment;
	}


	public String getCommOrgan() {
		return commOrgan;
	}


	public void setCommOrgan(String commOrgan) {
		this.commOrgan = commOrgan;
	}
	

}
