package com.nateiot.cis.domain;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 劳动保障--培训
 * 
 * @author xiaguangjun
 * 
 */
@Entity
@Table(name = "CIS_BM_CULTIVATE")
public class CisBmCultivate extends BaseEntity {

	private static final long serialVersionUID = 1L;


	/**
	 * 培训主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CULTIVATE_ID")
	private Integer cultivateId;


	/**
	 * 所属机构
	 */
	@Column(name = "MESH_ID")
	private Integer meshId;


	/**
	 * 姓名
	 */
	@Column(name = "CULTIVATE_NAME")
	private String cultivateName;


	/**
	 * 性别
	 */
	@Column(name = "CULTIVATE_GENDER")
	private String cultivateGender;


	/**
	 * 身份证号
	 */
	@Column(name = "ID_NUMBER")
	private String idNumber;


	/**
	 * 联系电话
	 */
	@Column(name = "PHONE")
	private String phone;


	/**
	 * 讲师
	 */
	@Column(name = "LECTURER")
	private String lecturer;


	/**
	 * 讲师联系方式
	 */
	@Column(name = "LECTURER_PHONE")
	private String lecturerPhone;


	/**
	 * 培训主题
	 */
	@Column(name = "TITLE")
	private String title;


	/**
	 * 培训时间
	 */
	@Column(name = "CULTIVATE_TIME")
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cultivateTime;


	/**
	 * 培训内容
	 */
	@Column(name = "CULTIVATE_CONTENT")
	private String cultivateContent;


	public Integer getCultivateId() {
		return cultivateId;
	}

	public void setCultivateId(Integer cultivateId) {
		this.cultivateId = cultivateId;
	}

	public Integer getMeshId() {
		return meshId;
	}

	public void setMeshId(Integer meshId) {
		this.meshId = meshId;
	}

	public String getCultivateName() {
		return cultivateName;
	}

	public void setCultivateName(String cultivateName) {
		this.cultivateName = cultivateName;
	}

	public String getCultivateGender() {
		return cultivateGender;
	}

	public void setCultivateGender(String cultivateGender) {
		this.cultivateGender = cultivateGender;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLecturer() {
		return lecturer;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	public String getLecturerPhone() {
		return lecturerPhone;
	}

	public void setLecturerPhone(String lecturerPhone) {
		this.lecturerPhone = lecturerPhone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCultivateTime() {
		return cultivateTime;
	}

	public void setCultivateTime(Date cultivateTime) {
		this.cultivateTime = cultivateTime;
	}

	public String getCultivateContent() {
		return cultivateContent;
	}

	public void setCultivateContent(String cultivateContent) {
		this.cultivateContent = cultivateContent;
	}
}
