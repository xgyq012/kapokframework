package com.nateiot.cis.domain;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.base.domain.GxwlSysDoc;
import com.nateiot.core.repository.DBUtil;

import javax.persistence.*;

/**
 * 求职登记
 *
 * @author xiaguangjun
 * 
 */
@Entity
@Table(name = "CIS_BM_JOB_REGISTRATION")
public class CisBmJobRegistration extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "JOBREG_ID")
	private Integer jobregId;



	/**
	 * 所属机构
	 */
	@Column(name = "MESH_ID")
	private Integer meshId;


	/**
	 * 求职者姓名
	 */
	@Column(name = "JOBREG_NAME")
	private String jobregName;


	/**
	 * 求职者身份证号
	 */
	@Column(name = "JOBREG_NUM")
	private String jobregNum;


	/**
	 * 求职者联系电话
	 */
	@Column(name = "JOBREG_PHONE")
	private String jobregPhone;


	/**
	 * 邮箱
	 */
	@Column(name = "EMAIL")
	private String email;


	/**
	 * 求职者性别
	 */
	@Column(name = "JOBREG_GENDER")
	private String jobregGender;


	/**
	 * 学历
	 */
	@Column(name = "EDU")
	private String edu;


	/**
	 * 期望工作地点
	 */
	@Column(name = "QW_ADDRESS")
	private String qwAddress;


	/**
	 * 现居地
	 */
	@Column(name = "ADDRESS")
	private String address;


	/**
	 * 职位类别
	 */
	@Column(name = "JOB_TYPE")
	private String jobType;


	/**
	 * 就业意向
	 */
	@Column(name = "EMPLOYMENT_INT")
	private String employmentInt;


	/**
	 * 技能简介
	 */
	@Column(name = "SKILL_INTRO")
	private String skillIntro;


	/**
	 * 个人简历
	 */
	/*@Column(name = "DOC_ID")
	private Integer docId;*/

	@OneToOne
	@JoinColumn(name="DOC_ID",referencedColumnName = "DOC_ID" )
	private GxwlSysDoc  gxwlSysDoc;

	/**
	 * 状态
	 */
	@Column(name="STATUS")
    private String status;


	@Transient
	public Integer getDocId() {
		return this.gxwlSysDoc==null ? null : this.gxwlSysDoc.getDocId();
	}

	@Transient
	public String getDocShowname() {
		return this.gxwlSysDoc==null ? "" : this.gxwlSysDoc.getDocShowname();
	}

	public void setDocId(Integer docId) {
		if(docId!=null){
			this.setGxwlSysDoc(DBUtil.find(GxwlSysDoc.class,docId));
		}
	}

	public GxwlSysDoc getGxwlSysDoc() {
		return gxwlSysDoc;
	}

	public void setGxwlSysDoc(GxwlSysDoc gxwlSysDoc) {
		this.gxwlSysDoc = gxwlSysDoc;
	}

	public Integer getJobregId() {
		return jobregId;
	}

	public void setJobregId(Integer jobregId) {
		this.jobregId = jobregId;
	}

	public Integer getMeshId() {
		return meshId;
	}

	public void setMeshId(Integer meshId) {
		this.meshId = meshId;
	}

	public String getJobregName() {
		return jobregName;
	}

	public void setJobregName(String jobregName) {
		this.jobregName = jobregName;
	}

	public String getJobregNum() {
		return jobregNum;
	}

	public void setJobregNum(String jobregNum) {
		this.jobregNum = jobregNum;
	}

	public String getJobregPhone() {
		return jobregPhone;
	}

	public void setJobregPhone(String jobregPhone) {
		this.jobregPhone = jobregPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJobregGender() {
		return jobregGender;
	}

	public void setJobregGender(String jobregGender) {
		this.jobregGender = jobregGender;
	}

	public String getEdu() {
		return edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}

	public String getQwAddress() {
		return qwAddress;
	}

	public void setQwAddress(String qwAddress) {
		this.qwAddress = qwAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getEmploymentInt() {
		return employmentInt;
	}

	public void setEmploymentInt(String employmentInt) {
		this.employmentInt = employmentInt;
	}

	public String getSkillIntro() {
		return skillIntro;
	}

	public void setSkillIntro(String skillIntro) {
		this.skillIntro = skillIntro;
	}



	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
