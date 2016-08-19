package com.nateiot.cis.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisEsModiary;
import com.nateiot.cis.service.CisEsModiaryService;
import com.nateiot.core.common.web.SearchUtil;
import com.nateiot.core.web.ExcelController;


/**
 * 考核督办 -- 民情日记统计
 *    
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/moDiary")
public class CisEsModiaryController extends ExcelController{
	
	@Autowired
	private CisEsModiaryService cisEsModiaryService;
	
	/**
	 * 加载视图
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list() {
		return "cis/es/moDiary/moDiary";
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
			return cisEsModiaryService.doSearch(
					SearchUtil.getSpecification(CisEsModiary.class, req, params),
					SearchUtil.getPageableWithOrderBy(req, "moDiaryId_DESC"));
	}
	
	/**
	 * 查询民情日记统计表数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/modiaryFormSearch")
	@ResponseBody
	public Map<String, Object> sfEnterpriseFormSearch(ServletRequest req) {
		return cisEsModiaryService.modiaryFormSearch(
				SearchUtil.getSearchFilters(req));
	}
	
	@Override
	protected void doExcelExport(HSSFWorkbook workbook, 
			HttpServletRequest req, HttpServletResponse res) throws Exception {
			cisEsModiaryService.moDiaryFormExport(
					SearchUtil.getSearchFilters(req), workbook);
	}

		
}
