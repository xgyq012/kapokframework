package com.nateiot.base.web;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.base.domain.GxwlSysOrg;
import com.nateiot.base.service.GxwlSysOrgService;

/**
 * @author Will WM. Zhang
 * 
 */
@Controller
@RequestMapping(value = "/org")
public class GxwlSysOrgController {

	@Autowired
	private GxwlSysOrgService orgService;

	@RequestMapping(value = "/list")
	public String toList() {
		return "base/org/org";
	}

	//案件模块的案件进度 所属股队弹出框
	@RequestMapping(value = "/selectOrg")
	public String selectOrg() {
		return "base/org/org-select";
	}
	
	/**
	 * 弹出树形机构选择菜单
	 * @return
	 */
	@RequestMapping(value = "/selectOrgTree")
	public String selectOrgTree() {
		return "base/org/org-select-tree";
	}
	
	/**
	 * 弹出树形机构选择菜单,支持多选
	 * @return
	 */
	@RequestMapping(value = "/selectOrgsTree")
	public String selectOrgsTree() {
		return "base/org/select-orgs-tree";
	}
	
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(ServletRequest req) {
		return orgService.doSearch();
	}
	

	// 保存组织
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveGxwlSysOrg(GxwlSysOrg org) {
		return orgService.doSave(org);
	}

	// 加载组织
	@RequestMapping(value = "/get/{id}")
	@ResponseBody
	public Map<String, Object> getGxwlSysOrgById(@PathVariable Integer id) {
		return orgService.doSelect(id);
	}
	
	@RequestMapping(value = "/getOrg", method = RequestMethod.POST)
	@ResponseBody
	public List<GxwlSysOrg> getGxwlSysOrgByParenOrgId(
			@RequestParam(value = "id", defaultValue = "0") Integer id) {
		return orgService.getGxwlSysOrgByParenOrgId(id);
	}

	// 删除组织
	@RequestMapping(value = "/del/{id}")
	@ResponseBody
	public Map<String, Object> delGxwlSysOrgById(@PathVariable Integer id) {
		return orgService.doDelete(id);
	}

	// 根据组织ID查找对应的行表数据
	@RequestMapping(value = "/getSubData/{orgId}")
	@ResponseBody
	public Map<String, Object> findSubDataByOrgId(@PathVariable Integer orgId) {
		return orgService.findSubDataByOrgId(orgId);
	}

}
