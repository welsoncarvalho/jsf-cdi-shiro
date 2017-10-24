package com.test.jsf.cdi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "tb_user")
@SequenceGenerator(name = "seqUser", sequenceName = "sq_user", initialValue = 1, allocationSize = 1)
public class User extends AbstractModel implements Serializable {

    private static final long serialVersionUID = 6034779029839655124L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUser")
    private Long id;
    
    @Version
    @Column(name = "version")
    private Long version;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "login")
    private String login;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "phone")
    private Long phone;
    
    @ManyToMany
    private List<Group> groups;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
    
    public List<Group> getGroups() {
        return groups;
    }
    
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    
    @Override
    public Object getIdObject() {
        return this.id;
    }
    
}
