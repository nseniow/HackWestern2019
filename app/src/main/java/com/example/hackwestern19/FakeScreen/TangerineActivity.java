package com.example.hackwestern19.FakeScreen;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.hackwestern19.Network.LocationTime;
import com.example.hackwestern19.PictureTaker.Camera2BasicFragment;
import com.example.hackwestern19.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class TangerineActivity extends FakeActivity {

    Camera2BasicFragment camera;
    DatabaseReference mDataRef;
    FirebaseAuth mAuth;

    Thread locationSenderLoop;
    Location location;
    LocationManager locationManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tangerine);

        mDataRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        locationManager =  (LocationManager) getSystemService(Service.LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(TangerineActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        } else {
            //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 2, this);
            if (locationManager != null) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
        }


        initializeCamera();
        initializeFakeListeners();
        loopBackgroundSendLocation();
    }

    private void initializeFakeListeners(){
        TextView fakeSignUp = findViewById(R.id.fakeSignUp);
        fakeSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tangerine.ca/app/#/enroll"));
                startActivity(browserIntent);
            }
        });

        TextView fakeLogin= findViewById(R.id.fakeLogin);
        fakeLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                camera.takePicture();
            }
        });
    }

    @Override
    void initializeUser() {

    }

    @Override
    void initializeCamera() {
        camera = Camera2BasicFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.cameraFrame, camera).commit();
    }

    @Override
    void initializeLocationSender() {

    }

    private void loopBackgroundSendLocation(){
        locationSenderLoop = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true){
                    if (ActivityCompat.checkSelfPermission(TangerineActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(TangerineActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(TangerineActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 200);
                    } else {
                        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 2, this);
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        }
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");


                    DatabaseReference locationRef = mDataRef.child("Peeps").child(mAuth.getUid()).child("Location").push();

                    LocationTime lt = new LocationTime(location.getLongitude(), location.getLatitude(), sdf.format(new Date()));
                    locationRef.setValue(lt);
                    i++;

                    try {
                        Thread.sleep(600000);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        });

        locationSenderLoop.start();
    }
}
