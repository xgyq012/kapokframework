package com.nateiot.cis.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nateiot.cis.domain.CisBmCommunityMesh;
import com.nateiot.cis.domain.CisBmCommunityMeshUser;
import com.nateiot.cis.service.CisBmCommunityMeshService;
import com.nateiot.cis.service.CisBmCommunityMeshUserService;
import com.nateiot.core.common.web.SearchUtil;


/**
 * @author xiaguangjun
 * 社区机构
 */
@Controller
@RequestMapping("/mesh")
public class CisBmCommunityMeshController {

	@Autowired
	private CisBmCommunityMeshService meshService;
	
	@Autowired
	private CisBmCommunityMeshUserService meshUserService;
	
	
	@RequestMapping(value = "/list")
	public String toList() {
		return "cis/bm/rkxx/mesh";
	}
	
	@RequestMapping(value = "/selectmesh")
	public String selectUser() {
		return "cis/bm/rkxx/mesh-select";
	}
	
	@RequestMapping(value = "/findAll")
	@ResponseBody
	public Map<String, Object> findAll() {
		return meshService.doSearch();
	}
	
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(ServletRequest req) {
		return meshService.doSearch(
			SearchUtil.getSpecification(CisBmCommunityMesh.class, req),
			SearchUtil.getPageable(req));
	}
	
	@RequestMapping(value = "/meshUserSearch")
	@ResponseBody
	public Map<String, Object> meshUserSearch(ServletRequest req) {
		return meshUserService.doSearch(
			SearchUtil.getSpecification(CisBmCommunityMeshUser.class, req),
			SearchUtil.getPageable(req));
	}
	
	@RequestMapping(value = "/getUserMesh/{userId}")
	@ResponseBody
	public Map<String, Object> getUserMesh(@PathVariable Integer userId) {
		return meshService.getUserMesh(userId);
	}
	
	@RequestMapping(value = "/getUserAllMesh/{userId}")
	@ResponseBody
	public Map<String, Object> getUserAllMesh(@PathVariable Integer userId) {
		return meshService.getUserAllMesh(userId);
	}
	
	@RequestMapping(value = "/getMeshChildren/{meshId}")
	@ResponseBody
	public Map<String, Object> getMeshChildren(@PathVariable Integer meshId) {
		return meshService.getMeshChildren(meshId);
	}

	// 加载网格
	@RequestMapping(value = "/get/{id}")
	@ResponseBody
	public Map<String, Object> get(@PathVariable Integer id) {
		return meshService.doSelect(id);
	}
	
	// 删除网格
	@RequestMapping(value = "/del/{id}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable Integer id) {
		return meshService.doDelete(id);
	}

	// 保存网格
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(CisBmCommunityMesh mesh) {
		return meshService.doSave(mesh);
	}
	
	// 保存网格成员
	@RequestMapping(value = "/saveMeshUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveMeshUser(String meshUser, @RequestParam(value = "isDel", defaultValue = "N") String isDel) {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<CisBmCommunityMeshUser> meshUsers = null;
		try {
			objectMapper.setDateFormat(dateFormat);
			meshUsers = objectMapper.readValue(meshUser, new TypeReference<List<CisBmCommunityMeshUser>>() {});
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		if (StringUtils.equalsIgnoreCase("N", isDel)) {
			return meshUserService.doSave(meshUsers);
		} else {
			return meshUserService.doDelete(meshUsers);
		}
	}

	@RequestMapping(value = "/getMesh", method = RequestMethod.POST)
	@ResponseBody
	public List<CisBmCommunityMesh> getMesh(
			@RequestParam(value = "id", defaultValue = "0") Integer id) {
		return meshService.getMeshByParenMeshId(id);
	}
	
	@RequestMapping(value = "/getMeshUser/{meshId}")
	@ResponseBody
	public Map<String, Object> getMeshUser(@PathVariable Integer meshId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mesh.meshId_EQ", meshId);
		return meshUserService.doSearch(SearchUtil.getSpecification(CisBmCommunityMeshUser.class, params));
	}
	
	@RequestMapping(value = "/getMeshAllUser/{meshId}")
	@SuppressWarnings("unchecked")
	@ResponseBody
	public Map<String, Object> getMeshAllUser(@PathVariable Integer meshId) {
		Map<String, Object> children = meshService.getMeshChildren(meshId);
		List<Map<String, Object>> rows = (List<Map<String, Object>>)children.get("rows");
		StringBuffer meshIds = new StringBuffer();
		for (Map<String, Object> row : rows) {
			meshIds.append("," + row.get("meshId"));
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mesh.meshId_IN", meshIds.substring(1));
		return meshUserService.doSearch(SearchUtil.getSpecification(CisBmCommunityMeshUser.class, params));
	}
	
}
