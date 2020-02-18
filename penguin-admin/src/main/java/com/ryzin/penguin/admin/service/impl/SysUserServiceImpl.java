package com.ryzin.penguin.admin.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryzin.penguin.admin.dao.SysUserMapper;
import com.ryzin.penguin.admin.model.SysMenu;
import com.ryzin.penguin.admin.model.SysUser;
import com.ryzin.penguin.admin.service.SysMenuService;
import com.ryzin.penguin.admin.service.SysUserService;
import com.ryzin.penguin.core.page.ColumnFilter;
import com.ryzin.penguin.core.page.MybatisPageHelper;
import com.ryzin.penguin.core.page.PageRequest;
import com.ryzin.penguin.core.page.PageResult;


// SysUserServiceImpl 实现类，调用 SysUserMapper 方法完成查询操作
@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@Override
	public int save(SysUser record) {
		if(record.getId() == null || record.getId() == 0) {
			return sysUserMapper.insertSelective(record);
		}
		return sysUserMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public int delete(SysUser record) {
		return sysUserMapper.deleteByPrimaryKey(record.getId());
	}

	@Override
	public int delete(List<SysUser> records) {
		for(SysUser record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysUser findById(Long id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysUser findByName(String name) {
		return sysUserMapper.findByName(name);
	}
	
	@Override
	public PageResult findPage(PageRequest pageRequest) {
		ColumnFilter columnFilter = pageRequest.getColumnFilter("userName");
		if(columnFilter != null && columnFilter.getValue() != null) {
			return MybatisPageHelper.findPage(pageRequest, sysUserMapper, "findPageByUserName", columnFilter.getValue());
		}
		return MybatisPageHelper.findPage(pageRequest, sysUserMapper);
	}

	@Override
	public Set<String> findPermissions(String userName) {
		Set<String> perms = new HashSet<>();
		List<SysMenu> sysMenus = sysMenuService.findByUser(userName);
		for(SysMenu sysMenu:sysMenus) {
			perms.add(sysMenu.getPerms());
		}
		return perms;
	}
}
