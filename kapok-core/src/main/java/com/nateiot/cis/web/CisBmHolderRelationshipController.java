package com.nateiot.cis.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmCommunityMeshUser;
import com.nateiot.cis.domain.CisBmHolderRelationship;
import com.nateiot.cis.service.CisBmHolderRelationshipService;
import com.nateiot.core.common.web.SearchUtil;

@Controller
@RequestMapping("/hoRelationShip")
public class CisBmHolderRelationshipController {
	
	@Autowired
	private CisBmHolderRelationshipService cisBmHolderRelationshipService;
	
	/**
	 * 查询
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req) {
		  	
			return cisBmHolderRelationshipService.doSearch(SearchUtil.getSpecification( CisBmHolderRelationship.class, req));
	}
	
	/**
	 * 保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(String dataList) {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<CisBmHolderRelationship> list = null;
		try {
			objectMapper.setDateFormat(dateFormat);
			list = objectMapper.readValue(dataList, new TypeReference<List<CisBmHolderRelationship>>() {});
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return cisBmHolderRelationshipService.doSave(list);
	}
	
	@RequestMapping(value = "/del/{id}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable(value = "id") Integer id) {
		return cisBmHolderRelationshipService.doDelete(id);
	}
	
	/**
	 * 硬删除记录
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delList(@RequestParam(value = "ids") String ids){
		
		List<Integer> list = null;
		if(StringUtils.isNotBlank(ids)){
			list = new ArrayList<Integer>()  ;
			String[] arry = ids.split(",");
			for (String id : arry) {
				if(id!=null){
					list.add(Integer.parseInt(id));
				}
			}
		}
		return cisBmHolderRelationshipService.delList(list);
	}

}
