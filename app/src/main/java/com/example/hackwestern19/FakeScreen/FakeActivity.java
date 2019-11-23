package com.example.hackwestern19.FakeScreen;

import androidx.appcompat.app.AppCompatActivity;


public abstract class FakeActivity extends AppCompatActivity {

    abstract void initializeUser();

    abstract void initializeCamera();

    abstract void initializeLocationSender();

}
