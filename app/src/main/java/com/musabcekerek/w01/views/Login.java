package com.musabcekerek.w01.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.musabcekerek.w01.R;




public class Login extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://loginapp-c21a1-default-rtdb.firebaseio.com/");
    private EditText eMail;
    private EditText password;
    private Button loginBtn;
    private TextView registerBtn1;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eMail = findViewById(R.id.eMail);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn1 = findViewById(R.id.registerBtn1);
        mAuth = FirebaseAuth.getInstance();




        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String InputMail = eMail.getText().toString();
                String InputPassword = password.getText().toString();
                loginUser(InputMail,InputPassword);



            }
        });

        registerBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Register activity açma
                startActivity(new Intent(Login.this, Register.class));
            }
        });


    }

    private void loginUser(String mail, String password) {

        if(mail.isEmpty() || password.isEmpty()) {
            Toast.makeText(Login.this, "Mail veya şifre boş bırakılamaz", Toast.LENGTH_SHORT).show();
        }

        else {
            mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()) {
                        Toast.makeText(Login.this, "Giriş yapıldı.", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(Login.this, MainActivity.class));

                    }

                    else {
                        Toast.makeText(Login.this, "Giriş Yapılamadı." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }
            });



        }





    }


}