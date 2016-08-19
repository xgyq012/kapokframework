package com.nateiot.cis.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import com.nateiot.base.domain.BaseEntity;
import com.nateiot.core.common.json.JsonDateTimeSerializer;
import com.nateiot.core.repository.DBUtil;

/**
 * 应急事件
 * @author xiewenhua
 *
 */

@Entity
@Table(name = "CIS_EM_YINGJI_SHIJIAN")
public class CisEmYingjiShijian extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 跟进明细
	 */
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "cisEmYingjiShijian")
	private List<CisEmYjsjClmx> cisEmYjsjClmxs;
	
	/**
	 * 应急事件附件
	 */
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "cisEmYingjiShijian")
	private List<CisEmYjsjDoc> cisEmYjsjDocs;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "YINGJI_SHIJIAN_ID")
	private Integer yingjiShijianId;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "YJSJLX_ID")
	private CisEmYjsjlx yjsjlx;
	
	
	/**
	 * 应急事件来源（数字129分别代表电话、微信、其他
	 */
	@Column(name = "LAIYUAN")
	private Integer laiyuan;
	
	/**
	 * 应急事件名称
	 */
	@Column(name = "NAME")
	private String name;
	
	/**
	 * 事发地
	 */
	@Column(name = "ADDRESS")
	private String address;
	
	/**
	 * 应急事件所处的阶段，0为先期预报、1为已经发生、2为正在进行、9已结束
	 */
/*	@Column(name = "JIEDUAN")
	private Integer jieduan;*/
	
	/**
	 * 应急事件所处的状态，数字012349分别表示草稿、已提交、已核查、已审核、正在跟进、已处理
	 */
	@Column(name = "SHIJIAN_STATUS")
	private Integer shijianStatus;
	
	/**
	 * 应急事件描述
	 */
	@Column(name = "SHIJIAN_PS")
	private String shijianPs;
	
	/**
	 * 上报人姓名
	 */
	@Column(name = "SHANGBAOREN")
	private String shangbaoren;
	
	/**
	 * 上报人联系电话
	 */
	@Column(name = "SHANGBAOREN_PHONE")
	private String shangbaorenPhone;
	
	/**
	 * 核查时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "SHANGBAO_TIME")
	private Date shangbaoTime;
	
	/**
	 * 软删除
	 */
	@Column(name = "DEL_SIGN")
	private String delSign;
	
	//+++++++++++++++++++++++++++++++++++++++++++核查信息+++++++++++++++++++++++++++++++++++++
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
	
	//+++++++++++++++++++++++++++++++++++++++ 审查信息 ++++++++++++++++++++++++++++++++++++++++++++++++
	/**
	 * 审查人id
	 */
	@Column(name = "SHENCHAREN_ID")
	private Integer shencharenId;
	
	/**
	 * 审查人名称
	 */
	@Column(name = "SHENCHAREN_NAME")
	private String shencharenName;
	
	/**
	 * 审查意见
	 */
	@Column(name = "SHENCHA_YIJIAN")
	private String shenchaYijian;
	
	/**
	 * 是否预报状态
	 */
	@Column(name = "YUBAO_STATUS")
	private String yubaoStatus;
	
	/**
	 * 是否启动应急状态
	 */
	@Column(name = "YINGJI_STATUS")
	private String yingjiStatus;

	/**
	 * 应急预案id
	 */
	@Column(name = "YINGJI_PLAN_ID")
	private Integer yingjiPlanId;
	
	/**
	 * 应急预案名称
	 */
	@Column(name = "YINGJI_PLAN_NAME")
	private String yingjiPlanName;
	
	/**
	 * 录入人id
	 */
	@Column(name = "LURUREN_ID")
	private Integer lururenId;
	
	/**
	 * 录入人名称
	 */
	@Column(name = "LURUREN_NAME")
	private String lururenName;
	
	/**
	 * 审查时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "SHENCHA_TIME")
	private Date shenchaTime;
	

	
	

	public Integer getYingjiShijianId() {
		return yingjiShijianId;
	}

	public void setYingjiShijianId(Integer yingjiShijianId) {
		this.yingjiShijianId = yingjiShijianId;
	}

	@Transient
	public Integer getYjsjlxId() {
		return this.yjsjlx == null ? null : this.yjsjlx.getYjsjlxId();
	}

	public void setYjsjlxId(Integer yjsjlxId) {
		if(yjsjlxId != null){
			this.yjsjlx = DBUtil.find(CisEmYjsjlx.class, yjsjlxId);
			if(this.yjsjlx == null){
				this.yjsjlx = new CisEmYjsjlx();
				this.yjsjlx.setYjsjlxId(yjsjlxId);
			}
		}
	}
	
	@Transient
	public String getYjsjlxName(){
		return this.yjsjlx == null ? null : this.yjsjlx.getLeixingName();
	}

	@Transient
	public String getFullName(){
		return this.yjsjlx == null ? null : this.yjsjlx.getFullName();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

/*	public Integer getJieduan() {
		return jieduan;
	}

	public void setJieduan(Integer jieduan) {
		this.jieduan = jieduan;
	}*/

	public String getShijianPs() {
		return shijianPs;
	}

	public void setShijianPs(String shijianPs) {
		this.shijianPs = shijianPs;
	}

	public String getShangbaoren() {
		return shangbaoren;
	}

	public void setShangbaoren(String shangbaoren) {
		this.shangbaoren = shangbaoren;
	}
	public String getShangbaorenPhone() {
		return shangbaorenPhone;
	}

	public void setShangbaorenPhone(String shangbaorenPhone) {
		this.shangbaorenPhone = shangbaorenPhone;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public Integer getLaiyuan() {
		return laiyuan;
	}

	public void setLaiyuan(Integer laiyuan) {
		this.laiyuan = laiyuan;
	}

	public Integer getShijianStatus() {
		return shijianStatus;
	}

	public void setShijianStatus(Integer shijianStatus) {
		this.shijianStatus = shijianStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Integer getShencharenId() {
		return shencharenId;
	}

	public void setShencharenId(Integer shencharenId) {
		this.shencharenId = shencharenId;
	}

	public String getShencharenName() {
		return shencharenName;
	}

	public void setShencharenName(String shencharenName) {
		this.shencharenName = shencharenName;
	}

	public String getShenchaYijian() {
		return shenchaYijian;
	}

	public void setShenchaYijian(String shenchaYijian) {
		this.shenchaYijian = shenchaYijian;
	}

	public String getYubaoStatus() {
		return yubaoStatus;
	}

	public void setYubaoStatus(String yubaoStatus) {
		this.yubaoStatus = yubaoStatus;
	}

	public String getYingjiStatus() {
		return yingjiStatus;
	}

	public void setYingjiStatus(String yingjiStatus) {
		this.yingjiStatus = yingjiStatus;
	}

	public Integer getYingjiPlanId() {
		return yingjiPlanId;
	}

	public void setYingjiPlanId(Integer yingjiPlanId) {
		this.yingjiPlanId = yingjiPlanId;
	}

	public String getYingjiPlanName() {
		return yingjiPlanName;
	}

	public void setYingjiPlanName(String yingjiPlanName) {
		this.yingjiPlanName = yingjiPlanName;
	}

	public Date getShenchaTime() {
		return shenchaTime;
	}

	public void setShenchaTime(Date shenchaTime) {
		this.shenchaTime = shenchaTime;
	}

	public Integer getHecharenId() {
		return hecharenId;
	}

	public void setHecharenId(Integer hecharenId) {
		this.hecharenId = hecharenId;
	}

	public List<CisEmYjsjClmx> getCisEmYjsjClmxs() {
		return cisEmYjsjClmxs;
	}

	public void setCisEmYjsjClmxs(List<CisEmYjsjClmx> cisEmYjsjClmxs) {
		this.cisEmYjsjClmxs = cisEmYjsjClmxs;
	}

	public List<CisEmYjsjDoc> getCisEmYjsjDocs() {
		return cisEmYjsjDocs;
	}

	public void setCisEmYjsjDocs(List<CisEmYjsjDoc> cisEmYjsjDocs) {
		this.cisEmYjsjDocs = cisEmYjsjDocs;
	}

	public Date getShangbaoTime() {
		return shangbaoTime;
	}

	public void setShangbaoTime(Date shangbaoTime) {
		this.shangbaoTime = shangbaoTime;
	}

	public Integer getLururenId() {
		return lururenId;
	}

	public void setLururenId(Integer lururenId) {
		this.lururenId = lururenId;
	}

	public String getLururenName() {
		return lururenName;
	}

	public void setLururenName(String lururenName) {
		this.lururenName = lururenName;
	}

	public CisEmYjsjlx getYjsjlx() {
		return yjsjlx;
	}

	public void setYjsjlx(CisEmYjsjlx yjsjlx) {
		this.yjsjlx = yjsjlx;
	}

	
}
