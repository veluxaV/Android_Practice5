package com.example.pr55.data;

import java.util.List;

public class ServiceRepository {
    private final ServiceDataSource localDataSource;

    public ServiceRepository(ServiceDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }

    public List<ServiceItem> getServices() {
        List<ServiceItem> ServicesList = localDataSource.getAutoServices();
        if (ServicesList == null || ServicesList.isEmpty()) {
            //autoServices = remoteDataSource.getAutoServices();
            localDataSource.saveAutoServices(ServicesList);
        }
        return ServicesList;
    }


}
