package com.nateiot.cis.web;

import com.nateiot.cis.domain.CisBmCultivate;
import com.nateiot.cis.service.CisBmCultivateService;
import com.nateiot.core.common.web.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 劳动保障--培训
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/cultivate")
public class CisBmCultivateController {
	
	@Autowired
	private CisBmCultivateService cisBmCultivateService;
	
	/**
	 * 加载视图
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/bm/cultivate/cultivate";
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
			return cisBmCultivateService.doSearch(
					SearchUtil.getSpecification(CisBmCultivate.class, req),
					SearchUtil.getPageableWithOrderBy(req, "cultivateId_DESC"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisBmCultivate cisBmCultivate) {
		 
		return cisBmCultivateService.doSave(cisBmCultivate);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{cultivateId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "cultivateId") Integer cultivateId){
		return cisBmCultivateService.doSelect(cultivateId);
	}

	
	/**
	 * 硬删除记录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/del/{cultivateId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "cultivateId") Integer cultivateId){
		return cisBmCultivateService.doDelete(cultivateId);
	}
	
}
