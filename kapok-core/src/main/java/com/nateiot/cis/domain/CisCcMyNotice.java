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
import com.nateiot.core.common.json.JsonDateTimeSerializer;
/**
 * 个人公告消息
 * @author 2
 *
 */
@Entity
@Table(name="CIS_CC_MY_NOTICE")
public class CisCcMyNotice extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MY_NOTICE_ID")
	private Integer myNoticeId;
	
	/**
	 * 消息公告id
	 */
	@Column(name = "NOTICE_ID")
	private Integer noticeId;
	
	/**
	 * 用户id
	 */
	@Column(name = "USER_ID")
	private Integer userId;
	
	/**
	 * 查阅状态
	 */
	@Column(name = "READ_STATUS")
	private Integer readStatus;
	
	/**
	 * 消息被查阅时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "READ_TIME")
	private Date readTime;
	public Integer getMyNoticeId() {
		return myNoticeId;
	}
	public void setMyNoticeId(Integer myNoticeId) {
		this.myNoticeId = myNoticeId;
	}
	public Integer getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getReadStatus() {
		return readStatus;
	}
	public void setReadStatus(Integer readStatus) {
		this.readStatus = readStatus;
	}
	public Date getReadTime() {
		return readTime;
	}
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

}
