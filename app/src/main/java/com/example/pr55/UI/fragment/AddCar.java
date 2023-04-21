package com.example.pr55.UI.fragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.Manifest;

import com.example.pr55.UI.MainActivity;
import com.example.pr55.R;


public class AddCar extends Fragment {

    Button backButton;
    ListView carBrands;
    final String TAG = "AddCarLayout";
    private EditText car_name;
    private EditText car_brandname;
    final static String ARG_PARAM1 = "CAR_NAME";
    final static String ARG_PARAM2 = "CAR_BRAND";
    private static final String PREFS_NAME = "car_prefs";


    // Идентификатор канала
    private static String CHANNEL_ID = "Cat channel";
    private static final int REQUEST_CODE_PERMISSION = 1;
    // Идентификатор уведомления
    private static final int notificationId = 1;

    public static AddCar newInstance() {
        AddCar fragment = new AddCar();
        return fragment;
    }
    public AddCar(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        Toast.makeText(getActivity(), "onCreateAddCar", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Toast.makeText(getActivity(), "onAttachAddCar", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onAttach");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_car, container, false);
        //Toast.makeText(getActivity(), "onCreateViewAddCar", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreateView");

        backButton = (Button) v.findViewById(R.id.GoBackButton);

        car_name = (EditText) v.findViewById(R.id.name_edit_text);
        //final String[] carBrandArray = getResources().getStringArray(R.array.car_brands);
        //car_brandname = (EditText) v.findViewById(R.id.brand_edit_text);


        String[] carBrandArray;// массив для названий
        try {
            carBrandArray = getBrandsFromFile(getContext()).toArray(new String[getBrandsFromFile(getContext()).size()]);
            // вызов метода считывания  построчно из файла
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Button click in AddCar");
                String name = car_name.getText().toString();
                //String brand = car_brandname.getText().toString();
                Log.d("Car name", name);

                String fName = "car.txt";
                //createFileAppSS(fName, name);
                //createFileExternalStorage(fName, name);

                // Получаем объект SharedPreferences для сохранения данных
                SharedPreferences sharedPref = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

                // Редактор SharedPreferences
                SharedPreferences.Editor editor = sharedPref.edit();

                // Сохраняем значение в SharedPreferences
                editor.putString("car_name", name);

                // Сохраняем изменения
                editor.apply();

                //Log.d("Car brand", brand);
                if (savedInstanceState == null) {
                    Bundle bundle = new Bundle();
                    //bundle.putString(ARG_PARAM1, name);

                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.POST_NOTIFICATIONS)
                            != PackageManager.PERMISSION_GRANTED) {
                        // Permission is not granted
                        // Request permission from the user
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.POST_NOTIFICATIONS},
                                REQUEST_CODE_PERMISSION);
                    } else {
                        String title = "Добавлена новая машина";
                        String message = "На твой телефон пришло новое уведомление, посмотри";
                        showNotification(getContext(), title, message);
                    }


                    Navigation.findNavController(view).navigate(R.id.action_addCar_to_firstScreen, bundle);
                }
            }
        });
        return v;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1 && grantResults.length == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                // Perform the operation that requires this permission
                String title = "Добавлена новая машина";
                String message = "На твой телефон пришло новое уведомление, посмотри";
                showNotification(getContext(), title, message);
                createFileExternalStorage("car.txt", "Пример текста для записи в файл");
            } else {
                // Permission is denied
                // Disable the functionality that depends on this permission
                Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
                Log.d("УВЕДОМЛЕНИЕ", "НЕДОСТУПНО");
            }
        }
    }

    public void createFileAppSS(String fName, String fText){
        Context context = getContext();
        try (FileOutputStream fos = context.openFileOutput(fName, Context.MODE_PRIVATE)) {
            fos.write(fText.getBytes());
            Log.d("AppSpecificStorage", "Был создан текстовый файл в app-specific storage"
                    + context.getDataDir().getAbsolutePath()+"/"
                    + fName);
        } catch (IOException e) {throw new RuntimeException(e);}

    }
    public void createFileExternalStorage(String fileName, String fileContent) {
        Context context = getContext();
        if (context.getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
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
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1 );
        }
    }


    public void showNotification(Context context, String title, String message) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            String name1 = "My notification channel name";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name1, importance);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500});

            // Register the channel with the system
            NotificationManager notificationManager = getContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        // Создаем intent, который будет запускать наше приложение при нажатии на уведомление
        Intent intent = new Intent(requireContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.car)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true) // автоматически закрывает уведомление после нажатия
                .setContentIntent(pendingIntent)// добавляем PendingIntent
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(notificationId, builder.build());
    }

    public ArrayList<String> getBrandsFromFile(Context context) throws IOException
    //метод для чтения построчно из файла
    {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            String line;
            AssetManager assetManager = context.getAssets();
            InputStreamReader istream = new InputStreamReader(assetManager.open("Brands.txt"));
            BufferedReader in = new BufferedReader(istream);
            while ((line = in.readLine()) != null){
                arrayList.add(line);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
    @Override
    public void onResume() {
        super.onResume();
        //Toast.makeText(getActivity(), "onResumeAddCar", Toast.LENGTH_SHORT).show();
        Log.d("TAG", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(getActivity(), "onPauseAddCar", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(getActivity(), "onDestroyViewAddCar", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Toast.makeText(getActivity(), "onDetachAddCar", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getActivity(), "onDestroyAddCar", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy");
    }
}