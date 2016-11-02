package com.gdgxwl.base.domain;

import javax.persistence.*;

/**
 * @author Will WM. Zhang
 * 
 */
@Entity
@Table(name = "GXWL_SYS_ATTACH")
public class GxwlSysAttach extends BaseEntity {

	private static final long serialVersionUID = 7950750083916595315L;

	/**
	 * 附件ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ATTACH_ID", nullable = false)
	private Integer attachId;

	/**
	 * 模块ID
	 */
	@Column(name = "MODEL_ID", nullable = false)
	private Integer modelId;

	/**
	 * 模块类型
	 */
	@Column(name = "MODEL_TYPE", length = 128, nullable = false)
	private String modelType;

	/**
	 * 附件显示名称
	 */
	@Column(name = "ATTACH_SHOW_NAME", length = 200, nullable = false)
	private String attachShowName;

	/**
	 * 附件保存名称
	 */
	@Column(name = "ATTACH_SAVE_NAME", length = 4000, nullable = false)
	private String attachSaveName;

	/**
	 * 附件地址
	 */
	@Column(name = "ATTACH_URL", length = 4000, nullable = false)
	private String attachUrl;

	/**
	 * 文件类型
	 */
	@Column(name = "DOC_TYPE", length = 128, nullable = false)
	private String docType;

	/**
	 * 文件大小
	 */
	@Column(name = "DOC_SIZE", nullable = false)
	private Long docSize;

	/**
	 * 默认打开方式
	 */
	@Column(name = "DOC_OPEN_PROGRAM", length = 200)
	private String docOpenProgram;

	/**
	 * 备注
	 */
	@Column(name = "REMARK", length = 4000)
	private String remark;
}
