package com.example.pr55.data.model;

public class ServiceModel {
    private int id;
    private String serviceName;
    private double price;

    public ServiceModel(int id, String serviceName, double price) {
        this.id = id;
        this.serviceName = serviceName;
        this.price = price;
    }
    public String getName() {
        return serviceName;
    }
    public double getPrice() {
        return price;
    }
    public int getId() {
        return id;
    }
    public void setName(String serviceName) {
        this.serviceName = serviceName;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
