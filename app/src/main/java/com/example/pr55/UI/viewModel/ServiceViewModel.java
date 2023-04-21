package com.example.pr55.UI.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pr55.data.dataSource.ServiceDataSource;
import com.example.pr55.data.entities.ServiceEntity;
import com.example.pr55.data.repository.ServiceRepository;
import com.example.pr55.data.model.ServiceModel;

import java.util.List;

public class ServiceViewModel extends AndroidViewModel {
    private ServiceRepository mRepository;

    private final LiveData<List<ServiceModel>> allServices;

    public ServiceViewModel (Application application) {
        super(application);
        mRepository = new ServiceRepository(application);
        allServices = mRepository.getAllBServices();
    }

    public LiveData<List<ServiceModel>> getAllServices() { return allServices; }
    public void updateAllServices(List<ServiceModel> servicesList){
        //allServices.postValue
    }

    public void insert(ServiceModel service) { mRepository.insert(new ServiceEntity(service.getId(), service.getName(), service.getPrice())); }


}

