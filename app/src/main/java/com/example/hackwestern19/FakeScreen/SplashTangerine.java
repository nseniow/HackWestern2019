package com.example.hackwestern19.FakeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hackwestern19.Network.LoginInformationStorer;
import com.example.hackwestern19.Network.User;
import com.example.hackwestern19.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SplashTangerine extends AppCompatActivity {

    private String email;
    private String password;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tangerine_splash);

        User user = LoginInformationStorer.getUserFromFile(this);
        email = user.getEmail();
        password = user.getPassword();
        mAuth = FirebaseAuth.getInstance();

        signIn();

    }

    private void signIn() {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            System.out.println(mAuth.getUid());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    /* Create an Intent that will start the Menu-Activity. */
                                    Intent mainIntent = new Intent(SplashTangerine.this, TangerineActivity.class);
                                    SplashTangerine.this.startActivity(mainIntent);
                                    SplashTangerine.this.finish();
                                }
                            }, 700);

                        } else {
                            Toast.makeText(SplashTangerine.this, "Network Error!", Toast.LENGTH_SHORT);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    SplashTangerine.this.finish();
                                }
                            }, 400);
                        }
                    }
                });
    }
}
