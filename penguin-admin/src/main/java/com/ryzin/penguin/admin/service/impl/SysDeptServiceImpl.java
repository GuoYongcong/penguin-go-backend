package com.ryzin.penguin.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryzin.penguin.admin.dao.SysDeptMapper;
import com.ryzin.penguin.admin.model.SysDept;
import com.ryzin.penguin.admin.service.SysDeptService;
import com.ryzin.penguin.core.page.MybatisPageHelper;
import com.ryzin.penguin.core.page.PageRequest;
import com.ryzin.penguin.core.page.PageResult;

@Service
public class SysDeptServiceImpl  implements SysDeptService {

	@Autowired
	private SysDeptMapper sysDeptMapper;

	@Override
	public int save(SysDept record) {
		return sysDeptMapper.insertSelective(record);
	}

	@Override
	public int update(SysDept record) {
		return sysDeptMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(SysDept record) {
		return sysDeptMapper.deleteByPrimaryKey(record.getDeptId());
	}

	@Override
	public int delete(List<SysDept> records) {
		for(SysDept record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public SysDept findById(Long id) {
		return sysDeptMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, sysDeptMapper);
	}
	
	@Override
	public List<SysDept> findTree() {
		List<SysDept> sysDepts = new ArrayList<>();
		List<SysDept> depts = sysDeptMapper.findAll();
		for (SysDept dept : depts) {
			if (dept.getParentId() == null || dept.getParentId() == 0) {
				sysDepts.add(dept);
			}
		}
		findChildren(sysDepts, depts);
		return sysDepts;
	}

	private void findChildren(List<SysDept> sysDepts, List<SysDept> depts) {
		for (SysDept sysDept : sysDepts) {
			List<SysDept> children = new ArrayList<>();
			for (SysDept dept : depts) {
				if (sysDept.getDeptId() != null && sysDept.getDeptId().equals(dept.getParentId())) {
					children.add(dept);
				}
			}
			sysDept.setChildren(children);
			findChildren(children, depts);
		}
	}
}
