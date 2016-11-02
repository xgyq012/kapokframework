package com.gdgxwl.base.domain;

import javax.persistence.*;

/**
 * @author Will WM. Zhang
 * 
 */
@Entity
@Table(name = "GXWL_SYS_RESOURCEP")
public class GxwlSysResourceP extends BaseEntity {

	private static final long serialVersionUID = 6650956825841152632L;

	/**
	 * 资源参数ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RESOURCEP_ID", nullable = false)
	private Integer resourcePId;

	/**
	 * 资源ID
	 */
	@Column(name = "RESOURCE_ID", nullable = false)
	private Integer resourceId;

	/**
	 * 资源参数名称
	 */
	@Column(name = "RESOURCEP_NAME", length = 50)
	private String resourcePName;

	/**
	 * 资源参数值
	 */
	@Column(name = "RESOURCEP_VALUE", length = 200)
	private String resourcePValue;

	/**
	 * 资源参数类型
	 */
	@Column(name = "RESOURCEP_TYPE", length = 128)
	private String resourcePType;

	/**
	 * 资源参数说明
	 */
	@Column(name = "RESOURCEP_DESC", length = 200)
	private String resourcePDesc;
}
