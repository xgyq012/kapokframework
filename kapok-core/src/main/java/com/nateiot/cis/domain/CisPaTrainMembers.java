package com.nateiot.cis.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateSerializer;

/**
 * 党务建设 -- 党员培训情况登记表
 * 
 * @author Guohw
 */
@Entity
@Table(name = "CIS_PA_TRAIN_MEMBERS")
public class CisPaTrainMembers extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 党员培训情况登记主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TRAIN_ID")
	private Integer trainId;
	
	/**
	 *  培训地点
	 */
	@Column(name = "TRAIN_PLACE")
	private String trainPlace;
	
	/**
	 * 培训日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "TRAIN_DATE")
	private Date trainDate;
	
	/**
	 *  授课人
	 */
	@Column(name = "LECTURE")
	private String lecture;
	
	/**
	 *  培训内容
	 */
	@Column(name = "TRAIN_CONTENT")
	private String trainContent;
	
	/**
	 *  参加人
	 */
	@Column(name = "TAKE_PART")
	private String takePart;
	
	/**
	 *  删除标记
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	/**
	 *  主要内容
	 */
	@Column(name = "MAIN_CONTENT")
	private String mainContent;

	public Integer getTrainId() {
		return trainId;
	}

	public void setTrainId(Integer trainId) {
		this.trainId = trainId;
	}

	public String getTrainPlace() {
		return trainPlace;
	}

	public void setTrainPlace(String trainPlace) {
		this.trainPlace = trainPlace;
	}

	public Date getTrainDate() {
		return trainDate;
	}

	public void setTrainDate(Date trainDate) {
		this.trainDate = trainDate;
	}

	public String getLecture() {
		return lecture;
	}

	public void setLecture(String lecture) {
		this.lecture = lecture;
	}

	public String getTrainContent() {
		return trainContent;
	}

	public void setTrainContent(String trainContent) {
		this.trainContent = trainContent;
	}

	public String getTakePart() {
		return takePart;
	}

	public void setTakePart(String takePart) {
		this.takePart = takePart;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public String getMainContent() {
		return mainContent;
	}

	public void setMainContent(String mainContent) {
		this.mainContent = mainContent;
	}
	
	
}
