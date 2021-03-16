package com.example.p222appli;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Inscriptions extends AppCompatActivity implements View.OnClickListener {

    private TextView pointsProfil;
    private DatabaseManager databaseManager;
    Button connexion;
    Button bt_valider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__inscriptions);


        bt_valider = (Button) findViewById(R.id.bt_valider);
        bt_valider.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_valider) {
            Toast.makeText(getApplicationContext(), "Know connect yourself", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, Activity_Login.class);
            startActivity(i);
        }

    }
}