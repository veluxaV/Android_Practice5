package com.example.pr55.UI.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pr55.R;

public class fragment_profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button backButton;
    private Button supportButton;
    private TextView supportTextView;
    private TextView problemTextView;

    public fragment_profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_profile.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_profile newInstance(String param1, String param2) {
        fragment_profile fragment = new fragment_profile();
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
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        backButton = (Button) v.findViewById(R.id.GoBackButton);
        supportButton = (Button) v.findViewById(R.id.support_button);
        supportTextView = (TextView) v.findViewById(R.id.support_textView);
        problemTextView = (TextView) v.findViewById(R.id.problem_textView);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (savedInstanceState == null) {
                    Navigation.findNavController(view).navigate(R.id.action_fragment_profile_to_firstScreen);
                }
            }
        });
        supportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String emailAddress = supportTextView.getText().toString();
                String subject = "Тема письма";
                String body = "Текст письма";
                sendEmail(emailAddress, subject, body);

                if (savedInstanceState == null) {

                }
            }
        });



        return v;
    }
    private void sendEmail(String emailAddress, String subject, String body) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddress});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);

        try {
            startActivity(Intent.createChooser(emailIntent, "Отправка письма..."));
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "Нет приложения для отправки письма.",
                    Toast.LENGTH_SHORT).show();
        }
    }


}