package com.gdgxwl.base.web;

import com.gdgxwl.base.domain.GxwlSysRole;
import com.gdgxwl.base.service.GxwlSysRoleService;
import com.gdgxwl.core.common.web.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
@Controller
@RequestMapping(value = "/role")
public class GxwlSysRoleController {

	@Autowired
	private GxwlSysRoleService roleService;

	@RequestMapping(value = "/list")
	public String toList() {
		return "base/role/role";
	}
	
	//新UI
	@RequestMapping(value = "/list2")
	public String toList2() {
		return "base2/role/role";
	}

	@RequestMapping(value = "/selectrole")
	public String selectRole() {
		return "base/role/role-select";
	}

	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(ServletRequest req) {
		// 构造默认条件
		Map<String, Object> params = new HashMap<String, Object>();
		// params.put("status_EQ", "Y");

		// 使用SearchUtil工具类从前端请求ServletRequest里获得GxwlSysUser的查询对象, 并附加上默认条件
		Specification<GxwlSysRole> spec = SearchUtil.getSpecification(
				GxwlSysRole.class, req, params);

		// 使用SearchUtil工具类从前端请求ServletRequest里获得分页对象，按用户ID倒序
		Pageable pageable = SearchUtil.getPageableWithOrderBy(req,
				"roleId_desc");

		// 使用BaseService里提供的doSearch方法查询用户
		return roleService.doSearch(spec, pageable);
	}

	// 加载角色
	@RequestMapping(value = "/get/{id}")
	@ResponseBody
	public Map<String, Object> getGxwlSysRoleById(@PathVariable Integer id) {
		return roleService.doSelect(id);
	}

	// 加载对应角色的所拥有资源
	@RequestMapping(value = "/getResource/{roleId}")
	@ResponseBody
	public Map<String, Object> getGxwlSysResourceByRoleId(
			@PathVariable Integer roleId) {
		return roleService.getGxwlSysResourceByRoleId(roleId);
	}

	// 删除角色
	@RequestMapping(value = "/del/{id}")
	@ResponseBody
	public Map<String, Object> delGxwlSysRoleById(@PathVariable Integer id) {
		return roleService.doDelete(id);
	}

	// 保存角色
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveGxwlSysRole(GxwlSysRole role) {
		return roleService.doSave(role);
	}
}
