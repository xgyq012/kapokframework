package com.gdgxwl.base.domain;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

/**
 * @author Will WM. Zhang
 * 
 */
@Entity
@Table(name = "GXWL_SYS_ROLEREDATAP")
public class GxwlSysRolereDataP extends BaseEntity {

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
	 * 数据ID
	 */
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "DATAP_ID")
	private GxwlSysDataP data;
	
	/**
	 * 是否有效
	 */
	@Column(name = "ENABLE", length = 1, nullable = false)
	private String enable;

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

	public GxwlSysDataP getData() {
		return data;
	}

	public void setData(GxwlSysDataP data) {
		this.data = data;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

}
