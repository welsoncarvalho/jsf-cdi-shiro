/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.jsf.cdi.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.realm.Realm;

/**
 *
 * @author welson
 */
public class AuthRealm implements Realm {

    private static final String REALM_NAME = "Auth_Realm";
    
    @Override
    public String getName() {
        // Unique name of realm
        return REALM_NAME;
    }

    @Override
    public boolean supports(AuthenticationToken at) {
        // Check if token is valid, how the authentication is by JSF, it's not necessary
        return Boolean.TRUE;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken at) throws AuthenticationException {
        String principal = (String) at.getPrincipal();
        
        String credential = "123456"; // Get credential from database.
        
        if(credential != null) {
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, credential, getName());
            SimpleCredentialsMatcher simpleCredentialsMatcher = new SimpleCredentialsMatcher();
            
            Boolean isMatch = simpleCredentialsMatcher.doCredentialsMatch(at, authenticationInfo);
            
            if(isMatch) {
                return authenticationInfo;
            }
        }
        
        throw new AuthenticationException();
    }
    
}
