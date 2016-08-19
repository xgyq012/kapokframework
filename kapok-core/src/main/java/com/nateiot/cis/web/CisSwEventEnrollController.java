package com.nateiot.cis.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nateiot.base.service.GxwlSysCoderuleService;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisSwEventEnroll;
import com.nateiot.cis.service.CisSwEventEnrollService;
import com.nateiot.core.common.web.SearchUtil;

/**
 * 服务办事 -- 事件登记
 * 
 *  @author Guohw
 */
@Controller
@RequestMapping(value = "/eventEnroll")
public class CisSwEventEnrollController {
	
	@Autowired
	private CisSwEventEnrollService cisSwEventEnrollService;

	@Autowired
	public GxwlSysCoderuleService gxwlSysCoderuleService;
	
	/**
	 * 加载视图
	 * 
	 *  @param
	 */
	@RequestMapping(value = "/list")
	public String list(){
		return "cis/sw/eventEnroll/eventEnroll";
	}
	
	/**
	 * 提交弹出框
	 * 
	 *  @param
	 */
	@RequestMapping(value = "/submitEnroll")
	public String submitEnroll(){
		return "cis/sw/eventEnroll/submitEnroll-select";
	}
	
	/**
	 * 签收弹出框
	 * 
	 *  @param
	 */
	@RequestMapping(value = "/signForEnroll")
	public String signForEnroll(){
		return "cis/sw/eventEnroll/signForEnroll-select";
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
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("delSign_EQ", "N");
		return cisSwEventEnrollService.doSearch(
				SearchUtil.getSpecification(CisSwEventEnroll.class, req, params),
				SearchUtil.getPageableWithOrderBy(req, "enrollId_desc"));
	}
	
	/**
	 * 详细
	 * 
	 *  @param enrollId
	 *  @return
	 */
	@RequestMapping(value = "/get/{enrollId}")
	@ResponseBody
	public Map<String, Object> get(
			@PathVariable(value = "enrollId")Integer enrollId){
		return cisSwEventEnrollService.enrollSelect(enrollId);
	}
	
	/**
	 * 保存
	 * 
	 *  @param cisSwEventEnroll
	 *  @return
	 */
//	@RequestMapping(value = "/save")
//	@ResponseBody
//	public Map<String, Object> save(CisSwEventEnroll cisSwEventEnroll){
//		return cisSwEventEnrollService.doSave(cisSwEventEnroll);
//	}
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, Object> save(
			CisSwEventEnroll cisSwEventEnroll, HttpServletRequest req){
		return cisSwEventEnrollService.enrollSave(cisSwEventEnroll, req.getSession()
				.getServletContext().getRealPath(""));
	}


	
	/**
	 * 删除
	 * 
	 *  @param enrollId
	 *  @return
	 */
	@RequestMapping(value = "/del/{enrollId}")
	@ResponseBody
	public Map<String, Object> del(
			@PathVariable(value = "enrollId")Integer enrollId){
		return cisSwEventEnrollService.enrollDelete(enrollId);
	}
	
	/**
	 * 事件类别下拉框
	 * 
	 *  @param eventType
	 *  @return
	 */
	@RequestMapping(value = "/comboBox/{eventType}")
	@ResponseBody
	public Map<String, Object> comboBox(
			@PathVariable(value = "eventType")String eventType){
		return cisSwEventEnrollService.comboBox(eventType);
	}
	
//	/**
//	 * 按钮权限判断
//	 * 
//	 *  @param userId
//	 *  @return
//	 */
//	@RequestMapping(value = "/permission/{enrollId}")
//	@ResponseBody
//	public Map<String, Object> permission(
//			@PathVariable(value = "enrollId")Integer enrollId){
//		return cisSwEventEnrollService.permission(enrollId);
//	}

	/**
	 * 微信保存
	 * @param req
     * @return
     */
	@RequestMapping(value = "/wxsave")
	@ResponseBody
	public Map<String, Object> wxsave(HttpServletResponse response, HttpServletRequest req){
		String  code = gxwlSysCoderuleService.generateCode("SJDJ");//事件编号

		StringBuilder sb = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) req.getInputStream(), "UTF-8"));
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
            String json = sb.toString();
			ObjectMapper objectMapper = new ObjectMapper();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			CisSwEventEnroll model = null;
			try {
				objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				objectMapper.setDateFormat(dateFormat);
				model = objectMapper.readValue(json, CisSwEventEnroll.class);
				model.setEnrollCode(code);
				model.setEnrollStatus("draft");//草稿状态
				model.setCreaterId(1);
				model.setLastUpdaterId(1);
				model.setEnrollCode(code);
				model.setUnitsId(1);
				return  cisSwEventEnrollService.doSave(model);
			} catch (IOException e) {
				e.printStackTrace();
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}





	
}
