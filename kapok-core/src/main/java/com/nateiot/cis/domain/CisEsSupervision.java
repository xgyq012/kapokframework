package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateSerializer;


/**
 * 公共安全--校园安全
 * 
 * @author guohuawen
 * 
 */
@Entity
@Table(name = "CIS_ES_SUPERVISION")
public class CisEsSupervision extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 事件督办主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUPERVISION_ID")
	private Integer supervisionId;
	
	/**
	 * 事件编号
	 */
	@Column(name = "SUPERVISION_CODE")
	private String supervisionCode;
	
	/**
	 * 事件类型
	 */
	@Column(name = "SUPERVISION_TYPE")
	private String supervisionType;
	
	/**
	 * 事件类别
	 */
	@Column(name = "SUPERVISION_SORT")
	private String supervisionSort;
	
	/**
	 * 事件标题
	 */
	@Column(name = "TITLE")
	private String title;
	
	/**
	 * 事件内容
	 */
	@Column(name = "CONTENT")
	private String content;
	
	/**
	 * 删除标记 
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 * 登记机构
	 */
	@Column(name = "UNITS")
	private String units;
	
	/**
	 * 登记时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "REGISTER_TIME")
	private Date registerTime;

	public Integer getSupervisionId() {
		return supervisionId;
	}

	public void setSupervisionId(Integer supervisionId) {
		this.supervisionId = supervisionId;
	}

	public String getSupervisionCode() {
		return supervisionCode;
	}

	public void setSupervisionCode(String supervisionCode) {
		this.supervisionCode = supervisionCode;
	}

	public String getSupervisionType() {
		return supervisionType;
	}

	public void setSupervisionType(String supervisionType) {
		this.supervisionType = supervisionType;
	}

	public String getSupervisionSort() {
		return supervisionSort;
	}

	public void setSupervisionSort(String supervisionSort) {
		this.supervisionSort = supervisionSort;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	
}
