package com.example.p222appli;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    public final String SQL_CREATE = "create table if not exists T_Profil (idProfil integer primary key autoincrement, name text not null, mail text not null, password text not null, points integer not null);";
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

    public void insertsProfil(String name, String mail, String password, int points) {
        name = name.replace("'", "''");
        String request = "insert into T_Profil (names, mail, password, points) values ('" + name + "', '" + mail + "','" +  password + "','" + points +"');";
        this.getWritableDatabase().execSQL(request);
        Log.i("DATABASE", "insertsProfil invoked");
    }

    public int readPoints(String inName, String inMail, String inPassword) {
        int points = 0;
        String request = "select points from T_Profil where inName = name and inMail = mail and inPassword = password;";
        Cursor cursor = this.getReadableDatabase().rawQuery(request, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            points = cursor.getInt(0);
            cursor.moveToNext();
        }
        cursor.close();
        Log.i("DATABASE", "readPoints invoked");
        return points;
    }

    public int readAuthProfil(String inName, String inMail, String inPassword) {
        int answer = -1;
        String request = "select idProfil from T_Profil where inName = name and inMail = mail and inPassword = password;";
        Cursor cursor = this.getReadableDatabase().rawQuery(request, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            answer = cursor.getInt(0);
            cursor.moveToNext();
        }
        cursor.close();
        Log.i("DATABASE", "readAuthProfil invoked");
        return answer;
    }

    public String readName(int inId) {
        String answer = null;
        String request = "select name from T_Profil where inId = idProfil;";
        Cursor cursor = this.getReadableDatabase().rawQuery(request, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            answer = cursor.getString(0);
            cursor.moveToNext();
        }
        cursor.close();
        Log.i("DATABASE", "readName invoked");
        return answer;
    }

    public String readMail (int inId) {
        String answer = null;
        String request = "select mail from T_Profil where inId = idProfil;";
        Cursor cursor = this.getReadableDatabase().rawQuery(request, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            answer = cursor.getString(0);
            cursor.moveToNext();
        }
        cursor.close();
        Log.i("DATABASE", "readMail invoked");
        return answer;
    }
}
