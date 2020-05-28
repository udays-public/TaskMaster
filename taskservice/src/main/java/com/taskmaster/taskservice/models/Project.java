package com.taskmaster.taskservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="projects")
@EntityListeners(AuditingEntityListener.class)
public class Project {

    public Project(){}

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @ReadOnlyProperty
    private long id;

    private String name;

    private String description;

    @Column(name = "created_by",nullable = false)
    @CreatedBy
    private String createdBy;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false,updatable = false)
    @CreatedDate
    @JsonIgnore
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at", nullable = false)
    @LastModifiedDate
    @JsonIgnore
    private Date lastModifiedTime;



    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getCreatedBy() {
        return createdBy;
    }
}
