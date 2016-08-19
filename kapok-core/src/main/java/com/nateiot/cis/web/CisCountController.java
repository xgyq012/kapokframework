package com.nateiot.cis.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.service.CisCountService;

@Controller
@RequestMapping(value = "/count")
public class CisCountController {

	@Autowired
	private CisCountService cisCountService;
	
	@RequestMapping(value = "getallcount")
	@ResponseBody
	public Map<String, Object> getCountResult(String meshIds){
		return cisCountService.getCountResult(meshIds);
	}

}
