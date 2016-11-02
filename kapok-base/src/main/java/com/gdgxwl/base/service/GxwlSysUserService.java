package com.gdgxwl.base.service;

import com.gdgxwl.base.domain.GxwlSysUser;
import com.gdgxwl.base.repository.GxwlSysUserDao;
import com.gdgxwl.core.service.BaseService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;
import java.util.Map;

/**
 * @author Will WM. Zhang
 * 
 */
public interface GxwlSysUserService extends
		BaseService<GxwlSysUserDao, GxwlSysUser, Integer> {

	public static final int DEFAULT_HASH_INTERATIONS = 64;

//	public boolean checkGxwlSysUserEmpcode(String empcode);

	public Map<String, Object> checkGxwlSysUserName(Integer userId, String userName);

	/**
	 * 修改用户密码
	 * 
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public Map<String, Object> modifyCurrentUserPassword(String oldPassword, String newPassword);
	
	/**
	 * 检查用户的旧密码
	 * 
	 * @param oldPassword
	 * @return
	 */
	public Map<String, Object> checkCurrentUserOldPassword(String oldPassword);
	
	/**
	 * 根据用户账号查找用户
	 * 
	 * @param userName
	 * @return
	 */
	public GxwlSysUser findByUserName(String userName);

	/**
	 * 重置用户密码
	 * 
	 * @param userId
	 * @return
	 */
	public Map<String, Object> resetPassword(Integer userId);

	
	/**
	 * 设置用户默认密码
	 * 
	 * @param gxwlSysUser
	 */
	public void setDefaultPassword(GxwlSysUser gxwlSysUser);
	
	/**
	 * 获取用户的权限列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> getUserPermissionList(Integer userId);
	
	/**
	 * 处理Excel导出业务逻辑
	 * 
	 * @param users
	 * @param workbook
	 */
	public void doExcelExport(List<GxwlSysUser> users, HSSFWorkbook workbook);
	
	
	
	/**
	 * 加载用户组成员
	 * @param userId
	 * @return
	 */
	public  Map<String, Object>  getGxwlSysUserRoleByUserId(Integer userId);
	
}
