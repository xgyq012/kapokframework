package com.nateiot.cis.domain;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;


/**
 * 老年人信息
 * @author xiaguangjun
 *
 */
@Table(name = "CIS_BM_OLD_PEOPLE_H")
@Entity
public class CisBmOldPeopleH extends BaseEntity  {
	
		
		/**
		 * ID
		 */
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "OP_ID")
		private Integer opId;
		
		
		/**
		 * 人员ID
		 */
		@Column(name = "HOUSEHOLDER_ID")
		private Integer householderId;
		
		
		/**
		 * 经济状态
		 */
		@Column(name = "ECONOMIC")
		private String economic;
		
		
		/**
		 * 药物过敏史
		 */
		@Column(name = "YWGMS")
		private String ywgms;
		
		
		/**
		 * 居住方式
		 */
		@Column(name = "JZFS")
		private String jzfs;
		
		
		/**
		 * 服务需求
		 */
		@Column(name = "FWXQ")
		private String fwxq;
		
		
		/**
		 * 软删除
		 */
		@Column(name = "DEL_SIGN")
		private String delSign;
		
		
		@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
				CascadeType.REMOVE }, mappedBy = "cisBmOldPeopleH")
		private List<CisBmOldPeopleL> cisBmOldPeopleL;


		
		public List<CisBmOldPeopleL> getCisBmOldPeopleL() {
			return cisBmOldPeopleL;
		}


		public void setCisBmOldPeopleL(List<CisBmOldPeopleL> cisBmOldPeopleL) {
			this.cisBmOldPeopleL = cisBmOldPeopleL;
		}


		public Integer getOpId() {
			return opId;
		}


		public void setOpId(Integer opId) {
			this.opId = opId;
		}


		public Integer getHouseholderId() {
			return householderId;
		}


		public void setHouseholderId(Integer householderId) {
			this.householderId = householderId;
		}


		public String getEconomic() {
			return economic;
		}


		public void setEconomic(String economic) {
			this.economic = economic;
		}


		public String getYwgms() {
			return ywgms;
		}


		public void setYwgms(String ywgms) {
			this.ywgms = ywgms;
		}


		public String getJzfs() {
			return jzfs;
		}


		public void setJzfs(String jzfs) {
			this.jzfs = jzfs;
		}


		public String getFwxq() {
			return fwxq;
		}


		public void setFwxq(String fwxq) {
			this.fwxq = fwxq;
		}


		public String getDelSign() {
			return delSign;
		}


		public void setDelSign(String delSign) {
			this.delSign = delSign;
		}
		
 
 
}

