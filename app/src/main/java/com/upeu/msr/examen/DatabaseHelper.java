package com.upeu.msr.examen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;

/**
 * Created by Redes on 27/04/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(nombre text PRIMARY KEY, email text, password text)");
        db.execSQL("Create table asistencia(nombre text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists asistencia");
    }
    //INSERTA EN LA BASE DE DATOS
    public boolean insert(String nombre, String email, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre",nombre);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long ins = db.insert("user",null, contentValues);
        if (ins==-1){
            return false;
        }
        else {
            return true;
        }
    }

    //INSERTAR ASISTENCIA EN LA BASE DE DATOS
    public boolean insertAsistencia(String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        long in = db.insert("asistencia",null, contentValues);
        if (in==-1){
            return false;
        }else{
            return true;
        }

    }

    //VALIDACION DE EMAIL

    public Boolean chkeemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email= ? ", new String[]{email});
        if(cursor.getCount()>0){
            return false;
        }else {
            return true;
        }
    }

    //USER AND PASSWORD

    public Boolean emailpassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=? and password=?", new String[]{email, password});
        if (cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

}
