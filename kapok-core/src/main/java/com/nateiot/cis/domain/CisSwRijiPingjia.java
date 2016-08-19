package com.nateiot.cis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nateiot.base.domain.BaseEntity;

/**
 * 民情日记评价
 * @author xiewenhua
 *
 */

@Entity
@Table(name = "CIS_SW_RIJI_PINGJIA")
public class CisSwRijiPingjia extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "RIJI_PINGJIA_ID")
	private Integer rijiPingjiaId;
	
	/**
	 * 被评论的 民情日记Id
	 */
	@Column(name = "MINQING_RIJI_ID")
	private Integer minqingRijiId;
	
	/**
	 * 评价语
	 */
	@Column(name = "PINGJIA_BODY")
	private String pingjiaBody;
	
	/**
	 * 给日记评论等级
	 */
	@Column(name = "PINGJIA_LEVEL")
	private Integer pingjiaLevel;
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	public Integer getRijiPingjiaId() {
		return rijiPingjiaId;
	}
	public void setRijiPingjiaId(Integer rijiPingjiaId) {
		this.rijiPingjiaId = rijiPingjiaId;
	}
	public Integer getMinqingRijiId() {
		return minqingRijiId;
	}
	public void setMinqingRijiId(Integer minqingRijiId) {
		this.minqingRijiId = minqingRijiId;
	}
	public String getPingjiaBody() {
		return pingjiaBody;
	}
	public void setPingjiaBody(String pingjiaBody) {
		this.pingjiaBody = pingjiaBody;
	}
	public Integer getPingjiaLevel() {
		return pingjiaLevel;
	}
	public void setPingjiaLevel(Integer pingjiaLevel) {
		this.pingjiaLevel = pingjiaLevel;
	}
	public String getDelSign() {
		return delSign;
	}
	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

}
