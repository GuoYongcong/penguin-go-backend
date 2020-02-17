package com.ryzin.penguin.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ryzin.penguin.admin.model.SysUserToken;

public interface SysUserTokenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserToken record);

    int insertSelective(SysUserToken record);

    SysUserToken selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserToken record);

    int updateByPrimaryKey(SysUserToken record);
    
    SysUserToken findByUserId(@Param(value="userId") Long userId);

    SysUserToken findByToken(@Param(value="token") String token);
}