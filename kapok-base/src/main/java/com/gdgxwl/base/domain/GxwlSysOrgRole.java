package com.gdgxwl.base.domain;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

/**
 * @author Will WM. Zhang
 * 
 */
@Entity
@Table(name = "GXWL_SYS_ROLEORG")
public class GxwlSysOrgRole extends BaseEntity {

	private static final long serialVersionUID = -3595657019596780644L;

	/**
	 * 主键ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RELA_ID", nullable = false)
	private Integer relaId;

	/**
	 * 行政组织
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ORG_ID")
	private GxwlSysOrg org;

	/**
	 * 角色
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
	 * 分配关系是否可继承
	 */
	@Column(name = "IS_INHERITED", length = 1)
	private String isInherited;

	/**
	 * 备注
	 */
	@Column(name = "REMARK", length = 4000)
	private String remark;

	public void setOrgId(Integer orgId) {
		if (orgId != null) {
			this.org = new GxwlSysOrg();
			this.org.setId(orgId);
		}
	}

	public void setRoleId(Integer roleId) {
		if (roleId != null) {
			this.role = new GxwlSysRole();
			this.role.setRoleId(roleId);
		}
	}

	@Transient
	public Integer getOrgId() {
		return this.org == null ? null : this.org.getId();
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
	
	public Integer getRelaId() {
		return relaId;
	}

	public void setRelaId(Integer relaId) {
		this.relaId = relaId;
	}

	public GxwlSysOrg getOrg() {
		return org;
	}

	public void setOrg(GxwlSysOrg org) {
		this.org = org;
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

	public String getIsInherited() {
		return isInherited;
	}

	public void setIsInherited(String isInherited) {
		this.isInherited = isInherited;
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
		result = prime * result + ((relaId == null) ? 0 : relaId.hashCode());
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
		GxwlSysOrgRole other = (GxwlSysOrgRole) obj;
		if (relaId == null) {
			if (other.relaId != null)
				return false;
		} else if (!relaId.equals(other.relaId))
			return false;
		return true;
	}

}
