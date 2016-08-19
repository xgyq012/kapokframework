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

import com.nateiot.base.common.Constant;
import com.nateiot.base.common.DictUtil;
import com.nateiot.base.domain.BaseEntity;
import com.nateiot.base.domain.GxwlSysUser;
import com.nateiot.core.repository.DBUtil;

/**
 * @author Will WM. Zhang
 * 
 */
@Entity
@Table(name = "CIS_BM_COMMUNITY_MESH_USER")
public class CisBmCommunityMeshUser extends BaseEntity {

	private static final long serialVersionUID = 5353346720259681980L;

	/**
	 * 主键ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RELA_ID", nullable = false)
	private Integer relaId;

	/**
	 * 用户ID
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private GxwlSysUser user;

	/**
	 * 辖区ID
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "MESH_ID")
	private CisBmCommunityMesh mesh;

	/**
	 * 是否行政组织负责人
	 */
	@Column(name = "IS_MANAGER", length = 1)
	private String isManager;

	public void setMeshId(Integer meshId) {
		if (meshId != null) {
			this.mesh = DBUtil.find(CisBmCommunityMesh.class, meshId);
		}
	}

	public void setUserId(Integer userId) {
		if (userId != null) {
			this.user = DBUtil.find(GxwlSysUser.class, userId);
		}
	}

	@Transient
	public Integer getMeshId() {
		return this.mesh == null ? null : this.mesh.getMeshId();
	}
	
	@Transient
	public String getMeshName() {
		return this.mesh == null ? null : this.mesh.getMeshName();
	}

	@Transient
	public Integer getUserId() {
		return this.user == null ? null : this.user.getUserId();
	}

	@Transient
	public String getUserName() {
		return this.user == null ? "" : this.user.getUserName();
	}

	@Transient
	public String getRealname() {
		return this.user == null ? "" : this.user.getRealname();
	}

	@Transient
	public String getStatusName() {
		return this.user != null ? DictUtil.getDictName(Constant.USER_STATUS,
				this.user.getStatus()) : null;
	}

	public Integer getRelaId() {
		return relaId;
	}

	public void setRelaId(Integer relaId) {
		this.relaId = relaId;
	}

	public CisBmCommunityMesh getMesh() {
		return mesh;
	}

	public void setMesh(CisBmCommunityMesh mesh) {
		this.mesh = mesh;
	}

	public GxwlSysUser getUser() {
		return user;
	}

	public void setUser(GxwlSysUser user) {
		this.user = user;
	}

	public String getIsManager() {
		return isManager;
	}

	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}

}
