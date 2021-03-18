package com.example.p222appli;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity_Welcome extends AppCompatActivity {

    private SQLiteDatabase db;
    DatabaseManager databaseManager;
    private RadioGroup rd_waste_choice;
    private RadioButton radioButton;
    private RadioButton verre, papier, plastique, metal, organique, autre;
    protected int idUserConnected;
    private String stringAdresse;
    private int valeurItem;

    public void open() throws SQLiteException {
        try {
            db = databaseManager.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = databaseManager.getReadableDatabase();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__welcome);
        rd_waste_choice = findViewById(R.id.rd_waste_choice);
        TextView profilName = findViewById(R.id.txt_bdd_profilname);
        TextView profilMail = findViewById(R.id.txt_bdd_profilmail);
        idUserConnected = this.getIntent().getExtras().getInt("idUserConnected");
        verre = findViewById(R.id.rdbt_glass);
        papier = findViewById(R.id.rdbt_paper);
        plastique = findViewById(R.id.rdbt_plastic);
        metal = findViewById(R.id.rdbt_metal);
        organique = findViewById(R.id.rdbt_organic);
        autre = findViewById(R.id.rdbt_other);
        databaseManager = new DatabaseManager(this);
        open();
        profilName.setText(databaseManager.readName(idUserConnected) + getIntent().getIntExtra("idUserConnected", 0));
        profilMail.setText(databaseManager.readMail(idUserConnected));
        db.close();

        Date now = new Date();

        DateFormat dateformatter = DateFormat.getDateInstance(DateFormat.SHORT);
        String formattedDate = dateformatter.format(now);

        ListView listeV = (ListView) findViewById(R.id.idListView);
        List<String> listeValeursDansLaListe = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listeValeursDansLaListe);
        listeV.setAdapter(adapter);

        // Code pour la gestion des clics sur ls items de la liste
        listeV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupère la valeur de l'item à la position sur laquelle on a cliqué
                String nomItem = (String) parent.getItemAtPosition(position);
                valeurItem = (int) parent.getSelectedItemPosition() + 1;

                Toast.makeText(Activity_Welcome.this, getString(R.string.yourSelection) + nomItem, Toast.LENGTH_SHORT).show();
            }
        });





        //---------------------------------------------------
        Button addButton = (Button)findViewById(R.id.idButtonAdd);
        Button deleteButton = (Button)findViewById(R.id.idButtonDelete);

        // Pour desactiver le bouton lorsqu'il n'y a pas de valeur à supprimer.. cf code bouton deleteButton
        deleteButton.setEnabled(false);
        addButton.setEnabled(false);

       verre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButton.setEnabled(true);
                radioButton = findViewById(R.id.rdbt_glass);
            }
        });
        papier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButton.setEnabled(true);
                radioButton = findViewById(R.id.rdbt_paper);
            }
        });
        plastique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButton.setEnabled(true);
                radioButton = findViewById(R.id.rdbt_plastic);
            }
        });
        metal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButton.setEnabled(true);
                radioButton = findViewById(R.id.rdbt_metal);
            }
        });
        organique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButton.setEnabled(true);
                radioButton = findViewById(R.id.rdbt_organic);
            }
        });
        autre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButton.setEnabled(true);
                radioButton = findViewById(R.id.rdbt_other);
            }
        });


        // On va ajouter une valeur dans la liste à chaque fois que l'on appuiera sur le bouton +
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioButton.getId() == verre.getId()){
                    stringAdresse = getString(R.string.wt_glass) + " " + formattedDate;
                    listeValeursDansLaListe.add(stringAdresse);
                    adapter.notifyDataSetChanged();
                }
                else if(radioButton.getId() == papier.getId()){
                    stringAdresse = getString(R.string.wt_paper) + " " + formattedDate;
                    listeValeursDansLaListe.add(stringAdresse);
                    adapter.notifyDataSetChanged();
                }
                else if(radioButton.getId() == plastique.getId()){
                    stringAdresse = getString(R.string.wt_plastic) + " " + formattedDate;
                    listeValeursDansLaListe.add(stringAdresse);
                    adapter.notifyDataSetChanged();
                }
                else if(radioButton.getId() == metal.getId()){
                    stringAdresse = getString(R.string.wt_metal) + " " + formattedDate;
                    listeValeursDansLaListe.add(stringAdresse);
                    adapter.notifyDataSetChanged();
                }
                else if(radioButton.getId() == organique.getId()){
                    stringAdresse = getString(R.string.wt_organic) + " " + formattedDate;
                    listeValeursDansLaListe.add(stringAdresse);
                    adapter.notifyDataSetChanged();
                }
                else if(radioButton.getId() == autre.getId()){
                    stringAdresse = getString(R.string.wt_other) + " " + formattedDate;
                    listeValeursDansLaListe.add(stringAdresse);
                    adapter.notifyDataSetChanged();
                }


                // Pour traiter le pb de la liste vide... Cf ci-dessous
                if (!deleteButton.isEnabled()){
                    deleteButton.setEnabled(true);
                }
            }
        });


        // On va enlever la première valeur dans la liste à chaque fois que l'on appuiera sur le bouton -
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Il vaudrait mieux désactiver le bouton s'il n'y a plus de valeur

                if (listeValeursDansLaListe.size()>0) {
                    listeValeursDansLaListe.remove(valeurItem);
                    adapter.notifyDataSetChanged();

                    if (listeValeursDansLaListe.size() == 0){
                        deleteButton.setEnabled(false);
                    }
                }

            }
        });

    }



    private Menu m = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profil, menu);
        m = menu;
        return true;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch( item.getItemId()) {

            case R.id.item3:
                Intent myIntent2 = new Intent(this.getApplicationContext(), Activity_WasteSorting.class);
                startActivityForResult(myIntent2, 1);
                return true;
            case R.id.item4:
                Intent myIntent3 = new Intent(this.getApplicationContext(), Activity_Maps.class);
                startActivityForResult(myIntent3, 2);
                Toast.makeText(Activity_Welcome.this, R.string.goingToMap, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}