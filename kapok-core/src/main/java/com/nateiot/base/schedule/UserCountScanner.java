package com.nateiot.base.schedule;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nateiot.base.service.GxwlSysUserService;

/**
 * @author Will WM. Zhang
 * 
 */
@Component
public class UserCountScanner {

	@Autowired
	private GxwlSysUserService gxwlSysUserService;

	public void execute() {
		// Map<String, Object> map = gxwlSysUserService.setUserName(68, "abc" +
		// System.currentTimeMillis());
		System.out.println("job : "
				+ new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
	}
}
