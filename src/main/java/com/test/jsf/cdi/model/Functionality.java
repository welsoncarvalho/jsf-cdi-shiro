package com.test.jsf.cdi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_functionality")
public class Functionality extends AbstractModel {

    @Id
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "path")
    private String path;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    @Override
    public Object getIdObject() {
        return this.id;
    }

}
