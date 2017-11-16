package com.test.jsf.cdi.controller;

import java.io.Serializable;
import org.apache.shiro.SecurityUtils;

import com.test.jsf.cdi.security.UserPrincipal;


public abstract class AbstractController implements Serializable {

    private static final long serialVersionUID = 3374634134390589790L;
    
    public String getUsername() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityUtils.getSubject().getPrincipal();
        return userPrincipal.getUser().getName();
    }

}
