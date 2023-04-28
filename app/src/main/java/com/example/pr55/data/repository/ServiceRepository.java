package com.example.pr55.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.pr55.data.dataSource.RoomDatabases.ServiceRoomDatabase;
import com.example.pr55.data.dataSource.ServiceDataSource;
import com.example.pr55.data.dataSource.dao.ServiceDao;
import com.example.pr55.data.dataSource.entities.ServiceEntity;
import com.example.pr55.data.model.ServiceModel;

import java.util.List;
import java.util.stream.Collectors;

public class ServiceRepository {
    private final ServiceDao serviceDao;
    private final LiveData<List<ServiceModel>> allServices ;
    private ServiceDataSource mDataSource;



    private final Context context;

    ServiceRoomDatabase serviceRoomDatabase;
    public ServiceRepository(Context applicationContext) {
        context = applicationContext;
        serviceRoomDatabase = ServiceRoomDatabase.getDatabase(context);
        serviceDao = ServiceRoomDatabase.getDatabase(context).serviceDao();
        allServices = Transformations.map(serviceDao.getAllServices(), entities -> entities.stream()
                .map(ServiceEntity::toServiceModel).collect(Collectors.toList()));
    }
    public LiveData<List<ServiceModel>> getAllBServices() {
        return allServices;
    }

    public void insert(ServiceEntity service) {
        ServiceRoomDatabase.databaseWriteExecutor.execute(() -> {
            serviceDao.insertService(service);
        });
    }
    public void createFileAppSS(String fName, String fText) {
        mDataSource.createFileAppSS(fName, fText);
    }
    public void createFileExternalStorage(String fileName, String fileContent) {
        mDataSource.createFileExternalStorage(fileName, fileContent);
    }

    public String getServiceName() {
        return mDataSource.getServiceName();
    }
}
