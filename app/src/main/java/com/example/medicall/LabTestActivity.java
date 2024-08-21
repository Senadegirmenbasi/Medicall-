package com.example.medicall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {



    private String[][] packages = {
            {"Tüm Vücut Tarama", "Tüm vücut sağlık taraması", "Tüm vücut tarama sonuçlarının değerlendirilmesi", "Tüm Vücut", "999"},
            {"Kan Testi Sonuçları", "Kan testi sonuçları", "Kan örneği analizinin sonuçları", "Kan", "299"},
            {"İdrar Testi Sonuçları", "İdrar testi sonuçları", "İdrar örneği analizinin sonuçları", "İdrar", "199"},
            {"Karaciğer Fonksiyon Testi", "Karaciğer fonksiyon testi", "Karaciğer fonksiyonlarını değerlendiren test sonuçları", "Karaciğer", "399"},
            {"Tiroid Testi Sonuçları", "Tiroid testi sonuçları", "Tiroid hormonlarının değerlendirilmesi", "Tiroid", "199"},
            {"Kolesterol Testi Sonuçları", "Kolesterol testi sonuçları", "Kolesterol seviyelerinin değerlendirilmesi", "Kolesterol", "149"}
    };


    private String[] packageDetails = {
            "Genel sağlık durumunu değerlendirmek için kapsamlı bir test paketi", // Tüm Vücut Tarama
            "Kan hücreleri ve kimyasal bileşenlerinin değerlendirilmesi", // Kan Testi Sonuçları
            "İdrar örneğinin analiz edilmesiyle böbrek ve idrar yolu sağlığını değerlendirme", // İdrar Testi Sonuçları
            "Karaciğer fonksiyonlarını ölçümlemek için bir dizi test", // Karaciğer Fonksiyon Testi
            "Tiroid hormonları ve işlevi hakkında bilgi sağlamak için kullanılır", // Tiroid Testi Sonuçları
            "Kandaki kolesterol seviyelerini değerlendirerek kardiyovasküler sağlık hakkında bilgi verir" // Kolesterol Testi Sonuçları
    };


    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGotoCart;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);



        btnGotoCart=findViewById(R.id.buttoncart);
        listView=findViewById(R.id.listviewT);

        btnGotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
            }
        });

        ImageButton backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this,HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][4]+ "₺/-");
            list.add(item);


            sa=new SimpleAdapter(this,list, R.layout.multi_lines2,
                    new String[]
                    {"line1","line2","line3","line4","line5"},
                    new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
          listView.setAdapter(sa);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent it=new Intent(LabTestActivity.this, LabTestDetailsActivity.class);
                    it.putExtra("text1", packages[i][0]);
                    it.putExtra("text2", packageDetails[i]);
                    it.putExtra("text3", packages[i][4]);
                    startActivity(it);

                }
            });


        }
}}