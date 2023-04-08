package com.example.pr55.data.repository;

import androidx.lifecycle.LiveData;

import com.example.pr55.data.model.ServiceModel;
import com.example.pr55.data.dataSource.ServiceDataSource;

import java.util.List;

public class ServiceRepository {
    private final ServiceDataSource localDataSource;

    public ServiceRepository(ServiceDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }

    public LiveData<List<ServiceModel>> getServices() {
        return localDataSource.getServices();
    }
    public LiveData<ServiceModel> getServiceById(int ServiceId) {
        return localDataSource.getServiceItemById(ServiceId);
    }
}
