package com.ryzin.penguin.admin.dao;

import java.util.List;

import com.ryzin.penguin.admin.model.FunRate;


public interface FunRateMapper {

	/**
	 * 添加
	 * @param record
	 * @return
	 */
    int insert(FunRate record);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Long id);
    
    /**
     * 修改
     * @param record
     * @return
     */
    int update(FunRate record);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */    
    FunRate findById(Long id);

    /**
     * 基础分页查询
     * @param 
     * @return
     */    
    List<FunRate> findPage();
    
    /**
     * 根据实验ID和被评价者ID查询评价记录
     * @param 
     * @return
     */    
    FunRate findByExpIdAndRatedId(Long expId, Long ratedId);
    
    /**
     * 根据被评价者ID查询所有评价分数
     * @param 
     * @return
     */    
    List<FunRate> findAllRateByRatedId(Long ratedId);
}