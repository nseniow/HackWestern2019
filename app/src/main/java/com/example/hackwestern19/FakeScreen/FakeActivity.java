package com.example.hackwestern19.FakeScreen;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hackwestern19.Network.LoginInformationStorer;
import com.example.hackwestern19.Network.User;

public class FakeActivity extends AppCompatActivity {

    User user;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    protected void initializeUser(){
        user = LoginInformationStorer.getUserFromFile(this);
        Log.i("User email: ", user.getEmail());
        Log.i("User password: ", user.getPassword());

        //Connect with Firebase
    }

    protected void initializeCamera(){

    }

    protected void initializeLocationTracking(){

    }

}
