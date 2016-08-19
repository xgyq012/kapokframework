package com.nateiot.cis.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class CisBmMemberController {

	@RequestMapping(value = "/list")
	public String toList() {
		
		return "cis/bm/member/list";
	}
	
}
