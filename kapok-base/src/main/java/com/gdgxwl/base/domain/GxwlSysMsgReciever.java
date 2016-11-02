package com.gdgxwl.base.domain;

import com.gdgxwl.core.common.json.JsonDateTimeSerializer;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Will WM. Zhang
 *
 */
@Entity
@Table(name = "GXWL_SYS_MSG_RECIEVER")
public class GxwlSysMsgReciever extends BaseEntity {

	private static final long serialVersionUID = 7556723298618552514L;

	/**
	 * 主键ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RECEIVER_ID", nullable = false)
	private Integer receiverId;
	
	/**
	 * 消息主键
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "MSG_ID")
	private GxwlSysMsg msg;
	
	/**
	 * 接收人
	 */
	@Column(name = "RECEIVER_BY")
	private Integer receiverBy;
	
	/**
	 * 接收人姓名
	 */
	@Column(name = "RECEIVER_NAME", length = 200)
	private String receiverName;
	
	/**
	 * 接收类型
	 */
	@Column(name = "RECEIVER_TYPE", length = 128)
	private String receiverType;
	
	/**
	 * 接收地址
	 */
	@Column(name = "RECEIVER_ADDRESS", length = 200)
	private String receiver;
	
	/**
	 * 是否成功发送
	 */
	@Column(name = "IS_SEND", length = 1)
	private String isSend;
	
	/**
	 * 发送时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "SEND_TIME")
	private Date sendTime;
	
	/**
	 * 是否已读
	 */
	@Column(name = "IS_READ", length = 1)
	private String isRead;
	
	/**
	 * 阅读时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "READ_TIME")
	private Date readTime;

	public void setMsgId(Integer msgId) {
		if (msgId != null) {
			this.msg = new GxwlSysMsg();
			this.msg.setMsgId(msgId);
		}
	}
	
	@Transient
	public Integer getMsgId() {
		return this.msg == null ? null : this.msg.getMsgId();
	}
	
	@Transient
	public String getMsgTitle() {
		return this.msg == null ? null : this.msg.getMsgTitle();
	}
	
	@Transient
	public String getMsgBody() {
		return this.msg == null ? null : this.msg.getMsgBody();
	}
	
	@Transient
	public String getSender() {
		return this.msg == null ? null : this.msg.getSender();
	}
	
	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public GxwlSysMsg getMsg() {
		return msg;
	}

	public void setMsg(GxwlSysMsg msg) {
		this.msg = msg;
	}

	public Integer getReceiverBy() {
		return receiverBy;
	}

	public void setReceiverBy(Integer receiverBy) {
		this.receiverBy = receiverBy;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getIsSend() {
		return isSend;
	}

	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
	
}
