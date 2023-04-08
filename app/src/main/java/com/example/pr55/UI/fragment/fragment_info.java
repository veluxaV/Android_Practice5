package com.example.pr55.UI.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pr55.R;
import com.example.pr55.UI.viewModel.ServiceViewModel;


public class fragment_info extends Fragment  {

    Button backButton;
    private TextView service_info;
    private ServiceViewModel serviceViewModel;

    public fragment_info() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static fragment_info newInstance(String param1, String param2) {
        fragment_info fragment = new fragment_info();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_info, container, false);

        backButton = (Button) v.findViewById(R.id.GoBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (savedInstanceState == null) {
                    Navigation.findNavController(view).navigate(R.id.action_fragment_info_to_services);
                }
            }
        });
        service_info = (TextView) v.findViewById(R.id.service_info);

        serviceViewModel = new ViewModelProvider(this).get(ServiceViewModel.class);
        serviceViewModel.getSelectedService().observe(getViewLifecycleOwner(), service -> {
            if (service != null) {
                // Обновление UI с названием выбранного сервиса
                Log.d("12345678", service.getName());
                service_info.setText(service.getName());
            }
        });
        serviceViewModel.loadServices();

        return v;
    }

}