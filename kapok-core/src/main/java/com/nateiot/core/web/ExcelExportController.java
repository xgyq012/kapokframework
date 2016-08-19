package com.nateiot.core.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.joda.time.DateTime;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.ServletContextResource;

import com.nateiot.core.common.PropertiesUtil;

/**
 * @author Will WM. Zhang
 * 
 */
public abstract class ExcelExportController {

	@RequestMapping(value = "/preview")
	public String preview() {
		return "base/excel/preview";
	}

	// Excel导出
	@RequestMapping(value = "/excelExport/{template}")
	public void excelExport(HttpServletRequest request, HttpServletResponse response, @PathVariable String template) {
		HSSFWorkbook workbook;
		OutputStream out = null;
		try {
			String url = PropertiesUtil.getProperty("excel.templateUrl." + template);
			if (StringUtils.isNotEmpty(url)) {
				workbook = getTemplateSource(url, request);
			} else {
				workbook = new HSSFWorkbook();
			}

			doExcelExport(workbook, template, request, response);

			String datetime = DateTime.now().toString("yyyyMMddHHmmss");
			String filename = PropertiesUtil.getProperty("excel.filename." + template, StringUtils.substringAfterLast(template, "/"));
			filename = filename == null ? "export-" + datetime + ".xls" : filename + "-" + datetime + ".xls";
			out = getOutputStream(new String(filename.getBytes("gbk"), "iso-8859-1"), response);

			workbook.write(out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 下载模板
	@RequestMapping(value = "/downloadTemplate/{template}")
	public void downloadTemplate(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String template) {
		OutputStream out = null;
		try {
			String url = PropertiesUtil.getProperty("excel.templateUrl."
					+ template, template);
			String filename = PropertiesUtil.getProperty("excel.filename."
					+ template, StringUtils.substringAfterLast(template, "/"));
			HSSFWorkbook workbook = getTemplateSource(url, request);
			out = getOutputStream(filename, response);

			workbook.write(out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected HSSFWorkbook getTemplateSource(String url,
			HttpServletRequest request) throws Exception {
		Resource inputFile = new ServletContextResource(request.getSession().getServletContext(), url);
		return new HSSFWorkbook(inputFile.getInputStream());
	}

	protected OutputStream getOutputStream(String filename,
			HttpServletResponse response) throws Exception {
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ filename);
		return response.getOutputStream();
	}

	protected abstract void doExcelExport(HSSFWorkbook workbook, String template,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;

}
