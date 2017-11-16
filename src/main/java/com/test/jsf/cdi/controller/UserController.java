package com.test.jsf.cdi.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.test.jsf.cdi.model.Group;
import com.test.jsf.cdi.model.User;
import com.test.jsf.cdi.service.GroupService;
import com.test.jsf.cdi.service.UserService;

@Named
@ViewScoped
public class UserController extends AbstractController {

    private static final long serialVersionUID = -5719492546649823097L;
    
    @Inject
    private UserService userService;
    
    @Inject
    private GroupService groupService;
    
    private User filter;
    
    private List<User> users;
    
    private User user;
    
    private List<Group> groups;
    
    @PostConstruct
    public void init() {
        this.filter = new User();
    }
    
    public void add() {
        this.user = new User();
        this.groups = groupService.list();
    }
    
    public void edit(User user) {
        this.user = user;
        this.groups = groupService.list();
    }
    
    public void cancel() {
        this.user = null;
    }
    
    public void save() {
        userService.save(this.user);
        this.user = null;
        
        search();
    }
    
    public void search() {
        this.users = userService.findByFilter(this.filter);
    }

    // Gets e Sets
    
    public User getFilter() {
        return filter;
    }

    public void setFilter(User filter) {
        this.filter = filter;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public List<Group> getGroups() {
        return groups;
    }
    
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

}
