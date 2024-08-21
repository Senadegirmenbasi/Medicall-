package com.example.medicall;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class FindDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        // Geri butonuna tıklama işlemi
        ImageButton backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FindDoctorActivity.this, HomeActivity.class));
            }
        });

        // Kartlara tıklama işlemi
        CardView family=findViewById(R.id.cardFamilyPhysician);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Aile Hekimleri");
                startActivity(it);

            }
        });
        CardView dentist=findViewById(R.id.cardDentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Diş Hekimleri");
                startActivity(it);

            }
        });
        CardView cardiolog=findViewById(R.id.cardcardiologists);
        cardiolog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Kardiyologlar");
                startActivity(it);

            }
        });
        CardView surgeon=findViewById(R.id.cardsurgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Cerrahlar");
                startActivity(it);

            }
        });
        CardView dietician=findViewById(R.id.carddietician);
        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Diyetisyenler");
                startActivity(it);

            }
        });CardView eye=findViewById(R.id.cardeye);
        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Göz Hekimleri");
                startActivity(it);

            }
        });

    }

}
