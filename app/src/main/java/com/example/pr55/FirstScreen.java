package com.example.pr55;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FirstScreen extends Fragment {

    Button addCarButton;
    Button chooseServiceButton;
    final String TAG = "FirstScreenLayout";
    final static String ARG_PARAM1 = "CAR_NAME";
    final static String ARG_PARAM2 = "CAR_BRAND";
    private TextView add_car_text;



    public static FirstScreen newInstance(String param1, String param2) {
        FirstScreen fragment = new FirstScreen();
        Bundle args = new Bundle();
        if(param1 != null && param2 != null){
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            fragment.setArguments(args);
        }
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(getActivity(), "onCreate", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Toast.makeText(getActivity(), "onAttach", Toast.LENGTH_SHORT).show();

        Log.d(TAG, "onAttach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v;
        v = inflater.inflate(R.layout.fragment_first_screen, container, false);
        Toast.makeText(getActivity(), "onCreateView", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onCreateView");

        add_car_text = (TextView) v.findViewById(R.id.add_car_text);
        addCarButton = (Button) v.findViewById(R.id.add_car_button);
        chooseServiceButton = (Button) v.findViewById(R.id.choose_service_button);
        if (getArguments() != null) {
            String name = getArguments().getString(ARG_PARAM1);
            //String brand = getArguments().getString(ARG_PARAM2);
            Log.d("Car name Get Args", name);
            //Log.d("Car brand  Get Args", brand);
            //add_car_text.setText("Ваша машина: " + name + " " + brand);
            add_car_text.setText("Ваша машина: " + name);
        }

        addCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Button clicked AddCar");

                if (savedInstanceState == null) {
                //    getActivity().getSupportFragmentManager().beginTransaction()
                //            .replace(R.id.container, AddCar.newInstance())
                //            .commit();
                }

                Navigation.findNavController(view).navigate(R.id.action_firstScreen_to_addCar);
            }
        });
        chooseServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Button clicked Services");

                if (savedInstanceState == null) {
                    //getActivity().getSupportFragmentManager().beginTransaction()
                    //        .replace(R.id.container, Services.newInstance())
                    //       .commit();
                    Navigation.findNavController(view).navigate(R.id.action_firstScreen_to_services);
                }
            }
        });
        return v;
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