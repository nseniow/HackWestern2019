package com.example.hackwestern19.FakeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hackwestern19.R;

public class SplashTangerine extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tangerine_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashTangerine.this, MainActivity.class);
                SplashTangerine.this.startActivity(mainIntent);
                SplashTangerine.this.finish();
            }
        }, 1000);

    }
}
