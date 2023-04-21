package com.example.pr55.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pr55.data.entities.ServiceEntity;

import java.util.List;

@Dao
public interface  ServiceDao {
    @Insert
    void insertService(ServiceEntity service);

    @Query("SELECT * FROM services")
    LiveData<List<ServiceEntity>> getAllServices();

    @Query("SELECT * FROM services WHERE id = :id")
    ServiceEntity getServiceById(int id);

    @Query("DELETE FROM services")
    void deleteAllServices();
}
