package com.example.medicall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.NoCopySpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editTextLoginUsername, editTextPassword;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextPassword=findViewById(R.id.editTextPassword);
        editTextLoginUsername=findViewById(R.id.editTextLoginUsername);
        btn=findViewById(R.id.buttonLogin);
        tv=findViewById(R.id.textViewNewUser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=editTextLoginUsername.getText().toString();
                String password=editTextPassword.getText().toString();
                Database db=new Database(getApplicationContext(), "healthcare", null, 1);

                if(username.length()==0|| password.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Lütfen Tekrar Deneyiniz", Toast.LENGTH_SHORT).show();

                }
                else{
                    if (db.login(username,password)==1)
                    {
                        Toast.makeText(getApplicationContext(),"Giriş Başarılı", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                         editor.putString("username",username);
                         editor.apply();
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Geçersiz kullanıcı adı ve şifre", Toast.LENGTH_SHORT).show();
                    }

                }



            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

            }
        });
    }
}