package com.test.jsf.cdi.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.test.jsf.cdi.model.Group;
import com.test.jsf.cdi.paginator.DataPagination;
import com.test.jsf.cdi.paginator.DataPaginator;
import com.test.jsf.cdi.service.GroupService;

@Named
@ViewScoped
public class GroupController extends AbstractConversationController {

    private static final long serialVersionUID = 6469563754182667659L;
    
    @Inject
    private GroupService groupService;
    
    private Group filter;
    
    private DataPaginator<Group> groupPaginator;
    
    private List<Group> groups;
    
    private Group group;
    
    @PostConstruct
    public void init() {
        System.out.println("Group Controller: " + getUsername());
        this.filter = new Group();
    }
    
    public void add() {
        this.group = new Group();
    }
    
    public void edit(Group group) {
        this.group = group;
    }
    
    public void cancel() {
        this.group = null;
    }
    
    public void save() {
        groupService.save(group);
        this.group = null;
        
        search();
    }
    
    public void search() {
        setGroupPaginator(new DataPaginator<>(new DataPagination<Group>() {

            @Override
            public Long getRowCount() {
                return groupService.countByFilter(getFilter());
            }

            @Override
            public List<Group> getListResult(int first, int pageSize) {
                return groupService.findByFilter(getFilter(), first, pageSize);
            }
        }));
        
    }
    
    // Get e Set
    
    public Group getFilter() {
        return filter;
    }
    
    public void setFilter(Group filter) {
        this.filter = filter;
    }
    
    public DataPaginator<Group> getGroupPaginator() {
        return groupPaginator;
    }
    
    public void setGroupPaginator(DataPaginator<Group> groupPaginator) {
        this.groupPaginator = groupPaginator;
    }

    public List<Group> getGroups() {
        return groups;
    }
    
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    
    public Group getGroup() {
        return group;
    }
    
    public void setGroup(Group group) {
        this.group = group;
    }
    
}
