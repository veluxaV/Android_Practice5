package com.example.pr55.data.RoomDatabases;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.pr55.data.dao.ServiceDao;
import com.example.pr55.data.entities.ServiceEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ServiceEntity.class}, version = 1, exportSchema = false)
public abstract class ServiceRoomDatabase extends RoomDatabase {
    public abstract ServiceDao serviceDao();
    private static volatile ServiceRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ServiceRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ServiceRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ServiceRoomDatabase.class, "service_database")
                            .addCallback(sRoomDatabaseCallback).build();}}
        }
        return INSTANCE;
    }

    public static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                ServiceDao dao = INSTANCE.serviceDao();
                dao.deleteAllServices();
                ServiceEntity service = new ServiceEntity(1,"Масло", 1000);
                dao.insertService(service);
                service = new ServiceEntity(2,"Замена масла", 3000);
                dao.insertService(service);
                service = new ServiceEntity(3,"Замена ремня ГРМ", 7000);
                dao.insertService(service);
                service = new ServiceEntity(4,"Замена тормозных колодок", 5000);
                dao.insertService(service);
                service = new ServiceEntity(5,"Замена рулевых тяг", 4000);
                dao.insertService(service);
            });
        }
    };
}

