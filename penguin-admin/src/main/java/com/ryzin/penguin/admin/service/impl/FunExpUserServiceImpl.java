package com.ryzin.penguin.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryzin.penguin.core.page.MybatisPageHelper;
import com.ryzin.penguin.core.page.PageRequest;
import com.ryzin.penguin.core.page.PageResult;

import com.ryzin.penguin.admin.model.FunExpUser;
import com.ryzin.penguin.admin.dao.FunExpUserMapper;
import com.ryzin.penguin.admin.dao.SysUserMapper;
import com.ryzin.penguin.admin.service.FunExpUserService;


@Service
public class FunExpUserServiceImpl implements FunExpUserService {

	@Autowired
	private FunExpUserMapper funExpUserMapper;
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Override
	public int save(FunExpUser record) {
		if(record.getId() == null || record.getId() == 0) {
			return funExpUserMapper.insert(record);
		}
		return funExpUserMapper.update(record);
	}

	@Override
	public int delete(FunExpUser record) {
		return funExpUserMapper.delete(record.getId());
	}

	@Override
	public int delete(List<FunExpUser> records) {
		for(FunExpUser record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public FunExpUser findById(Long id) {
		return funExpUserMapper.findById(id);
	}
	
	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, funExpUserMapper);
	}
	
	@Override
	public List<FunExpUser> findExpUserByUserId(long id) {
		return funExpUserMapper.findByUserId(id);
	}

	@Override
	public FunExpUser findByExpIdAndUserName(Long expId, String userName) {
		Long userId = sysUserMapper.getIdByName(userName);
		return funExpUserMapper.findByExpIdAndUserId(expId, userId);
	}
	
}
