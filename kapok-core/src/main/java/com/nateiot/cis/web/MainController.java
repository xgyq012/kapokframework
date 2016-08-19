package com.nateiot.cis.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {

	@RequestMapping(value = "/main")
	public String main(Model model) {
		return "base/main";
	}
	
	@RequestMapping(value = "/main/{module}")
	public String module(@PathVariable(value = "module") String module) {
		return  "cis/" + module + "/" + module;
	}
	
	@RequestMapping(value = "/main/{module}/workbench")
	public String workbench(@PathVariable(value = "module") String module) {
		return  "cis/" + module + "/" + module + "-workbench";
	}

	@RequestMapping(value = "/main/dhs")
	public String homeDhs() {
		return  "dhs/dhs";
	}


}