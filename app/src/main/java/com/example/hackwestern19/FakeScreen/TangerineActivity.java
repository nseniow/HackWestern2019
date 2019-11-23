package com.example.hackwestern19.FakeScreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.hackwestern19.PictureTaker.Camera2BasicFragment;
import com.example.hackwestern19.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TangerineActivity extends FakeActivity {

    Camera2BasicFragment camera;
    DatabaseReference mDataRef;
    FirebaseAuth mAuth;

    Thread locationSenderLoop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tangerine);

        mDataRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

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
                    DatabaseReference locationRef = mDataRef.child("Peeps").child(mAuth.getUid()).child("Location").push();
                    locationRef.setValue("Fake Data No." + i);
                    i++;

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        });

        locationSenderLoop.start();
    }
}
