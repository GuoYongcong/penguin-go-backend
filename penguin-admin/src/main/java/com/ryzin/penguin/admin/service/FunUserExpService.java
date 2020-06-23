package com.ryzin.penguin.admin.service;

import java.util.List;

import com.ryzin.penguin.admin.model.FunExpUser;
import com.ryzin.penguin.admin.model.FunUserExp;
import com.ryzin.penguin.core.service.CurdService;


public interface FunUserExpService extends CurdService<FunUserExp> {
	List<FunUserExp> findUserExpByUserId(long id);
	
    /**
	 * 查询实验主试
     * @param 
     */
    public FunUserExp findUserExpByExpId(Long expId);
}
