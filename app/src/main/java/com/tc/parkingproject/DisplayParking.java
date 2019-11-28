package com.tc.parkingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tc.parkingproject.model.Parking;
import com.tc.parkingproject.viewModel.ParkingViewModel;

import java.util.List;

public class DisplayParking extends AppCompatActivity implements View.OnClickListener {

    TextView txtBuildingCode;
    TextView txtNumHours;
    TextView txtCarPlateNum;
    TextView txtSuitNum;
    TextView txtDate;
    TextView txtCost;
    Button btnBack;

    private String TAG = "DisplayParkingActivity";

    ParkingViewModel parkingViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_parking);
        referWidgets();

        parkingViewModel = new ParkingViewModel((getApplication()));
        parkingViewModel.getAllParking().observe(this, new Observer<List<Parking>>() {
            @Override
            public void onChanged(List<Parking> parkings) {
                Parking largestID = null;
                for (Parking parking : parkings) {
                    Log.e(TAG, parking.toString());

                    if (largestID == null || parking.getId() > largestID.getId())
                    {
                        largestID = parking;
                    }

                }

                txtBuildingCode.setText(largestID.getBuildingCode());
                txtNumHours.setText(largestID.getNumHours());
                txtCarPlateNum.setText(largestID.getPlateNum());
                txtSuitNum.setText(largestID.getSuitNum());
                txtDate.setText(largestID.getDate().toString());
                txtCost.setText(String.valueOf(largestID.getCost()));
            }
        });
    }

    void referWidgets() {
        txtBuildingCode = findViewById(R.id.txtBuildingCode);
        txtNumHours = findViewById(R.id.txtNumHours);
        txtCarPlateNum = findViewById(R.id.txtCarPlateNum);
        txtSuitNum = findViewById(R.id.txtSuitNum);
        txtDate = findViewById(R.id.txtDate);
        txtCost = findViewById(R.id.txtCost);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                openAddParkingActivity();
                break;
        }
    }

    void openAddParkingActivity() {
        Intent mainIntent = new Intent(this, ParkingLot.class);
        mainIntent.putExtra("EMAIL_EXTRA", this.getIntent().getStringExtra("EXTRA_EMAIL"));
        startActivity(mainIntent);
    }
}
