package com.test.jsf.cdi.controller;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.test.jsf.cdi.model.User;
import javax.enterprise.context.RequestScoped;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

@Named
@RequestScoped
public class LoginController extends AbstractController {

    private static final long serialVersionUID = 1673300369896233907L;
    
    private User user;
    
    @PostConstruct
    public void init() {
        setUser(new User());
    }
    
    public String login() {        
        UsernamePasswordToken token = new UsernamePasswordToken(getUser().getLogin(), getUser().getPassword());
        Subject subUser = SecurityUtils.getSubject();
        subUser.login(token);
        return "/pages/group/group";
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
}
