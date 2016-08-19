package com.nateiot.base.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.base.domain.GxwlSysResource;
import com.nateiot.base.service.GxwlSysResourceService;

/**
 * @author Will WM. Zhang
 * 
 */
@Controller
@RequestMapping(value = "/resource")
public class GxwlSysResourceController {

	@Autowired
	private GxwlSysResourceService resourceService;

	@RequestMapping(value = "/list")
	public String toList() {
		return "base/resource/resource";
	}

	// 根据父ID获取当前用户的菜单
	@RequestMapping(value = "/getMenu", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCurrentUserMenuByParenResourceId(
			@RequestParam(value = "id", defaultValue = "1") Integer id) {
		return resourceService.getCurrentUserMenuByParenResourceId(id);
	}	
	
	// 加载资源
	@RequestMapping(value = "/get/{id}")
	@ResponseBody
	public Map<String, Object> getGxwlSysResourceById(@PathVariable Integer id) {
		return resourceService.doSelect(id);
	}
	
	@RequestMapping(value = "/getResource", method = RequestMethod.POST)
	@ResponseBody
	public List<GxwlSysResource> getGxwlSysResourceByParenResourceId(
			@RequestParam(value = "id", defaultValue = "0") Integer id) {
		return resourceService.getGxwlSysResourceByParenResourceId(id);
	}

	// 根据资源类型获取资源
	@RequestMapping(value = "/getResourceByResourceType", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getResourceByResourceType(
			@RequestParam(value = "resourceType", defaultValue = "menu") String resourceType) {
		return resourceService.getCurrentUserMenuByResourceType(resourceType);
	}
	
	// 删除资源
	@RequestMapping(value = "/del/{id}")
	@ResponseBody
	public Map<String, Object> delGxwlSysResourceById(@PathVariable Integer id) {
		return resourceService.doDelete(id);
	}

	// 保存资源
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveGxwlSysResource(GxwlSysResource resource) {
		return resourceService.doSave(resource);
	}
	
	
	// 加载菜单
	@RequestMapping(value = "/getMenuAll")
	@ResponseBody
	public List<Map<String, Object>> getMenuAll() {
		return resourceService.getCurrentUserMenu();
	}	
	
	@RequestMapping(value = "/getMenuByModule/{menuId}")
	@ResponseBody
	public List<Map<String, Object>> getCurrentUserMenuByModule(@PathVariable Integer  menuId){
		return resourceService.getCurrentUserMenuByModule(menuId);
	}
	
	@RequestMapping(value = "/getMenuByModuleParent/{menuId}")
	@ResponseBody
	public List<Map<String, Object>> getCurrentUserMenuByModuleParent(@PathVariable Integer  menuId){
		return resourceService.getCurrentUserMenuByModuleParent(menuId);
	}

}
