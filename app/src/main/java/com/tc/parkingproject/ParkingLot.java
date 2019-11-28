package com.tc.parkingproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class ParkingLot extends AppCompatActivity implements View.OnClickListener {

    Button btnAddParking;
    Button btnViewReceiptList;
    Button btnSearchParking;
    Button btnUpdateUser;
    Button btnAppManual;
    Button btnConsumerSupport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_lot);
        referWidgets();
    }

    private void referWidgets() {
        btnAddParking = findViewById(R.id.btnAddParking);
        btnAddParking.setOnClickListener(this);

        btnViewReceiptList = findViewById(R.id.btnViewReceiptList);
        btnViewReceiptList.setOnClickListener(this);

        btnSearchParking = findViewById(R.id.btnSearchParking);
        btnSearchParking.setOnClickListener(this);

        btnUpdateUser = findViewById(R.id.btnUpdateUser);
        btnUpdateUser.setOnClickListener(this);

        btnAppManual = findViewById(R.id.btnAppManual);
        btnAppManual.setOnClickListener(this);

        btnConsumerSupport = findViewById(R.id.btnConsumerSupport);
        btnConsumerSupport.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddParking:
                this.openAddParkingActivity();
                break;
            case R.id.btnViewReceiptList:
                this.openViewReceiptListActivity();
                break;
            case R.id.btnSearchParking:
                this.openSearchParkingActivity();
                break;
            case R.id.btnAppManual:
                this.openParkingAppManualActivity();
                break;
            case R.id.btnConsumerSupport:
                this.openCustomerSupportActivity();
                break;
        }
    }

    private void openAddParkingActivity() {
        Intent addParkingIntent = new Intent(this, AddParking.class);
        addParkingIntent.putExtra("EXTRA_EMAIL", this.getIntent().getStringExtra("EMAIL_EXTRA"));
        startActivity(addParkingIntent);
    }

    private void openViewReceiptListActivity() {
        Intent viewParkingListIntent = new Intent(this, ParkingList.class);
        viewParkingListIntent.putExtra("EXTRA_EMAIL", this.getIntent().getStringExtra("EMAIL_EXTRA"));
        startActivity(viewParkingListIntent);
    }

    private void openSearchParkingActivity() {

    }

    private void openParkingAppManualActivity() {


    }

    private void openCustomerSupportActivity() {

    }

}
