package com.nateiot.cis.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisCcMyNotice;
import com.nateiot.cis.domain.CisCcNotice;
import com.nateiot.cis.service.CisCcMyNoticeService;
import com.nateiot.cis.service.CisCcNoticeService;
import com.nateiot.cis.service.CisCcTargetUserService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 消息公告或短信消息
 * @author xiewenhua
 *
 */
@Controller
@RequestMapping(value = "/notice")
public class CisCcNoticeController {
	@Autowired
	private CisCcNoticeService cisCcNoticeService;
	
	@Autowired
	private CisCcMyNoticeService cisCcMyNoticeService;
	
	@Autowired
	private CisCcTargetUserService cisCcTargetUserService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/ad/notice/noticelist";
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
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sms/list")
	public String smsList() {
		return "cis/ad/sms/smslist";
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
			return cisCcNoticeService.doSearch(
					SearchUtil.getSpecification(CisCcNotice.class, req, params),
					SearchUtil.getPageableWithOrderBy(req, "createTime_desc"));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public Map<String, Object> save(CisCcNotice cisCcNotice) {
		return cisCcNoticeService.doSave(cisCcNotice); 
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
		return cisCcNoticeService.doSelect(noticeId);
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
		return cisCcNoticeService.doDelete(noticeId);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/resetreadstatus/{noticeId}")
	@ResponseBody
	public Map<String, Object> resetReadStatus(
			@PathVariable(value = "noticeId") Integer noticeId){
		//Integer userId = SessionUtil.getCurrentUser().getUserId();  
		//XXX 66为测试用的常量，应改为变量userId
		cisCcMyNoticeService.resetReadStatus(66, noticeId);
		return null;
	}

	/**
	 * 定时更新与用户相关的公告消息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updatenotice")
	@ResponseBody
	public Map<String, Object> updateNotice(){
		//Integer userId = SessionUtil.getCurrentUser().getUserId();  
		//66为测试用的常量，应改为变量userId
		List<CisCcMyNotice> list = cisCcMyNoticeService.findByUserIdAndReadStatus(66, 0);
		Map<String, Object> map = new HashMap<String, Object>();
		if(! list.isEmpty()){
			map.put("newInfo", "yes");
			return map;
		}else{
			map.put("newInfo", "no");
		}
		return map;
	}
	
	/**
	 * 加载消息公告彈窗
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/totanchuang")
	public String toTanchuang() {
		return "cis/ad/notice/tanchuang_noticelist";
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param householderIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(@RequestParam(value = "ids") String noticeIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(noticeIds)){
			list = new ArrayList<Integer>()  ;
			String[] arry = noticeIds.split(",");
			for (String id : arry) {
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisCcNoticeService.softDel(list);
	}
}
