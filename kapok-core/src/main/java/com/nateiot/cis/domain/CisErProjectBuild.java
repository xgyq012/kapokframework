package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;

public class CisErProjectBuild {


/**
 * 主键
 */
@Column(name = "M_ID")
private Integer mId;


/**
 * 项目名称
 */
@Column(name = "PROJECT_NAME")
private String projectName;


/**
 * 项目概述
 */
@Column(name = "PROJECT_DETAIL")
private String projectDetail;


/**
 * 项目类型
 */
@Column(name = "PROJECT_TYPE")
private String projectType;


/**
 * 负责单位
 */
@Column(name = "UNIT")
private String unit;


/**
 * 创建人
 */
@Column(name = "CREATE_BY")
private Integer createBy;


/**
 * 创建时间
 */
@Column(name = "CREATE_TIME")
private Date createTime;


/**
 * 最后修改人
 */
@Column(name = "LAST_UPDATE_BY")
private Integer lastUpdateBy;


/**
 * 最后修改时间
 */
@Column(name = "LAST_UPDATE_TIME")
private Date lastUpdateTime;
}
