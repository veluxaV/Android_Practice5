package com.example.pr55.UI.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pr55.data.dataSource.entities.ServiceEntity;
import com.example.pr55.data.repository.ServiceRepository;
import com.example.pr55.data.model.ServiceModel;

import java.util.List;

public class ServiceViewModel extends AndroidViewModel {
    private ServiceRepository mRepository;

    private final LiveData<List<ServiceModel>> allServices;
    private final MutableLiveData<String> serviceNameLiveData;


    public ServiceViewModel (Application application, MutableLiveData<String> serviceNameLiveData) {
        super(application);
        mRepository = new ServiceRepository(application);
        this.serviceNameLiveData = serviceNameLiveData;
        allServices = mRepository.getAllBServices();
    }

    public LiveData<List<ServiceModel>> getAllServices() { return allServices; }
    public void updateAllServices(List<ServiceModel> servicesList){
        //allServices.postValue
    }

    public void insert(ServiceModel service) { mRepository.insert
            (new ServiceEntity(service.getId(), service.getName(), service.getPrice())); }

    public void createFileAppSS(String fName, String fText) {
        mRepository.createFileAppSS(fName, fText);
    }
    public void createFileExternalStorage(String fileName, String fileContent) {
        mRepository.createFileExternalStorage(fileName, fileContent);
    }

    public LiveData<String> getServiceNameLiveData() {
        return serviceNameLiveData;
    }
    public String getServiceName() {
        return mRepository.getServiceName();
    }

}

