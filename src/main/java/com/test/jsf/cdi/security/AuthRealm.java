/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.jsf.cdi.security;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.inject.spi.CDI;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.test.jsf.cdi.model.Group;
import com.test.jsf.cdi.model.User;
import com.test.jsf.cdi.service.GroupService;
import com.test.jsf.cdi.service.UserService;

/**
 *
 * @author welson
 */
public class AuthRealm extends AuthorizingRealm {

    private static final String REALM_NAME = "Auth_Realm";
    
    @Override
    public String getName() {
        // Unique name of realm
        return REALM_NAME;
    }
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken at) throws AuthenticationException {
        String principal = (String) at.getPrincipal();
        
        User user = findUser(principal);
        
        if(user != null) {
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(new UserPrincipal(user), user.getPassword(), getName());            
            SimpleCredentialsMatcher simpleCredentialsMatcher = new SimpleCredentialsMatcher();
            
            Boolean isMatch = simpleCredentialsMatcher.doCredentialsMatch(at, authenticationInfo);
            
            if(isMatch) {
                
                Group group = findGroup(user.getIdGroup());
                
                List<Permission> permissions = group.getFunctionalities().stream().map(func -> {
                    return new FunctionalityPermission(func);
                }).collect(Collectors.toList());
                
                UserPrincipal userPrincipal = (UserPrincipal) authenticationInfo.getPrincipals().getPrimaryPrincipal();
                userPrincipal.setPermissions(permissions);
                
                return authenticationInfo;
            }
        }
        
        throw new AuthenticationException();
    }
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        
        UserPrincipal userPrincipal = (UserPrincipal) principalCollection.getPrimaryPrincipal();
        
        authorizationInfo.addObjectPermissions(userPrincipal.getPermissions());
        
        return authorizationInfo;
    }
    
    private User findUser(String principal) {
        UserService userService = CDI.current().select(UserService.class).get();
        return userService.findByLogin(principal);
    }
    
    private Group findGroup(Long idGroup) {
        GroupService groupService = CDI.current().select(GroupService.class).get();
        return groupService.findWithFunctionalities(idGroup);
    }
    
}
