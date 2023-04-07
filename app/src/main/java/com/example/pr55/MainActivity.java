package com.example.pr55;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.Openable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.pr55.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
/*
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                                                           @Override
                                                           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                                               switch (item.getItemId()) {
                                                                   case R.id.home:
                                                                       // Переключение на фрагмент Home
                                                                       Navigation.findNavController((View) item).navigate(R.id.action_firstScreen_to_addCar);
                                                                       return true;
                                                                   case R.id.services:
                                                                       // Переключение на фрагмент services
                                                                       Navigation.findNavController((View) item).navigate(R.id.action_firstScreen_to_services);
                                                                       return true;
                                                                   case R.id.profile:
                                                                       // Переключение на фрагмент profile
                                                                       Navigation.findNavController((View) item).navigate(R.id.action_firstScreen_to_fragment_profile);
                                                                       return true;
                                                               }
                                                               return false;
                                                           }
                                                       });

*/

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, (Openable) null) || super.onSupportNavigateUp();
    }
}