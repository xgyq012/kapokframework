package com.nateiot.cis.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.base.domain.GxwlSysDoc;
import com.nateiot.base.repository.GxwlSysDocDao;
import com.nateiot.base.service.GxwlSysDocService;
import com.nateiot.cis.domain.CisEsKnowledge;
import com.nateiot.cis.service.CisEsKnowledgeService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * 考核督办--知识库
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/knowledge")
public class CisEsKnowledgeController {
	
	@Autowired
	private CisEsKnowledgeService cisEsKnowledgeService;
	
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
	@RequestMapping(value = "/list/{pageType}")
	public String list(@PathVariable(value = "pageType") String pageType , Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(pageType.equals("examine")){
			params.put("pageType", pageType);
		}else if(pageType.equals("knowledge")){
			params.put("pageType", pageType);
		}
		
		return "cis/es/knowledge/knowledge";
	}
	
	/**
	 * 查询
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/search/{pageType}")
	@ResponseBody
	public Map<String, Object> search(
			@PathVariable(value="pageType")String pageType, HttpServletRequest req) {
		 	Map<String ,Object> params = new HashMap<String, Object>();
		 		params.put("delSign_EQ", "N");
		 		params.put("knowledgeType_EQ", pageType);
			return cisEsKnowledgeService.doSearch(
					SearchUtil.getSpecification(CisEsKnowledge.class, req, params),
					SearchUtil.getPageableWithOrderBy(req, "knowledgeId_desc"));
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
		 		params.put("delSign_EQ", "N");
			return cisEsKnowledgeService.doSearch(
					SearchUtil.getSpecification(CisEsKnowledge.class, req, params),
					SearchUtil.getPageableWithOrderBy(req, "knowledgeId_desc"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisEsKnowledge cisEsKnowledge) {
		if(cisEsKnowledge!=null){
			cisEsKnowledge.setDelSign("N");
			if(cisEsKnowledge.getKnowledgeDocId() != null){
				GxwlSysDoc bean = gxwlSysDocDao.findOne(cisEsKnowledge.getKnowledgeDocId());
				bean.setIsTemp("N");
			}
		}
		return cisEsKnowledgeService.doSave(cisEsKnowledge);
	}
	
	/**
	 * 详细
	 * 
	 * @param knowledgeId
	 * @return
	 */
	@RequestMapping(value = "/get/{knowledgeId}")
	@ResponseBody
	public Map<String, Object> getKnowledgeId(
			@PathVariable(value = "knowledgeId") Integer knowledgeId){
		return cisEsKnowledgeService.doSelect(knowledgeId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param knowledgeId
	 * @return
	 */
	@RequestMapping(value = "/del/{knowledgeId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "knowledgeId") Integer knowledgeId){
		return cisEsKnowledgeService.doDelete(knowledgeId);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param knowledgeIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(
			@RequestParam(value = "knowledgeIds") String knowledgeIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(knowledgeIds)){
			list = new ArrayList<Integer>();
			String[] arr = knowledgeIds.split(",");
			for(String id : arr){
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisEsKnowledgeService.softDelList(list);
	}
	
	@RequestMapping(value = "/send")
	@ResponseBody
	public Map<String, Object> send(String emailAddress, Integer knowledgeId) {
		return cisEsKnowledgeService.sendmail(emailAddress, knowledgeId);
	}

	/**
	 * 手机端接口：发送邮件
	 * 
	 * @param knowledgeId
	 * @param enterpriseIds
	 * @return
	 */
//	@RequestMapping(value = "/sendmail")
//	@ResponseBody
//	public Map<String, Object> sendmail(
//			@RequestParam(value = "knowledgeId", required = true) Integer knowledgeId,
//			@RequestParam(value = "enterpriseIds", required = true) String enterpriseIds) {
//		return cisEsKnowledgeService.sendmail(enterpriseIds, knowledgeId);
//	}
	@RequestMapping(value = "/sendmail")
	@ResponseBody
	public Map<String, Object> sendmail(
			@RequestParam(value = "knowledgeId", required = true) Integer knowledgeId,
			@RequestParam(value = "emailAddress", required = true) String emailAddress) {
		return cisEsKnowledgeService.sendmail2(emailAddress, knowledgeId);
	}
	
	/**
	 * 考核督办工作台
	 * 
	 *  @param 
	 *  @return
	 */
	@RequestMapping(value = "/getData")
	@ResponseBody
	public Map<String, Object> getData(){
		return cisEsKnowledgeService.getData();
	}

}
