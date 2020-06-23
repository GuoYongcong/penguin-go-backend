package com.ryzin.penguin.admin.dao;

import java.util.List;

import com.ryzin.penguin.admin.model.FunExpUser;
import com.ryzin.penguin.admin.model.SysUser;

import org.apache.ibatis.annotations.Param;


public interface FunExpUserMapper {

    /**
	 * 添加
	 * @param record
	 * @return
	 */
    int insert(FunExpUser record);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Long id);
    
    int deleteByExpIdAndUserId(Long expId, Long userId);
    
    /**
     * 修改
     * @param record
     * @return
     */
    int update(FunExpUser record);
    
    int insertSelective(FunExpUser record);

    FunExpUser selectByPrimaryKey(Long id);

    int updateByPrimaryKey(FunExpUser record);
    
    int deleteByPrimaryKey(Long id);
    
	List<FunExpUser> findExpUsers(@Param(value="expId") Long expId);
	
	List<FunExpUser> findAll();
	
	 /**
     * 根据主键查询
     * @param id
     * @return
     */    
    FunExpUser findById(Long id);
    
    /**
     * 根据user_id查询
     * @param id
     * @return
     */    
    List<FunExpUser> findByUserId(@Param(value="userId") Long userId);

	int deleteByExpId(@Param(value="expId") Long expId);

    int getExpUserCount(@Param(value="expId") Long expId);

	FunExpUser findByExpIdAndUserId(Long expId, Long userId);
}