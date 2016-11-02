package com.gdgxwl.base.domain;

import com.gdgxwl.core.common.json.JsonDateSerializer;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Will WM. Zhang
 * 
 */
@Entity
@Table(name = "GXWL_SYS_ORG")
public class GxwlSysOrg extends BaseEntity {

	private static final long serialVersionUID = 8165494021380693072L;

	/**
	 * 行政组织ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORG_ID", nullable = false)
	private Integer id;

	/**
	 * 行政组织编码
	 */
	@Column(name = "ORG_CODE", length = 50, nullable = false)
	private String orgCode;

	/**
	 * 行政组织名称
	 */
	@Column(name = "ORG_NAME", length = 200, nullable = false)
	private String orgName;

	/**
	 * 行政组织全称
	 */
	@Column(name = "ORG_FULLNAME", length = 200)
	private String orgFullname;

	/**
	 * 上级行政组织ID
	 */
	@Column(name = "PARENT_ORGID")
	private Integer parentOrgId;

	/**
	 * 主管职位
	 */
	@Column(name = "MANA_POSITION", length = 128)
	private String manaPosition;

	/**
	 * 行政组织地址
	 */
	@Column(name = "ORG_ADDR", length = 200)
	private String orgAddr;

	/**
	 * 行政组织联系人
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "LINKMAN")
	private GxwlSysUser linkman;

	/**
	 * 邮箱地址
	 */
	@Column(name = "EMAIL", length = 128)
	private String email;

	/**
	 * 备注
	 */
	@Column(name = "REMARK", length = 4000)
	private String remark;

	/**
	 * 网址
	 */
	@Column(name = "WEBURL", length = 200)
	private String weburl;

	/**
	 * 全路径
	 */
	@Column(name = "FULLPATH", length = 200)
	private String fullpath;

	/**
	 * 行政组织类型
	 */
	@Column(name = "ORGTYPE", length = 128)
	private String orgtype;

	/**
	 * 行政组织小类
	 */
	@Column(name = "ORGDETAILTYPE", length = 128)
	private String orgdetailtype;

	/**
	 * 联系电话
	 */
	@Column(name = "LINKTEL", length = 50)
	private String linktel;

	/**
	 * 是否叶子节点
	 */
	@Column(name = "ISLEAF", length = 1, nullable = false)
	private String isleaf;

	/**
	 * 生效日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "VALID_DATE", nullable = false)
	private Date validDate = new Date();

	/**
	 * 失效日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "INVALID_DATE")
	private Date invalidDate;

	/**
	 * 删除标记
	 */
	@Column(name = "DEL_SIGN", length = 1)
	private String delSign;

	/**
	 * 组织的角色列表
	 */
	@JsonIgnore
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "org")
	private List<GxwlSysOrgRole> orgRoles;
	
	/**
	 * 组织的用户列表
	 */
	@JsonIgnore
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "org")
	private List<GxwlSysOrgUser> orgUsers;
	
	@Transient
	public String getState() {
		return "N".equalsIgnoreCase(getIsleaf()) ? "closed" : "open";
	}

	@Transient
	public String getLinkmanName() {
		GxwlSysUser linkman = getLinkman();
		return linkman == null ? "" : linkman.getRealname();
	}
	
	@Transient
	public Integer getLinkmanId() {
		GxwlSysUser linkman = getLinkman();
		return linkman == null ? null : linkman.getUserId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgFullname() {
		return orgFullname;
	}

	public void setOrgFullname(String orgFullname) {
		this.orgFullname = orgFullname;
	}

	public Integer getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(Integer parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getManaPosition() {
		return manaPosition;
	}

	public void setManaPosition(String manaPosition) {
		this.manaPosition = manaPosition;
	}

	public String getOrgAddr() {
		return orgAddr;
	}

	public void setOrgAddr(String orgAddr) {
		this.orgAddr = orgAddr;
	}

	public GxwlSysUser getLinkman() {
		return linkman;
	}

	public void setLinkmanId(Integer linkmanId) {
		if (linkmanId != null) {
			this.linkman = new GxwlSysUser();
			this.linkman.setUserId(linkmanId);
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWeburl() {
		return weburl;
	}

	public void setWeburl(String weburl) {
		this.weburl = weburl;
	}

	public String getFullpath() {
		return fullpath;
	}

	public void setFullpath(String fullpath) {
		this.fullpath = fullpath;
	}

	public String getOrgtype() {
		return orgtype;
	}

	public void setOrgtype(String orgtype) {
		this.orgtype = orgtype;
	}

	public String getOrgdetailtype() {
		return orgdetailtype;
	}

	public void setOrgdetailtype(String orgdetailtype) {
		this.orgdetailtype = orgdetailtype;
	}

	public String getLinktel() {
		return linktel;
	}

	public void setLinktel(String linktel) {
		this.linktel = linktel;
	}

	public String getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	public String getDelSign() {
		return delSign;
	}

	public void setDelSign(String delSign) {
		this.delSign = delSign;
	}

	public List<GxwlSysOrgRole> getOrgRoles() {
		return orgRoles;
	}

	public void setOrgRoles(List<GxwlSysOrgRole> orgRoles) {
		this.orgRoles = orgRoles;
	}

	public List<GxwlSysOrgUser> getOrgUsers() {
		return orgUsers;
	}

	public void setOrgUsers(List<GxwlSysOrgUser> orgUsers) {
		this.orgUsers = orgUsers;
	}

}
