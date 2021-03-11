package com.example.p222appli;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    public final String SQL_CREATE = "create table if not exists T_Profil (idProfil integer primary key autoincrement, name text not null, mail text not null);";
    public final String SQL_DELETE = "drop table if exists T_Profil;";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
        Log.i("DATABASE", "onCreate invoked");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE);
        this.onCreate(db);
        Log.i("DATABASE", "onUpgrade invoked");
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE);
        Log.i("DATABASE", "onDowngrade invoked");
    }

    public void insertsProfil(String name, String mail) {
        name = name.replace("'", "''");
        String request = "insert into T_Profil (names, mail) values ('" + name + "', '" + mail + "');";
        this.getWritableDatabase().execSQL(request);
        Log.i("DATABASE", "insertsProfil invoked");
    }
}
