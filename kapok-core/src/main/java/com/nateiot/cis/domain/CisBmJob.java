package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;

public class CisBmJob {


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
 * 身份证号
 */
@Column(name = "M_IDENTITY_CODE")
private String mIdentityCode;


/**
 * 联系电话
 */
@Column(name = "TEL")
private String tel;


/**
 * 就业意向
 */
@Column(name = "JOB_INTENT")
private String jobIntent;


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
