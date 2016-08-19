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
 * 从事邪教人员
 * @author xiaguangjun
 *
 */
@Entity
@Table(name="CIS_BM_CSXJRY")
public class CisBmCsxjry  extends BaseEntity {
	
		/**
		 * ID
		 */
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name = "XJ_ID")
		private Integer xjId;
		
		
		/**
		 * 人员id
		 */
		@Column(name = "HOUSEHOLDER_ID")
		private Integer householderId;
		
		
		/**
		 * 练功时间
		 */
		@Column(name = "LGSJ_DATE")
		@Temporal(TemporalType.DATE)
		@JsonSerialize(using = JsonDateSerializer.class)
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private Date lgsjDate;
		
		
		/**
		 * 转化情况
		 */
		@Column(name = "ZHQK")
		private String zhqk;
		
		
		/**
		 * 练功原因
		 */
		@Column(name = "LGYY")
		private String lgyy;
		
		
		/**
		 * 受打击处理情况
		 */
		@Column(name = "SDJCLQK")
		private String sdjclqk;
		
		
		/**
		 * 备注
		 */
		@Column(name = "REMARK")
		private String remark;
		
		
		/**
		 * 软删除
		 */
		@Column(name = "DEL_SIGN")
		private String delSign;
		


		public Integer getXjId() {
			return xjId;
		}


		public void setXjId(Integer xjId) {
			this.xjId = xjId;
		}


		public Integer getHouseholderId() {
			return householderId;
		}


		public void setHouseholderId(Integer householderId) {
			this.householderId = householderId;
		}


		public Date getLgsjDate() {
			return lgsjDate;
		}


		public void setLgsjDate(Date lgsjDate) {
			this.lgsjDate = lgsjDate;
		}


		public String getZhqk() {
			return zhqk;
		}


		public void setZhqk(String zhqk) {
			this.zhqk = zhqk;
		}


		public String getLgyy() {
			return lgyy;
		}


		public void setLgyy(String lgyy) {
			this.lgyy = lgyy;
		}


		public String getSdjclqk() {
			return sdjclqk;
		}


		public void setSdjclqk(String sdjclqk) {
			this.sdjclqk = sdjclqk;
		}


		public String getRemark() {
			return remark;
		}


		public void setRemark(String remark) {
			this.remark = remark;
		}


		public String getDelSign() {
			return delSign;
		}


		public void setDelSign(String delSign) {
			this.delSign = delSign;
		}

}
