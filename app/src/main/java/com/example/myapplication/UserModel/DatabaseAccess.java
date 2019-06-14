package com.example.myapplication.UserModel;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;;

import  com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.nio.Buffer;


public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private  static DatabaseAccess instance;
    Cursor c =null;

    private  DatabaseAccess(Context context){
        this.openHelper=new DatabaseOpenHelper(context);
    }

    public  static  DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance=new DatabaseAccess(context);

        }
        return instance;
    }
    public void open(){
        this.db=openHelper.getWritableDatabase();
    }

    public void close(){
        if(db!=null)
            this.db.close();
    }

    public String[] getWord(String name,String language,String category) throws InterruptedException {


        c=db.rawQuery("select * from "+category+ " where Windex ='"+name+"'",null);
        // StringBuffer buffer = new StringBuffer();
        int a=0;
        int b=0;
        String record []=new String[3];
        if(language.equals("english")){
            a=1; b=5;
        }
        else if(language.equals("korean")){
            a=0; b=4;
        }
        else if(language.equals("russian")){
            a=2; b=6;
        }
        while (c.moveToNext()){
            String spell = c.getString(a);
            String sound =c.getString(b);
            String picture =c.getString(3);
            // buffer.append(""+eng);
            //buffer.append()

            record[0]=spell;
            record[1]=sound;
            record[2]=picture;
            return record;
        }

        return record;
    }
    public int Count(String category){
        // c=db.rawQuery("select COUNT(*) from animal",null);

        int k =66;
        k=(int) DatabaseUtils.queryNumEntries(db,category);
        // k=c.
        return k;
    }

}

