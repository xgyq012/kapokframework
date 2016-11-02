package com.gdgxwl.base.domain;

import com.gdgxwl.core.common.json.JsonDateTimeSerializer;
import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Will WM. Zhang
 *
 */
@Entity
@Table(name = "GXWL_SYS_MSG")
public class GxwlSysMsg extends BaseEntity {

	private static final long serialVersionUID = -4501403050993890868L;
	
	public enum MsgType {
		INTERNAL, SMS, EMAIL
	}
	
	/**
	 * 消息主键ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MSG_ID", nullable = false)
	private Integer msgId;

	/**
	 * 标题
	 */
	@Column(name = "MSG_TITLE", length = 200)
	private String msgTitle;
	
	/**
	 * 内容
	 */
	@Column(name = "MSG_BODY")
	private String msgBody;
	
	/**
	 * 消息类型
	 */
	@Column(name = "MSG_TYPE", length = 128)
	private MsgType msgType;
	
	/**
	 * 发送类型
	 */
	@Column(name = "SEND_TYPE", length = 128)
	private String sendType;
	
	/**
	 * 发信人
	 */
	@Column(name = "SEND_BY")
	private Integer sendBy;
	
	/**
	 * 发信人地址
	 */
	@Column(name = "SEND_ADDRESS", length = 200)
	private String sender;
	
	/**
	 * 是否已发送
	 */
	@Column(name = "IS_SENDED", length = 1)
	private String isSended;
	
	/**
	 * 发送完成时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "SENDED_TIME")
	private Date sendedTime;
	
	/**
	 * 发送失败时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "FAIL_TIME")
	private Date failTime;
	
	/**
	 * 来源单据类型
	 */
	@Column(name = "SOURCE_TYPE", length = 128)
	private String sourceType;
	
	/**
	 * 来源单据编码
	 */
	@Column(name = "SOURCE_CODE", length = 50)
	private String sourceCode;
	
	/**
	 * 来源单据id
	 */
	@Column(name = "SOURCE_ID")
	private Integer sourceId;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "msg")
	private List<GxwlSysMsgReciever> recievers;

	@Transient
	public String getReciever() {
		if (!CollectionUtils.isEmpty(this.recievers)) {
			StringBuffer sb = new StringBuffer();
			for (GxwlSysMsgReciever r : this.recievers) {
				sb.append("," + r.getReceiver());
			}
			return sb.toString().substring(1);
		} else {
			return "";
		}
	}
	
	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	public MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = MsgType.valueOf(msgType);
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public Integer getSendBy() {
		return sendBy;
	}

	public void setSendBy(Integer sendBy) {
		this.sendBy = sendBy;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getIsSended() {
		return isSended;
	}

	public void setIsSended(String isSended) {
		this.isSended = isSended;
	}

	public Date getSendedTime() {
		return sendedTime;
	}

	public void setSendedTime(Date sendedTime) {
		this.sendedTime = sendedTime;
	}

	public Date getFailTime() {
		return failTime;
	}

	public void setFailTime(Date failTime) {
		this.failTime = failTime;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public List<GxwlSysMsgReciever> getRecievers() {
		return recievers;
	}

	public void setRecievers(List<GxwlSysMsgReciever> recievers) {
		this.recievers = recievers;
	}
	
}
