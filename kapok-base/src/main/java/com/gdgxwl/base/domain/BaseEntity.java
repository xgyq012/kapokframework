package com.gdgxwl.base.domain;

import com.gdgxwl.core.common.json.JsonDateTimeSerializer;
import com.gdgxwl.core.repository.DBUtil;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Will WM. Zhang
 * 
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 创建人
	 */
	@CreatedBy
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "CREATE_BY", nullable = false)
	private GxwlSysUser createBy;

	/**
	 * 创建时间
	 */
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "CREATE_TIME", nullable = false)
	private Date createTime;

	/**
	 * 最后修改人
	 */
	@LastModifiedBy
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "LAST_UPDATE_BY", nullable = false)
	private GxwlSysUser lastUpdateBy;

	/**
	 * 最后修改时间
	 */
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@Column(name = "LAST_UPDATE_TIME", nullable = false)
	private Date lastUpdateTime;

	@Transient
	public Integer getCreaterId() {
		return getCreateBy() == null ? null : getCreateBy().getUserId();
	}

	@Transient
	public String getCreater() {
		return getCreateBy() == null ? null : getCreateBy().getRealname();
	}

	@Transient
	public Integer getLastUpdaterId() {
		return getLastUpdateBy() == null ? null : getLastUpdateBy().getUserId();
	}
	
	@Transient
	public String getLastUpdater() {
		return getLastUpdateBy() == null ? null : getLastUpdateBy().getRealname();
	}

	public GxwlSysUser getCreateBy() {
		return createBy;
	}

	public void setCreateBy(GxwlSysUser createBy) {
		this.createBy = createBy;
	}

	public void setCreaterId(Integer createrId) {
		if (createrId != null) {
			this.createBy =	DBUtil.find(GxwlSysUser.class, createrId);
		}
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public GxwlSysUser getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(GxwlSysUser lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public void setLastUpdaterId(Integer lastUpdaterId) {
		if (lastUpdaterId != null) {
			this.lastUpdateBy =	DBUtil.find(GxwlSysUser.class, lastUpdaterId);
		}
	}
	
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
