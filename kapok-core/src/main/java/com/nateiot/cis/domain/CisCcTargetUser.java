package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.base.domain.GxwlSysUser;
import com.nateiot.core.repository.DBUtil;

@Entity
@Table(name = "CIS_CC_TARGET_USER")
public class CisCcTargetUser extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TARGET_ID")
	private Integer targetId;
	
	/**
	 * 应急事件处理ID
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "NOTICE_ID")
	private CisCcNotice cisCcNotice;
	@Transient
	public Integer getNoticeId(){
		return this.cisCcNotice == null ? null : this.cisCcNotice.getNoticeId();
	}
	
	public void setNoticeId(Integer noticeId){
		if(noticeId != null){
			this.cisCcNotice = new CisCcNotice();
			this.cisCcNotice.setNoticeId(noticeId);
		}
	}
	
	
	/**
	 * 接收消息公告的对象的类型  有所有、机构、个人
	 */
	@Column(name = "TARGET_TYPE")
	private String targetType;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "TARGET_MESH_ID")
	private CisBmCommunityMesh targetMesh;
	@Transient
	public Integer getTargetMeshId(){
		return this.targetMesh == null ? null : this.targetMesh.getMeshId();
	}
	
	@Transient
	public String getTargetMeshName(){
		return this.targetMesh == null ? null : this.targetMesh.getMeshName();
	}
	
	public void setTargetMeshId(Integer targetMeshId){
		if(targetMeshId != null){
			this.targetMesh = DBUtil.find(CisBmCommunityMesh.class, targetMeshId);
		}
	}
	
	
	
	
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "TARGET_USER_ID")
	private GxwlSysUser targetUser;
	
	@Transient
	public Integer getTargetUserId(){
		return this.targetUser == null ? null : this.targetUser.getUserId();
	}
	
	@Transient
	public String getTargetUserName(){
		return this.targetUser == null ? null : this.targetUser.getRealname();
	}
	
	public void setTargetUserId(Integer targetUserId){
		if(targetUserId != null){
			this.targetUser = DBUtil.find(GxwlSysUser.class, targetUserId);
		}
	}

	
	
	
	
	
	
	
	public Integer getTargetId() {
		return targetId;
	}


	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}


	public CisCcNotice getCisCcNotice() {
		return cisCcNotice;
	}

	public void setCisCcNotice(CisCcNotice cisCcNotice) {
		this.cisCcNotice = cisCcNotice;
	}

	public String getTargetType() {
		return targetType;
	}


	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}


	public CisBmCommunityMesh getTargetMesh() {
		return targetMesh;
	}


	public void setTargetMesh(CisBmCommunityMesh targetMesh) {
		this.targetMesh = targetMesh;
	}


	public GxwlSysUser getTargetUser() {
		return targetUser;
	}


	public void setTargetUser(GxwlSysUser targetUser) {
		this.targetUser = targetUser;
	}

	
	
}
