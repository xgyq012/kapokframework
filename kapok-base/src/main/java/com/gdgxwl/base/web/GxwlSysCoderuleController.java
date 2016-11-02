package com.gdgxwl.base.web;

import com.gdgxwl.base.service.GxwlSysCoderuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Will WM. Zhang
 * 
 */
@Controller
@RequestMapping(value = "/coderule")
public class GxwlSysCoderuleController {

	@Autowired
	public GxwlSysCoderuleService gxwlSysCoderuleService;

	@RequestMapping(value = "/generateCode/{coderuleCode}")
	@ResponseBody
	public String generateCode(@PathVariable String coderuleCode) {
		return gxwlSysCoderuleService.generateCode(coderuleCode);
	}

}
