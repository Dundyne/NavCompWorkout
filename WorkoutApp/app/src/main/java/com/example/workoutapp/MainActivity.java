package com.example.workoutapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.workoutapp.databinding.ActivityMainBinding;
import com.example.workoutapp.exercises.ExerciseModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    FusedLocationProviderClient fusedLocationProviderClient;
    private FirebaseFirestore firestoreDb;
    private CollectionReference localeCollection;
    Location oldLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firestoreDb = FirebaseFirestore.getInstance();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        firestoreDb.setFirestoreSettings(settings);


        localeCollection = firestoreDb.collection("locale");



        ///////////
        SharedPreferences sharedPreferences
                = this.getSharedPreferences(
                "sharedPrefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor
                = sharedPreferences.edit();
        final boolean isDarkModeOn
                = sharedPreferences
                .getBoolean(
                        "isDarkModeOn", false);

        // When user reopens the app
        // after applying dark/light mode
        if (isDarkModeOn) {
            AppCompatDelegate
                    .setDefaultNightMode(
                            AppCompatDelegate
                                    .MODE_NIGHT_YES);

        }
        else {
            AppCompatDelegate
                    .setDefaultNightMode(
                            AppCompatDelegate
                                    .MODE_NIGHT_NO);

        }
        /////////

        NavController navController = Navigation.findNavController(this, R.id.activity_main_nav_host_fragment);
        BottomNavigationView bottomNavigationView = findViewById(R.id.activity_main_bottom_navigation_view);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            getLocation();
        }
        else
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
    }
    @SuppressLint("MissingPermission")
    private void getLocation(){
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();

                if(location != null && location != oldLocation ) {

                    try {
                        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());

                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1

                        );
                        oldLocation = location;
                        localeCollection.add(new Location(location));

                        //Ingen anelse hvorfor toast ikke funker
                        Toast.makeText(getApplicationContext(), "Latitude" + addresses.get(0).getAddressLine(0)  , Toast.LENGTH_LONG).show();
                        Log.d("mapstag", addresses.get(0).getAddressLine(0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });}
}