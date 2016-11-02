package com.gdgxwl.base.web;

import com.gdgxwl.base.service.GxwlSysDataPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author Will WM. Zhang
 *
 */
@Controller
@RequestMapping(value = "/datap")
public class GxwlSysDataPController {

	@Autowired
	private GxwlSysDataPService datapService;

	// 根据资源编码获取当前用户的可用资源列表头
	@RequestMapping(value = "/getResourceHeaders", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCurrentUserResourceHeaderByResourceCode(
			String resourceCode) {
		return datapService
				.getCurrentUserResourceHeaderByResourceCode(resourceCode);
	}

	// 根据资源编码获取当前用户的可用资源查询条件
	@RequestMapping(value = "/getResourceSqlWhere", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCurrentUserResourceSqlWhereByResourceCode(
			String resourceCode) {
		return datapService
				.getCurrentUserResourceSqlWhereByResourceCode(resourceCode);
	}
}
