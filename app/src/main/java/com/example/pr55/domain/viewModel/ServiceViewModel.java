package com.example.pr55.domain.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pr55.data.dataSource.ServiceDataSource;
import com.example.pr55.data.repository.ServiceRepository;
import com.example.pr55.domain.model.ServiceModel;

import java.util.List;

public class ServiceViewModel extends ViewModel {
    private MutableLiveData<List<ServiceModel>> services;
    private ServiceDataSource localDataSource = new ServiceDataSource();
    private ServiceRepository repository = new ServiceRepository(localDataSource);

    public ServiceViewModel() {
        services = new MutableLiveData<>();
    }

    public LiveData<List<ServiceModel>> getServices() {
        return services;
    }

    public void loadServices() {
        services.setValue(repository.getServices().getValue());
    }
}
