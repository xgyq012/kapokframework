package com.nateiot.cis.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmSchool;
import com.nateiot.cis.service.CisBmSchoolService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 学校信息
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/school")
public class CisBmSchoolController {
	
	@Autowired
	private CisBmSchoolService cisBmSchoolService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/school/list";
	}
	
	
	/**
	 * 查询
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req) {
		 	 
			return cisBmSchoolService.doSearch(
					SearchUtil.getSpecification(CisBmSchool.class, req),
					SearchUtil.getPageableWithOrderBy(req, "schoolId_desc"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmSchool cisBmSchool) {
		return cisBmSchoolService.doSave(cisBmSchool);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{schoolId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "schoolId") Integer schoolId){
		return cisBmSchoolService.doSelect(schoolId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{schoolId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "schoolId") Integer schoolId){
		return cisBmSchoolService.softDel(schoolId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{schoolId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "schoolId") Integer schoolId){
		return cisBmSchoolService.doDelete(schoolId);
	}
	
	/**
	 * 硬批量删除记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delList(@RequestParam(value = "ids") String ids){
		
		List<Integer> list = null;
		if(StringUtils.isNotBlank(ids)){
			list = new ArrayList<Integer>()  ;
			String[] arry = ids.split(",");
			for (String id : arry) {
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		
		return cisBmSchoolService.delList(list);
	}

	/**
	 * 学校查询
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/querySchool")
	@ResponseBody
	public Map<String, Object> querySchol(HttpServletRequest req){
		
		return  cisBmSchoolService.querySchool(SearchUtil.getSearchFilters(req),
				SearchUtil.getPageableWithOrderBy(req, ""));
	}
}
