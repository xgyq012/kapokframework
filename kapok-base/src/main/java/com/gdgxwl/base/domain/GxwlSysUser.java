package com.gdgxwl.base.domain;

import com.gdgxwl.base.common.Constant;
import com.gdgxwl.base.common.DictUtil;
import com.gdgxwl.core.common.json.JsonDateSerializer;
import org.apache.commons.lang3.StringUtils;
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
@Table(name = "GXWL_SYS_USER")
public class GxwlSysUser extends BaseEntity {

	private static final long serialVersionUID = -1439639041109947265L;

	/**
	 * 用户ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Integer userId;

	/**
	 * 用户登录名称
	 */
	@Column(name = "USER_NAME", length = 200)
	private String userName;

	/**
	 * 密码
	 */
	@JsonIgnore
	@Column(name = "PASSWORD", length = 128)
	private String password;
	
	/**
	 * 盐
	 */
	@JsonIgnore
	@Column(name = "SALT", length = 128)
	private String salt;

	/**
	 * 认证模式
	 */
	@Column(name = "AUTHMODE", length = 128)
	private String authmode;

	/**
	 * 状态
	 */
	@Column(name = "STATUS", length = 128)
	private String status;

	/**
	 * 最近登录日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "LASTLOGIN")
	private Date lastlogin;

	/**
	 * 出错次数
	 */
	@Column(name = "ERRCOUNT")
	private Integer errcount;

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
	 * 允许操作时间
	 */
	@Column(name = "VALID_TIMES")
	private int validTimes;

	/**
	 * 允许的MAC
	 */
	@Column(name = "MAC_CODE", length = 4000)
	private String macCode;

	/**
	 * 允许的IP
	 */
	@Column(name = "IP_ADDRESS", length = 4000)
	private String ipAddress;

	/**
	 * 员工编号
	 */
	@Column(name = "EMPCODE", length = 50)
	private String empcode;

	/**
	 * 员工姓名
	 */
	@Column(name = "EMPNAME", length = 200)
	private String empname;

	/**
	 * 实际名称
	 */
	@Column(name = "REALNAME", length = 200)
	private String realname;

	/**
	 * 性别
	 */
	@Column(name = "GENDER", length = 128)
	private String gender;

	/**
	 * 出生日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "BIRTHDATE")
	private Date birthdate;

	/**
	 * 职位
	 */
	@Column(name = "POSITION", length = 128)
	private String position;

	/**
	 * 在职状态
	 */
	@Column(name = "EMPSTATUS", length = 128)
	private String empstatus;

	/**
	 * 证件类型
	 */
	@Column(name = "CARDTYPE", length = 128)
	private String cardtype;

	/**
	 * 证件号码
	 */
	@Column(name = "CARDNO", length = 128)
	private String cardno;

	/**
	 * 入职日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "INDATE")
	private Date indate;

	/**
	 * 离职日期
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	@JsonSerialize(using = JsonDateSerializer.class)
	@Column(name = "OUTDATE")
	private Date outdate;

	/**
	 * 办公电话
	 */
	@Column(name = "OTEL", length = 50)
	private String otel;

	/**
	 * 办公地址
	 */
	@Column(name = "OADDRESS", length = 200)
	private String oaddress;

	/**
	 * 邮政编码
	 */
	@Column(name = "OZIPCODE", length = 50)
	private String ozipcode;

	/**
	 * 传真
	 */
	@Column(name = "FAXNO", length = 50)
	private String faxno;

	/**
	 * 移动电话
	 */
	@Column(name = "MOBILENO", length = 50)
	private String mobileno;

	/**
	 * MSN
	 */
	@Column(name = "MSN", length = 50)
	private String msn;

	/**
	 * QQ
	 */
	@Column(name = "QQ", length = 50)
	private String qq;

	/**
	 * 家庭电话
	 */
	@Column(name = "HTEL", length = 50)
	private String htel;

	/**
	 * 联系地址
	 */
	@Column(name = "HADDRESS", length = 200)
	private String haddress;

	/**
	 * 职级
	 */
	@Column(name = "POSITION_LEVEL", length = 128)
	private String positionLevel;

	/**
	 * 职等
	 */
	@Column(name = "POSITION_RANK", length = 128)
	private String positionRank;

	/**
	 * 国籍
	 */
	@Column(name = "NATIONALITY", length = 128)
	private String nationality;

	/**
	 * 邮箱
	 */
	@Column(name = "EMAIL", length = 128)
	private String email;

	/**
	 * 用户的角色列表
	 */
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, mappedBy = "user")
	private List<GxwlSysUserRole> userRoles;

	@Transient
	public String getStatusName() {
		if (StringUtils.isEmpty(getStatus())) {
			return "";
		} else {
			return DictUtil.getDictName(Constant.USER_STATUS, getStatus());
		}
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getAuthmode() {
		return authmode;
	}

	public void setAuthmode(String authmode) {
		this.authmode = authmode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	public Integer getErrcount() {
		return errcount;
	}

	public void setErrcount(Integer errcount) {
		this.errcount = errcount;
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

	public int getValidTimes() {
		return validTimes;
	}

	public void setValidTimes(int validTimes) {
		this.validTimes = validTimes;
	}

	public String getMacCode() {
		return macCode;
	}

	public void setMacCode(String macCode) {
		this.macCode = macCode;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getEmpcode() {
		return empcode;
	}

	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmpstatus() {
		return empstatus;
	}

	public void setEmpstatus(String empstatus) {
		this.empstatus = empstatus;
	}

	public String getCardtype() {
		return cardtype;
	}

	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public Date getIndate() {
		return indate;
	}

	public void setIndate(Date indate) {
		this.indate = indate;
	}

	public Date getOutdate() {
		return outdate;
	}

	public void setOutdate(Date outdate) {
		this.outdate = outdate;
	}

	public String getOtel() {
		return otel;
	}

	public void setOtel(String otel) {
		this.otel = otel;
	}

	public String getOaddress() {
		return oaddress;
	}

	public void setOaddress(String oaddress) {
		this.oaddress = oaddress;
	}

	public String getOzipcode() {
		return ozipcode;
	}

	public void setOzipcode(String ozipcode) {
		this.ozipcode = ozipcode;
	}

	public String getFaxno() {
		return faxno;
	}

	public void setFaxno(String faxno) {
		this.faxno = faxno;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getHtel() {
		return htel;
	}

	public void setHtel(String htel) {
		this.htel = htel;
	}

	public String getHaddress() {
		return haddress;
	}

	public void setHaddress(String haddress) {
		this.haddress = haddress;
	}

	public String getPositionLevel() {
		return positionLevel;
	}

	public void setPositionLevel(String positionLevel) {
		this.positionLevel = positionLevel;
	}

	public String getPositionRank() {
		return positionRank;
	}

	public void setPositionRank(String positionRank) {
		this.positionRank = positionRank;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<GxwlSysUserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<GxwlSysUserRole> userRoles) {
		this.userRoles = userRoles;
	}

}