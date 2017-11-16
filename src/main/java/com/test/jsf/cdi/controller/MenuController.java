package com.test.jsf.cdi.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.Permission;

import com.test.jsf.cdi.security.UserPrincipal;

@Named
@ViewScoped
public class MenuController extends AbstractController {

    private static final long serialVersionUID = 1899324019449113572L;
    
    public List<Permission> getPermissions() {
        return ((UserPrincipal) SecurityUtils.getSubject().getPrincipal()).getPermissions();
    }

}
