package com.example.pr55.data;

public class ServiceItem {
    private int id;
    private String name;

    public ServiceItem(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
