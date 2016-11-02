package com.gdgxwl.base.web;

import com.gdgxwl.base.domain.GxwlSysDoc;
import com.gdgxwl.base.service.GxwlSysDocService;
import com.gdgxwl.core.common.json.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
@Controller
@RequestMapping(value = "/doc")
public class GxwlSysDocController {

	@Autowired
	private GxwlSysDocService docService;

	@RequestMapping(value = "/list")
	public String list(Model model) { 
		return "base/doc/doc";
	}

	/**
	 * 上传
	 * 
	 * @param upfile
	 * @param directory
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	public String upload(@RequestParam("upfile") MultipartFile upfile,
			@RequestParam(required = false) String directory) throws Exception {
		GxwlSysDoc doc = docService.saveDoc(upfile, directory);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (doc != null) {
			if (doc.getDocId() != null) {
				resultMap.put("errorCode", "");
				resultMap.put("resultCode", 0);
				resultMap.put("resultMsg", "上传文件成功！");
				resultMap.put("docId", doc.getDocId());
				resultMap.put("docUri", doc.getDocUri());
				resultMap.put("docShowname", doc.getDocShowname());
			} else {
				resultMap.put("errorCode", "");
				resultMap.put("resultCode", 1);
				resultMap.put("resultMsg", doc.getRemark());
			}
		} else {
			resultMap.put("errorCode", "");
			resultMap.put("resultCode", -1);
			resultMap.put("resultMsg", "上传文件时系统出错！");
		}
		return JsonUtil.toJsonString(resultMap);
	}

	/**
	 * 下载
	 *
	 * @param req
	 * @param res
	 * @param docId
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/download/{docId}")
	public void download(HttpServletRequest req, HttpServletResponse res,
			@PathVariable Integer docId) throws UnsupportedEncodingException {

		GxwlSysDoc doc = (GxwlSysDoc) docService.doSelect(docId).get("row");
		String contentType = doc.getDocContentType();
		OutputStream out = null;
		res.setContentType(contentType);
		res.setHeader("Content-Disposition", "attachment;filename="
				+ new String(doc.getDocShowname().getBytes("gb2312"),
						"ISO8859-1"));

		try {
			out = res.getOutputStream();
			out.write(docService.getDoc(docId));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 删除
	@RequestMapping(value = "/delete/{id}")
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request,
			HttpServletResponse response, @PathVariable Integer id) {
		// GxwlSysClientVersion clientVersion = (GxwlSysClientVersion)
		// gxwlSysClientVersionService
		// .doSelect(id).get("row");
		// String filePath = request.getSession().getServletContext()
		// .getRealPath(clientVersion.getClientUrl());
		// File deleteFile = new File(filePath);
		// return gxwlSysClientVersionService
		// .doDeleteAndDeleteFile(id, deleteFile);
		return null;
	}

	@RequestMapping(value = "/del")
	@ResponseBody
	public Map<String, Object> del(@RequestParam(value = "ids") String ids) {
		List<GxwlSysDoc> list = (List<GxwlSysDoc>) docService.doSearch(split(ids)).get("rows");
		return docService.doDelete(list);
	}
	
	// 查询
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req) {
		return doSearch(req);
	}

	// 加载
	@RequestMapping(value = "/get/{id}")
	@ResponseBody
	public Map<String, Object> get(@PathVariable Integer id) {
		return null;
	}
	
	@RequestMapping(value = "/show/{docId}")
	@ResponseBody
	public void showImg(@PathVariable Integer docId,HttpServletRequest req, HttpServletResponse response) {
		response.setContentType("image/*");
        OutputStream os = null; 
        try {
        	os = response.getOutputStream();
        	os.write(docService.getDoc(docId));
        }catch(Exception e){
        	e.printStackTrace();
        }finally {
            try {
        		os.flush();
        		os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}

	private Map<String, Object> doSearch(HttpServletRequest req) {
		// 构造默认条件
		// Map<String, Object> params = new HashMap<String, Object>();

		// 使用SearchUtil工具类从前端请求ServletRequest里获得GxwlSysDictH的查询对象, 并附加上默认条件
		// Specification<GxwlSysClientVersion> spec =
		// SearchUtil.getSpecification(
		// GxwlSysClientVersion.class, req, params);

		// 使用SearchUtil工具类从前端请求ServletRequest里获得分页对象，按ID倒序
		// Pageable pageable = SearchUtil.getPageableWithOrderBy(req,
		// "versionId_desc");
		return null;
		// return gxwlSysClientVersionService.doSearch(spec, pageable);
	}
	
	@RequestMapping(value = "/findbyids")
	@ResponseBody
	public Map<String, Object> findByIds(HttpServletRequest req){
		String ids = req.getParameter("ids");
		return docService.doSearch(split(ids));
	}
	
	private List<Integer> split(String ids){
		List<Integer> list = new ArrayList<Integer>();
		String strs[] = ids.split(",");
		for(int i = 0; i < strs.length; i ++){
			list.add(Integer.parseInt(strs[i]));
		}
		return list;
	}
	
	@RequestMapping(value = "/showImgAndIsTemp/{docId}")
	@ResponseBody
	public void showImgAndIsTemp(@PathVariable Integer docId,HttpServletRequest req, HttpServletResponse response) {
		response.setContentType("image/*");
        OutputStream os = null; 
        try {
        	os = response.getOutputStream();
        	os.write(docService.getDoc(docId));
        }catch(Exception e){
        	e.printStackTrace();
        }finally {
            try {
        		os.flush();
        		os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}

	/**
	 * 下载
	 *
	 * @param req
	 * @param res
	 * @param docId
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/downloadFile/{docId}")
	public void downloadFile(HttpServletRequest req, HttpServletResponse res,
						 @PathVariable Integer docId) throws UnsupportedEncodingException {
		String type = req.getParameter("type");

		if(!StringUtils.isEmpty(type) && "wxRequest".equals(type)){
			GxwlSysDoc doc = (GxwlSysDoc) docService.doSelect(docId).get("row");
			String contentType = doc.getDocContentType();
			OutputStream out = null;
			res.setContentType(contentType);
			res.setHeader("Content-Disposition", "attachment;filename="
					+ new String(doc.getDocShowname().getBytes("gb2312"),
					"ISO8859-1"));

			try {
				out = res.getOutputStream();
				out.write(docService.getDoc(docId));
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
 
}
