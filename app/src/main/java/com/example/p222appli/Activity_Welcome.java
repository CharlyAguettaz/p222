package com.example.p222appli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    private DatabaseManager databaseManager;
    private RadioGroup rd_waste_choice;
    private RadioButton radioButton;
    protected int idUserConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__welcome);

        rd_waste_choice = findViewById(R.id.rd_waste_choice);
        TextView profilName = findViewById(R.id.txt_bdd_profilname);
        TextView profilMail = findViewById(R.id.txt_bdd_profilmail);

        profilName.setText((databaseManager.readName(idUserConnected)));
        profilMail.setText((databaseManager.readMail(idUserConnected)));

        Date now = new Date();

        DateFormat dateformatter = DateFormat.getDateInstance(DateFormat.SHORT);
        String formattedDate = dateformatter.format(now);

        // Récupération de la liste
        ListView listeV = (ListView) findViewById(R.id.idListView);

        // Liste des valeurs affchées dans la listeView
        List<String> listeValeursDansLaListe = new ArrayList<>();

        // Création d'un adapter à partir de la liste
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listeValeursDansLaListe);

        // lie l'adapter à la listeView
        listeV.setAdapter(adapter);

        // Code pour la gestion des clics sur ls items de la liste
        listeV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupère la valeur de l'item à la position sur laquelle on a cliqué
                String valeurItem = (String) parent.getItemAtPosition(position);

                Toast.makeText(Activity_Welcome.this, valeurItem, Toast.LENGTH_SHORT).show();
            }
        });


        //---------------------------------------------------
        Button addButton = (Button)findViewById(R.id.idButtonAdd);
        Button deleteButton = (Button)findViewById(R.id.idButtonDelete);

        // Pour desactiver le bouton lorsqu'il n'y a pas de valeur à supprimer.. cf code bouton deleteButton
        deleteButton.setEnabled(false);

        // On va ajouter une valeur dans la liste à chaque fois que l'on appuiera sur le bouton +
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radioButton.getId() == R.id.rdbt_glass){
                    listeValeursDansLaListe.add(getString(R.string.wt_glass) + " " + formattedDate);
                    adapter.notifyDataSetChanged();
                }
                else if(radioButton.getId() == R.id.rdbt_paper){
                    listeValeursDansLaListe.add(getString(R.string.wt_paper) + " " + formattedDate);
                    adapter.notifyDataSetChanged();
                }
                else if(radioButton.getId() == R.id.rdbt_plastic){
                    listeValeursDansLaListe.add(getString(R.string.wt_plastic) + " " + formattedDate);
                    adapter.notifyDataSetChanged();
                }
                else if(radioButton.getId() == R.id.rdbt_metal){
                    listeValeursDansLaListe.add(getString(R.string.wt_metal) + " " + formattedDate);
                    adapter.notifyDataSetChanged();
                }
                else if(radioButton.getId() == R.id.rdbt_organic){
                    listeValeursDansLaListe.add(getString(R.string.wt_organic) + " " + formattedDate);
                    adapter.notifyDataSetChanged();
                }
                else if(radioButton.getId() == R.id.rdbt_other){
                    listeValeursDansLaListe.add(getString(R.string.wt_other) + " " + formattedDate);
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
                // bof bof ....
                /*
                if (listeValeursDansLaListe.size()>0) {
                    listeValeursDansLaListe.remove(0);
                    adapter.notifyDataSetChanged();
                }
                */

                // Il vaudrait mieux désactiver le bouton s'il n'y a plus de valeur
                if (listeValeursDansLaListe.size()>0) {
                    listeValeursDansLaListe.remove(listeValeursDansLaListe.size() - 1);
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



    public void checkButton(View v){
        int radioId = rd_waste_choice.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selection: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
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