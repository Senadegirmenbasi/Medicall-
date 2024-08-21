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
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailsActivity extends AppCompatActivity {


    TextView tvpackagename,tvTotalcost;
    EditText edDetails;
    Button btnAddtocard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        tvpackagename=findViewById(R.id.textViewpackname);
        tvTotalcost=findViewById(R.id.textViewtotalcost);
        edDetails=findViewById(R.id.editTextTextMultiLine3);

        btnAddtocard=findViewById(R.id.buttoncartlabd);

        edDetails.setKeyListener(null);

        Intent intent=getIntent();
        tvpackagename.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalcost.setText("Tutar: "+ intent.getStringExtra("text3")+"₺");



        ImageButton backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
            }
        });

        btnAddtocard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username", " ").toString();
                String product=tvpackagename.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if (db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(),"Ürün zaten eklenmiş.", Toast.LENGTH_SHORT).show();
                }else {
                    db.addCart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(),"Sepete kayıt eklendi",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
                }
            }
        });

    }
}