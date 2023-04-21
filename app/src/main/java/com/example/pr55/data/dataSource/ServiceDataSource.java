package com.example.pr55.data.dataSource;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pr55.data.model.ServiceModel;

import java.util.ArrayList;
import java.util.List;

public class ServiceDataSource {
    private final List<ServiceModel> autoServices;

    public ServiceDataSource() {
        autoServices = new ArrayList<>();

    }

    public LiveData<List<ServiceModel>> getServices() {
        MutableLiveData<List<ServiceModel>> data = new MutableLiveData<>();
        data.setValue(autoServices);
        return data;
    }

    public void saveServices(List<ServiceModel> autoServices) {
        // пока ничего не сохраняем
    }
    public LiveData<ServiceModel> getServiceItemById(int id) {
        MutableLiveData<ServiceModel> data = new MutableLiveData<>();
        for (ServiceModel serviceModel : autoServices) {
            if (serviceModel.getId() == id) {
                data.setValue(serviceModel);
                return data;
            }
        }
        return null;
    }
}
