package com.test.jsf.cdi.security;

import org.apache.shiro.authz.Permission;

import com.test.jsf.cdi.model.Functionality;

public class FunctionalityPermission implements Permission {
    
    private Functionality functionality;
    
    public FunctionalityPermission(Functionality functionality) {
        this.functionality = functionality;
    }
    
    public Functionality getFunctionality() {
        return functionality;
    }

    @Override
    public boolean implies(Permission permission) {
        FunctionalityPermission funcPermission = (FunctionalityPermission) permission;
        return this.functionality.getPath().startsWith(funcPermission.getFunctionality().getPath());
    }

}
