package com.example.medicall;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.HashMap;


public class DoctorDetailsActivity extends AppCompatActivity{

    TextView tv;
    String[][] doctor_details={};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv=findViewById(R.id.textViewDDTitle);

        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);

        if (title.compareTo("Aile Hekimleri")==0)
            doctor_details=doctor_details1;
        else

        if (title.compareTo("Diş Hekimleri")==0)
            doctor_details=doctor_details_2;
        else
        if (title.compareTo("Kardiyologlar")==0)
            doctor_details=doctor_details_3;
        else

        if (title.compareTo("Cerrahlar")==0)
            doctor_details=doctor_details_4;
        else

        if (title.compareTo("Diyetisyenler")==0)
            doctor_details=doctor_details_5;
        else
        if (title.compareTo("Göz Hekimleri")==0)
            doctor_details=doctor_details_6;
        else
            doctor_details=doctor_details_7;




        ImageButton backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });



        list = new ArrayList();
        for (int i=0;i<doctor_details.length;i++){
            item=new HashMap<String,String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5",doctor_details[i][4]+ "/-");
            list.add(item);




        }
        sa=new SimpleAdapter(this,list, R.layout.multi_lines, new String[]
                {"line1","line2","line3","line4","line5"},
                new int[]{R.id.textViewDoctorName,R.id.textViewHospitalAddress,R.id.textViewAge,
                        R.id.textViewPhoneNumber,R.id.textViewSalary}
        );
        ListView lst=findViewById(R.id.listviewDD);
        lst.setAdapter(sa);



        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it=new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);

                it.putExtra("text1", title);
                it.putExtra("text2", doctor_details[i][0]);
                it.putExtra("text3", doctor_details[i][1]);
                it.putExtra("text4", doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);

            }
        });



    }

    private String[][] doctor_details1=
            {
                    {"Dr. Mehmet Yılmaz", "Ankara Caddesi No:123", "45", "555-123-4567", "600"},
                    {"Dr. Ayşe Kaya", "İstanbul Bulvarı No:456", "38", "555-987-6543", "600"},
                    {"Dr. Ahmet Demir", "İzmir Sokak No:789", "50", "555-567-8901", "600"},
                    {"Dr. Fatma Çelik", "Bursa Caddesi No:321", "42", "555-234-5678", "600"},
                    {"Dr. Ali Toprak", "Antalya Bulvarı No:654", "47", "555-876-5432", "600"},
                    {"Dr. Zeynep Yıldırım", "dana Sokak No:987", "35", "555-345-6789", "600"}

            };
    private String[][] doctor_details_2 = {
            {"Dr. Emre Aksoy", "İzmir Bulvarı No:789", "40", "555-987-6543", "600"},
            {"Dr. Elif Korkmaz", "Ankara Caddesi No:321", "37", "555-567-8901", "600"},
            {"Dr. Burak Demir", "Bursa Sokak No:456", "48", "555-234-5678", "600"},
            {"Dr. Selin Yılmaz", "Antalya Bulvarı No:654", "43", "555-876-5432", "600"},
            {"Dr. Canan Çelik", "İstanbul Caddesi No:987", "39", "555-345-6789", "600"}
    };

    private String[][] doctor_details_3 = {
            {"Dr. Ali Kaplan", "İstanbul Bulvarı No:123", "42", "555-123-4567", "600"},
            {"Dr. Zeynep Kaya", "Ankara Sokak No:456", "36", "555-987-6543", "600"},
            {"Dr. Burak Yıldırım", "İzmir Caddesi No:789", "41", "555-567-8901", "600"},
            {"Dr. Elif Demir", "Antalya Bulvarı No:321", "49", "555-234-5678", "600"},
            {"Dr. Selim Çetin", "Bursa Sokak No:654", "44", "555-876-5432", "600"}
    };

    private String[][] doctor_details_4 = {
            {"Dr. Cemal Yılmaz", "İstanbul Sokak No:123", "47", "555-123-4567", "600"},
            {"Dr. Sevil Korkmaz", "Ankara Bulvarı No:456", "35", "555-987-6543", "600"},
            {"Dr. Ayşe Kaya", "İzmir Caddesi No:789", "50", "555-567-8901", "600"},
            {"Dr. Emir Demir", "Antalya Sokak No:321", "46", "555-234-5678", "600"},
            {"Dr. Esra Yıldız", "Bursa Bulvarı No:654", "34", "555-876-5432", "600"}
    };

    private String[][] doctor_details_5 = {
            {"Dr. Hasan Aksoy", "Ankara Bulvarı No:123", "41", "555-123-4567", "600"},
            {"Dr. Yasemin Kaya", "İstanbul Sokak No:456", "37", "555-987-6543", "600"},
            {"Dr. Murat Demir", "İzmir Bulvarı No:789", "45", "555-567-8901", "600"},
            {"Dr. Aslı Çelik", "Antalya Caddesi No:321", "39", "555-234-5678", "600"},
            {"Dr. Mehmet Toprak", "Bursa Sokak No:654", "47", "555-876-5432", "600"}
    };

    private String[][] doctor_details_6 = {
            {"Dr. Zeki Yılmaz", "İstanbul Bulvarı No:123", "48", "555-123-4567", "600"},
            {"Dr. Nazlı Korkmaz", "Ankara Caddesi No:456", "36", "555-987-6543", "600"},
            {"Dr. Melis Demir", "İzmir Sokak No:789", "43", "555-567-8901", "600"},
            {"Dr. Hakan Yıldırım", "Antalya Bulvarı No:321", "40", "555-234-5678", "600"},
            {"Dr. Zehra Çelik", "Bursa Caddesi No:654", "38", "555-876-5432", "600"}
    };

    private String[][] doctor_details_7 = {
            {"Dr. Zeki Yılmaz", "İstanbul Bulvarı No:123", "48", "555-123-4567", "600"},
            {"Dr. Nazlı Korkmaz", "Ankara Caddesi No:456", "36", "555-987-6543", "600"},
            {"Dr. Melis Demir", "İzmir Sokak No:789", "43", "555-567-8901", "600"},
            {"Dr. Hakan Yıldırım", "Antalya Bulvarı No:321", "40", "555-234-5678", "600"},
            {"Dr. Zehra Çelik", "Bursa Caddesi No:654", "38", "555-876-5432", "600"}
    };






}
