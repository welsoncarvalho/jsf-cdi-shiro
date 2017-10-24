package com.test.jsf.cdi.controller;

import java.io.Serializable;
import org.apache.shiro.SecurityUtils;


public abstract class AbstractController implements Serializable {

    private static final long serialVersionUID = 3374634134390589790L;
    
    public String getUsername() {
        return (String) SecurityUtils.getSubject().getPrincipal();
    }

}
