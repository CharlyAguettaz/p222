package com.example.p222appli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Inscriptions extends AppCompatActivity implements View.OnClickListener {

    private TextView pointsProfil;
    private DatabaseManager databaseManager;
    Button bt_validerIns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);

        bt_validerIns = (Button) findViewById(R.id.bt_valider);
        bt_validerIns.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_valider) {
            Intent i = new Intent(this, Activity_Login.class);
            Toast.makeText(getApplicationContext(), "inscription", Toast.LENGTH_LONG).show();
            startActivity(i);
        }

    }
}