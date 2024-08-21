package com.example.medicall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailsActivity extends AppCompatActivity {

    TextView tvpackagenamemed,tvTotalcost;
    EditText edDetailss;
    Button btnAddtocard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        tvpackagenamemed=findViewById(R.id.textViewBMDPackName);
        edDetailss=findViewById(R.id.editTextTextMultiLinemed);
        edDetailss.setKeyListener(null);
        tvTotalcost=findViewById(R.id.textViewtotalcostmed);
        btnAddtocard=findViewById(R.id.buttoncartmed);

        Intent intent=getIntent();
        tvpackagenamemed.setText(intent.getStringExtra("text1"));
        edDetailss.setText(intent.getStringExtra("text2"));
        tvTotalcost.setText("Tutar: "+ intent.getStringExtra("text3")+"₺");


        ImageButton backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
            }
        });
        btnAddtocard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username"," ").toString();
                String product=tvpackagenamemed.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());
                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if (db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(),"Ürün zaten eklenmiş",Toast.LENGTH_SHORT).show();

                }
                else{
                    db.addCart(username,product,price,"medicine");
                    Toast.makeText(getApplicationContext(),"Ürün sepete eklendi",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
                }

            }
        });











    }
}