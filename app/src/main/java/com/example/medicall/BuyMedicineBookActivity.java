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

public class BuyMedicineBookActivity extends AppCompatActivity {
    EditText edname, edaddres,edcontact,edpinkod;
    Button btnBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname=findViewById(R.id.editTextBMBFullName);
        edaddres=findViewById(R.id.editTextBMBAdress);
        edcontact=findViewById(R.id.editTextBMBPhoneNumber);
        edpinkod=findViewById(R.id.editTextBMBpin);
        btnBooking=findViewById(R.id.buttonBMBBooking);

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
                db.addOrder(username,edname.getText().toString(),edaddres.getText().toString(),edcontact.getText().toString(),Integer.parseInt(edpinkod.getText().toString()),date.toString()," ",Float.parseFloat(price[1].toString()),"medicine");
                db.removeCart(username,"medicine");
                Toast.makeText(getApplicationContext(), "Randevunuz başarıyla oluşturuldu.", Toast.LENGTH_LONG).show();
                startActivity(new Intent(BuyMedicineBookActivity.this,HomeActivity.class));

            }
        });



    }
}