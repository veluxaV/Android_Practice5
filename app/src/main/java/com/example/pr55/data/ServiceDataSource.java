package com.example.pr55.data;

import java.util.ArrayList;
import java.util.List;

public class ServiceDataSource {
    private final List<ServiceItem> autoServices;

    public ServiceDataSource() {
        autoServices = new ArrayList<>();
        autoServices.add(new ServiceItem(1, "Service 1"));
        autoServices.add(new ServiceItem(2, "Service 2"));
        autoServices.add(new ServiceItem(3, "Service 3"));
    }

    public List<ServiceItem> getAutoServices() {
        return autoServices;
    }

    public void saveAutoServices(List<ServiceItem> autoServices) {
        // пока ничего не сохраняем
    }
}
