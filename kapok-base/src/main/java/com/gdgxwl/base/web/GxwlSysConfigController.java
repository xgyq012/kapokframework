package com.gdgxwl.base.web;

import com.gdgxwl.base.common.ConfigUtil;
import com.gdgxwl.base.domain.GxwlSysConfig;
import com.gdgxwl.base.service.GxwlSysConfigService;
import com.gdgxwl.core.common.web.SearchUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
@Controller
@RequestMapping(value = "/config")
public class GxwlSysConfigController {

	@Autowired
	public GxwlSysConfigService gxwlSysConfigService;
	
	@RequestMapping(value = "/list")
	public String list(Model model) { 
		return "base/config/config";
	}
	
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req) {
		return doSearch(req);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(String config) {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<GxwlSysConfig> configs = null;
		try {
			objectMapper.setDateFormat(dateFormat);
			configs = objectMapper.readValue(config, new TypeReference<List<GxwlSysConfig>>() {});
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		Map<String, Object> result = gxwlSysConfigService.doSave(configs);
		ConfigUtil.syncConfig();
		return result;
				
	}

	private Map<String, Object> doSearch(HttpServletRequest req) {
		// 构造默认条件
		Map<String, Object> params = new HashMap<String, Object>();

		// 使用SearchUtil工具类从前端请求ServletRequest里获得GxwlSysDictH的查询对象, 并附加上默认条件
		Specification<GxwlSysConfig> spec = SearchUtil.getSpecification(GxwlSysConfig.class, req, params);
		
		// 使用SearchUtil工具类从前端请求ServletRequest里获得分页对象，按ID倒序
		Pageable pageable = SearchUtil.getPageableWithOrderBy(req, "configId_desc");
		return gxwlSysConfigService.doSearch(spec, pageable);
	}

}
