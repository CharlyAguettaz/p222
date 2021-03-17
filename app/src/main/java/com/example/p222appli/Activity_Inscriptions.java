package com.example.p222appli;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Inscriptions extends AppCompatActivity implements View.OnClickListener {

    //private TextView pointsProfil;
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

        databaseManager = new DatabaseManager(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_valider) {

            Intent i = new Intent(this, Activity_Login.class);
            //i.putExtras();
            //databaseManager = new DatabaseManager(this);
            //open();
            //databaseManager.onCreate(db);
            databaseManager.insertsProfil(name.getText().toString(), mail.getText().toString(), password.getText().toString());
            databaseManager.close();

            Toast.makeText(getApplicationContext(), "inscription", Toast.LENGTH_LONG).show();
            startActivity(i);
        }

    }
}