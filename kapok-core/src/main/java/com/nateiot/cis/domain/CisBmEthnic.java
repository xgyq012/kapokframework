package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;

public class CisBmEthnic {


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
@Column(name = "M_ORG")
private String mOrg;


/**
 * 姓名
 */
@Column(name = "M_NAME")
private String mName;


/**
 * 性别
 */
@Column(name = "M_SEX")
private String mSex;


/**
 * 门牌号
 */
@Column(name = "HOUSE_NUMBER")
private String houseNumber;


/**
 * 年龄
 */
@Column(name = "M_AGE")
private Integer mAge;


/**
 * 民族
 */
@Column(name = "ETHNIC")
private String ethnic;


/**
 * 政治面貌
 */
@Column(name = "POLITICAL_STATUS")
private String politicalStatus;


/**
 * 文化程度
 */
@Column(name = "CULTURE_DEGREE")
private String cultureDegree;


/**
 * 身份证号
 */
@Column(name = "身份证号")
private String 身份证号;


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
