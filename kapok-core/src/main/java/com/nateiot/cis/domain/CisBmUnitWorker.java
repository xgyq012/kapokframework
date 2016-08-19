package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;

public class CisBmUnitWorker {


/**
 * 主键
 */
@Column(name = "M_ID")
private Integer mId;


/**
 * 编号
 */
@Column(name = "M_CODE")
private String mCode;


/**
 * 所属机构
 */
@Column(name = "ORG")
private String org;


/**
 * 单位
 */
@Column(name = "UNIT")
private String unit;


/**
 * 地址
 */
@Column(name = "ADDRESS")
private String address;


/**
 * 联系电话
 */
@Column(name = "TEL")
private String tel;


/**
 * 招工简介
 */
@Column(name = "CONTENT")
private String content;


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
