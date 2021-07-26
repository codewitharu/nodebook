package com.example.mynotestory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityShow extends AppCompatActivity {
    TextView t1,t2,t3;
     private int sid;
    Toolbar toolbar;
    ImageButton backim;
    private DBhelper dBhelper;
    public String stitle,sdate,sdes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        t1=(TextView) findViewById(R.id.showtitle);
        t2=(TextView)findViewById(R.id.showdate);
        t3=(TextView)findViewById(R.id.showdescription);
        toolbar = findViewById(R.id.toolbar);
        backim= (ImageButton)toolbar.findViewById(R.id.back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        dBhelper = new DBhelper(ActivityShow.this);
        Bundle bundle=getIntent().getExtras();
        //Intent intent= getIntent();
        /*String stitle= intent.getStringExtra("name");
        String sdate= intent.getStringExtra("date");
        String sdes= intent.getStringExtra("description");*/
        sid= bundle.getInt("id");
         stitle= bundle.getString("name");
         sdate= bundle.getString("date");
         sdes= bundle.getString("description");
       // Toast.makeText(ActivityShow.this,sdate,Toast.LENGTH_LONG).show();
        t1.setText(stitle);
        t2.setText(sdate);
        t3.setText(sdes);
        t1.setTextIsSelectable(true);
        //t2.setTextIsSelectable(true);
        t3.setTextIsSelectable(true);
        backim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inback= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(inback);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.show_menu,menu);
        return true;
    }


    @Override
   public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.update:
                Intent intent= new Intent(getApplicationContext(),UpdateActvity.class);
                intent.putExtra("id",sid);
                intent.putExtra("name",stitle);
                intent.putExtra("date",sdate);
                intent.putExtra("description",sdes);
                startActivity(intent);
                finish();
                break;
            case R.id.deleteitem:

                dBhelper.deleteNote(sid);

                Toast.makeText(this,"Item is Deleted ",Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent1);
                finish();
                break;


        }


        return super.onOptionsItemSelected(item);
    }

}