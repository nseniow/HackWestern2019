package com.example.hackwestern19.Network;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hackwestern19.FakeScreen.MainActivity;
import com.example.hackwestern19.FakeScreen.TangerineActivity;
import com.example.hackwestern19.R;

import java.util.ArrayList;
import java.util.List;

public class StartUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        Button loginBtn = findViewById(R.id.loginBtn);
        Button signupBtn = findViewById(R.id.signupBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(StartUpActivity.this, LoginActivity.class);
                startActivity(main);
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(StartUpActivity.this, SignUpActivity.class);
                startActivity(main);
            }
        });

        requestPersmissions();
    }

    private void requestPersmissions(){

        List<String> requestList = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requestList.add(Manifest.permission.CAMERA);
        }


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            requestList.add(Manifest.permission.INTERNET);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            requestList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (!requestList.isEmpty()) {
            ActivityCompat.requestPermissions(this, requestList.toArray(new String[requestList.size()]),100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int i = 0; i < grantResults.length; i++){
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "You need to allow access! App will now exit",Toast.LENGTH_LONG);

                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        StartUpActivity.this.finish();
                    }
                }, 500);
            }
        }
    }
}
