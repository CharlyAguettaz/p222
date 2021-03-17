package com.example.p222appli;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Inscriptions extends AppCompatActivity implements View.OnClickListener {

    //private TextView pointsProfil;
    private SQLiteDatabase db;
    DatabaseManager databaseManager;
    private Button bt_valider;
    private EditText name, mail, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__inscriptions);

        bt_valider = (Button) findViewById(R.id.bt_valider);
        name = (EditText) findViewById(R.id.et_name);
        mail = (EditText) findViewById(R.id.et_mail);
        password = (EditText) findViewById(R.id.et_password);
        bt_valider.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_valider) {
            Toast.makeText(getApplicationContext(), "Know connect yourself", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, Activity_Login.class);

            try {
                db = databaseManager.getReadableDatabase();
            } catch (SQLException ex) {
                db = databaseManager.getWritableDatabase();
            }

            databaseManager.insertsProfil(name.getText().toString(), mail.getText().toString(), password.getText().toString(),0);
            db.close();
            Log.v("input database", name.getText().toString() + ", " + mail.getText().toString() + ", " + password.getText().toString() + ", 0");

            Toast.makeText(getApplicationContext(), "inscription", Toast.LENGTH_LONG).show();
            startActivity(i);
        }

    }
}