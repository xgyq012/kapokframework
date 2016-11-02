package com.gdgxwl.base.domain;

import com.gdgxwl.core.common.json.JsonDateTimeSerializer;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gxwl_sys_operatelog")
public class GxwlSysOperatelog extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 日志ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LOG_ID")
	private Integer logId;

	/**
	 * 操作类型
	 */
	@Column(name = "OPERATE_TYPE", length = 128, nullable = false)
	private String operateType;
	
	/**
	 * 操作人
	 */
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "USER_ID", nullable = false)
	private GxwlSysUser operator;

	/**
	 * 操作说明
	 */
	@Column(name = "LOG_DESC", length = 4000, nullable = false)
	private String logDesc;
	
	/**
	 * 操作时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "OPERATE_TIME", nullable = false)
	private Date operateTime;
	
	/**
	 * 记录ID
	 */
	@Column(name = "ORDER_ID")
	private Integer orderId;
	
	/**
	 * 操作单号
	 */
	@Column(name = "ORDER_CODE", length = 50)
	private String orderCode;
	
	/**
	 * 资源ID
	 */
	@Column(name = "RESOURCE_ID")
	private Integer resourceId;

	/**
	 * 模块类型
	 */
	@Column(name = "MODEL_TYPE", length = 128)
	private String modelType;
	
	@Transient
	public String getRealname() {
		return this.operator == null ? "" : this.operator.getRealname();
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public GxwlSysUser getOperator() {
		return operator;
	}

	public void setOperator(GxwlSysUser operator) {
		this.operator = operator;
	}

	public String getLogDesc() {
		return logDesc;
	}

	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

}