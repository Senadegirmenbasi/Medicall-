package com.example.medicall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] packages = {
            {"Parol", "Ağrı kesici", "Baş ağrısı için etkili", "10 mg", "10"}, // Parol
            {"Aspirin", "Ağrı kesici", "Ateş düşürücü özellik", "5 mg", "5"}, // Aspirin
            {"Voltaren", "Ağrı kesici", "Romatizma ve eklem ağrılarına etkili", "15 mg", "15"}, // Voltaren
            {"Panadol", "Ağrı kesici", "Hafif ağrılar için etkili", "8 mg", "8"}, // Panadol
            {"Nurofen", "Ağrı kesici", "Baş ağrısı ve migren için etkili", "12 mg", "12"}, // Nurofen
            {"Advil", "Ağrı kesici", "Kas ağrılarına etkili", "9 mg", "9"}, // Advil
            {"Tylenol", "Ağrı kesici", "Ateş düşürücü ve ağrı kesici", "7 mg", "7"}, // Tylenol
            {"Claritin", "Alerji ilacı", "Alerjik reaksiyonlarda kullanılır", "20 mg", "20"}, // Claritin
            {"Zyrtec", "Alerji ilacı", "Kaşıntı ve hapşırma için etkili", "18 mg", "18"}, // Zyrtec
            {"Benadryl", "Alerji ilacı", "Uyku yapıcı etkisi vardır", "13 mg", "13"}, // Benadryl
            {"Sancotec", "Mide ilacı", "Mide rahatsızlıkları için etkili", "25 mg", "25"}, // Sancotec
            {"Buscopan", "Mide ilacı", "Sindirim sistemi spazmlarını hafifletir", "17 mg", "17"}, // Buscopan
            {"Daleron", "Ağrı kesici", "Baş ve kas ağrılarına karşı etkilidir", "6 mg", "6"}, // Daleron
            {"Neurodex", "Sinir ilacı", "Sinir sistemi rahatsızlıklarında kullanılır", "22 mg", "22"}, // Neurodex
            {"Ranitidine", "Mide ilacı", "Mide asidini azaltır", "14 mg", "14"}, // Ranitidine
            {"Cipralex", "Antidepresan", "Depresyon tedavisinde kullanılır", "30 mg", "30"}, // Cipralex
            {"Tramadol", "Ağrı kesici", "Şiddetli ağrılarda kullanılır", "28 mg", "28"}, // Tramadol
            {"Lustral", "Antidepresan", "Obsesif-kompulsif bozukluk için etkili", "27 mg", "27"}, // Lustral
            {"Dexamethasone", "Steroid ilaç", "İltihaplanma ve alerjik reaksiyonları tedavi eder", "23 mg", "23"}, // Dexamethasone
            {"Prednol", "Steroid ilaç", "Bağışıklık sistemi rahatsızlıklarında kullanılır", "21 mg", "21"}, // Prednol
            {"Amoxicillin", "Antibiyotik", "Enfeksiyon tedavisinde kullanılır", "11 mg", "11"}, // Amoxicillin
            {"Cefuroxime", "Antibiyotik", "Bakteriyel enfeksiyon tedavisi için kullanılır", "19 mg", "19"}, // Cefuroxime
            {"Clarithromycin", "Antibiyotik", "Solunum yolu enfeksiyonlarında etkilidir", "24 mg", "24"}, // Clarithromycin
            {"Fluconazole", "Antifungal ilaç", "Mantar enfeksiyonlarına karşı etkilidir", "16 mg", "16"} // Fluconazole
    };


    private String[] packages_details = {
            "Aspirin, kan inceltici ve ağrı kesici ilaç.",
            "Voltaren, ağrı kesici ve iltihap önleyici ilaç.",
            "Panadol, ağrı kesici ve ateş düşürücü ilaç.",
            "Nurofen, ağrı kesici ve ateş düşürücü ilaç.",
            "Advil, ağrı kesici ve ateş düşürücü ilaç.",
            "Tylenol, ağrı kesici ve ateş düşürücü ilaç.",
            "Claritin, alerji semptomlarını hafifleten ilaç.",
            "Zyrtec, alerji semptomlarını hafifleten ilaç.",
            "Benadryl, alerji semptomlarını hafifleten ilaç.",
            "Sancotec, kas spazmlarını ve ağrılarını hafifleten ilaç.",
            "Buscopan, kas spazmlarını hafifleten ilaç.",
            "Daleron, ağrı kesici ilaç.",
            "Neurodex, nörolojik sorunları tedavi eden ilaç.",
            "Ranitidine, mide asidini azaltan ilaç.",
            "Cipralex, antidepresan ilaç.",
            "Tramadol, şiddetli ağrıları kesen ilaç.",
            "Lustral, antidepresan ilaç.",
            "Dexamethasone, iltihap önleyici ve bağışıklık sistemini baskılayan ilaç.",
            "Prednol, iltihap önleyici ilaç.",
            "Amoxicillin, antibiyotik ilaç.",
            "Cefuroxime, antibiyotik ilaç.",
            "Clarithromycin, antibiyotik ilaç.",
            "Fluconazole, mantar enfeksiyonlarını tedavi eden ilaç."
    };

    HashMap<String ,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnMed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        lst=findViewById(R.id.listviewBMB);
        btnMed=findViewById(R.id.buttonmedicine);
        ImageButton backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });
        btnMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));

            }
        });
        list=new ArrayList();
        for (int i=0;i<packages.length;i++){
            item= new HashMap<String,String >();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", packages[i][4]+"₺");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines4,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",packages_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });

    }
}