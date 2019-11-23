package com.example.hackwestern19.FakeScreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.hackwestern19.PictureTaker.Camera2BasicFragment;
import com.example.hackwestern19.R;

public class TangerineActivity extends FakeActivity {

    Camera2BasicFragment camera;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tangerine);
        initializeCamera();
        initializeFakeListeners();
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
}
