package com.example.demo.update;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity
@DynamicUpdate
public class VersionDynamicUpdateObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Version
    private Long version;
    private String name;
    private String data;

    public VersionDynamicUpdateObject(){}

    public VersionDynamicUpdateObject(Long id, String name, String data) {
        Id = id;
        this.name = name;
        this.data = data;
    }

    public VersionDynamicUpdateObject(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
