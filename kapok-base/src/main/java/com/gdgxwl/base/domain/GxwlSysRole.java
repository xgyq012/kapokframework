package com.gdgxwl.base.domain;

import com.gdgxwl.core.common.json.JsonDateSerializer;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Will WM. Zhang
 * 
 */
@Entity
@Table(name = "GXWL_SYS_ROLE")
public class GxwlSysRole extends BaseEntity {

	private static final long serialVersionUID = 2087894461212033731L;

	/**
	 * 角色ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROLE_ID")
	private Integer roleId;

	/**
	 * 角色编码
	 */
	@Column(name = "ROLE_CODE", length = 50)
	private String roleCode;

	/**
	 * 角色名称
	 */
	@Column(name = "ROLE_NAME", length = 200)
	private String roleName;

	/**
	 * 角色类型
	 */
	@Column(name = "ROLE_TYPE", length = 128)
	private String roleType;

	/**
	 * 角色描述
	 */
	@Column(name = "ROLE_DESC", length = 4000)
	private String roleDesc;

	/**
	 * 生效日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "VALID_DATE", nullable = false)
	private Date validDate = new Date();

	/**
	 * 失效日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "INVALID_DATE")
	private Date invalidDate;

	@JsonIgnore
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "role")
	private List<GxwlSysRoleResource> resources;

	@Transient
	private String isGranted;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public List<GxwlSysRoleResource> getResources() {
		return resources;
	}

	public void setResources(List<GxwlSysRoleResource> resources) {
		this.resources = resources;
	}

	public String getIsGranted() {
		return isGranted;
	}

	public void setIsGranted(String isGranted) {
		this.isGranted = isGranted;
	}
}