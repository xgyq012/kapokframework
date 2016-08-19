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

import com.nateiot.cis.domain.CisBmHospitalDisputes;
import com.nateiot.cis.service.CisBmHospitalDisputesService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 公共安全--医院纠纷
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/hoDisputes")
public class CisBmHospitalDisputesController {
	
	@Autowired
	private CisBmHospitalDisputesService cisBmHospitalDisputesService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/hoDisputes/hoDisputes";
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
		 	 
			return cisBmHospitalDisputesService.doSearch(
					SearchUtil.getSpecification(CisBmHospitalDisputes.class, req),
					SearchUtil.getPageableWithOrderBy(req, "disputesId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmHospitalDisputes cisBmHospitalDisputes) {
		 
		return cisBmHospitalDisputesService.doSave(cisBmHospitalDisputes);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{disputesId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "disputesId") Integer disputesId){
		return cisBmHospitalDisputesService.doSelect(disputesId);
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/softDel/{disputesId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "disputesId") Integer disputesId){
		return cisBmHospitalDisputesService.softDel(disputesId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{disputesId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "disputesId") Integer disputesId){
		return cisBmHospitalDisputesService.doDelete(disputesId);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param disputesIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(
			@RequestParam(value = "disputesIds") String disputesIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(disputesIds)){
			list = new ArrayList<Integer>();
			String[] arr = disputesIds.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmHospitalDisputesService.softDelList(list);
	}

}
