package com.gdgxwl.base.web;

import com.gdgxwl.base.common.DictUtil;
import com.gdgxwl.base.domain.GxwlSysDictH;
import com.gdgxwl.base.service.GxwlSysDictHService;
import com.gdgxwl.core.common.web.SearchUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
@Controller
@RequestMapping(value = "/dict")
public class GxwlSysDictHController {

	@Autowired
	private GxwlSysDictHService gxwlSysDictHService;
	
	@RequestMapping(value = "/list")
	public String list(Model model){
		return "base/dict/dict";
	}

	// 查询数据字典
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req) {
		return doSearch(req);
	}

	// 加载数据字典
	@RequestMapping(value = "/get/{id}")
	@ResponseBody
	public Map<String, Object> getDicth(@PathVariable Integer id){
		return gxwlSysDictHService.doSelect(id);
	}
	
	// 删除数据字典
	@RequestMapping(value = "/del/{id}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable Integer id){
		Map<String, Object> map = gxwlSysDictHService.doDelete(id);
		DictUtil.syncDict();
		return map;		
	}
	
	// 保存数据字典
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveGxwlSysDictH(GxwlSysDictH dictH){	
		Map<String, Object> map = gxwlSysDictHService.doSave(dictH);
		DictUtil.syncDict();
		return map;
	}
	
	// 根据字典数据编码获取字典值
	@RequestMapping(value = "/getdict/{dictTypeCode}")
	@ResponseBody
	public List<Map<String, Object>> getDict(@PathVariable String dictTypeCode,
			@RequestParam(value = "excludes", required = false) String excludes) {
		if (excludes != null) {
			return DictUtil.getDictList(dictTypeCode, excludes);
		}
		return DictUtil.getDictList(dictTypeCode);
	}

	// 根据字典数据编码获取字典值（带“请选择”）
	@RequestMapping(value = "/getdict/blank/{dictTypeCode}")
	@ResponseBody
	public List<Map<String, Object>> getDictBlank(
			@PathVariable String dictTypeCode,
			@RequestParam(value = "excludes", required = false) String excludes) {
		List<Map<String, Object>> dicts = excludes == null ? DictUtil
				.getDictList(dictTypeCode) : DictUtil.getDictList(dictTypeCode,
				excludes);
		Map<String, Object> dict = new HashMap<String, Object>();
		dict.put("dictCode", "");
		dict.put("dictName", "请选择");
		dicts.add(0, dict);
		return dicts;
	}
	
	@RequestMapping(value = "/getDictList")
	@ResponseBody
	public List<Map<String,Object>> getDictList() {
		DictUtil.syncDict();
		return DictUtil.getDictList();
	}

	// 同步数据字典
	@RequestMapping(value = "/syncDict")
	@ResponseBody
	public Map<String, Object> syncDict() {
		return gxwlSysDictHService.syncDict();
	}	
	
	// 手机端同步数据字典
	@RequestMapping(value = "/getDictByDictType")
	@ResponseBody
	public Map<String, Object> getDictByDictType(String dictTypeCode,
			Long lastSyncTime) {
		return gxwlSysDictHService.getDictByDictTypeCode(dictTypeCode,
				new DateTime(lastSyncTime));
	}

	private Map<String, Object> doSearch(HttpServletRequest req) {
		// 构造默认条件
		Map<String, Object> params = new HashMap<String, Object>();

		// 使用SearchUtil工具类从前端请求ServletRequest里获得GxwlSysDictH的查询对象, 并附加上默认条件
		Specification<GxwlSysDictH> spec = SearchUtil.getSpecification(GxwlSysDictH.class, req, params);
		
		// 使用SearchUtil工具类从前端请求ServletRequest里获得分页对象，按ID倒序
		Pageable pageable = SearchUtil.getPageableWithOrderBy(req, "dictTypeId_desc");
		return gxwlSysDictHService.doSearch(spec, pageable);
	}
	
}
