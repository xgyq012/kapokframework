package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;

public class CisBmSkillTrain {


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
@Column(name = "IDENTITY_CODE")
private String identityCode;


/**
 * 培训时间
 */
@Column(name = "TRAIN_TIME")
private Date trainTime;


/**
 * 培训内容
 */
@Column(name = "TRAIN_CONTENT")
private String trainContent;


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
