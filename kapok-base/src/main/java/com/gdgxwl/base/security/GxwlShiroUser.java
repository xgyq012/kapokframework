package com.gdgxwl.base.security;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * @author Will WM. Zhang
 *
 */
public class GxwlShiroUser implements Serializable {
	
	private static final long serialVersionUID = 6409824269412984497L;
	
	private Integer userId;
	private String userName;
	private String realname;

	public GxwlShiroUser(Integer userId, String userName, String realname) {
		this.userId = userId;
		this.userName = userName;
		this.realname = realname;
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

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * 作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return realname;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "username");
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, "username");
	}
}
