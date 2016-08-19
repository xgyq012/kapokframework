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

import com.nateiot.cis.domain.CisCcNotice;
import com.nateiot.cis.domain.CisSwRijiPingjia;
import com.nateiot.cis.service.CisCcNoticeService;
import com.nateiot.cis.service.CisSwRijiPingjiaService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 民情日记评价
 * @author xiewenhua
 *
 */
@Controller
@RequestMapping("/rijipingjia")
public class CisSwRijiPingjiaController {
	@Autowired
	private CisSwRijiPingjiaService cisSwRijiPingjiaService;
	
	
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
			return cisSwRijiPingjiaService.doSearch(
					SearchUtil.getSpecification(CisSwRijiPingjia.class, req),
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
	public Map<String, Object> save(CisSwRijiPingjia cisSwRijiPingjia) {
		cisSwRijiPingjia = testAdd();
		return cisSwRijiPingjiaService.doSave(cisSwRijiPingjia);
	}
	
	/**
	 * 详细
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/get/{noticeId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "noticeId") Integer noticeId){
		return cisSwRijiPingjiaService.doSelect(noticeId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{noticeId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "noticeId") Integer noticeId){
		return cisSwRijiPingjiaService.doDelete(noticeId);
	}

	private CisSwRijiPingjia testAdd(){
		CisSwRijiPingjia model = new CisSwRijiPingjia();
		model.setCreaterId(1);
		model.setCreateTime(new Date());
		model.setLastUpdaterId(1);
		model.setLastUpdateTime(new Date());
		
		model.setMinqingRijiId(2);
		model.setPingjiaBody("文章写得一塌糊涂，通篇不知所云！");
		model.setPingjiaLevel(3);
		
		return model;
	}
}
