package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;

public class CisRaRiskAssessments {


/**
 * 主键
 */
@Column(name = "M_ID")
private Integer mId;


/**
 * 所属机构
 */
@Column(name = "ORG")
private String org;


/**
 * 项目名称
 */
@Column(name = "PROJECT_NAME")
private String projectName;


/**
 * 项目简介
 */
@Column(name = "PROJECT_DETAIL")
private String projectDetail;


/**
 * 项目类型
 */
@Column(name = "PROJECT_TYPE")
private String projectType;


/**
 * 实施单位
 */
@Column(name = "UINT")
private String uint;


/**
 * 评估负责人
 */
@Column(name = "CONTROLLER")
private String controller;


/**
 * 项目进展
 */
@Column(name = "SPEED")
private String speed;


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
