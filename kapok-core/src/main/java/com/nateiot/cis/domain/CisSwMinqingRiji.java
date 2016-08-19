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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateTimeSerializer;

/**
 * 民情日记
 * @author xiewenhua
 *
 */
@Entity
@Table(name = "CIS_SW_MINQING_RIJI")
public class CisSwMinqingRiji extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "MINQING_RIJI_ID")
	private Integer minqingRijiId;
	
	/**
	 * 日记标题
	 */
	@Column(name = "TITLE")
	private String title;
	
	/**
	 * 日记内容
	 */
	@Column(name = "BODY")
	private String body;
	
	/**
	 * 日记状态  有草稿 ，发布，下撤三种状态，分别用数字012表示
	 */
	@Column(name = "RIJI_STATUS")
	private Integer rijiStatus;
	
	/**
	 * 发布时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "SUBMIT_TIME", nullable = true)
	private Date submitTime;
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	public Integer getMinqingRijiId() {
		return minqingRijiId;
	}
	public void setMinqingRijiId(Integer minqingRijiId) {
		this.minqingRijiId = minqingRijiId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Integer getRijiStatus() {
		return rijiStatus;
	}
	public void setRijiStatus(Integer rijiStatus) {
		this.rijiStatus = rijiStatus;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public String getDelSign() {
		return delSign;
	}
	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

}
