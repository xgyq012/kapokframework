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
 * 社区矫正对象
 * @author xiaguangjun
 *
 */
@Entity
@Table(name="CIS_BM_SQJZDX")
public class CisBmSqjzdx extends BaseEntity{

		/**
		 * ID
		 */
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name = "JZ_ID")
		private Integer jzId;
		
		
		/**
		 * 人员id
		 */
		@Column(name = "HOUSEHOLDER_ID")
		private Integer householderId;
		
		
		/**
		 * 审判机关
		 */
		@Column(name = "SPJG")
		private String spjg;
		
		
		/**
		 * 刑罚
		 */
		@Column(name = "XF")
		private String xf;
		
		
		/**
		 * 单位或地址
		 */
		@Column(name = "ADDRESS")
		private String address;
		
		
		/**
		 * 生效日时期
		 */
		@Temporal(TemporalType.DATE)
		@JsonSerialize(using = JsonDateSerializer.class)
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@Column(name = "SXRQ_DATE")
		private Date sxrqDate;
		
		
		/**
		 * 判决日期
		 */
		@Temporal(TemporalType.DATE)
		@JsonSerialize(using = JsonDateSerializer.class)
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@Column(name = "PJRQ_DATE")
		private Date pjrqDate;
		
		
		/**
		 * 社区矫正起止时间
		 */
		@Temporal(TemporalType.DATE)
		@JsonSerialize(using = JsonDateSerializer.class)
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@Column(name = "SQJZQZSJ_DATE")
		private Date sqjzqzsjDate;
		
		
		/**
		 * 判决罪名
		 */
		@Column(name = "PJZM")
		private String pjzm;
		
		
		/**
		 * 社区矫正类型
		 */
		@Column(name = "SQJZ_TYPE")
		private String sqjzType;
		
		
		/**
		 * 家庭监护人
		 */
		@Column(name = "JTJHR_NAME")
		private String jtjhrName;
		
		
		/**
		 * 社区监护人
		 */
		@Column(name = "SQJHR_NAME")
		private String sqjhrName;
		
		
		/**
		 * 软删除
		 */
		@Column(name = "DEL_SIGN")
		private String delSign;


		public Integer getJzId() {
			return jzId;
		}


		public void setJzId(Integer jzId) {
			this.jzId = jzId;
		}


		public Integer getHouseholderId() {
			return householderId;
		}


		public void setHouseholderId(Integer householderId) {
			this.householderId = householderId;
		}


		public String getSpjg() {
			return spjg;
		}


		public void setSpjg(String spjg) {
			this.spjg = spjg;
		}


		public String getXf() {
			return xf;
		}


		public void setXf(String xf) {
			this.xf = xf;
		}


		public String getAddress() {
			return address;
		}


		public void setAddress(String address) {
			this.address = address;
		}


		public Date getSxrqDate() {
			return sxrqDate;
		}


		public void setSxrqDate(Date sxrqDate) {
			this.sxrqDate = sxrqDate;
		}


		public Date getPjrqDate() {
			return pjrqDate;
		}


		public void setPjrqDate(Date pjrqDate) {
			this.pjrqDate = pjrqDate;
		}


		public Date getSqjzqzsjDate() {
			return sqjzqzsjDate;
		}


		public void setSqjzqzsjDate(Date sqjzqzsjDate) {
			this.sqjzqzsjDate = sqjzqzsjDate;
		}


		public String getPjzm() {
			return pjzm;
		}


		public void setPjzm(String pjzm) {
			this.pjzm = pjzm;
		}


		public String getSqjzType() {
			return sqjzType;
		}


		public void setSqjzType(String sqjzType) {
			this.sqjzType = sqjzType;
		}


		public String getJtjhrName() {
			return jtjhrName;
		}


		public void setJtjhrName(String jtjhrName) {
			this.jtjhrName = jtjhrName;
		}


		public String getSqjhrName() {
			return sqjhrName;
		}


		public void setSqjhrName(String sqjhrName) {
			this.sqjhrName = sqjhrName;
		}


		public String getDelSign() {
			return delSign;
		}


		public void setDelSign(String delSign) {
			this.delSign = delSign;
		}

		
 
}
