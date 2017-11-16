package com.test.jsf.cdi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "tb_group")
@SequenceGenerator(name = "seqGroup", sequenceName = "sq_group", initialValue = 1, allocationSize = 1)
public class Group extends AbstractModel implements Serializable {

    private static final long serialVersionUID = -7984499092593347331L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGroup")
    private Long id;
    
    @Version
    @Column(name = "version")
    private Long version;
    
    @Column(name = "name")
    private String name;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_group_func",
            joinColumns = {@JoinColumn(name = "id_group", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_functionality", referencedColumnName = "id")})
    private List<Functionality> functionalities;

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
    
    public List<Functionality> getFunctionalities() {
        return functionalities;
    }
    
    public void setFunctionalities(List<Functionality> functionalities) {
        this.functionalities = functionalities;
    }
    
    @Override
    public Object getIdObject() {
        return this.id;
    }
    
}
