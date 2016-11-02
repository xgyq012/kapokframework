package com.gdgxwl.base.web;

import com.gdgxwl.base.domain.GxwlSysUser;
import com.gdgxwl.base.service.GxwlSysUserService;
import com.gdgxwl.core.common.excel.ExcelFile;
import com.gdgxwl.core.common.web.SearchUtil;
import com.gdgxwl.core.service.BaseService;
import com.gdgxwl.core.web.ExcelController;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
@Controller
@RequestMapping(value = "/user")
public class GxwlSysUserController extends ExcelController {

	public GxwlSysUserController() {
		super.setTemplate("user");
	}

	@Autowired
	private GxwlSysUserService userService;

	@RequiresPermissions("USER_LIST")
	@RequestMapping(value = "/list")
	public String toList() {
		return "base/user/user";
	}
	
	@RequestMapping(value="/showuserlist")
	public String showUserList(){
		return "base/user/selectuser";
	}
	
	@RequestMapping(value="/selectuserbymesh")
	public String seletcUserByMesh(){
		return "base/user/selectuserbymesh";
	}
	
	@RequiresPermissions("USER_PWD")
	@RequestMapping(value = "/modifyPwd")
	public String modifyPwd() {
		return "base/user/user-modifyPwd";
	}

	@RequestMapping(value = "/selectuser")
	public String selectUser() {
		return "base/user/user-select";
	}

	// 查询用户
	@RequestMapping(value = "/search")
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest req) {
		return doSearch(req);
	}

	// 加载用户
	@RequiresPermissions("USER_EDIT")
	@RequestMapping(value = "/get/{id}")
	@ResponseBody
	public Map<String, Object> getGxwlSysUserById(@PathVariable Integer id) {
		return userService.doSelect(id);
	}

	// 删除用户
	@RequiresPermissions("USER_DEL")
	@RequestMapping(value = "/del/{id}")
	@ResponseBody
	public Map<String, Object> delGxwlSysUserById(@PathVariable Integer id) {
		return userService.doDelete(id);
	}

	// 保存用户
	@RequiresPermissions("USER_SAVE")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveGxwlSysUser(GxwlSysUser user) {
		return userService.doSave(user);
	}

	// 重设密码
	@RequiresPermissions("USER_RESETPASSWORD")
	@RequestMapping(value = "/resetpassword/{userId}")
	@ResponseBody
	public Map<String, Object> resetPassword(@PathVariable Integer userId) {
		return userService.resetPassword(userId);
	}

	// Excel导入
	@RequestMapping(value = "/excelImport", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> excelImport(ExcelFile excelFile) {
		return userService.excelImport(excelFile);
	}

	// Excel导出
	@Override
	@SuppressWarnings("unchecked")
	protected void doExcelExport(HSSFWorkbook workbook, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		Map<String, Object> map = doSearch(req);
		List<GxwlSysUser> users = (List<GxwlSysUser>)map.get(BaseService.RESULT_ROWS);
		userService.doExcelExport(users, workbook);
	}

	// 修改用户密码
	@RequestMapping(value = "/modifyUserPassword", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modifyUserPassword(String oldPassword, String newPassword) {
		return userService.modifyCurrentUserPassword(oldPassword, newPassword);
	}

	// 检查用户密码
	@RequestMapping(value = "/checkUserPassword", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkUserPassword(String oldPassword) {
		return userService.checkCurrentUserOldPassword(oldPassword);
	}
	
	// 检查用户账号
	@RequestMapping(value = "/checkUserName", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkUserName(@RequestParam(value = "userId", required = false) Integer userId, String userName) {
		return userService.checkGxwlSysUserName(userId, userName);
	}
	
	// 打印
	@RequestMapping(value = "/print")
	public String print(HttpServletRequest req, Model model) {
		Map<String, Object> map = doSearch(req);
		model.addAttribute("users", map.get(BaseService.RESULT_ROWS));
		return "base/user/user-print";
	}

	private Map<String, Object> doSearch(HttpServletRequest req) {
		// 构造默认条件
		Map<String, Object> params = new HashMap<String, Object>();

		// 使用SearchUtil工具类从前端请求ServletRequest里获得GxwlSysUser的查询对象, 并附加上默认条件
		Specification<GxwlSysUser> spec = SearchUtil.getSpecification(GxwlSysUser.class, req, params);
		
		String isAll = req.getParameter("isAll");
		
		if ("Y".equals(isAll)) {
			Sort sort = SearchUtil.getSort("userId_desc");
			return userService.doSearch(spec, sort);
		}
		else {
			// 使用SearchUtil工具类从前端请求ServletRequest里获得分页对象，按用户ID倒序
			Pageable pageable = SearchUtil.getPageableWithOrderBy(req, "userId_desc");
			return userService.doSearch(spec, pageable);
		}
	}
	
	// 加载用户资源
	@RequestMapping(value = "/getUserResource/{userId}")
	@ResponseBody
	public Map<String, Object> getUserResource(@PathVariable Integer userId) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("resultCode", 0);
		result.put("resultMsg", "");
		result.put("errorCode", "");
		result.put("row", userService.getUserPermissionList(userId));
		return result;
	}
	
	// 加载用户组成员
	@RequestMapping(value = "/getUserRole/{userId}")
	@ResponseBody
	public Map<String, Object> getGxwlSysUserRoleByUserId(@PathVariable Integer userId) {
		return userService.getGxwlSysUserRoleByUserId(userId);
	}
	
}
