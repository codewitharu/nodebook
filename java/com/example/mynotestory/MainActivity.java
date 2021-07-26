package com.example.mynotestory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton fb;
    Toolbar tool;
    ImageButton btback;
    private RecyclerView obj;
    DBhelper mydb;
   // RemindDb remindDb;
   CustomAdapter adapter;
    private List<NoteTry> noteTRyArrayList;
    private ArrayAdapter<String> arrayAdapter;
    //private ArrayList<NoteTry> noteTRyArrayList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fb= (FloatingActionButton) findViewById(R.id.floatingActionButton3);
        mydb = new DBhelper(MainActivity.this);
        //remindDb= new RemindDb(MainActivity.this);
        obj=(RecyclerView) findViewById(R.id.recyclerView);
        tool=(Toolbar)findViewById(R.id.maintool);
        btback=(ImageButton)tool.findViewById(R.id.back);
        setSupportActionBar(tool);
        getSupportActionBar().setTitle(null);


        obj.setHasFixedSize(true);
        obj.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<NoteTry> noteTRyArrayList= mydb.getAllNotes();
        Log.d("dbNote","get all created");
        for(NoteTry noteTry: noteTRyArrayList) {
            Log.d("dbNote", "\nId: " + noteTry.getId() + "\n" +
                    "Title: " + noteTry.getNametitle() );
        }

        if (noteTRyArrayList.size() > 0) {
           // Log.d("dbNote","Array"+noteTRyArrayList);
            obj.setVisibility(View.VISIBLE);
            CustomAdapter recyclerViewAdapter = new CustomAdapter(MainActivity.this, noteTRyArrayList);
            obj.setAdapter(recyclerViewAdapter);
        }
        else {
            obj.setVisibility(View.GONE);
            Toast.makeText(this, "There is no contact in the database. Start adding now", Toast.LENGTH_LONG).show();
        }


       // Log.d("dbNote","Added data"+ar1.getId()+ar2.getId());




        //obj.setAdapter(arrayAdapter);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),ActivityCreate.class);
                startActivity(intent);
                finish();
            }
        });

        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });



    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater= getMenuInflater();
        getMenuInflater().inflate(R.menu.my_menu,menu);
        MenuItem searchintem= menu.findItem(R.id.search);
        SearchView searchView= (SearchView)searchintem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               searchView.clearFocus();
               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
              // recyclerView.getFilter().filter(newText);

              adapter.getFilter().filter(newText);
              adapter.getItemCount();
               Toast.makeText(getApplicationContext(),"Please Enter a title ",Toast.LENGTH_LONG).show();
               return true;
           }
       });


        return super.onCreateOptionsMenu(menu);
    }

}
