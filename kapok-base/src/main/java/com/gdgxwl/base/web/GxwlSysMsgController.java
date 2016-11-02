package com.gdgxwl.base.web;

import com.gdgxwl.base.common.SessionUtil;
import com.gdgxwl.base.domain.GxwlSysMsg;
import com.gdgxwl.base.domain.GxwlSysMsgReciever;
import com.gdgxwl.base.service.GxwlSysMsgService;
import com.gdgxwl.base.service.GxwlSysMsgServiceReciever;
import com.gdgxwl.core.common.web.SearchUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Will WM. Zhang
 *
 */
@Controller
@RequestMapping(value = "/msg")
public class GxwlSysMsgController {
	
	@Autowired
	private GxwlSysMsgService msgService;
	
	@Autowired
	private GxwlSysMsgServiceReciever msgServiceReciever;
	
	@RequiresPermissions("MSG_LIST")
	@RequestMapping(value = "/list")
	public String toList() {
		return "base/msg/msg";
	}
	// 发送消息
	/**
	 * @param msg 为消息对象其中包含了以下属性：<br/>
	 * 	      msgTitle（消息标题）必填<br/>
	 * 	      msgBody（消息内容）必填<br/>
	 * 	      msgType（消息类型：INTERNAL, SMS, EMAIL）必填，默认为INTERNAL<br/>
	 * 	      sendBy（发送人，当发送人是基础应用中的用户时（userId），使用此字段）<br/>
	 * 	      sender（发送人，当发送人是非基础应用中的用户时，使用此字段）<br/>
	 * 		  sendBy与sender二选一必填
	 * @return
	 */
	@RequestMapping(value = "/send")
	@ResponseBody
	public Map<String, Object> send(GxwlSysMsg msg) {
		return msgService.sendMsg(msg);
	}
	
	// 读取消息
	/**
	 * @param recieverId
	 * @return
	 */
	@RequestMapping(value = "/read/{recieverId}")
	@ResponseBody
	public Map<String, Object> read(@PathVariable Integer recieverId) {
		return msgService.readMsg(recieverId);
	}
	
	/**
	 * @param sendBy
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/getSendbyMsgList")
	@ResponseBody
	public Map<String, Object> getSendedList(ServletRequest req) {
		// 构造默认条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sendBy_EQ", SessionUtil.getCurrentUser().getUserId());
		
		// 使用SearchUtil工具类从前端请求ServletRequest里获得GxwlSysUser的查询对象, 并附加上默认条件
		Specification<GxwlSysMsg> spec = SearchUtil.getSpecification(GxwlSysMsg.class, req, params);
		
		// 使用SearchUtil工具类从前端请求ServletRequest里获得分页对象
		Pageable pageable = SearchUtil.getPageableWithOrderBy(req, "sendedTime_desc");
		return msgService.doSearch(spec, pageable);
	}
	
	/**
	 * @param sender
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/getSenderMsgList")
	@ResponseBody
	public Map<String, Object> getSendedList(String sender, ServletRequest req) {
		// 构造默认条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sender_EQ", sender);
		
		// 使用SearchUtil工具类从前端请求ServletRequest里获得GxwlSysUser的查询对象, 并附加上默认条件
		Specification<GxwlSysMsg> spec = SearchUtil.getSpecification(GxwlSysMsg.class, req, params);
		
		// 使用SearchUtil工具类从前端请求ServletRequest里获得分页对象
		Pageable pageable = SearchUtil.getPageableWithOrderBy(req, "sendedTime_desc");
		return msgService.doSearch(spec, pageable);
	}


	/**
	 * @param reciever
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/getRecieverByMsgList")
	@ResponseBody
	public Map<String, Object> getRecieverByMsgList(ServletRequest req) {
		// 构造默认条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("receiverBy_EQ", SessionUtil.getCurrentUser().getUserId());
		
		// 使用SearchUtil工具类从前端请求ServletRequest里获得GxwlSysUser的查询对象, 并附加上默认条件
		Specification<GxwlSysMsgReciever> spec = SearchUtil.getSpecification(GxwlSysMsgReciever.class, req, params);
		
		// 使用SearchUtil工具类从前端请求ServletRequest里获得分页对象
		Pageable pageable = SearchUtil.getPageableWithOrderBy(req, "sendTime_desc");
		return msgServiceReciever.doSearch(spec, pageable);
	}	
	
	/**
	 * @param reciever
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/getRecieverMsgList")
	@ResponseBody
	public Map<String, Object> getRecieveredList(String reciever, ServletRequest req) {
		// 构造默认条件
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("receiver_EQ", reciever);
		
		// 使用SearchUtil工具类从前端请求ServletRequest里获得GxwlSysUser的查询对象, 并附加上默认条件
		Specification<GxwlSysMsgReciever> spec = SearchUtil.getSpecification(GxwlSysMsgReciever.class, req, params);
		
		// 使用SearchUtil工具类从前端请求ServletRequest里获得分页对象
		Pageable pageable = SearchUtil.getPageableWithOrderBy(req, "sendTime_desc");
		return msgServiceReciever.doSearch(spec, pageable);
	}
}
