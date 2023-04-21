package com.example.pr55.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.pr55.data.model.ServiceModel;

@Entity(tableName = "services")
public class ServiceEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String serviceName;
    private double price;
    // Конструкторы
    public ServiceEntity(int id, String serviceName, double price) {
        this.id = id;
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
    public ServiceModel toServiceModel(){
        return new ServiceModel(this.id,this.serviceName,this.price);
    }
}
