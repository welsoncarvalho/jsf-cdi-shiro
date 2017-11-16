package com.test.jsf.cdi.security;

import java.util.List;

import org.apache.shiro.authz.Permission;

import com.test.jsf.cdi.model.User;

public class UserPrincipal {

    private User user;
    private List<Permission> permissions;
    
    public UserPrincipal(User user) {
        this.user = user;
    }
    
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<Permission> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
    
}
