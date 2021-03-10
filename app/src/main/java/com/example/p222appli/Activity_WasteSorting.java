package com.example.p222appli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_WasteSorting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__waste_sorting);


        //activité

        LinearLayout linly_body = new LinearLayout(this);
        linly_body.setOrientation(LinearLayout.VERTICAL);

        TextView txt_waste_type = findViewById(R.id.txt_waste_type);
        txt_waste_type.setText("Waste Types");

        RadioGroup rd_waste_choice = findViewById(R.id.rd_waste_choice);

        RadioButton rdbt_glass = findViewById(R.id.rdbt_glass);
        rdbt_glass.setText("Glass");
        RadioButton rdbt_paper = findViewById(R.id.rdbt_paper);
        rdbt_paper.setText("Paper");
        RadioButton rdbt_plastic = findViewById(R.id.rdbt_plastic);
        rdbt_plastic.setText("Plastic");
        RadioButton rdbt_metal = findViewById(R.id.rdbt_metal);
        rdbt_metal.setText("Metal");
        RadioButton rdbt_organic = findViewById(R.id.rdbt_organic);
        rdbt_organic.setText("Organic");
        RadioButton rdbt_other = findViewById(R.id.rdbt_other);
        rdbt_other.setText("Other");

        Button bt_validate = findViewById(R.id.bt_validate);
        bt_validate.setText("Validate");

        linly_body.addView(txt_waste_type);

        rd_waste_choice.addView(rdbt_glass);
        rd_waste_choice.addView(rdbt_paper);
        rd_waste_choice.addView(rdbt_plastic);
        rd_waste_choice.addView(rdbt_metal);
        rd_waste_choice.addView(rdbt_organic);
        rd_waste_choice.addView(rdbt_other);

        linly_body.addView(rd_waste_choice);
        linly_body.addView(bt_validate);

        bt_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Activity_WasteSorting.this, "will go to Activity_WasteInfos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}