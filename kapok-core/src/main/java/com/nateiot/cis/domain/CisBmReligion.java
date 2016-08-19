package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;

public class CisBmReligion {


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
 * 宗教
 */
@Column(name = "RELIGION")
private String religion;


/**
 * 文化程度
 */
@Column(name = "CULTURE_DEGREE")
private String cultureDegree;


/**
 * 联系电话
 */
@Column(name = "TEL")
private String tel;


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
