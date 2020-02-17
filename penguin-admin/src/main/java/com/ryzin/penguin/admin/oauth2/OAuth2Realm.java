package com.ryzin.penguin.admin.oauth2;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryzin.penguin.admin.model.SysUser;
import com.ryzin.penguin.admin.model.SysUserToken;
import com.ryzin.penguin.admin.service.SysUserService;
import com.ryzin.penguin.admin.service.SysUserTokenService;

/**
 * 认证Realm实现
 * 逻辑认证器是认证和授权的主体逻辑，主要包含两部分。
 * doGetAuthenticationInfo：实现自己的登录验证逻辑，这里主要是认证 token。
 * doGetAuthorizationInfo：实现接口授权逻辑，收集权限标识或角色，用来判定接口是否可以访问
 * @author 
 * @date 
 */
@Component
public class OAuth2Realm extends AuthorizingRealm {

	@Autowired
	SysUserService sysUserService;
	@Autowired
	SysUserTokenService sysUserTokenService;
	
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(接口保护，验证接口调用权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUser user = (SysUser)principals.getPrimaryPrincipal();
        // 用户权限列表，根据用户拥有的权限标识与如 @permission标注的接口对比，决定是否可以调用接口
        Set<String> permsSet = sysUserService.findPermissions(user.getUserName());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();
        // 根据accessToken，查询用户token信息
        SysUserToken sysUserToken = sysUserTokenService.findByToken(token);
        if(sysUserToken == null || sysUserToken.getExpireTime().getTime() < System.currentTimeMillis()){
        	// token已经失效
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }
        // 查询用户信息
        SysUser user = sysUserService.findById(sysUserToken.getUserId());
        // 账号被锁定
        if(user.getStatus() == 0){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, token, getName());
        return info;
    }
}
