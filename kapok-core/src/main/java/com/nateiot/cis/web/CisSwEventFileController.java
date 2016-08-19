package com.nateiot.cis.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisSwEventFile;
import com.nateiot.cis.service.CisSwEventFileService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 服务办事 -- 事件登记文件上传
 * 
 *  @author Guohw
 */
@Controller
@RequestMapping(value = "/eventFile")
public class CisSwEventFileController {
	
	@Autowired
	private CisSwEventFileService cisSwEventFileService;
	
	/**
	 * 加载视图
	 * 
	 * @param 
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/sw/eventFile/eventFile";
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
		return cisSwEventFileService.doSearch(
				SearchUtil.getSpecification(CisSwEventFile.class, req),
				SearchUtil.getPageableWithOrderBy(req, "fileId_desc"));
	}
	
	/**
	 * 详细
	 * 
	 *  @param fileId
	 *  @return
	 */
	@RequestMapping(value = "/get/{fileId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "fileId")Integer fileId){
		return cisSwEventFileService.doSelect(fileId);
	}
	
	/**
	 * 保存
	 * 
	 * @param cisSwEventFile
	 * @return
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(CisSwEventFile cisSwEventFile){
		return cisSwEventFileService.doSave(cisSwEventFile);
	}
	
	/**
	 * 删除
	 * 
	 *  @param fileId
	 *  @return
	 */
	@RequestMapping(value = "/del/{fileId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "fileId")Integer fileId){
		return cisSwEventFileService.doDelete(fileId);
	}

}
