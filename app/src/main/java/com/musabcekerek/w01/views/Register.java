package com.musabcekerek.w01.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.musabcekerek.w01.R;

import java.nio.charset.StandardCharsets;

public class Register extends AppCompatActivity {

    // Database e ulaşmak için ref create

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://loginapp-c21a1-default-rtdb.firebaseio.com/");
    private TextView loginBtn1;
    private EditText firstPassord;
    private EditText secondPassword;
    private EditText eMail;
    private EditText userName;
    private Button registerBtn;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.userName);
        eMail = findViewById(R.id.eMail);
        firstPassord = findViewById(R.id.firstPassword);
        secondPassword = findViewById(R.id.secondPassword);
        registerBtn = findViewById(R.id.registerBtn);
        loginBtn1 = findViewById(R.id.loginBtn1);
        mAuth = FirebaseAuth.getInstance();


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String InputName = userName.getText().toString();
                String InputMail = eMail.getText().toString();
                String InputPassword1 = firstPassord.getText().toString();
                String InputPassword2 = secondPassword.getText().toString();

                createUser(InputName,InputMail,InputPassword1,InputPassword2);




            }
        });

        loginBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                startActivity(new Intent(Register.this, Login.class));
            }
        });



    }


    private void createUser(String username,String mail, String password, String secondpassword) {


        if(username.isEmpty() || mail.isEmpty() || password.isEmpty() || secondpassword.isEmpty()) {
            Toast.makeText(Register.this, "Lütfen boşlukları doldurunuz!", Toast.LENGTH_SHORT).show();


        }

        else if (!password.equals(secondpassword)) {
            Toast.makeText(Register.this, "Şifre birbirinden farklı olamaz!", Toast.LENGTH_SHORT).show();
        }


        else{
            databaseReference.child("kullanıcılar").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {


                    if(snapshot.hasChild(username)) {
                        Toast.makeText(Register.this, "Bu kullanıcı adı daha önceden kullanılmış.", Toast.LENGTH_SHORT).show();
                    }

                    else{

                        mAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    // Dataları Database e gönderme
                                    // kullanıcı adı her kullanıcı için unique

                                    databaseReference.child("kullanıcılar").child(username).child("e-mail").setValue(mail);
                                    databaseReference.child("kullanıcılar").child(username).child("şifre").setValue(password);
                                    Toast.makeText(Register.this, "Kayıt başarıyla oluşturuldu.", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Register.this, Login.class));

                                }

                                else {
                                    Toast.makeText(Register.this, "Kayıt oluşturulamadı." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }

                            }
                        });


                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }






    }
}