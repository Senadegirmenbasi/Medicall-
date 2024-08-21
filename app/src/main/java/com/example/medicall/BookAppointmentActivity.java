package com.example.medicall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {

    EditText ed1, ed2,ed3,ed4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton,btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        ImageButton backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this,FindDoctorActivity.class));
            }
        });


        tv=findViewById(R.id.textViewAppTitle);
        ed1=findViewById(R.id.editTextLBTFullName);
        ed2=findViewById(R.id.editTextLBTAdress);
        ed3=findViewById(R.id.editTextLBTPhoneNumber);
        ed4=findViewById(R.id.editTextLBTpin);
        dateButton=findViewById(R.id.buttonAppDate);
        timeButton=findViewById(R.id.buttonAppTime);
        btnBook=findViewById(R.id.buttonAppointment);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        Intent it=getIntent();
        String title=it.getStringExtra("text1");
        String fullname=it.getStringExtra("text2");
        String adress=it.getStringExtra("text3");
        String contact=it.getStringExtra("text4");
        String ucret=it.getStringExtra("text5");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(adress);
        ed3.setText(contact);
        ed4.setText( ucret+"/-");


        //datepicker
        initDatePicker();
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });


        //timepicker
        initTimePicker();
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username"," ").toString();

                if (db.checkAppointmentExists(username,title+" => "+ fullname,adress,contact,dateButton.getText().toString(),timeButton.getText().toString())==1)
                {
                    Toast.makeText(getApplicationContext(),"Randevunuz zaten oluşturulmuş.",Toast.LENGTH_LONG).show();

                }
                else{
                    db.addOrder(username,title+" => "+fullname,adress,contact,0,dateButton.getText().toString(),timeButton.getText().toString(),Float.parseFloat(ucret),"appointment");
                    Toast.makeText(getApplicationContext(),"Randevunuz başarıyla oluşturulmuştur.",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(BookAppointmentActivity.this,HomeActivity.class));

                }
            }
        });

 }
 private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                i1=i1+1;
                dateButton.setText(i2+"/"+i1+"/"+i);

            }
        };
     Calendar cal=Calendar.getInstance();
     int year= cal.get(Calendar.YEAR);
     int month=cal.get(Calendar.MONTH);
     int day=cal.get(Calendar.DAY_OF_MONTH);

     int style= AlertDialog.THEME_HOLO_DARK;
     datePickerDialog=new DatePickerDialog(this,dateSetListener,year,month,day);
     datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
 }
 private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int i, int i1) {
                timeButton.setText(i+":"+i1);


            }
        };

        Calendar cal=Calendar.getInstance();
        int hrs=cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);
     int style= AlertDialog.THEME_HOLO_DARK;
timePickerDialog=new TimePickerDialog(this, style, timeSetListener, hrs, mins,true);


 }


}