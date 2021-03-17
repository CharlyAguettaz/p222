package com.example.p222appli;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Login extends AppCompatActivity implements View.OnClickListener {

    //private TextView pointsProfil;
    private DatabaseManager databaseManager;
    private EditText name, mail, password;
    private Button connexion;
    private Button bt_inscrire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);

        connexion = (Button) findViewById(R.id.connexion);
        bt_inscrire = (Button) findViewById(R.id.bt_inscrire);
        name = (EditText) findViewById(R.id.et_name);
        mail = (EditText) findViewById(R.id.et_mail);
        password = (EditText) findViewById(R.id.et_password);
        connexion.setOnClickListener(this);
        bt_inscrire.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connexion:
                databaseManager = new DatabaseManager(this);
                int idUserConnected = databaseManager.readAuthProfil(name.getText().toString(), mail.getText().toString(), password.getText().toString());
                databaseManager.close();
                if (idUserConnected != -1) {
                    Toast.makeText(getApplicationContext(), "connexion", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(this, Activity_Welcome.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "incorrect info", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.bt_inscrire:
                Intent i2 = new Intent(this, Activity_Inscriptions.class);
                startActivity(i2);
        }

    }
}