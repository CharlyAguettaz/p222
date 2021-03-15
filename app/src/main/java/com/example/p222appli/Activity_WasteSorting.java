package com.example.p222appli;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_WasteSorting extends AppCompatActivity {

    RadioGroup rd_waste_choice;
    RadioButton radioButton;
    RadioButton checkedBoutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__waste_sorting);

        //activité

        rd_waste_choice = findViewById(R.id.rd_waste_choice);
        Button bt_validate = findViewById(R.id.bt_validate);

        bt_validate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(Activity_WasteSorting.this, R.string.goingToMap, Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Activity_WasteSorting.this, Activity_MapFromWaste.class);
                Bundle bundle = new Bundle();
                bundle.putInt("idBoutton", radioButton.getId());
                bundle.putInt("idVerre", (R.id.rdbt_glass));
                bundle.putInt("idPapier", (R.id.rdbt_paper));
                bundle.putInt("idPlastique", (R.id.rdbt_plastic));
                bundle.putInt("idMetal", (R.id.rdbt_metal));
                bundle.putInt("idOrganique", (R.id.rdbt_organic));
                bundle.putInt("idAutres", (R.id.rdbt_other));
                intent1.putExtras(bundle);
                startActivity(intent1);

            }
        });

    }
    public void checkButton(View v){
        int radioId = rd_waste_choice.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selection: " + radioButton.getText(), Toast.LENGTH_SHORT).show();
    }

    private Menu m = null;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        m = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent myIntent = new Intent(this.getApplicationContext(), Activity_Welcome.class);
                startActivityForResult(myIntent, 0);
                return true;
            case R.id.item3:
                Intent myIntent2 = new Intent(this.getApplicationContext(), Activity_WasteSorting.class);
                startActivityForResult(myIntent2, 1);
                return true;
            case R.id.item4:
                Intent myIntent3 = new Intent(this.getApplicationContext(), Activity_Maps.class);
                startActivityForResult(myIntent3, 2);
                Toast.makeText(Activity_WasteSorting.this, R.string.goingToMap, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}