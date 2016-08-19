package com.nateiot.cis.web;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.service.CisAdAuxiliaryService;
import com.nateiot.core.common.web.SearchUtil;
import com.nateiot.core.web.ExcelExportController;


/**
 * 辅助决策 
 * 
 * @author guohuawen
 *
 */
@Controller
@RequestMapping(value = "/basicData")
public class CisAdAuxiliaryController extends ExcelExportController {
	
	@Autowired
	private CisAdAuxiliaryService cisAdAuxiliaryService;
	
	
	//人口统计
	@RequestMapping(value = "/vitalStatistics")
	public String pageRktj(){
		
		return "cis/ad/populace/rkxxtj";
	}
	 
	
	//房屋信息统计
	@RequestMapping(value = "/houseStatistics")
	public String pageFwtj(){
		
		return "cis/ad/house/fwtj";
	}
	
	//人员事件处理统计
	@RequestMapping(value = "/eventHandling")
	public String pageEvent(){
		
		return "cis/ad/event/event";
	}
	
	//党群建设统计
	@RequestMapping(value = "/partyBuilding")
	public String pageparty(){
		
		return "cis/ad/party/masses";
	}
	
	//应急管理统计
	@RequestMapping(value = "/emergencyManage")
	public String pageemergency(){
		
		return "cis/ad/emergency/emergency";
	}
	
	//机构人员统计
	@RequestMapping(value = "/organization")
	public String pageJgry(){
		
		return "cis/ad/personnel/personnel";
	}
	
	//特殊人群统计
	@RequestMapping(value = "/specialCrowd")
	public String pagetsrq(){
		
		return "cis/ad/crowd/crowd";
	}
	
	//综合治理统计
	@RequestMapping(value = "/governance")
	public String pagezhzl(){
		
		return "cis/ad/govern/govern";
	}
		
	//人口信息统计
	@RequestMapping(value = "/rkxxtj")
	@ResponseBody
	public Map<String, Object> rkxxtj(ServletRequest req) {
		
		
		return cisAdAuxiliaryService.rkxxtj(SearchUtil.getSearchFilters(req),
					SearchUtil.getPageableWithOrderBy(req, "h.ORG_ID_asc"));
	}
	
	//机构人员信息统计查询
	@RequestMapping(value = "/memberSearch")
	@ResponseBody
	public Map<String, Object> memberSearch(ServletRequest req) {
		
		
		return cisAdAuxiliaryService.orgPeople(SearchUtil.getSearchFilters(req),
					SearchUtil.getPageableWithOrderBy(req, "m.MESH_ID_asc"));
	}
	
	//人员事件处理信息统计查询
	@RequestMapping(value = "/eventSearch")
	@ResponseBody
	public Map<String, Object> eventSearch(ServletRequest req) {
		
		
		return cisAdAuxiliaryService.event(SearchUtil.getSearchFilters(req),
					SearchUtil.getPageableWithOrderBy(req, "e.UNITS_ID_asc"));
	}
	
	//房屋管理信息统计
	@RequestMapping(value = "/fwgltj")
	@ResponseBody
	public Map<String, Object> fwgltj(ServletRequest req) {
		
		
		return cisAdAuxiliaryService.fwgltj(SearchUtil.getSearchFilters(req),
					SearchUtil.getPageableWithOrderBy(req, "t.org_asc"));
	}
	
	//应急管理信息统计查询
	@RequestMapping(value = "/emergencySearch")
	@ResponseBody
	public Map<String, Object> emergencySearch(ServletRequest req) {
		
		
		return cisAdAuxiliaryService.emergency(SearchUtil.getSearchFilters(req),
					SearchUtil.getPageableWithOrderBy(req, "h.YJSJLX_ID_asc"));
	}
	
	
	//党群建设信息统计查询
	@RequestMapping(value = "/partySearch")
	@ResponseBody
	public Map<String, Object> partySearch(ServletRequest req) {
		
		
		return cisAdAuxiliaryService.party(SearchUtil.getSearchFilters(req),
					SearchUtil.getPageableWithOrderBy(req, "t.org_asc"));
	}
	
	//特殊人群信息统计查询
	@RequestMapping(value = "/tsrqtjSearch")
	@ResponseBody
	public Map<String, Object> crowd(ServletRequest req) {
		
		
		return cisAdAuxiliaryService.crowd(SearchUtil.getSearchFilters(req),
					SearchUtil.getPageableWithOrderBy(req, "t.ORG_asc"));
	}
	
	//综合治理信息统计查询
	@RequestMapping(value = "/zhzlSearch")
	@ResponseBody
	public Map<String, Object> governSearch(ServletRequest req) {
		
		
		return cisAdAuxiliaryService.govern(SearchUtil.getSearchFilters(req),
					SearchUtil.getPageableWithOrderBy(req, "t.ORG_asc"));
	}
		
	/**
	 * 综合治理数据统计(加载视图)
	 * 
	 *  @param
	 *  @return
	 */
	@RequestMapping(value = "/overHaul")
	public String overHaul(){
		return "cis/ad/overHaul/overHaul";
	}
	
	/**
	 * 综合治理数据统计(查询) 
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/overHaulFormSearch")
	@ResponseBody
	public Map<String, Object> overHaulFormSearch(ServletRequest req){
		return cisAdAuxiliaryService.overHaulFormSearch(
				SearchUtil.getSearchFilters(req));
	}
	
	// 统计表Excel导出
	@Override
	protected void doExcelExport(HSSFWorkbook workbook, String template,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//综合治理数据统计(导出报表)
		if(template.equals("overHaul")){
			cisAdAuxiliaryService.overHaulFormExport(
					SearchUtil.getSearchFilters(request), workbook);
		}
	}
	
}
