package com.gdgxwl.base.domain;

import com.gdgxwl.base.common.Constant;
import com.gdgxwl.base.common.DictUtil;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

/**
 * @author Will WM. Zhang
 * 
 */
@Entity
@Table(name = "GXWL_SYS_ORGUSER")
public class GxwlSysOrgUser extends BaseEntity {

	private static final long serialVersionUID = 5353346720259681980L;

	/**
	 * 主键ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RELA_ID", nullable = false)
	private Integer relaId;

	/**
	 * 行政组织
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ORG_ID")
	private GxwlSysOrg org;
	
	/**
	 * 用户
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private GxwlSysUser user;


	/**
	 * 是否人员的主行政组织
	 */
	@Column(name = "IS_MAIN", length = 1)
	private String isMain;

	/**
	 * 是否行政组织负责人
	 */
	@Column(name = "IS_MANAGE", length = 1)
	private String isManage;
	
	public void setOrgId(Integer orgId) {
		if (orgId != null) {
			this.org = new GxwlSysOrg();
			this.org.setId(orgId);
		}
	}

	public void setUserId(Integer userId) {
		if (userId != null) {
			this.user = new GxwlSysUser();
			this.user.setUserId(userId);
		}
	}

	@Transient
	public Integer getOrgId() {
		return this.org == null ? null : this.org.getId();
	}

	@Transient
	public Integer getUserId() {
		return this.user == null ? null : this.user.getUserId();
	}

	@Transient
	public String getUserName() {
		return this.user == null ? "" : this.user.getUserName();
	}

	@Transient
	public String getRealname() {
		return this.user == null ? "" : this.user.getRealname();
	}
	
	@Transient
	public String getStatusName() {
		if (this.user != null) {
			if (StringUtils.isEmpty(this.user.getStatus())) {
				return "";
			} else {
				return DictUtil.getDictName(Constant.USER_STATUS, this.user.getStatus());
			}
		} else {
			return "";
		}
	}
	
	public Integer getRelaId() {
		return relaId;
	}

	public void setRelaId(Integer relaId) {
		this.relaId = relaId;
	}

	public GxwlSysOrg getOrg() {
		return org;
	}

	public void setOrg(GxwlSysOrg org) {
		this.org = org;
	}

	public GxwlSysUser getUser() {
		return user;
	}

	public void setUser(GxwlSysUser user) {
		this.user = user;
	}

	public String getIsMain() {
		return isMain;
	}

	public void setIsMain(String isMain) {
		this.isMain = isMain;
	}

	public String getIsManage() {
		return isManage;
	}

	public void setIsManage(String isManage) {
		this.isManage = isManage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((relaId == null) ? 0 : relaId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GxwlSysOrgUser other = (GxwlSysOrgUser) obj;
		if (relaId == null) {
			if (other.relaId != null)
				return false;
		} else if (!relaId.equals(other.relaId))
			return false;
		return true;
	}
	
}
