package com.example.mynotestory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DBhelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 5;
    public static final String DATABASE_NAME= "Mynote";
    public static final String NOTE_TABLE_NAME="Keepnotes";
    public static final String NOTE_COLUMN_ID="id";
    public static final String NOTE_COLUMN_NAME="name";
    public static final String NOTE_COLUMN_DATE="date";
    public static final String NOTE_COLUMN_DESCRIPTION="description";
    public static final String DB_COLUMN_ID= "id";
    public static final String DB_TABLE_NAME= "remindertable";
    public static final String DB_COLUMN_NAME= "name";
    public static final String DB_COLUMN_DATE= "date";
    public static final String DB_COLUMN_TIME= "time";
    public static final String DB_COLUMN_repeat= "repeat";
    public static final String DB_COLUMN_nor= "no_repeat";
    public static final String DB_COLUMN_type= "rtype";
    public static final String DB_COLUMN_key= "akey";

    private HashMap hp;
   // NoteTry noteTRy;

    public DBhelper(Context context) {
        super(context, DATABASE_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table keepnotes"+"(id integer primary key, name text, date text,description text)");
        db.execSQL("create table remindertable"+"(id integer primary key, name text, date text, time integer, repeat boolean, no_repeat integer, rtype text,akey boolean)");
        Log.d("dbNote","Remind table created ");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NOTE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME);
        onCreate(db);
        onCreate(db);


    }

    public void insertNote( NoteTry noteTRy)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(NOTE_COLUMN_NAME,noteTRy.getNametitle());
       contentValues.put(NOTE_COLUMN_DATE,noteTRy.getDatename());
        contentValues.put(NOTE_COLUMN_DESCRIPTION,noteTRy.getDescriptionname());
        db.insert(NOTE_TABLE_NAME,null,contentValues);
        Log.d("dbNote", "Successfully inserted");
        //Log.d("dbNote","The table"+NOTE_TABLE_NAME);
        db.close();
    }
    public ArrayList<NoteTry> getAllNotes() {
       // Log.d("dbNote", "Successfully inserted to Notetry");
        String selectnote = "SELECT * FROM " + NOTE_TABLE_NAME;
      //  Log.d("dbNote", "Successfully inserted to Notetry query");
        ArrayList<NoteTry> notelist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectnote, null);
        //Log.d("dbNote", "Successfully inserted to Notetry cursor");
        //cursor.moveToFirst();
       // Log.d("dbNote", "Successfully inserted to Notetry cursor first");
            if(cursor.moveToFirst())
            {
               // Log.d("dbNote", "Successfully inserted to Notetry if");
                do {
                 //   Log.d("dbNote", "Successfully inserted to Notetry loop");
               /* NoteTry noteTry= new NoteTry();
                noteTry.setId(Integer.parseInt(cursor.getString(0)));
                noteTry.setNametitle(cursor.getString(1));
                noteTry.setDatename(cursor.getString(2));
                noteTry.setDescriptionname(cursor.getString(3));
                notelist.add(noteTry);*/
                    int id = Integer.parseInt(cursor.getString(0));
                    String nametitle = cursor.getString(1);
                    String notedate = cursor.getString(2);
                    String notedes= cursor.getString(3);
                    notelist.add(new NoteTry(id,nametitle,notedate,notedes));

                } while (cursor.moveToNext());
            }




        cursor.close();
        return notelist;

    }
    public Cursor getData(int id)
    {
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor res= db.rawQuery("select *from keepnotes where id="+id+"",null);
        return res;
    }
    public int numberofRows()
    {
        SQLiteDatabase db= this.getReadableDatabase();
        int numRows= (int) DatabaseUtils.queryNumEntries(db, NOTE_TABLE_NAME);
        return numRows;
    }
    public void updateNote(NoteTry noteTRy)
    {
        Log.d("dbNote","Updated ");
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NOTE_COLUMN_NAME,noteTRy.getNametitle());
        contentValues.put(NOTE_COLUMN_DATE,noteTRy.getDatename());
        contentValues.put(NOTE_COLUMN_DESCRIPTION,noteTRy.getDescriptionname());
        db.update(NOTE_TABLE_NAME,contentValues,"id= ?",new String[]{Integer.toString(noteTRy.getId())});
        db.close();

    }
    public  void deleteNote(Integer id)
    {
        Log.d("dbNote","Deleted ");
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NOTE_TABLE_NAME, NOTE_COLUMN_ID +"=?", new String[]{String.valueOf(id)});
        db.close();
    }



    public int getCount(){
        String query = "SELECT  * FROM " + NOTE_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();

    }
    public void insertRemind(RemindNote remindNote)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        //contentValues.put("name",Dataclass.NOTE_COLUMN_NAME);
        contentValues.put(DB_COLUMN_NAME,remindNote.getRtitle());
        //contentValues.put(NOTE_COLUMN_NAME,"A");
        // contentValues.put(NOTE_COLUMN_DATE,"5");
        //contentValues.put(NOTE_COLUMN_DESCRIPTION,"Asd");
        contentValues.put(DB_COLUMN_DATE,remindNote.getRdate());
        contentValues.put(DB_COLUMN_TIME,remindNote.getRtime());
        contentValues.put(DB_COLUMN_repeat,remindNote.getrRepeat());
        contentValues.put(DB_COLUMN_nor,remindNote.getrRepeatNo());
        contentValues.put(DB_COLUMN_type,remindNote.getrRepeatType());
        contentValues.put(DB_COLUMN_key,remindNote.getrActive());
        db.insert(DB_TABLE_NAME,null,contentValues);
        Log.d("dbNote", "Successfully inserted");
        //Log.d("dbNote","The table"+NOTE_TABLE_NAME);
        db.close();
    }
    public ArrayList<RemindNote> getAllRemind() {
        // Log.d("dbNote", "Successfully inserted to Notetry");
        String selectnote = "SELECT * FROM " + DB_TABLE_NAME;
        //  Log.d("dbNote", "Successfully inserted to Notetry query");
        ArrayList<RemindNote> rnotelist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectnote, null);
        //Log.d("dbNote", "Successfully inserted to Notetry cursor");
        //cursor.moveToFirst();
        // Log.d("dbNote", "Successfully inserted to Notetry cursor first");
        if(cursor.moveToFirst())
        {
            // Log.d("dbNote", "Successfully inserted to Notetry if");
            do {
                //   Log.d("dbNote", "Successfully inserted to Notetry loop");
               /* NoteTry noteTry= new NoteTry();
                noteTry.setId(Integer.parseInt(cursor.getString(0)));
                noteTry.setNametitle(cursor.getString(1));
                noteTry.setDatename(cursor.getString(2));
                noteTry.setDescriptionname(cursor.getString(3));
                notelist.add(noteTry);*/
                int id = Integer.parseInt(cursor.getString(0));
                String retitle = cursor.getString(1);
                String redate = cursor.getString(2);
                String retime= cursor.getString(3);
                String rerepeat= cursor.getString(4);
                String renorepeat= cursor.getString(5);
                String retype= cursor.getString(6);
                String rekey=cursor.getString(7);
                //rnotelist.add(new NoteTry(id,nametitle,notedate,notedes));
                rnotelist.add(new RemindNote(id,retitle,redate,retime,rerepeat,renorepeat,retype,rekey));

            } while (cursor.moveToNext());
        }
        cursor.close();
        return rnotelist;


    }
    public Cursor getDataremind(int id)
    {
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor res= db.rawQuery("select *from remindertable where id="+id+"",null);
        return res;
    }
    public int RenumberofRows()
    {
        SQLiteDatabase db= this.getReadableDatabase();
        int numRows= (int) DatabaseUtils.queryNumEntries(db, DB_TABLE_NAME);
        return numRows;
    }
    public void updateRemind(RemindNote remindNote)
    {
        Log.d("dbNote","Updated ");
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DB_COLUMN_NAME,remindNote.getRtitle());
        //contentValues.put(NOTE_COLUMN_NAME,"A");
        // contentValues.put(NOTE_COLUMN_DATE,"5");
        //contentValues.put(NOTE_COLUMN_DESCRIPTION,"Asd");
        contentValues.put(DB_COLUMN_DATE,remindNote.getRdate());
        contentValues.put(DB_COLUMN_TIME,remindNote.getRtime());
        contentValues.put(DB_COLUMN_repeat,remindNote.getrRepeat());
        contentValues.put(DB_COLUMN_nor,remindNote.getrRepeatNo());
        contentValues.put(DB_COLUMN_type,remindNote.getrRepeatType());
        contentValues.put(DB_COLUMN_key,remindNote.getrActive());
        db.update(DB_TABLE_NAME,contentValues,"id= ?",new String[]{Integer.toString(remindNote.getrID())});
        db.close();

    }
    public  void deleteRemind(Integer id)
    {
        Log.d("dbNote","Deleted ");
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE_NAME, DB_COLUMN_ID +"=?", new String[]{String.valueOf(id)});
        db.close();
    }



    public int getReCount(){
        String query = "SELECT  * FROM " + DB_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();

    }
}
