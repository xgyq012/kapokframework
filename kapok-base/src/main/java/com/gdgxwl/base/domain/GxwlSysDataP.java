package com.gdgxwl.base.domain;

import javax.persistence.*;

/**
 * @author Will WM. Zhang
 *
 */
@Entity
@Table(name = "GXWL_SYS_DATAP")
public class GxwlSysDataP extends BaseEntity {

	private static final long serialVersionUID = 6458279938149502101L;

	/**
	 * 主键ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DATAP_ID", nullable = false)
	private Integer datapId;
	
	/**
	 * 资源ID
	 */
	@Column(name = "RESOURCE_ID")
	private Integer resourceId;
	
	/**
	 * 数据权限类型
	 */
	@Column(name = "DATAP_TYPE", length = 128)
	private String datapType;
	
	/**
	 * 显示列名
	 */
	@Column(name = "COLUMN_NAMES", length = 4000)
	private String columnNames;
	
	/**
	 * 显示列标题
	 */
	@Column(name = "TITLE_NAMES", length = 4000)
	private String titleNames;
	
	/**
	 * 查询条件
	 */
	@Column(name = "SQL_WHERE", length = 4000)
	private String sqlWhere;

	/**
	 * 不显示的控件名
	 */
	@Column(name = "NO_CONTROLS", length = 4000)
	private String noControls;
	
	/**
	 * 是否有效
	 */
	@Column(name = "ENABLE", length = 1, nullable = false)
	private String enable;
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK", length = 4000)
	private String remark;

	/**
	 * 扩展字段01
	 */
	@Column(name = "ATTRIBUTE01", length = 200)
	private String attribute01;
	
	/**
	 * 扩展字段02
	 */
	@Column(name = "ATTRIBUTE02", length = 200)
	private String attribute02;
	
	/**
	 * 扩展字段03
	 */
	@Column(name = "ATTRIBUTE03", length = 200)
	private String attribute03;
	
	/**
	 * 扩展字段04
	 */
	@Column(name = "ATTRIBUTE04", length = 200)
	private String attribute04;
	
	/**
	 * 扩展字段05
	 */
	@Column(name = "ATTRIBUTE05", length = 200)
	private String attribute05;

	public Integer getDatapId() {
		return datapId;
	}

	public void setDatapId(Integer datapId) {
		this.datapId = datapId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getDatapType() {
		return datapType;
	}

	public void setDatapType(String datapType) {
		this.datapType = datapType;
	}

	public String getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String columnNames) {
		this.columnNames = columnNames;
	}

	public String getTitleNames() {
		return titleNames;
	}

	public void setTitleNames(String titleNames) {
		this.titleNames = titleNames;
	}

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public String getNoControls() {
		return noControls;
	}

	public void setNoControls(String noControls) {
		this.noControls = noControls;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAttribute01() {
		return attribute01;
	}

	public void setAttribute01(String attribute01) {
		this.attribute01 = attribute01;
	}

	public String getAttribute02() {
		return attribute02;
	}

	public void setAttribute02(String attribute02) {
		this.attribute02 = attribute02;
	}

	public String getAttribute03() {
		return attribute03;
	}

	public void setAttribute03(String attribute03) {
		this.attribute03 = attribute03;
	}

	public String getAttribute04() {
		return attribute04;
	}

	public void setAttribute04(String attribute04) {
		this.attribute04 = attribute04;
	}

	public String getAttribute05() {
		return attribute05;
	}

	public void setAttribute05(String attribute05) {
		this.attribute05 = attribute05;
	}
	
}
