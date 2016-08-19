package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;

public class CisErVillagesTransform {


/**
 * 主键
 */
@Column(name = "M_ID")
private Integer mId;


/**
 * 名称
 */
@Column(name = "M_NAME")
private String mName;


/**
 * 城中村概述
 */
@Column(name = "DETAIL")
private String detail;


/**
 * 城中村类型
 */
@Column(name = "TYPE")
private String type;


/**
 * 改造进度
 */
@Column(name = "DEGREE")
private String degree;


/**
 * 负责人
 */
@Column(name = "CONTROLLER")
private String controller;


/**
 * 城中村地址
 */
@Column(name = "ADDRESS")
private String address;


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
