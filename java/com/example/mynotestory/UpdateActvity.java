package com.example.mynotestory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class UpdateActvity extends AppCompatActivity {
    TextView uptitle, update1, updes;
    ImageButton upbt,cnbt;
    private DBhelper mydbhelper;
    Toolbar toolbar1;
    DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_actvity);
        uptitle = (TextView) findViewById(R.id.noteTitleup);
        update1 = (TextView) findViewById(R.id.noteDateup);
        updes = (TextView) findViewById(R.id.noteDescriptionup);
        toolbar1=findViewById(R.id.uptoolbar);
        upbt= (ImageButton) toolbar1.findViewById(R.id.buttonsaveup);
        cnbt=(ImageButton)toolbar1.findViewById(R.id.backupdate);
        mydbhelper = new DBhelper(UpdateActvity.this);
        Bundle bundle = getIntent().getExtras();
        //Intent intent= getIntent();
       /*String stitle= intent.getStringExtra("name");
        String sdate= intent.getStringExtra("date");
        String sdes= intent.getStringExtra("description");*/
        String sctitle = bundle.getString("name");
        String scdate = bundle.getString("date");
        String scdes = bundle.getString("description");
        int upid = bundle.getInt("id");
        // Toast.makeText(ActivityShow.this,sdate,Toast.LENGTH_LONG).show();
        uptitle.setText(sctitle);
        update1.setText(scdate);
        updes.setText(scdes);
        cnbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                Toast.makeText(getApplicationContext(),"Leaving page without saving",Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();
            }
        });
        Calendar calendar= Calendar.getInstance();
        final int year= calendar.get(Calendar.YEAR);
        final int month= calendar.get(Calendar.MONTH);
        final int day= calendar.get(Calendar.DAY_OF_MONTH);
        update1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog= new DatePickerDialog(UpdateActvity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month= month+1;
                        String date= day+"/"+month+"/"+year;
                        update1.setText(date);

                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        upbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upt= uptitle.getText().toString();
                String upd= update1.getText().toString();
                String upds= updes.getText().toString();
                NoteTry upnotetry= new NoteTry(upid,upt,upd,upds);
                //String ts= noteTRy.setNametitle(titlename);
                upnotetry.setId(upid);
                upnotetry.setNametitle(upt);
                upnotetry.setDatename(upd);
                upnotetry.setDescriptionname(upds);
                mydbhelper.updateNote(upnotetry);
                Toast.makeText(getApplicationContext(),"Updated ",Toast.LENGTH_SHORT).show();
                Intent innt= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(innt);
                finish();
            }
        });
    }
}
