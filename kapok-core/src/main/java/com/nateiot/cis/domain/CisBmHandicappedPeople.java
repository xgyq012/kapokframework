package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 残疾人
 * @author xiaguangjun
 *
 */
@Table(name="CIS_BM_HANDICAPPED_PEOPLE")
@Entity
public class CisBmHandicappedPeople extends BaseEntity{

		/**
		 * ID
		 */
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name = "H_ID")
		private Integer hId;
		
		
		/**
		 * 人员id
		 */
		@Column(name = "HOUSEHOLDER_ID")
		private Integer householderId;
		
		
		/**
		 * 残疾人类别
		 */
		@Column(name = "CJR_TYPE")
		private String cjrType;
		
		
		/**
		 * 残疾等级
		 */
		@Column(name = "CJR_LEVEL")
		private String cjrLevel;
		
		
		/**
		 * 残疾人证号
		 */
		@Column(name = "CJR_CODE")
		private String cjrCode;
		
		
		/**
		 * 残疾部位
		 */
		@Column(name = "CJBW")
		private String cjbw;
		
		
		/**
		 * 致残原因
		 */
		@Column(name = "CJYY")
		private String cjyy;
		
		
		/**
		 * 软删除
		 */
		@Column(name = "DEL_SIGN")
		private String delSign;


		public Integer gethId() {
			return hId;
		}


		public void sethId(Integer hId) {
			this.hId = hId;
		}


		public Integer getHouseholderId() {
			return householderId;
		}


		public void setHouseholderId(Integer householderId) {
			this.householderId = householderId;
		}


		public String getCjrType() {
			return cjrType;
		}


		public void setCjrType(String cjrType) {
			this.cjrType = cjrType;
		}


		public String getCjrLevel() {
			return cjrLevel;
		}


		public void setCjrLevel(String cjrLevel) {
			this.cjrLevel = cjrLevel;
		}


		public String getCjrCode() {
			return cjrCode;
		}


		public void setCjrCode(String cjrCode) {
			this.cjrCode = cjrCode;
		}


		public String getCjbw() {
			return cjbw;
		}


		public void setCjbw(String cjbw) {
			this.cjbw = cjbw;
		}


		public String getCjyy() {
			return cjyy;
		}


		public void setCjyy(String cjyy) {
			this.cjyy = cjyy;
		}


		public String getDelSign() {
			return delSign;
		}


		public void setDelSign(String delSign) {
			this.delSign = delSign;
		}
		

 
}
