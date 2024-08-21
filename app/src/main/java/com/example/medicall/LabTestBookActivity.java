package com.example.medicall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {
    EditText edname, edaddres,edcontact,edpinkod;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edname=findViewById(R.id.editTextLBTFullName);
        edaddres=findViewById(R.id.editTextLBTAdress);
        edcontact=findViewById(R.id.editTextLBTPhoneNumber);
        edpinkod=findViewById(R.id.editTextLBTpin);
        btnBooking=findViewById(R.id.buttonLBTBooking);

        Intent intent=getIntent();
        String[] price=intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=intent.getStringExtra("date");
        String time=intent.getStringExtra("time");


        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username"," ").toString();

                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                db.addOrder(username,edname.getText().toString(),edaddres.getText().toString(),edcontact.getText().toString(),Integer.parseInt(edpinkod.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"lab");
                db.removeCart(username,"lab");
                Toast.makeText(getApplicationContext(), "Randevunuz başarıyla oluşturuldu.", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LabTestBookActivity.this,HomeActivity.class));

            }
        });


    }
}