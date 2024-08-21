package com.example.medicall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity {


    private String [][] order_details={};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        lst=findViewById(R.id.listViewOD);
        ImageButton backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this,HomeActivity.class));
            }
        });


        Database db=new Database(getApplicationContext(),"healthcare",null,1);

        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username"," ").toString();
        ArrayList dbData=db.getOrderData(username);

        order_details = new String[dbData.size()][];
        int count = 0;

        for (int i = 0; i < dbData.size(); i++) {
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("₺"));

            if (!strData[7].equals("appointment")) {
                order_details[count] = new String[5];
                order_details[count][0] = strData[0];
                order_details[count][1] = strData[1];

                if (strData[7].compareTo("medicine") == 0) {
                    order_details[count][3] = strData[4];
                } else {
                    order_details[count][3] = strData[4] + " " + strData[5];
                }
                order_details[count][2] = strData[6];
                order_details[count][4] = strData[7];

                count++;
            }
        }

        list = new ArrayList();
        for (int i = 0; i < count; i++) {
            item = new HashMap<String,String >();
            item.put("line1", order_details[i][0]);
            item.put("line2", order_details[i][1]);
            item.put("line3", order_details[i][2]);
            item.put("line4", order_details[i][3]);
            item.put("line5", order_details[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines3,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);





    }
}