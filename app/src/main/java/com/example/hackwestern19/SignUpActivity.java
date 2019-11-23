package com.example.hackwestern19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String email;
    private String password;
    private String phoneNum;
    private String recoveryNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        System.out.println(mAuth);
    }


    public void onClick(View v){
        int id = v.getId();
        if(id == R.id.confirmButton){
            updateData();
            createAccount();
        }
    }

    private void createUserDataBaseEntry(FirebaseUser mAuth, User user){
        this.mDatabase = FirebaseDatabase.getInstance().getReference("Peeps");
        mDatabase.child(mAuth.getUid()).setValue(user).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) { //task should be set to Void to prevent error
                if(task.isSuccessful()){

                    finish();

                } else {
                    Toast.makeText(SignUpActivity.this, "Is a RIP", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void createAccount(){
        final User user = new User(email, password, phoneNum, recoveryNum);

        this.mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            FirebaseUser mUser = mAuth.getCurrentUser();

                            createUserDataBaseEntry(mUser, user);

                            Toast.makeText(SignUpActivity.this, "Account created",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            System.out.println(task.getException());
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignUpActivity.this, "Is a big rip",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateData(){
        email = ((EditText) this.findViewById(R.id.emailText)).getText().toString();
        password = ((EditText) this.findViewById(R.id.passwordText)).getText().toString();
        phoneNum = ((EditText) this.findViewById(R.id.phoneText)).getText().toString();
        recoveryNum = ((EditText) this.findViewById(R.id.recoverPhoneText)).getText().toString();
    }




}
