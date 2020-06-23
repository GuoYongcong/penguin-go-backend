package com.ryzin.penguin.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryzin.penguin.core.page.MybatisPageHelper;
import com.ryzin.penguin.core.page.PageRequest;
import com.ryzin.penguin.core.page.PageResult;
import com.ryzin.penguin.admin.model.FunExpUser;
import com.ryzin.penguin.admin.model.FunUserExp;
import com.ryzin.penguin.admin.dao.FunUserExpMapper;
import com.ryzin.penguin.admin.service.FunUserExpService;


@Service
public class FunUserExpServiceImpl implements FunUserExpService {

	@Autowired
	private FunUserExpMapper funUserExpMapper;

	@Override
	public int save(FunUserExp record) {
		if(record.getId() == null || record.getId() == 0) {
			return funUserExpMapper.insert(record);
		}
		return funUserExpMapper.update(record);
	}

	@Override
	public int delete(FunUserExp record) {
		return funUserExpMapper.delete(record.getId());
	}

	@Override
	public int delete(List<FunUserExp> records) {
		for(FunUserExp record:records) {
			delete(record);
		}
		return 1;
	}

	@Override
	public FunUserExp findById(Long id) {
		return funUserExpMapper.findById(id);
	}

	@Override
	public PageResult findPage(PageRequest pageRequest) {
		return MybatisPageHelper.findPage(pageRequest, funUserExpMapper);
	}
	
	@Override
	public List<FunUserExp> findUserExpByUserId(long id){
		return funUserExpMapper.findByUserId(id);
	}
	
	
    /**
	 * 查询实验主试
     * @param 
     */
    public FunUserExp findUserExpByExpId(Long expId) {
    	return funUserExpMapper.findByExpId(expId);
    }
}
