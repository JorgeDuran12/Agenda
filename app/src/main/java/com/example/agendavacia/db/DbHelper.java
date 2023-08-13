package com.example.agendavacia.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper  {

    private  static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "agenda.db";
      static final  String TABLE_CONTACTO = "t_contacto";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase SQLiteDatabase ) {
        SQLiteDatabase.execSQL("CREATE TABLE " + TABLE_CONTACTO + "("+

                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT  NOT NULL," +
                "telefono  TEXT NOT NULL," +
                "correro_electronico Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase SQLiteDatabase , int oldVersion , int newVersion) {
        SQLiteDatabase.execSQL(" DROP TABLE "+ TABLE_CONTACTO);
        onCreate(SQLiteDatabase);


    }
}

