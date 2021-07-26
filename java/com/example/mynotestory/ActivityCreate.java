package com.example.mynotestory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class ActivityCreate extends AppCompatActivity {
    int from_where_I_am_coming = 0;
    private DBhelper mydb;
    EditText ntitle, ndate, ndescription;
    ImageButton imcreate, svcreate;
    Toolbar tool;
    DatePickerDialog.OnDateSetListener dateSetListener;

    int id_to_update = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        mydb = new DBhelper(this);
        NoteTry noteTry= new NoteTry();
        ArrayList<NoteTry> noteTries= mydb.getAllNotes();
        //ArrayList<NoteTry> allContacts = mDatabase.listContacts();
             tool= findViewById(R.id.toolbarcreate);
            ntitle= (EditText)findViewById(R.id.noteTitle);
            ndate=(EditText)findViewById(R.id.noteDate);
            ndescription=(EditText)findViewById(R.id.noteDescription);
            //nsave=(Button)tool.findViewById(R.id.buttonsave);
            imcreate=(ImageButton)tool.findViewById(R.id.backcreate);
            svcreate=(ImageButton)tool.findViewById(R.id.savecreate);
            Calendar calendar= Calendar.getInstance();
            final int year= calendar.get(Calendar.YEAR);
            final int month= calendar.get(Calendar.MONTH);
            final int day= calendar.get(Calendar.DAY_OF_MONTH);
            mydb= new DBhelper(ActivityCreate.this);
            imcreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                    Toast.makeText(getApplicationContext(),"Leaving page without saving",Toast.LENGTH_LONG).show();
                    startActivity(intent);
                    finish();
                }
            });
        ndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog= new DatePickerDialog(ActivityCreate.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month= month+1;
                        String date= day+"/"+month+"/"+year;
                        ndate.setText(date);

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
            svcreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String titlename= ntitle.getText().toString();


                    String datename= ndate.getText().toString();

                    String descriptionname= ndescription.getText().toString();

                    if(titlename.isEmpty())
                    {
                        Toast.makeText(getApplicationContext(),"Please Enter a title ",Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        NoteTry noteTRy= new NoteTry(titlename, datename,descriptionname);
                        //String ts= noteTRy.setNametitle(titlename);
                        noteTRy.setNametitle(titlename);
                        noteTRy.setDatename(datename);

                        noteTRy.setDescriptionname(descriptionname);
                        mydb.insertNote(noteTRy);
                        Log.d("dbNote","Data Inseerted");
                        Toast.makeText(getApplicationContext(),"Data saved",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();

                    }

                }
            });
       /* Bundle bundle=getIntent().getExtras();
        //Intent intent= getIntent();
        /*String stitle= intent.getStringExtra("name");
        String sdate= intent.getStringExtra("date");
        String sdes= intent.getStringExtra("description");
        String sctitle= bundle.getString("name");
       String scdate= bundle.getString("date");
       String scdes= bundle.getString("description");
        // Toast.makeText(ActivityShow.this,sdate,Toast.LENGTH_LONG).show();
        ntitle.setText(sctitle);
        ndate.setText(scdate);
        ndescription.setText(scdes);*/

        }


    }

