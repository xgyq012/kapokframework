package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateTimeSerializer;

/**
 * 服务办事 -- 事件类型管理行表
 * 
 *  @author Guohw
 */
@Entity
@Table(name = "CIS_SW_EVENT_SLAVE")
public class CisSwEventSlave extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 事件类型行表主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SLAVE_ID")
	private Integer slaveId;
	
//	/**
//	 * 事件类型管理主键 
//	 */
//	@Column(name = "TYPE_ID")
//	private Integer typeId;
	
	/**
	 *  行表名称
	 */
	@Column(name = "SLAVE_NAME")
	private String slaveName;
	
	/**
	 *  超时(h)
	 */
//	@Temporal(TemporalType.TIMESTAMP)
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@JsonSerialize(using = JsonDateTimeSerializer.class)
//	@Column(name = "TIME_OUT")
//	private Date timeOut;
	@Column(name = "TIME_OUT")
	private Integer timeOut;
	
	/**
	 * 红/黄牌
	 */
	@Column(name = "RED_YELLOW_CARD")
	private String redYellowCard;
	
	/**
	 * 删除标记 
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 * 描述 
	 */
	@Column(name = "REMARK")
	private String remark;
	
	/**
	 * 红黄牌
	 */
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "TYPE_ID")
	private CisSwEventType cisSwEventType;

	public Integer getSlaveId() {
		return slaveId;
	}

	public void setSlaveId(Integer slaveId) {
		this.slaveId = slaveId;
	}
	
	/**
	 * 事件类型管理ID
	 */
	@Transient
	public Integer getTypeId() {
		return cisSwEventType == null ? null : cisSwEventType.getTypeId();
	}

	@Transient
	public void setTypeId(Integer typeId) {
		if (typeId != null) {
			this.cisSwEventType = new CisSwEventType();
			this.cisSwEventType.setTypeId(typeId);;
		}
	}

	public String getSlaveName() {
		return slaveName;
	}

	public void setSlaveName(String slaveName) {
		this.slaveName = slaveName;
	}

	public String getRedYellowCard() {
		return redYellowCard;
	}

	public void setRedYellowCard(String redYellowCard) {
		this.redYellowCard = redYellowCard;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public CisSwEventType getCisSwEventType() {
		return cisSwEventType;
	}

	public void setCisSwEventType(CisSwEventType cisSwEventType) {
		this.cisSwEventType = cisSwEventType;
	}

	public Integer getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}

//	public Integer getTypeId() {
//		return typeId;
//	}
//
//	public void setTypeId(Integer typeId) {
//		this.typeId = typeId;
//	}
	
}