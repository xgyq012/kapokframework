package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.transaction.annotation.Transactional;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.base.domain.GxwlSysUser;
import com.nateiot.core.repository.DBUtil;

/**
 * 服务办事 -- 事件登记行表
 * 
 *  @author Guohw
 */
@Entity
@Table(name = "CIS_SW_ENROLL_ROWTABLE")
public class CisSwEnrollRowTable extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 事件登记行表主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROWTABLE_ID")
	private Integer rowTableId;
	
	/**
	 *  事件登记ID
	 */
	@Column(name = "ENROLL_ID")
	private Integer enrollId;

	/**
	 *  行表名称
	 */
	@Column(name = "ROWTABLE_NAME")
	private String rowTableName;

	/**
	 *  处理过程
	 */
	@Column(name = "PROCESS")
	private String process;

	/**
	 *  待办机构(人)
	 */
	@Column(name = "BACKLOG_ONE")
	private String backLogOne;
	
	/**
	 *  待办机构ID
	 */
	@Column(name = "BACKLOG_ID")
	private Integer backLogId;

	/**
	 * 操作人ID 
	 */
	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "OPERATE_ID")
	private GxwlSysUser operateUser;

	/**
	 *  操作人
	 */
	@Column(name = "OPERATE")
	private String operate;

	/**
	 *  意见
	 */
	@Column(name = "REMARK")
	private String remark;
	
	/**
	 * 积分字段 
	 */
	@Column(name = "SCORE_DETAIL")
	private String scoreDetail;
	
	public GxwlSysUser getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(GxwlSysUser operateUser) {
		this.operateUser = operateUser;
	}
	
	@Transactional
	public Integer getOperateId(){
		return this.operateUser == null ? null : this.operateUser.getUserId();
	}
	
	public void setOperateId(Integer operateId){
		if(operateId != null){
			this.operateUser = DBUtil.find(GxwlSysUser.class, operateId);
		}
	}
	
	@Transactional
	public String getOperate(){
		return this.operateUser ==  null ? null : this.operateUser.getRealname();
	}
	
	public void setOperate(String operateName){
		
	}
	
	public Integer getRowTableId() {
		return rowTableId;
	}

	public void setRowTableId(Integer rowTableId) {
		this.rowTableId = rowTableId;
	}

	public Integer getEnrollId() {
		return enrollId;
	}

	public void setEnrollId(Integer enrollId) {
		this.enrollId = enrollId;
	}

	public String getRowTableName() {
		return rowTableName;
	}

	public void setRowTableName(String rowTableName) {
		this.rowTableName = rowTableName;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getBackLogOne() {
		return backLogOne;
	}

	public void setBackLogOne(String backLogOne) {
		this.backLogOne = backLogOne;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getBackLogId() {
		return backLogId;
	}

	public void setBackLogId(Integer backLogId) {
		this.backLogId = backLogId;
	}

	public String getScoreDetail() {
		return scoreDetail;
	}

	public void setScoreDetail(String scoreDetail) {
		this.scoreDetail = scoreDetail;
	}
	
}
