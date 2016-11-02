package com.gdgxwl.base.web;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author Will WM. Zhang
 * 
 */
@Controller
@RequestMapping
public class LoginController {
	
//	@RequestMapping(value = "/index")
//	public String index() {
//		return "base/index";
//	}
	
	@RequestMapping(value = "/index")
	public String index() {
		return "base/index";
	}
	
	@RequestMapping(value = "/sys")
	public String sys() {
		return "base/sys/sys";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:/";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String fail(@RequestParam("username") String username, Model model) {
		return "base/login";
	}
	

}
