package com.example.pr55.UI;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.pr55.R;
import com.example.pr55.data.ServiceDataSource;
import com.example.pr55.data.ServiceItem;
import com.example.pr55.data.ServiceRepository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Services extends Fragment implements ServicesAdapter.OnItemClickListener {

    final String TAG = "ServicesLayout";
    Button backButton;
    RecyclerView services;
    public Services() {
        // Required empty public constructor
    }

    public static Services newInstance() {
        Services fragment = new Services();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        Toast.makeText(getActivity(), "onCreate", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_services, container, false);
        Toast.makeText(getActivity(), "onCreateViewAddCar", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreateView");

        backButton = (Button) v.findViewById(R.id.GoBackButton);


        // инициализация RecyclerView и адаптера
        // Первым делом необходимо найти список на верстке экрана
        services = (RecyclerView) v.findViewById(R.id.services);
        // загрузка данных из репозитория и обновление списка услуг
        ServiceRepository repository = new ServiceRepository(
                new ServiceDataSource());
        List<ServiceItem> servicesList = repository.getServices();

        // Далее, создать адаптер и передать в него Context имассив элементов
        ServicesAdapter adapter = new ServicesAdapter(getContext(), servicesList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        services.setLayoutManager(layoutManager);
        // устанавливаем для списка адаптер
        services.setAdapter(adapter);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (savedInstanceState == null) {
                    Navigation.findNavController(view).navigate(R.id.action_services_to_firstScreen);
                }
            }
        });
        return v;
    }

    public ArrayList<String> getServicesFromFile(Context context) throws IOException
    //метод для чтения построчно из файла
    {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            String line;
            AssetManager assetManager = context.getAssets();
            InputStreamReader istream = new InputStreamReader(assetManager.open("Services.txt"));
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
    public void onItemClick(int position) {
        Toast.makeText(getContext(), "RecyclerView нажатие", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "RecyclerView нажатие");
    }
    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(), "onResume", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(getActivity(), "onPause", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(getActivity(), "onDestroyView", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Toast.makeText(getActivity(), "onDetach", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getActivity(), "onDestroy", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy");
    }
}