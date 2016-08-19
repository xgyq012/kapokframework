package com.nateiot.base.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author Will WM. Zhang
 * 
 */
@Entity
@Table(name = "GXWL_SYS_ROLERESOURCE")
public class GxwlSysRoleResource extends BaseEntity {

	private static final long serialVersionUID = 6148610277270509375L;

	/**
	 * 主键ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RELA_ID", nullable = false)
	private Integer relaId;

	/**
	 * 角色
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	private GxwlSysRole role;

	/**
	 * 资源ID
	 */
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "RESOURCE_ID")
	private GxwlSysResource resource;

	public void setRoleId(Integer roleId) {
		if (roleId != null) {
			this.role = new GxwlSysRole();
			this.role.setRoleId(roleId);
		}
	}
	
	public void setResourceId(Integer resourceId) {
		if (resourceId != null) {
			this.resource = new GxwlSysResource();
			this.resource.setId(resourceId);
		}
	}
	
	@Transient
	public Integer getRoleId() {
		return this.role == null ? null : this.role.getRoleId();
	}
	
	@Transient
	public Integer getResourceId() {
		return this.resource == null ? null : this.resource.getId();
	}
	
	public Integer getRelaId() {
		return relaId;
	}

	public void setRelaId(Integer relaId) {
		this.relaId = relaId;
	}

	public GxwlSysRole getRole() {
		return role;
	}

	public void setRole(GxwlSysRole role) {
		this.role = role;
	}

	public GxwlSysResource getResource() {
		return resource;
	}

	public void setResource(GxwlSysResource resource) {
		this.resource = resource;
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
		GxwlSysRoleResource other = (GxwlSysRoleResource) obj;
		if (relaId == null) {
			if (other.relaId != null)
				return false;
		} else if (!relaId.equals(other.relaId))
			return false;
		return true;
	}

}
