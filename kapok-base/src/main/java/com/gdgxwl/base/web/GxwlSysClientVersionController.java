package com.gdgxwl.base.web;

import com.gdgxwl.base.domain.GxwlSysClientVersion;
import com.gdgxwl.base.service.GxwlSysClientVersionService;
import com.gdgxwl.base.service.GxwlSysDocService;
import com.gdgxwl.core.common.web.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
@Controller
@RequestMapping(value = "/clientversion")
public class GxwlSysClientVersionController {

	@Autowired
	private GxwlSysClientVersionService gxwlSysClientVersionService;
	
	@Autowired
	private GxwlSysDocService gxwlSysDocService;

	@RequestMapping(value = "/list")
	public String list(Model model) {
		return "base/clientversion/clientversion";
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
		return doSearch(req);
	}
	
	/**
	 * 保存
	 * 
	 * @param clientVersion
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(GxwlSysClientVersion clientVersion) {
		return gxwlSysClientVersionService.doSave(clientVersion);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del/{id}")
	@ResponseBody
	public Map<String, Object> del(@PathVariable Integer id) {
		return gxwlSysClientVersionService.doDelete(id);
	}	
	
	/**
	 * 加载
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get/{id}")
	@ResponseBody
	public Map<String, Object> getGxwlSysClientVersion(@PathVariable Integer id) {
		return gxwlSysClientVersionService.doSelect(id);
	}

	/**
	 * 检测
	 * 
	 * @param fileNumber
	 * @param versionNumber
	 * @return
	 */
	@RequestMapping(value = "/check")
	@ResponseBody
	public Map<String, Object> checkGxwlSysClientVersion(String fileNumber, String versionNumber) {
		return gxwlSysClientVersionService.checkGxwlSysClientVersion(fileNumber, versionNumber);
	}

	/**
	 * 下载
	 * 
	 * @param req
	 * @param res
	 * @param fileNumber
	 * @param versionNumber
	 */
	@RequestMapping(value = "/download")
	public void download(HttpServletRequest req, HttpServletResponse res, String fileNumber, String versionNumber) {
		
		GxwlSysClientVersion clientVersion = gxwlSysClientVersionService.findByFileNumberAndVersionNumber(fileNumber, versionNumber);
		Integer docId = Integer.valueOf(clientVersion.getClientUrl());
		byte[] bytes = gxwlSysDocService.getDoc(docId);
		
		OutputStream out = null;
		res.setHeader("Content-Disposition", "attachment;filename="+clientVersion.getFileName());
		res.setHeader("Content-Length", String.valueOf(bytes.length));

		try {
			out = res.getOutputStream();
			out.write(bytes);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	/**
	 * 获取最新版本号
	 * 
	 * @param fileNumber
	 * @return
	 */
	@RequestMapping(value = "/getVersionNumber/{fileNumber}")
	@ResponseBody
	public Map<String, Object> getVersionNumber(@PathVariable String fileNumber) {
		return gxwlSysClientVersionService.findByFileNumberAndIsLast(fileNumber);
	}

	private Map<String, Object> doSearch(HttpServletRequest req) {
		// 构造默认条件
		Map<String, Object> params = new HashMap<String, Object>();

		// 使用SearchUtil工具类从前端请求ServletRequest里获得GxwlSysDictH的查询对象, 并附加上默认条件
		Specification<GxwlSysClientVersion> spec = SearchUtil.getSpecification(
				GxwlSysClientVersion.class, req, params);

		// 使用SearchUtil工具类从前端请求ServletRequest里获得分页对象，按ID倒序
		Pageable pageable = SearchUtil.getPageableWithOrderBy(req,
				"versionId_desc");
		return gxwlSysClientVersionService.doSearch(spec, pageable);
	}

}
