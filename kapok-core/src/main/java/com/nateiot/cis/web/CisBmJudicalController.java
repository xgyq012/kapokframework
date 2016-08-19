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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmJudicialInfo;
import com.nateiot.cis.service.CisBmJudicalService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 司法信息
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/judical")
public class CisBmJudicalController {
	
	@Autowired
	private CisBmJudicalService cisBmJudicalService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/judical/judical";
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
		 		
		 	
			return cisBmJudicalService.doSearch(
					SearchUtil.getSpecification(CisBmJudicialInfo.class, req),
					SearchUtil.getPageableWithOrderBy(req, "judicialId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmJudicialInfo cisBmJudicialInfo) {
		 
		return cisBmJudicalService.doSave(cisBmJudicialInfo);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{judicialId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "judicialId") Integer judicialId){
		return cisBmJudicalService.doSelect(judicialId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{judicialId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "judicialId") Integer judicialId){
		return cisBmJudicalService.softDel(judicialId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{judicialId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "judicialId") Integer judicialId){
		return cisBmJudicalService.doDelete(judicialId);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param judicalIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(
			@RequestParam(value = "judicialId") String judicialId){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(judicialId)){
			list = new ArrayList<Integer>();
			String[] arr = judicialId.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmJudicalService.softDelList(list);
	}

}
