package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateTimeSerializer;
/**
 * 应急事件核查表
 * @author xiewenhua
 *
 */
@Entity
@Table(name = "CIS_EM_HECHA")
public class CisEmHecha extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SHIJIAN_HECHA_ID")
	private Integer shijianHechaId;
	/**
	 * 应急事件id
	 */
	@Column(name = "YINGJI_SHIJIAN_ID")
	private Integer yingjiShijianId;
	/**
	 * 核查人id
	 */
	@Column(name = "HECHAREN_ID")
	private Integer hecharenId;
	/**
	 * 核查人名称
	 */
	@Column(name = "HECHAREN_NAME")
	private String hecharenName;
	/**
	 * 是否上报的状态
	 */
	@Column(name = "SHIFOU_SHANGBAO")
	private String shifouShangbao;
	/**
	 * 核查意见
	 */
	@Column(name = "HECHA_YIJIAN")
	private String hechaYijian;
	
	
	/**
	 * 核查时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "HECHA_TIME")
	private Date hechaTime;
	
	public Integer getShijianHechaId() {
		return shijianHechaId;
	}
	public void setShijianHechaId(Integer shijianHechaId) {
		this.shijianHechaId = shijianHechaId;
	}
	public Integer getYingjiShijianId() {
		return yingjiShijianId;
	}
	public void setYingjiShijianId(Integer yingjiShijianId) {
		this.yingjiShijianId = yingjiShijianId;
	}
	public Integer getHecharenId() {
		return hecharenId;
	}
	public void setHecharenId(Integer hecharenId) {
		this.hecharenId = hecharenId;
	}
	public String getHecharenName() {
		return hecharenName;
	}
	public void setHecharenName(String hecharenName) {
		this.hecharenName = hecharenName;
	}

	public String getShifouShangbao() {
		return shifouShangbao;
	}
	public void setShifouShangbao(String shifouShangbao) {
		this.shifouShangbao = shifouShangbao;
	}
	public String getHechaYijian() {
		return hechaYijian;
	}
	public void setHechaYijian(String hechaYijian) {
		this.hechaYijian = hechaYijian;
	}
	public Date getHechaTime() {
		return hechaTime;
	}
	public void setHechaTime(Date hechaTime) {
		this.hechaTime = hechaTime;
	}
}
