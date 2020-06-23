package com.ryzin.penguin.admin.service;

import java.util.List;

import com.ryzin.penguin.admin.model.FunExpUser;
import com.ryzin.penguin.core.service.CurdService;


public interface FunExpUserService extends CurdService<FunExpUser> {
	
	List<FunExpUser> findExpUserByUserId(long id);
	
	FunExpUser findByExpIdAndUserName(Long expId, String userName);
}
