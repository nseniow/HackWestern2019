package com.example.hackwestern19.FakeScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.hackwestern19.BuildConfig;
import com.example.hackwestern19.PictureTaker.Camera2BasicFragment;
import com.example.hackwestern19.R;

import java.util.ArrayList;
import java.util.List;

import in.myinnos.library.AppIconNameChanger;

public class MainActivity extends AppCompatActivity {

    Camera2BasicFragment camera2BasicFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        camera2BasicFragment = Camera2BasicFragment.newInstance();

        FrameLayout frame = findViewById(R.id.frame);
        frame.setAlpha(0f);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, camera2BasicFragment).commit();

        Button b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                camera2BasicFragment.takePicture();
            }
        });

        List<String> disableNames = new ArrayList<>();
        disableNames.add("com.example.hackwestern19Tangerine");

        new AppIconNameChanger.Builder(MainActivity.this)
                .activeName("com.example.hackwestern19TD") // String
                .disableNames(disableNames) // List<String>
                .packageName(BuildConfig.APPLICATION_ID)
                .build()
                .setNow();
    }
}
