package com.nateiot.cis.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;


/**
 * 服务办事 -- 事件类型管理
 * 
 *  @author Guohw
 */
@Entity
@Table(name = "CIS_SW_EVENT_TYPE")
public class CisSwEventType extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 事件类型管理主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TYPE_ID")
	private Integer typeId;
	
	/**
	 * 事件类型
	 */
	@Column(name = "EVENT_TYPE")
	private String eventType;
	
	/**
	 * 事件类别
	 */
	@Column(name = "TYPE_NAME")
	private String typeName;
	
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
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "cisSwEventType")
	private List<CisSwEventSlave> cisSwEventSlave;

	public Integer getTypeId() {
		return typeId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public List<CisSwEventSlave> getCisSwEventSlave() {
		return cisSwEventSlave;
	}

	public void setCisSwEventSlave(List<CisSwEventSlave> cisSwEventSlave) {
		this.cisSwEventSlave = cisSwEventSlave;
	}
	
}
