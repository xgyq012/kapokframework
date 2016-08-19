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
 * 长期涉法涉诉人员
 * @author xiaguangjun
 *
 */
@Table(name = "CIS_BM_CQSFSSRY")
@Entity
public class CisBmCqsfssry extends BaseEntity {
		
		/**
		 * ID
		 */
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name = "CF_ID")
		private Integer cfId;
		
		
		/**
		 * 人员id
		 */
		@Column(name = "HOUSEHOLDER_ID")
		private Integer householderId;
		
		
		/**
		 * 涉法涉诉单位
		 */
		@Column(name = "SFSSDW_NAME")
		private String sfssdwName;
		
		
		/**
		 * 涉法涉诉人员
		 */
		@Column(name = "SFSSRY_NAME")
		private String sfssryName;
		
		
		/**
		 * 案件包保单位
		 */
		@Column(name = "AJBBDW")
		private String ajbbdw;
		
		
		/**
		 * 包保人员
		 */
		@Column(name = "BBRY_NAME")
		private String bbryName;
		
		
		/**
		 * 涉法涉诉起始时间
		 */
		@Column(name = "SFSSQS_DATE")
		@Temporal(TemporalType.DATE)
		@JsonSerialize(using = JsonDateSerializer.class)
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date sfssqsDate;
		
		
		/**
		 * 涉法涉诉事由
		 */
		@Column(name = "SFSS_REASON")
		private String sfssReason;
		
		
		/**
		 * 现阶段处理情况
		 */
		@Column(name = "XJDCLQK")
		private String xjdclqk;
		
		
		/**
		 * 软删除
		 */
		@Column(name = "DEL_SIGN")
		private String delSign;


		public Integer getCfId() {
			return cfId;
		}


		public void setCfId(Integer cfId) {
			this.cfId = cfId;
		}


		public Integer getHouseholderId() {
			return householderId;
		}


		public void setHouseholderId(Integer householderId) {
			this.householderId = householderId;
		}


		public String getSfssdwName() {
			return sfssdwName;
		}


		public void setSfssdwName(String sfssdwName) {
			this.sfssdwName = sfssdwName;
		}


		public String getSfssryName() {
			return sfssryName;
		}


		public void setSfssryName(String sfssryName) {
			this.sfssryName = sfssryName;
		}


		public String getAjbbdw() {
			return ajbbdw;
		}


		public void setAjbbdw(String ajbbdw) {
			this.ajbbdw = ajbbdw;
		}


		public String getBbryName() {
			return bbryName;
		}


		public void setBbryName(String bbryName) {
			this.bbryName = bbryName;
		}


		public Date getSfssqsDate() {
			return sfssqsDate;
		}


		public void setSfssqsDate(Date sfssqsDate) {
			this.sfssqsDate = sfssqsDate;
		}


		public String getSfssReason() {
			return sfssReason;
		}


		public void setSfssReason(String sfssReason) {
			this.sfssReason = sfssReason;
		}


		public String getXjdclqk() {
			return xjdclqk;
		}


		public void setXjdclqk(String xjdclqk) {
			this.xjdclqk = xjdclqk;
		}


		public String getDelSign() {
			return delSign;
		}


		public void setDelSign(String delSign) {
			this.delSign = delSign;
		}

}
