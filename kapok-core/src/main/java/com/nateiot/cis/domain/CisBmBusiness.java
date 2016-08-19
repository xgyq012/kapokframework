package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;

public class CisBmBusiness {


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
 * 门店名称
 */
@Column(name = "SHOP_NAME")
private String shopName;


/**
 * 联系电话
 */
@Column(name = "TEL")
private String tel;


/**
 * 地址
 */
@Column(name = "ADDRESS")
private String address;


/**
 * 法人
 */
@Column(name = "LAWER")
private String lawer;


/**
 * 经营范围
 */
@Column(name = "MANAGE_RANGE")
private String manageRange;


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
