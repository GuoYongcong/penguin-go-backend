package com.ryzin.penguin.admin.service;

import java.util.List;


import com.ryzin.penguin.admin.model.FunRate;
import com.ryzin.penguin.core.service.CurdService;


public interface FunRateService extends CurdService<FunRate> {
	
	FunRate findRateByExpIdAndRatedId(Long expId, Long ratedId);
	
	List<FunRate> findAllRateByRatedId(Long ratedId);
}
