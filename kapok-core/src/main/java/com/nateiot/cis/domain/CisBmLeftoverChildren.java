package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 留守儿童
 * @author xiaguangjun
 *
 */
@Table(name="CIS_BM_LEFTOVER_CHILDREN")
@Entity
public class CisBmLeftoverChildren extends BaseEntity {
			
				
		
		/**
		 * ID
		 */
		@Id
		@Column(name = "CHILDREN_ID")
		@GeneratedValue(strategy=GenerationType.AUTO)
		private Integer childrenId;
		
		
		/**
		 * 人员ID
		 */
		@Column(name = "HOUSEHOLDER_ID")
		private Integer householderId;
		
		
		/**
		 * 父亲
		 */
		@Column(name = "FATCHER_NAME")
		private String fatcherName;
		
		
		/**
		 * 打工地
		 */
		@Column(name = "FATCHER_WORKER")
		private String fatcherWorker;
		
		
		/**
		 * 母亲
		 */
		@Column(name = "MOTHER_NAME")
		private String motherName;
		
		
		/**
		 * 打工地2
		 */
		@Column(name = "MOTHER_WORKER")
		private String motherWorker;
		
		
		/**
		 * 是否入学
		 */
		@Column(name = "IS_SCHOOL")
		private String isSchool;
		
		
		/**
		 * 软删除
		 */
		@Column(name = "DEL_SIGN")
		private String delSign;


		public Integer getChildrenId() {
			return childrenId;
		}


		public void setChildrenId(Integer childrenId) {
			this.childrenId = childrenId;
		}


		public Integer getHouseholderId() {
			return householderId;
		}


		public void setHouseholderId(Integer householderId) {
			this.householderId = householderId;
		}


		public String getFatcherName() {
			return fatcherName;
		}


		public void setFatcherName(String fatcherName) {
			this.fatcherName = fatcherName;
		}


		public String getFatcherWorker() {
			return fatcherWorker;
		}


		public void setFatcherWorker(String fatcherWorker) {
			this.fatcherWorker = fatcherWorker;
		}


		public String getMotherName() {
			return motherName;
		}


		public void setMotherName(String motherName) {
			this.motherName = motherName;
		}


		public String getMotherWorker() {
			return motherWorker;
		}


		public void setMotherWorker(String motherWorker) {
			this.motherWorker = motherWorker;
		}


		public String getIsSchool() {
			return isSchool;
		}


		public void setIsSchool(String isSchool) {
			this.isSchool = isSchool;
		}


		public String getDelSign() {
			return delSign;
		}


		public void setDelSign(String delSign) {
			this.delSign = delSign;
		}

 
	
}
