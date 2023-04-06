package com.example.pr55.data;

import android.security.identity.EphemeralPublicKeyNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ServiceDataSource {
    private final List<ServiceItem> autoServices;

    public ServiceDataSource() {
        autoServices = new ArrayList<>();
        autoServices.add(new ServiceItem(1, "Service 1", "Описание сервиса 1"));
        autoServices.add(new ServiceItem(2, "Service 2", "Описание сервиса 2"));
        autoServices.add(new ServiceItem(3, "Service 3", "Описание сервиса 3"));
    }

    public List<ServiceItem> getServices() {
        return autoServices;
    }

    public void saveServices(List<ServiceItem> autoServices) {
        // пока ничего не сохраняем
    }
    public ServiceItem getServiceItemById(int id) {
        for (ServiceItem serviceItem : autoServices) {
            if (serviceItem.getId() == id) {
                return serviceItem;
            }
        }
        return null;
    }
}
