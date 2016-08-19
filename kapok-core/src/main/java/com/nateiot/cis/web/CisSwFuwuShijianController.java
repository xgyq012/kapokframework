package com.nateiot.cis.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisSwFuwuShijian;
import com.nateiot.cis.service.CisSwFuwuShijianService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 服务事件
 * @author xiewenhua
 *
 */
@Controller
@RequestMapping("/fuwushijian")
public class CisSwFuwuShijianController {
	@Autowired
	private CisSwFuwuShijianService cisSwFuwuShijianService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/sw/fuwushijian/fuwushijian";
	}
	
	/**
	 * 加载视图2
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list2")
	public String list2() {
		return "cis/bm/hospital/hospital-data";
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
		 	Map<String ,Object> params = new HashMap<String, Object>();
		 		params.put("", "");
			return cisSwFuwuShijianService.doSearch(
					SearchUtil.getSpecification(CisSwFuwuShijian.class, req),
					SearchUtil.getPageableWithOrderBy(req, ""));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisSwFuwuShijian cisSwFuwuShijian) {
		cisSwFuwuShijian = testAdd();
		return cisSwFuwuShijianService.doSave(cisSwFuwuShijian);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{fuwuShijianId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "fuwuShijianId") Integer fuwuShijianId){
		return cisSwFuwuShijianService.doSelect(fuwuShijianId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{fuwuShijianId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "fuwuShijianId") Integer fuwuShijianId){
		return cisSwFuwuShijianService.doDelete(fuwuShijianId);
	}

	private CisSwFuwuShijian testAdd(){
		CisSwFuwuShijian model = new CisSwFuwuShijian();
		model.setCreaterId(1);
		model.setCreateTime(new Date());
		model.setLastUpdaterId(1);
		model.setLastUpdateTime(new Date());
		
		model.setQudao(2);
		model.setXyValue("100,38");
		model.setAddress("四川省汶川县");
		model.setShijianPs("发生里氏7.8级地震");
		model.setLastTime(new Date());
		model.setShijianStatus(2);
		model.setBanjiePs("干的很棒！");
		model.setFankuiren("买买提.亚当斯密");
		return model;
	}
	
	/**
	 * 事件督办 -- 获取详细 
	 */
	@RequestMapping(value = "/getDetail/{fuwushijianId}")
	@ResponseBody
	public Map<String, Object> getDetail(
			@PathVariable(value = "fuwushijianId") Integer fuwushijianId){
		return cisSwFuwuShijianService.getDetail(fuwushijianId);
	} 

}
