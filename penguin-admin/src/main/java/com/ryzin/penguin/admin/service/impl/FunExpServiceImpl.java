package com.ryzin.penguin.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryzin.penguin.core.page.MybatisPageHelper;
import com.ryzin.penguin.core.page.PageRequest;
import com.ryzin.penguin.core.page.PageResult;
import com.ryzin.penguin.core.page.ColumnFilter;

import com.ryzin.penguin.admin.model.FunExp;
import com.ryzin.penguin.admin.model.FunExpUser;
import com.ryzin.penguin.admin.model.FunUserExp;
import com.ryzin.penguin.admin.model.SysUser;
import com.ryzin.penguin.admin.model.SysUserRole;
import com.ryzin.penguin.admin.dao.FunExpMapper;
import com.ryzin.penguin.admin.dao.FunExpUserMapper;
import com.ryzin.penguin.admin.dao.FunUserExpMapper;
import com.ryzin.penguin.admin.dao.SysUserMapper;
import com.ryzin.penguin.admin.dao.SysUserRoleMapper;
import com.ryzin.penguin.admin.service.FunExpService;


@Service
public class FunExpServiceImpl implements FunExpService {

	@Autowired
	private FunExpMapper funExpMapper;
	@Autowired
	private FunExpUserMapper funExpUserMapper;
	@Autowired
	private FunUserExpMapper funUserExpMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Transactional
	@Override
	public int save(FunExp record) {
		Long expId = null;
		if(record.getId() == null || record.getId() == 0) {
			funExpMapper.insert(record);
			expId = funExpMapper.getLastExpId();
		} else {
			expId = record.getId();
			funExpMapper.update(record);
		}
		
		// 根据userName获取userId
		SysUser sysUser = sysUserMapper.findByName(record.getCreateBy());
		FunUserExp funUserExp = new FunUserExp();
		// 更新主试实验关系表
		if(sysUser != null) {
			funUserExp.setUserId(sysUser.getId());
		} 
		else {
			funUserExpMapper.deleteByUserName(record.getCreateBy());
		}
		funUserExp.setExpId(expId);
		funUserExpMapper.insert(funUserExp);
		return 1;
	}

	@Override
	public int delete(FunExp record) {
		funExpMapper.delete(record.getId());
		funUserExpMapper.deleteByExpId(record.getId());
		funExpUserMapper.deleteByExpId(record.getId());
		return 1;
	}
	
	@Override
	public int delete(List<FunExp> records) {
		for(FunExp record:records) {
			delete(record);
		}
		return 1;
	}
	
	@Override
	public int deletePeo(List<FunExpUser> records) {
		for(FunExpUser record:records) {
			funExpUserMapper.delete(record.getId());
		}
		return 1;
	}
	
	@Override
	public int deletePeoByExpIdAndUserName(FunExpUser record) {
		Long expId = record.getExpId();
		Long userId = sysUserMapper.getIdByName(record.getCreateBy());
		System.out.println("expId:" + expId);
		System.out.println("userName:" + record.getCreateBy());
		funExpUserMapper.deleteByExpIdAndUserId(expId, userId);
		return 1;
	}
	
	@Override
	public FunExp findById(Long id) {
		return funExpMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		PageResult pageResult = null;
		String userName = getColumnFilterValue(pageRequest, "userName");
		String expName = getColumnFilterValue(pageRequest, "expName");
		if(userName != null && userName.length() > 0) {
			System.out.println("------------1,name="+userName);
			SysUser user = sysUserMapper.findByName(userName);
			List<SysUserRole> roles = sysUserRoleMapper.findUserRoles(user.getId());
			//角色为被试
			if(roles.get(0).getRoleId() == 9) {
				System.out.println("------------2");
				if(expName != null)
					pageResult = MybatisPageHelper.findPage(pageRequest, funExpMapper, "findPageBySubjectUserIdAndExpName", user.getId(), expName);
				else
					pageResult = MybatisPageHelper.findPage(pageRequest, funExpMapper, "findPageBySubjectUserId", user.getId());
			}else if(roles.get(0).getRoleId() == 10) {
				System.out.println("------------3");
				//角色为主试
				if(expName != null)
					pageResult = MybatisPageHelper.findPage(pageRequest, funExpMapper, "findPageByExperimenterUserIdAndExpName", user.getId(), expName);
				else
					pageResult = MybatisPageHelper.findPage(pageRequest, funExpMapper, "findPageByExperimenterUserId", user.getId());
			}else {
				System.out.println("------------4");
				if(expName != null)
					pageResult = MybatisPageHelper.findPage(pageRequest, funExpMapper, "findPageByName", expName);
				else
					pageResult = MybatisPageHelper.findPage(pageRequest, funExpMapper);
			}
		}else {
			System.out.println("------------5");
			if(expName != null)
				pageResult = MybatisPageHelper.findPage(pageRequest, funExpMapper, "findPageByName", expName);
			else
				pageResult = MybatisPageHelper.findPage(pageRequest, funExpMapper);
		}
		return pageResult;
	}
	
	/**
	 *  获取过滤字段的值
	 * @param filterName
	 * @return
	 */
	public String getColumnFilterValue(PageRequest pageRequest, String filterName) {
		String value = null;
		ColumnFilter columnFilter = pageRequest.getColumnFilter(filterName);
		
		if(columnFilter != null) {
			value = columnFilter.getValue();
		}
		return value;
	}
	
	/**
	 * 根据实验id获取被试列表
	 * @author gyc
	 */
	@Override
	public List<FunExpUser> findExpUsers(Long expId) {
		return funExpUserMapper.findExpUsers(expId);
	}
	
	/**
	 * 根据实验id获取被试列表
	 * @author Ryzin
	 */
	@Override
	public PageResult findExpUsersPage(PageRequest pageRequest) {
		PageResult pageResult = null;
		String id = getColumnFilterValue(pageRequest, "expId");
		Long expId = Long.parseLong(id);
		System.out.println("实验id：" + expId);
		pageResult = MybatisPageHelper.findPage(pageRequest, sysUserMapper, "findExpUsersPageByExpId", expId);
		return pageResult;
	}
	
	/**
	 * 获取被试参与的实验
	 */
	@Override
	public PageResult findSubjectPageByUserName(PageRequest pageRequest) {
		PageResult pageResult = null;
		String userName = getColumnFilterValue(pageRequest, "userName");
		Long userId = sysUserMapper.getIdByName(userName);
		System.out.println("用户id：" + userId);
		pageResult = MybatisPageHelper.findPage(pageRequest, funExpMapper, "findPageBySubjectUserId", userId);
		return pageResult;
	}
	
	@Transactional
	@Override
	public int saveExpUser(FunExpUser record) {  // 保存实验被试记录
		if(record.getId() == null) {
			return funExpUserMapper.insert(record);
		}
		return funExpUserMapper.update(record);
	}
	
	@Transactional
	@Override
	public int saveUserExp(FunUserExp record) {  // 保存主试实验记录
		if(record.getId() == null) {
			return funUserExpMapper.insert(record);
		}
		return funUserExpMapper.update(record);
	}
	
	@Override
	public int getExpUserCount(Long expId) {
		return funExpUserMapper.getExpUserCount(expId);
	}

}
