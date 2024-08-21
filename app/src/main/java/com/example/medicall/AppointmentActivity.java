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

import java.util.ArrayList;
import java.util.HashMap;

public class AppointmentActivity extends AppCompatActivity {

    private ArrayList<HashMap<String,String>> appointmentList;
    private SimpleAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        listView = findViewById(R.id.listViewappo);
        ImageButton backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppointmentActivity.this, HomeActivity.class));
            }
        });

        // Fetch appointment data
        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", " ");
        ArrayList<String> allOrders = db.getOrderData(username);

// Prepare data for the adapter
        appointmentList = new ArrayList<>();
        for (String data : allOrders) {
            String[] strData = data.split("₺");
            if (strData[7].equals("appointment")) { // Filter for "appointment" type orders
                HashMap<String,String> item = new HashMap<>();
                item.put("line1", strData[0]);
                item.put("line2", strData[1]);
                item.put("line3", strData[2]);
                item.put("line4", strData[4] + " " + strData[5]);
                item.put("line5", strData[7]);
                appointmentList.add(item);
            }
        }

        // Set up the adapter
        adapter = new SimpleAdapter(this, appointmentList,
                R.layout.multi_lines3,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(adapter);
    }
}
