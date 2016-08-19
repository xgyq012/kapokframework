package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;

public class CisErProjectMerchants {


/**
 * 主键
 */
@Column(name = "M_ID")
private Integer mId;


/**
 * 招商名称
 */
@Column(name = "MERCHANTS_NAME")
private String merchantsName;


/**
 * 招商类型
 */
@Column(name = "MERCHANTS_TYPE")
private String merchantsType;


/**
 * 负责人
 */
@Column(name = "CONTROLLER")
private String controller;


/**
 * 登记机构
 */
@Column(name = "REGISTER_ORG")
private String registerOrg;


/**
 * 开工日期
 */
@Column(name = "WORK_DATE")
private Date workDate;


/**
 * 建成日期
 */
@Column(name = "FILISH_DATE")
private Date filishDate;


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
