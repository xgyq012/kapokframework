package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 服务团队人员信息
 * @author xiewenhua
 *
 */
@Entity
@Table(name = "CIS_SW_WAITER")
public class CisSwWaiter extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "WAITER_ID")
	private Integer waiterId;
	
	/**
	 * 所属机构ID
	 */
	@Column(name = "JIGOU_ID")
	private Integer jigouId;
	
	/**
	 * 职责
	 */
	@Column(name = "JOB")
	private String job;
	
	/**
	 * 队员ID
	 */
	@Column(name = "DUIYUAN_NAME")
	private String duiyuanName;
	
	/**
	 * 负责区域
	 */
	@Column(name = "SCOPE")
	private String scope;
	
	/**
	 * 队员联系方式
	 */
	@Column(name = "PHONE")
	private String phone;
	
	/**
	 * 头像
	 */
	@Column(name = "PHOTO")
	private String photo;
	
	/**
	 * 在职状态 有在职和离职两种状态，分别用状态码0和9表示
	 */
	@Column(name = "ZAIZHI_STATUS")
	private Integer zaizhiStatus;
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	public Integer getWaiterId() {
		return waiterId;
	}
	public void setWaiterId(Integer waiterId) {
		this.waiterId = waiterId;
	}
	public Integer getJigouId() {
		return jigouId;
	}
	public void setJigouId(Integer jigouId) {
		this.jigouId = jigouId;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getDuiyuanName() {
		return duiyuanName;
	}
	public void setDuiyuanName(String duiyuanName) {
		this.duiyuanName = duiyuanName;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getZaizhiStatus() {
		return zaizhiStatus;
	}
	public void setZaizhiStatus(Integer zaizhiStatus) {
		this.zaizhiStatus = zaizhiStatus;
	}
	public String getDelSign() {
		return delSign;
	}
	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

}
