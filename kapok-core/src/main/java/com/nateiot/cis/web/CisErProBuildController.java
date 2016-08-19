package com.nateiot.cis.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.base.domain.GxwlSysDoc;
import com.nateiot.base.repository.GxwlSysDocDao;
import com.nateiot.base.service.GxwlSysDocService;
import com.nateiot.cis.domain.CisErProBuild;
import com.nateiot.cis.service.CisErProBuildService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 经济运行 -- 项目建设
 * 
 *  @author guohuawen
 */
@Controller
@RequestMapping(value = "/proBuild")
public class CisErProBuildController {
	
	@Autowired
	private CisErProBuildService cisErProBuildService;
	
	@Autowired
	private GxwlSysDocDao gxwlSysDocDao;
	
	@Autowired
	private GxwlSysDocService gxwlSysDocService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/er/proBuild/proBuild";
	}
	
	/**
	 * 查询
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("delSign_EQ", "N");
		return cisErProBuildService.doSearch(
				SearchUtil.getSpecification(CisErProBuild.class, req, params),
				SearchUtil.getPageableWithOrderBy(req, "proBuildId_desc"));
	}
	
	/**
	 * 保存
	 * 
	 *  @param cisErProBuild
	 *  @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisErProBuild cisErProBuild){
		if(cisErProBuild != null){
			cisErProBuild.setDelSign("N");
			if(cisErProBuild.getPictureId() != null){
				GxwlSysDoc bean = gxwlSysDocDao.findOne(cisErProBuild.getPictureId());
				bean.setIsTemp("N");
			}
			
		}
		return cisErProBuildService.doSave(cisErProBuild);
	}
	
	/**
	 * 加载数据
	 * 
	 * @param proBuildId
	 * @return
	 */
	@RequestMapping(value = "/get/{proBuildId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "proBuildId") Integer proBuildId){
		return cisErProBuildService.doSelect(proBuildId);
	}
	
	/**
	 * 硬删除
	 *  
	 * @param proBuildId
	 * @return
	 */
	@RequestMapping(value = "/del/{proBuildId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "proBuildId") Integer proBuildId){
		return cisErProBuildService.doDelete(proBuildId);
	}
	
	/**
	 * 软删除
	 * 
	 * @param proBuildId
	 * @return
	 */
	@RequestMapping(value = "/softDel/{proBuildId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "proBuildId") Integer proBuildId){
		return cisErProBuildService.softDel(proBuildId);
	}

}
