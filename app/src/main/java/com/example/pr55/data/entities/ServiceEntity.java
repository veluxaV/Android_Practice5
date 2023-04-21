package com.example.pr55.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "services")
public class ServiceEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String serviceName;
    private double price;
    // Конструкторы
    public ServiceEntity(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }
    // Методы доступа к полям
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
