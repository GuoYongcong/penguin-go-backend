package com.ryzin.penguin.admin.service;

import java.util.List;
import java.util.Set;

import com.ryzin.penguin.admin.model.SysUser;
import com.ryzin.penguin.admin.model.SysUserRole;
import com.ryzin.penguin.core.service.CurdService;

/**
 * 用户管理
 */
public interface SysUserService extends CurdService<SysUser> {

	SysUser findByName(String username);
	
	/**
	 * 查找用户的菜单权限标识集合
	 * @param userName
	 * @return
	 */
	Set<String> findPermissions(String userName);
	
	/**
	 * 查找用户的角色集合
	 * @param userName
	 * @return
	 */
	List<SysUserRole> findUserRoles(Long userId);	
	
	/**
	 * 查找用户的基本信息不含密码等敏感信息
	 * @param userName
	 * @return
	 */
	SysUser getInfoByName(String username);

}