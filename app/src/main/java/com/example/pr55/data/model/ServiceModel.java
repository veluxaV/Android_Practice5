package com.example.pr55.data.model;

public class ServiceModel {
    private int id;
    private String name;
    private String description;

    public ServiceModel(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
