package com.gdgxwl.base.domain;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

/**
 * @author Will WM. Zhang
 * 
 */
@Entity
@Table(name = "GXWL_SYS_ROLEUSER")
public class GxwlSysUserRole extends BaseEntity {

	private static final long serialVersionUID = 9096703624952768846L;

	/**
	 * 主键ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RELA_ID")
	private int relaId;

	/**
	 * 用户
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private GxwlSysUser user;

	/**
	 * 角色ID
	 */
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "ROLE_ID")
	private GxwlSysRole role;

	/**
	 * 角色是否可授出
	 */
	@Column(name = "IS_GRANTED", length = 1)
	private String isGranted;

	/**
	 * 备注
	 */
	@Column(name = "REMARK", length = 4000)
	private String remark;

	public void setUserId(Integer userId) {
		if (userId != null) {
			this.user = new GxwlSysUser();
			this.user.setUserId(userId);
		}
	}

	public void setRoleId(Integer roleId) {
		if (roleId != null) {
			this.role = new GxwlSysRole();
			this.role.setRoleId(roleId);
		}
	}

	@Transient
	public Integer getUserId() {
		return this.user == null ? null : this.user.getUserId();
	}

	@Transient
	public Integer getRoleId() {
		return this.role == null ? null : this.role.getRoleId();
	}

	@Transient
	public String getRoleCode() {
		return this.role == null ? "" : this.role.getRoleCode();
	}

	@Transient
	public String getRoleName() {
		return this.role == null ? "" : this.role.getRoleName();
	}

	@Transient
	public String getRoleType() {
		return this.role == null ? "" : this.role.getRoleType();
	}
	
	@Transient
	public String getRoleDesc() {
		return this.role == null ? "" : this.role.getRoleDesc();
	}

	public int getRelaId() {
		return relaId;
	}

	public void setRelaId(int relaId) {
		this.relaId = relaId;
	}

	public GxwlSysUser getUser() {
		return user;
	}

	public void setUser(GxwlSysUser user) {
		this.user = user;
	}

	public GxwlSysRole getRole() {
		return role;
	}

	public void setRole(GxwlSysRole role) {
		this.role = role;
	}

	public String getIsGranted() {
		return isGranted;
	}

	public void setIsGranted(String isGranted) {
		this.isGranted = isGranted;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + relaId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GxwlSysUserRole other = (GxwlSysUserRole) obj;
		if (relaId != other.relaId)
			return false;
		return true;
	}

}