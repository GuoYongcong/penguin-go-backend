package com.ryzin.penguin.admin.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.ryzin.penguin.admin.model.FunExp;
import com.ryzin.penguin.core.http.HttpResult;
import com.ryzin.penguin.core.page.PageRequest;
import com.ryzin.penguin.core.page.PageResult;
import com.ryzin.penguin.core.service.CurdService;
import com.ryzin.penguin.admin.model.FunExpUser;
import com.ryzin.penguin.admin.model.FunUserExp;


public interface FunExpService extends CurdService<FunExp> {
	
	/**
	 * 查询实验用户集合
	 * @param expId
	 * @return
	 */
	List<FunExpUser> findExpUsers(Long expId);
	
	/**
	 * 查询实验用户集合
	 * @param 
	 * @return
	 */
	PageResult findExpUsersPage(PageRequest pageRequest);
	
	/**
	 * 查询被试参与的实验
	 */
	PageResult findSubjectPageByUserName(PageRequest pageRequest);
	
	/**
	 * 保存实验被试
	 * @param records
	 * @return
	 */
	int saveExpUser(FunExpUser record);
	
	/**
	 * 保存主试实验
	 * @param records
	 * @return
	 */
	int saveUserExp(FunUserExp record);
	
	/**
	 * 查询实验的报名人数
	 * @param expId
	 * @return
	 */
	int getExpUserCount(Long expId);
	
	/**
	 * 删除已报名被试
     * @param entities
     */
    int deletePeo(List<FunExpUser> records);
    
    /**
	 * 删除已报名被试
     * @param entities
     */
    int deletePeoByExpIdAndUserName(FunExpUser record);
    
}
