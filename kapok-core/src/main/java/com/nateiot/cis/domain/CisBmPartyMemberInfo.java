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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateSerializer;
import com.nateiot.core.repository.DBUtil;


/**
 * @author xiaguangjun
 * 党员信息
 */
@Entity
@Table(name="CIS_BM_PARTY_MEMBER_INFO")
public class CisBmPartyMemberInfo extends BaseEntity {

		
		/**
		 * 主键
		 */
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name = "PARTY_ID")
		private Integer partyId;
		
		
		
		/**
		 * 关联ID
		 */
		@Column(name = "HOUSEHOLDER_ID")
		private Integer householderId;
		
		
		/**
		 * 党组织类别
		 */
		@Column(name = "ORGANIZATION_TYPE")
		private String organizationType;
		
		
		/**
		 * 直管党员
		 */
		@Column(name = "STRAIGHT_PARTY_MEMBER")
		private String straightPartyMember;
		
		
		/**
		 * 流动党员
		 */
		@Column(name = "FLOATING_PARTY_MEMBERS")
		private String floatingPartyMembers;
		
		
		/**
		 * 困难党员
		 */
		@Column(name = "DIFFICULT_PARTY_MEMBERS")
		private String difficultPartyMembers;
		
		
		/**
		 * 建国前老党员
		 */
		@Column(name = "OLD_PARTY_MEMBERS")
		private String  oldPartyMembers;
		
		
		/**
		 * 民主评议结果
		 */
		@Column(name = "APPRAISAL_RESULT")
		private String  appraisalResult;
		
		
		/**
		 * 奖惩情况
		 */
		@Column(name = "REWARDS_RESULT")
		private String  rewardsResult;
		
		
		/**
		 * 培训情况
		 */
		@Column(name = "TRAIN_RESULT")
		private String  trainResult;
		
		
		/**
		 * 关系接转
		 */
		@Column(name = "RELATIONSHIP_TRANSFER")
		private String  relationshipTransfer;
		
		
		/**
		 * 党组织关系是否在社区
		 */
		@Column(name = "IS_INCOMMUNITY")
		private String isIncommunity;
		
		
		/**
		 * 入党时间
		 */
		@Temporal(TemporalType.DATE)
		@JsonSerialize(using = JsonDateSerializer.class)
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@Column(name = "IN_PARTY_TIME")
		private Date inPartyTime;


		public Integer getPartyId() {
			return partyId;
		}


		public void setPartyId(Integer partyId) {
			this.partyId = partyId;
		}


		public Integer getHouseholderId() {
			return householderId;
		}


		public void setHouseholderId(Integer householderId) {
			this.householderId = householderId;
		}


		public String getOrganizationType() {
			return organizationType;
		}


		public void setOrganizationType(String organizationType) {
			this.organizationType = organizationType;
		}


		public String getStraightPartyMember() {
			return straightPartyMember;
		}


		public void setStraightPartyMember(String straightPartyMember) {
			this.straightPartyMember = straightPartyMember;
		}


		public String getFloatingPartyMembers() {
			return floatingPartyMembers;
		}


		public void setFloatingPartyMembers(String floatingPartyMembers) {
			this.floatingPartyMembers = floatingPartyMembers;
		}


		public String getDifficultPartyMembers() {
			return difficultPartyMembers;
		}


		public void setDifficultPartyMembers(String difficultPartyMembers) {
			this.difficultPartyMembers = difficultPartyMembers;
		}


		public String getOldPartyMembers() {
			return oldPartyMembers;
		}


		public void setOldPartyMembers(String oldPartyMembers) {
			this.oldPartyMembers = oldPartyMembers;
		}


		public String getAppraisalResult() {
			return appraisalResult;
		}


		public void setAppraisalResult(String appraisalResult) {
			this.appraisalResult = appraisalResult;
		}


		public String getRewardsResult() {
			return rewardsResult;
		}


		public void setRewardsResult(String rewardsResult) {
			this.rewardsResult = rewardsResult;
		}


		public String getTrainResult() {
			return trainResult;
		}


		public void setTrainResult(String trainResult) {
			this.trainResult = trainResult;
		}


		public String getRelationshipTransfer() {
			return relationshipTransfer;
		}


		public void setRelationshipTransfer(String relationshipTransfer) {
			this.relationshipTransfer = relationshipTransfer;
		}


		public String getIsIncommunity() {
			return isIncommunity;
		}


		public void setIsIncommunity(String isIncommunity) {
			this.isIncommunity = isIncommunity;
		}


		public Date getInPartyTime() {
			return inPartyTime;
		}


		public void setInPartyTime(Date inPartyTime) {
			this.inPartyTime = inPartyTime;
		}

		
}
