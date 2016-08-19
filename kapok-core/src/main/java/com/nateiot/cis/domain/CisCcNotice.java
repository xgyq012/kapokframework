package com.nateiot.cis.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateTimeSerializer;
/**
 * 消息公告与短信消息
 * @author xiewenhua
 *
 */
@Entity
@Table(name = "CIS_CC_NOTICE")
public class CisCcNotice extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 接收消息公告的
	 */
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "cisCcNotice")
	private List<CisCcTargetUser> targetList;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "NOTICE_ID")
	private Integer noticeId;
	
	/**
	 * 消息标题
	 */
	@Column(name = "TITLE")
	private String title;
	
	/**
	 * 消息主体
	 */
	@Column(name = "BODY")
	private String body;
	
	/**
	 * 通知所属类别，有任务和通知两种，分别用数字1和2表示
	 */
	@Column(name = "NOTICE_CLASS")
	private Integer noticeClass;
	
	
	/**
	 * 消息的紧急程度
	 */
	@Column(name = "NOTICE_LEVEL")
	private Integer noticeLevel;
	
	/**
	 * 发送时间
	 */
	@Column(name = "SEND_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	private Date sendTime;
	
	/**
	 * 消息的发送状态
	 */
	@Column(name = "SEND_STATUS")
	private Integer sendStatus;
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNoticeClass() {
		return noticeClass;
	}

	public void setNoticeClass(Integer noticeClass) {
		this.noticeClass = noticeClass;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getNoticeLevel() {
		return noticeLevel;
	}

	public void setNoticeLevel(Integer noticeLevel) {
		this.noticeLevel = noticeLevel;
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

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public List<CisCcTargetUser> getTargetList() {
		return targetList;
	}

	public void setTargetList(List<CisCcTargetUser> targetList) {
		this.targetList = targetList;
	}
	

}
