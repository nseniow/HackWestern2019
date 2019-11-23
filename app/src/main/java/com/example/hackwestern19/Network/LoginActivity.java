package com.example.hackwestern19.Network;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hackwestern19.FakeScreen.MainActivity;
import com.example.hackwestern19.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String email;
    private String password;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void updateData(){
        email = ((EditText) this.findViewById(R.id.eText)).getText().toString();
        password = ((EditText) this.findViewById(R.id.pwordText)).getText().toString();

    }

    private void signIn(){
        System.out.println("HEllo world");
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            System.out.println(mAuth.getUid());

                            mDatabase.child("Peeps").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    User user = dataSnapshot.getValue(User.class);

                                    LoginInformationStorer.storeLoginInformation(user.getEmail(), user.getPassword(), LoginActivity.this);
                                    User user2 = LoginInformationStorer.getUserFromFile(LoginActivity.this);

                                    Log.i("Username: ", user2.getEmail());
                                    Log.i("Password: ", user2.getPassword());



                                    if (user != null) {
                                        Intent main = new Intent(LoginActivity.this, MainActivity.class);
                                        LoginActivity.this.startActivity(main);
                                        Toast.makeText(LoginActivity.this, "Login succeeded",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });



                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Login failed",
                                    Toast.LENGTH_SHORT).show();
                            System.out.println(task.getException());
                        }
                    }
                });
    }

    public void onClick(View v){
        int id = v.getId();
            System.out.println("HHH");
            updateData();
            signIn();

    }
}
