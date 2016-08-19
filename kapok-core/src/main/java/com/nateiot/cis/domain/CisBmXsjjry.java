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
import com.nateiot.core.common.json.JsonDateTimeSerializer;

/**
 * 刑释解教人员
 * @author xiaguangjun
 *
 */
@Table(name="CIS_BM_XSJJRY")
@Entity
public class CisBmXsjjry extends BaseEntity{

		/**
		 * ID
		 */
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name = "X_ID")
		private Integer xId;
		
		
		/**
		 * 人员id
		 */
		@Column(name = "HOUSEHOLDER_ID")
		private Integer householderId;
		
		
		/**
		 * 是否农村籍
		 */
		@Column(name = "IS_NCJ")
		private String isNcj;
		
		
		/**
		 * 原判刑罚及期限
		 */
		@Column(name = "YPXFJQX")
		private String ypxfjqx;
		
		
		/**
		 * 释解日期
		 */
		@Temporal(TemporalType.DATE)
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		@JsonSerialize(using = JsonDateSerializer.class)
		@Column(name = "XJRQ_DATE")
		private Date xjrqDate;
		
		
		/**
		 * 罪名
		 */
		@Column(name = "ZM")
		private String zm;
		
		
		/**
		 * 详细住址
		 */
		@Column(name = "ADDRESS")
		private String address;
		
		
		/**
		 * 工作状况
		 */
		@Column(name = "GZZK")
		private String gzzk;
		
		
		/**
		 * 软删除
		 */
		@Column(name = "DEL_SIGN")
		private String delSign;


		public Integer getxId() {
			return xId;
		}


		public void setxId(Integer xId) {
			this.xId = xId;
		}


		public Integer getHouseholderId() {
			return householderId;
		}


		public void setHouseholderId(Integer householderId) {
			this.householderId = householderId;
		}


		public String getIsNcj() {
			return isNcj;
		}


		public void setIsNcj(String isNcj) {
			this.isNcj = isNcj;
		}


		public String getYpxfjqx() {
			return ypxfjqx;
		}


		public void setYpxfjqx(String ypxfjqx) {
			this.ypxfjqx = ypxfjqx;
		}


		public Date getXjrqDate() {
			return xjrqDate;
		}


		public void setXjrqDate(Date xjrqDate) {
			this.xjrqDate = xjrqDate;
		}


		public String getZm() {
			return zm;
		}


		public void setZm(String zm) {
			this.zm = zm;
		}


		public String getAddress() {
			return address;
		}


		public void setAddress(String address) {
			this.address = address;
		}


		public String getGzzk() {
			return gzzk;
		}


		public void setGzzk(String gzzk) {
			this.gzzk = gzzk;
		}


		public String getDelSign() {
			return delSign;
		}


		public void setDelSign(String delSign) {
			this.delSign = delSign;
		}
	
}
