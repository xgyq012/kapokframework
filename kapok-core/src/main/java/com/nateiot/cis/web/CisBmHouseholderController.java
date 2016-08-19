package com.nateiot.cis.web;

import com.nateiot.cis.domain.CisBmHouseholder;
import com.nateiot.cis.service.*;
import com.nateiot.core.common.web.SearchUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/householder")
public class CisBmHouseholderController {

	//人口信息
	@Autowired
	private CisBmHouseholderService cisBmHouseholderService;
	//社保信息
	@Autowired
	private CisBmSocialinfoService cisBmSocialinfoService;
	//医保信息
	@Autowired
	private CisBmHealthinSuranceinfoService cisBmHealthinSuranceinfoService;
	//优抚人员信息
	@Autowired
	private CisBmComfortService cisBmComfortService;
	//低保信息
	@Autowired
	private CisBmLowPeopleService cisBmLowPeopleService;
	//五保供养
	@Autowired
	private CisBmWbgyService cisBmWbgyService;
	//死亡信息
	@Autowired
	private CisBmDieinfoService cisBmDieinfoService;
	//留守儿童信息
	@Autowired
	private CisBmLeftoverChildrenService cisBmLeftoverChildrenService;
	//孤儿信息
	@Autowired
	private CisBmOrphanService cisBmOrphanService;
	//残疾人信息
	@Autowired
	private CisBmHandicappedPeopleService cisBmHandicappedPeopleService;
	//刑释解教人员 
	@Autowired
	private CisBmXsjjryService cisBmXsjjryService;
	//违法青少年
	@Autowired
	private CisBmWfjlService cisBmWfjlService;
	//从事邪教人员 
	@Autowired
	private CisBmCsxjryService cisBmCsxjryService;
	//长期涉法涉诉人员 
	@Autowired
	private CisBmCqsfssryService cisBmCqsfssryService;
	//社区矫正对象 
	@Autowired
	private CisBmSqjzdxService cisBmSqjzdxService;
	//服刑人员 
	@Autowired
	private CisBmFxryService cisBmFxryService;
	//吸毒人员 
	@Autowired
	private CisBmXdryService cisBmXdryService;
	//老年信息
	@Autowired
	private CisBmOldPeopleHService cisBmOldPeopleHService;
	
	@RequestMapping(value = "/list")
	public String toList() {
		
		return "cis/bm/rkxx/list";
	}
	
	@RequestMapping(value = "/holderRegisList")
	public String holderRegisList() {
		
		return "cis/bm/rkxx/registration";
	}
	
	//选择不是户主的人员界面
	@RequestMapping(value = "/holderList")
	public String selectNotHouseHoder() {
		
		return "cis/bm/rkxx/select-notHouseHoder";
	}

	//选择户主
	@RequestMapping(value = "/selectHz")
	public String selectHz() {

		return "cis/bm/rkxx/selectHz";
	}

	//选择人员
	@RequestMapping(value = "/selectHolder")
	public String selectHouseHolder() {

		return "cis/bm/rkxx/selectHolder";
	}
	
	@RequestMapping(value = "/edit")
	public String edit() {
		
		return "cis/bm/rkxx/edit";
	}
	
	
	//育龄妇女
	@RequestMapping(value = "/fertileWoman")
	public String gofertileWoman() {
		
		return "cis/bm/fertileWoman/woman";
	}
	
	//志愿者
	@RequestMapping(value = "/volunteerPage")
	public String goVolunteer() {
		
		return "cis/bm/volunteer/volunteer";
	}
	
	@RequestMapping(value = "/get/{householderId}")
	@ResponseBody
	public Map<String, Object> getHouseholder(@PathVariable(value = "householderId") Integer householderId) {
		
		return cisBmHouseholderService.doSelect(householderId);
	}

	// 保存
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveModel(CisBmHouseholder householder) {
		if(householder!=null){
			householder.setDelSign("N");
		}
		return cisBmHouseholderService.doSave(householder);
	}
	
	
	//查询
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(ServletRequest req) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("delSign_EQ","N");
		Specification<CisBmHouseholder> S = SearchUtil.getSpecification(CisBmHouseholder.class, req , map);
		return cisBmHouseholderService.doSearch(S,SearchUtil.getPageableWithOrderBy(req, "householderId_desc"));
	}
	
	//查询 户主 信息
	@RequestMapping(value = "/searchHousehold")
	@ResponseBody
	public Map<String, Object> searchHousehold(ServletRequest req) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("delSign_NEQ","Y");
		map.put("householderRelation_EQ","1");
		Specification<CisBmHouseholder> S = SearchUtil.getSpecification(CisBmHouseholder.class, req , map);
		return cisBmHouseholderService.doSearch(S,SearchUtil.getPageableWithOrderBy(req, "householderId_desc"));
	}


	
	//查询 不是户主 信息
	@RequestMapping(value = "/searchNotHousehold")
	@ResponseBody
	public Map<String, Object> searchNotHousehold(ServletRequest req) {
		 
		return cisBmHouseholderService.getNotHouseHolder(SearchUtil.getSearchFilters(req),SearchUtil.getPageableWithOrderBy(req, "h.householder_Id_desc"));
	}
	
	//查询党员信息
	@RequestMapping(value = "/partymember")
	@ResponseBody
	public Map<String, Object> getPartymemberInfo(ServletRequest req) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("delSign_EQ","N");
		map.put("politicsStatus_EQ","3");
		Specification<CisBmHouseholder> S = SearchUtil.getSpecification(CisBmHouseholder.class, req , map);
		return cisBmHouseholderService.doSearch(S,SearchUtil.getPageableWithOrderBy(req, "householderId_desc"));
	}
	
	//查询团员信息
	@RequestMapping(value = "/member")
	@ResponseBody
	public Map<String, Object> queryMember(ServletRequest req) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("delSign_EQ","N");
		map.put("politicsStatus_EQ","2");
		Specification<CisBmHouseholder> S = SearchUtil.getSpecification(CisBmHouseholder.class, req , map);
		return cisBmHouseholderService.doSearch(S,SearchUtil.getPageableWithOrderBy(req, "householderId_desc"));
	}
	
	//查询志愿者信息
	@RequestMapping(value = "/volunteer")
	@ResponseBody
	public Map<String, Object> queryVolunteer(ServletRequest req) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("delSign_EQ","N");
		map.put("isVolunteer_EQ","Y");
		Specification<CisBmHouseholder> S = SearchUtil.getSpecification(CisBmHouseholder.class, req , map);
		return cisBmHouseholderService.doSearch(S,SearchUtil.getPageableWithOrderBy(req, "householderId_desc"));
	}
	
	//查询育龄妇女信息
	@RequestMapping(value = "/woman")
	@ResponseBody
	public Map<String, Object> queryWoman(ServletRequest req) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("delSign_EQ","N");
		map.put("age_GTE",15);
		map.put("age_LTE",49);
		map.put("sex_EQ","female");
		Specification<CisBmHouseholder> S = SearchUtil.getSpecification(CisBmHouseholder.class, req , map);
		return cisBmHouseholderService.doSearch(S,SearchUtil.getPageableWithOrderBy(req, "householderId_desc"));
	}


	//查询 存在社保 信息的人员
	@RequestMapping(value = "/searchSocialinfoHolder")
	@ResponseBody
	public Map<String, Object> searchHolderSocialinfo(ServletRequest req) {
		return cisBmSocialinfoService.searchHolderSocialinfo(SearchUtil.getSearchFilters(req),SearchUtil.getPageableWithOrderBy(req, "h.householder_Id_desc"));
	}

	//查询 存在医保 信息的人员
	@RequestMapping(value = "/searchHealthInsurance")
	@ResponseBody
	public Map<String, Object> searchHolderHealthInsurance(ServletRequest req) {
		return cisBmHealthinSuranceinfoService.searchHolderHealthSuranceinfo(SearchUtil.getSearchFilters(req),SearchUtil.getPageableWithOrderBy(req, "h.householder_Id_desc"));
	}
	
	
	@RequestMapping(value = "/getInfo")
	@ResponseBody
	public Object getInfo(@RequestParam (value = "type") String type,@RequestParam(value="householderId") int householderId ){
		
		Object obj = null;
		
		if("sbxx".equals(type)){
			obj = cisBmSocialinfoService.getCisBmSocialinfoByHouseholderId(householderId);
		}else if("ybxx".equals(type)){
			obj = cisBmHealthinSuranceinfoService.getCisBmHealthinSuranceinfoByHouseholderId(householderId);
		}else if("yfxx".equals(type)){
			obj = cisBmComfortService.getCisBmComfortByHouseholderId(householderId);
		}else if("dbxx".equals(type)){
			obj = cisBmLowPeopleService.getCisBmCisBmLowPeopleByHouseholderId(householderId);
		}else if("wbxx".equals(type)){
			obj = cisBmWbgyService.getCisBmWbgyByHouseholderId(householderId);
		}else if("swxx".equals(type)){
			obj = cisBmDieinfoService.getCisBmDieinfoByHouseholderId(householderId);
		}else if("crxx".equals(type)){
			obj = cisBmLeftoverChildrenService.getCisBmLeftoverChildrenByHouseholderId(householderId);
		}else if("crxx".equals(type)){
			obj = cisBmLeftoverChildrenService.getCisBmLeftoverChildrenByHouseholderId(householderId);
		}else if("grxx".equals(type)){
			obj = cisBmOrphanService.getCisBmOrphanByHouseholderId(householderId);
		}else if("cjxx".equals(type)){
			obj = cisBmHandicappedPeopleService.getCisBmHandicappedPeopleByHouseholderId(householderId);
		}else if("xsjjxx".equals(type)){
			obj = cisBmXsjjryService.getCisBmXsjjryByHouseholderId(householderId);
		}else if("wfqsn".equals(type)){
			obj = cisBmWfjlService.getCisBmWfjlByHouseholderId(householderId);
		}else if("xjry".equals(type)){
			obj = cisBmCsxjryService.getCisBmCsxjryByHouseholderId(householderId);
		}else if("cqsfss".equals(type)){
			obj = cisBmCqsfssryService.getCisBmCqsfssryByHouseholderId(householderId);
		}else if("fyry".equals(type)){
			obj = cisBmFxryService.getCisBmFxryByHouseholderId(householderId);
		}else if("lnxx".equals(type)){
			obj = cisBmOldPeopleHService.getCisBmOldPeopleHByHouseholderId(householderId);
		}else if("xdry".equals(type)){
			obj = cisBmXdryService.getCisBmXdryByHouseholderId(householderId);
		}else if("sqjz".equals(type)){
			obj = cisBmSqjzdxService.getCisBmSqjzdxByHouseholderId(householderId);
		}
		
		return obj;
	}
	
	@RequestMapping(value = "/delInfo")
	@ResponseBody
	public Map<String,Object> delInfo(@RequestParam (value = "type") String type,@RequestParam(value="householderId") int householderId ){
		
		Map<String,Object> map = null;
		
		if("sbxx".equals(type)){
			map = cisBmSocialinfoService.doDelete(householderId);
		}else if("ybxx".equals(type)){
			map = cisBmHealthinSuranceinfoService.doDelete(householderId);
		}else if("yfxx".equals(type)){
			map = cisBmComfortService.doDelete(householderId);
		}else if("dbxx".equals(type)){
			map = cisBmLowPeopleService.doDelete(householderId);
		}else if("wbxx".equals(type)){
			map = cisBmWbgyService.doDelete(householderId);
		}else if("swxx".equals(type)){
			map = cisBmDieinfoService.doDelete(householderId);
		}else if("crxx".equals(type)){
			map = cisBmLeftoverChildrenService.doDelete(householderId);
		}else if("grxx".equals(type)){
			map = cisBmOrphanService.doDelete(householderId);
		}else if("cjxx".equals(type)){
			map = cisBmHandicappedPeopleService.doDelete(householderId);
		}else if("xsjjxx".equals(type)){
			map = cisBmXsjjryService.doDelete(householderId);
		}else if("wfqsn".equals(type)){
			map = cisBmWfjlService.doDelete(householderId);
		}else if("xjry".equals(type)){
			map = cisBmCsxjryService.doDelete(householderId);
		}else if("cqsfss".equals(type)){
			map = cisBmCqsfssryService.doDelete(householderId);
		}else if("fyry".equals(type)){
			map = cisBmFxryService.doDelete(householderId);
		}else if("lnxx".equals(type)){
			map = cisBmOldPeopleHService.doDelete(householderId);
		}else if("xdry".equals(type)){
			map = cisBmXdryService.doDelete(householderId);
		}else if("sqjz".equals(type)){
			map = cisBmSqjzdxService.doDelete(householderId);
		}
		
		return map;
	}
	
	/**
	 * 软删除记录
	 * 
	 * @param householderId
	 * @return
	 */
	@RequestMapping(value = "/softDel/{householderId}")
	@ResponseBody
	public Map<String, Object> softDel(
			@PathVariable(value = "householderId") Integer householderId){
		
		return cisBmHouseholderService.softDel(householderId);
	}
	
	/**
	 * 软删除记录列表
	 * 
	 * @param householderIds
	 * @return
	 */
	@RequestMapping(value = "/softDelList")
	@ResponseBody
	public Map<String, Object> softDelList(@RequestParam(value = "ids") String householderIds){
		List<Integer> list = null;
		if(StringUtils.isNotBlank(householderIds)){
			list = new ArrayList<Integer>()  ;
			String[] arry = householderIds.split(",");
			for (String id : arry) {
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmHouseholderService.softDel(list);
	}
	
	
}














