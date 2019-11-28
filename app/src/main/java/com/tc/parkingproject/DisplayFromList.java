package com.tc.parkingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayFromList extends AppCompatActivity {

    TextView txtBuildingCode;
    TextView txtNumHours;
    TextView txtPlateNum;
    TextView txtSuitNum;
    TextView txtDate;
    TextView txtCost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_from_list);

        referWidgets();
        setExtras();
    }

    void referWidgets() {

        txtBuildingCode = findViewById(R.id.txtBuildingCode);
        txtNumHours = findViewById(R.id.txtNumHours);
        txtPlateNum = findViewById(R.id.txtCarPlateNum);
        txtSuitNum = findViewById(R.id.txtSuitNum);
        txtDate = findViewById(R.id.txtDate);
        txtCost = findViewById(R.id.txtCost);


    }

    void setExtras() {
        txtBuildingCode.setText(this.getIntent().getStringExtra("EXTRA_BUILDING_CODE"));
        txtNumHours.setText(this.getIntent().getStringExtra("EXTRA_NUM_HOURS"));
        txtPlateNum.setText(this.getIntent().getStringExtra("EXTRA_PLATE_NUM"));
        txtSuitNum.setText(this.getIntent().getStringExtra("EXTRA_SUIT_NUM"));
        txtDate.setText(this.getIntent().getStringExtra("EXTRA_DATE"));
        txtCost.setText(this.getIntent().getStringExtra("EXTRA_COST"));
    }
}
