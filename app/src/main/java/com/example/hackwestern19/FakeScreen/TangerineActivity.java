package com.example.hackwestern19.FakeScreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hackwestern19.R;

public class TangerineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tangerine);
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
    }
}
