package com.gdgxwl.base.web;

import com.gdgxwl.base.domain.GxwlSysOperatelog;
import com.gdgxwl.base.service.GxwlSysOperatelogService;
import com.gdgxwl.core.common.web.SearchUtil;
import com.gdgxwl.core.service.BaseService;
import com.gdgxwl.core.web.ExcelController;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/operatelog")
public class GxwlSysOperatelogController extends ExcelController {

	public GxwlSysOperatelogController() {
		super.setTemplate("log");
	}

	@Autowired
	private GxwlSysOperatelogService gxwlSysOperatelogService;

	@RequestMapping(value = "/list")
	public String toList() {
		return "base/operatelog/operatelog";
	}

	// 查询操作日志
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req) {
		return doSearch(req);
	}

	// 导出操作日志
	@Override
	@SuppressWarnings("unchecked")
	protected void doExcelExport(HSSFWorkbook workbook, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map<String, Object> map = doSearch(req);
		List<GxwlSysOperatelog> logs = (List<GxwlSysOperatelog>) map
				.get(BaseService.RESULT_ROWS);
		gxwlSysOperatelogService.doExcelExport(logs, workbook);
	}

	// 清除六个月前的日志
	@RequestMapping(value = "/clear")
	@ResponseBody
	public Map<String, Object> clear() {
		return gxwlSysOperatelogService.clearSixMonthsAgoLogs();
	}

	private Map<String, Object> doSearch(HttpServletRequest req) {
		// 构造默认条件
		Map<String, Object> params = new HashMap<String, Object>();

		// 使用SearchUtil工具类从前端请求ServletRequest里获得GxwlSysOperatelog的查询对象, 并附加上默认条件
		Specification<GxwlSysOperatelog> spec = SearchUtil.getSpecification(
				GxwlSysOperatelog.class, req, params);

		String isAll = req.getParameter("isAll");

		if ("Y".equals(isAll)) {
			Sort sort = SearchUtil.getSort("operateTime_desc");
			return gxwlSysOperatelogService.doSearch(spec, sort);
		} else {
			// 使用SearchUtil工具类从前端请求ServletRequest里获得分页对象，按用户ID倒序
			Pageable pageable = SearchUtil.getPageableWithOrderBy(req,
					"operateTime_desc");
			return gxwlSysOperatelogService.doSearch(spec, pageable);
		}
	}

}
