package com.example.p222appli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Inscriptions extends AppCompatActivity implements View.OnClickListener {

    //private TextView pointsProfil;
    private DatabaseManager databaseManager;
    public Button bt_validerIns;

    private EditText name, mail, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);

        bt_validerIns = (Button) findViewById(R.id.bt_valider);
        name = (EditText) findViewById(R.id.et_name);
        mail = (EditText) findViewById(R.id.et_mail);
        password = (EditText) findViewById(R.id.et_password);
        bt_validerIns.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_valider) {
            Intent i = new Intent(this, Activity_Login.class);

            databaseManager = new DatabaseManager(this);
            databaseManager.insertsProfil(name.getText().toString(), mail.getText().toString(), password.getText().toString(),0);
            databaseManager.close();
            Log.v("input database", name.getText().toString() + ", " + mail.getText().toString() + ", " + password.getText().toString() + ", 0");

            Toast.makeText(getApplicationContext(), "inscription", Toast.LENGTH_LONG).show();
            startActivity(i);
        }

    }
}