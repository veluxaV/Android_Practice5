package com.example.pr55.UI.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pr55.R;
import com.example.pr55.domain.model.ServiceModel;
import com.example.pr55.domain.viewModel.ServiceViewModel;


public class fragment_info extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
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
        serviceViewModel.getServices().observe(getViewLifecycleOwner(), serviceModels -> {
            ServiceModel firstService = serviceModels.get(0);
            String serviceName = firstService.getName();
            service_info.setText(serviceName);
        });
        serviceViewModel.loadServices();

        return v;
    }
}