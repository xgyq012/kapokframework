package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 值班室信息
 * 
 * @author huo
 * 
 */
@Entity
@Table(name = "CIS_BM_DURY_ROOM_INFO")
public class CisBmDuryroomInfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DURYROOM_ID")
	private Integer duryroomId;

	/**
	 * 所属机构
	 */
	@Column(name = "ORG_ID")
	private Integer orgId;

	/**
	 * 值班室名称
	 */
	@Column(name = "DURYROOM_NAME")
	private String duryroomName;

	/**
	 * 值班室电话
	 */
	@Column(name = "DURYROOM_PHONE")
	private String duryroomPhone;

	/**
	 * 值班室负责人
	 */
	@Column(name = "DURYROOM_DIRECTOR")
	private String duryroomDirector;
	
	/**
	 * 删除标记 
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;

	/**
	 * 负责人电话
	 */
	@Column(name = "DIRECTOR_PHONE")
	private String directorPhone;

	/**
	 * 电子监控
	 */
	@Column(name = "MONITORING")
	private String monitoring;

	/**
	 * 防火设施
	 */
	@Column(name = "FIREPROOFING")
	private String fireproofing;

	/**
	 * 防盗措施
	 */
	@Column(name = "PREVENTIVE_MEASURES")
	private String preventiveMeasures;

	public Integer getDuryroomId() {
		return duryroomId;
	}

	public void setDuryroomId(Integer duryroomId) {
		this.duryroomId = duryroomId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getDuryroomName() {
		return duryroomName;
	}

	public void setDuryroomName(String duryroomName) {
		this.duryroomName = duryroomName;
	}

	public String getDuryroomPhone() {
		return duryroomPhone;
	}

	public void setDuryroomPhone(String duryroomPhone) {
		this.duryroomPhone = duryroomPhone;
	}

	public String getDuryroomDirector() {
		return duryroomDirector;
	}

	public void setDuryroomDirector(String duryroomDirector) {
		this.duryroomDirector = duryroomDirector;
	}

	public String getDirectorPhone() {
		return directorPhone;
	}

	public void setDirectorPhone(String directorPhone) {
		this.directorPhone = directorPhone;
	}

	public String getMonitoring() {
		return monitoring;
	}

	public void setMonitoring(String monitoring) {
		this.monitoring = monitoring;
	}

	public String getFireproofing() {
		return fireproofing;
	}

	public void setFireproofing(String fireproofing) {
		this.fireproofing = fireproofing;
	}

	public String getPreventiveMeasures() {
		return preventiveMeasures;
	}

	public void setPreventiveMeasures(String preventiveMeasures) {
		this.preventiveMeasures = preventiveMeasures;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}
	

}
