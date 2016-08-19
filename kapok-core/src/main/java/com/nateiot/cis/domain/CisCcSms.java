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
 * 指挥中心  短信消息
 * @author xiewenhua
 *
 */
@Entity
@Table(name = "CIS_CC_SMS")
public class CisCcSms extends BaseEntity{

	private static final long serialVersionUID = 1L;

	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SMS_ID")
	private Integer smsId;
	
	/**
	 * 短信消息内容
	 */
	@Column(name = "SMS_BODY")
	private String smsBody;
	
	/**
	 * 接收消息的机构的id列表，多个对象时用符号“,”分割
	 */
	@Column(name = "TARGET_JIGOUS")
	private String targetJigous;
	
	/**
	 * 接收消息的个人的id列表，多个对象时用符号“,”分割
	 */
	@Column(name = "TARGET_USERS")
	private String targetUsers;
	/**
	 * 消息发送时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "SEND_TIME")
	private Date sendTime;
	
	/**
	 * 消息发送状态
	 */
	@Column(name = "SEND_STATUS")
	private Integer sendStatus;
	
	
	public Integer getSmsId() {
		return smsId;
	}
	public void setSmsId(Integer smsId) {
		this.smsId = smsId;
	}
	public String getSmsBody() {
		return smsBody;
	}
	public void setSmsBody(String smsBody) {
		this.smsBody = smsBody;
	}
	public String getTargetJigous() {
		return targetJigous;
	}
	public void setTargetJigous(String targetJigous) {
		this.targetJigous = targetJigous;
	}
	public String getTargetUsers() {
		return targetUsers;
	}
	public void setTargetUsers(String targetUsers) {
		this.targetUsers = targetUsers;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Integer getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}
	
}
