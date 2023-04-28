package com.example.pr55.data.dataSource;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pr55.data.model.ServiceModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDataSource {
    private final List<ServiceModel> autoServices;
    private Context mContext;
    private final SharedPreferences sharedPreferences;

    public ServiceDataSource(Context context, SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        autoServices = new ArrayList<>();
        mContext = context;
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

    public void createFileAppSS(String fName, String fText) {
        try (FileOutputStream fos = mContext.openFileOutput(fName, Context.MODE_PRIVATE)) {
            fos.write(fText.getBytes());
            Log.d("AppSpecificStorage", "Был создан текстовый файл в app-specific storage"
                    + mContext.getDataDir().getAbsolutePath() + "/"
                    + fName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createFileExternalStorage(String fileName, String fileContent) {
        if (mContext.getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            File filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(filePath, fileName);
            FileOutputStream outputStream;
            try {
                outputStream = new FileOutputStream(file);
                outputStream.write(fileContent.getBytes());
                Log.d("ExternalStorage", "Был создан текстовый файл в общем хранилище " + filePath +"/"+ fileName);
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            // Обработка запроса на разрешение WRITE_EXTERNAL_STORAGE
        }
    }

    private static final String PREFS_NAME = "MyPrefsFile";

    public String getServiceName() {
        SharedPreferences sharedPref = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPref.getString("service_name", "");
    }

}
