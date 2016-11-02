package com.gdgxwl.base.web;

import com.gdgxwl.base.domain.GxwlSysResource;
import com.gdgxwl.base.service.GxwlSysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

	// 获取当前用户资源
	@RequestMapping(value = "/getCurrentUserResource")
	@ResponseBody
	public Map<String, Object> getCurrentUserResource(
			@RequestParam(value = "resourceType", defaultValue = "menu") String resourceType,
			@RequestParam(value = "systemType", defaultValue = "browser") String systemType) {
		return resourceService.getCurrentUserMenuByResourceType(resourceType, systemType);
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
