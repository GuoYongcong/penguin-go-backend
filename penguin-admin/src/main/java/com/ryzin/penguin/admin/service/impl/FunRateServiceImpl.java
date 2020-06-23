package com.ryzin.penguin.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryzin.penguin.core.page.MybatisPageHelper;
import com.ryzin.penguin.core.page.PageRequest;
import com.ryzin.penguin.core.page.PageResult;

import com.ryzin.penguin.admin.model.FunRate;
import com.ryzin.penguin.admin.dao.FunRateMapper;
import com.ryzin.penguin.admin.service.FunRateService;


@Service
public class FunRateServiceImpl implements FunRateService {

	@Autowired
	private FunRateMapper funRateMapper;

	@Override
	public int save(FunRate record) {
		if(record.getId() == null || record.getId() == 0) {
			return funRateMapper.insert(record);
		}
		return funRateMapper.update(record);
	}

	@Override
	public int delete(FunRate record) {
		return funRateMapper.delete(record.getId());
	}

	@Override
	public int delete(List<FunRate> records) {
		for(FunRate record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public FunRate findById(Long id) {
		return funRateMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, funRateMapper);
	}
	
	@Override
	public FunRate findRateByExpIdAndRatedId(Long expId, Long ratedId) {
		return funRateMapper.findByExpIdAndRatedId(expId, ratedId);
	}
	
	@Override
	public List<FunRate> findAllRateByRatedId(Long ratedId) {
		return funRateMapper.findAllRateByRatedId(ratedId);
	}
	
}
