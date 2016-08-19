package com.nateiot.cis.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisSwEventSlave;
import com.nateiot.cis.service.CisSwEventSlaveService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 服务办事 -- 事件类型行表
 * 
 *  @author Guohw
 */
@Controller
@RequestMapping(value = "/eventSlave")
public class CisSwEventSlaveController {
	
	@Autowired
	private CisSwEventSlaveService cisSwEventSlaveService;
	
	/**
	 * 加载视图
	 * 
	 *  @param 
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/sw/eventSlave/eventSlave";
	}
	
	/**
	 * 查询
	 * 
	 *  @param req
	 *  @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req){
		return cisSwEventSlaveService.doSearch(
				SearchUtil.getSpecification(CisSwEventSlave.class, req),
				SearchUtil.getPageableWithOrderBy(req, "slaveId_desc"));
	}
	
	/**
	 * 详细
	 * 
	 *  @param slaveId
	 *  @return
	 */
	@RequestMapping(value = "/get/{slaveId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "slaveId")Integer slaveId){
		return cisSwEventSlaveService.doSelect(slaveId);
	}
	
	/**
	 * 保存
	 * 
	 *  @param cisSwEventSlave
	 *  @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisSwEventSlave cisSwEventSlave){
		return cisSwEventSlaveService.doSave(cisSwEventSlave);
	}
	
	/**
	 * 删除
	 * 
	 *  @param slaveId
	 *  @return
	 */
	@RequestMapping(value = "/del/{slaveId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "slaveId")Integer slaveId){
		return cisSwEventSlaveService.doDelete(slaveId);
	}

}
