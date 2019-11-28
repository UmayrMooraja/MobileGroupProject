package com.tc.parkingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tc.parkingproject.model.Parking;
import com.tc.parkingproject.model.User;
import com.tc.parkingproject.viewModel.ParkingViewModel;
import com.tc.parkingproject.viewModel.UserViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddParking extends AppCompatActivity implements View.OnClickListener {

    EditText edtBuildingCode;
    Spinner spnNumHours;
    EditText edtPlateNum;
    EditText edtSuitNum;
    TextView txtDate;
    Button btnEnter;

    ParkingViewModel parkingViewModel;
    UserViewModel userViewModel;

    private int parkingID;

    Date newDate;
    String extraEmail;

    private String numHours;
    private int parkingCost;
    int numVisits = 1;
    private String carPlateNum;
    String suitNum;
    String buildingCode;

    private static final String TAG = "AddParking";

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private Date date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parking);

        userViewModel = new UserViewModel(getApplication());
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User user : users){
                    Log.e(TAG, user.toString());
                }
            }
        });

        parkingViewModel = new ParkingViewModel((getApplication()));
        parkingViewModel.getAllParking().observe(this, new Observer<List<Parking>>() {
            @Override
            public void onChanged(List<Parking> parkings) {
                for (Parking parking : parkings){
                    Log.e(TAG, parking.toString());
                }
            }
        });

        referWidgets();

        calendar = Calendar.getInstance();
        currentDateAndTime();
    }

    void referWidgets() {
        edtBuildingCode = findViewById(R.id.edtBuildingCode);

        spnNumHours = findViewById(R.id.spnNumHours);
        ArrayAdapter numHoursAdapter = ArrayAdapter.createFromResource(this, R.array.num_hours_array, android.R.layout.simple_spinner_dropdown_item);
        spnNumHours.setAdapter(numHoursAdapter);

        edtPlateNum = findViewById(R.id.edtPlateNum);
        edtSuitNum = findViewById(R.id.edtSuitNum);

        txtDate = findViewById(R.id.txtDate);

        btnEnter = findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnEnter:
                createNewParkingReceipt();
                break;
        }
    }

    private void currentDateAndTime() {
        dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm a");
        String dateStr = dateFormat.format(calendar.getTime());
        date = calendar.getTime();
        txtDate.setText(dateStr);
    }

    private void calculateHoursAndCost() {
        if(spnNumHours.getSelectedItem().toString().equals("Less than one hour")) {
            numHours = "Less than one hour";
            parkingCost = 4;
        }
        else if (spnNumHours.getSelectedItem().toString().equals("Up to three hours")) {
            numHours = "Up to three hours";
            parkingCost = 8;
        }
        else if (spnNumHours.getSelectedItem().toString().equals("Up to ten hours")) {
            numHours = "Up to ten hours";
            parkingCost = 12;
        }
        else if (spnNumHours.getSelectedItem().toString().equals("Daily max (24 hours)")){
            numHours = "Daily max (24 hours)";
            parkingCost = 20;
        }
    }

    private void calculateHours() {
        parkingCost = 0;
        if(spnNumHours.getSelectedItem().toString().equals("Less than one hour")) {
            numHours = "Less than one hour";
        }
        else if (spnNumHours.getSelectedItem().toString().equals("Up to three hours")) {
            numHours = "Up to three hours";
        }
        else if (spnNumHours.getSelectedItem().toString().equals("Up to ten hours")) {
            numHours = "Up to ten hours";
        }
        else {
            numHours = "Daily max (24 hours)";
        }
    }

    private void createNewParkingReceipt() {
        buildingCode = edtBuildingCode.getText().toString();
        suitNum = edtSuitNum.getText().toString();
        carPlateNum = edtPlateNum.getText().toString();

        List<User> allUsers = userViewModel.getAllUsers().getValue();
        List<Parking> allParking = parkingViewModel.getAllParking().getValue();

        boolean usersFound = false;

        for(User user: allUsers){
            Log.e("HI",user.getLicensePlate());
            extraEmail = this.getIntent().getStringExtra("EXTRA_EMAIL");
            if (user.getEmail().equals(extraEmail) && user.getLicensePlate().equals(carPlateNum)){
                for(Parking parking: allParking) {
                    numVisits = parking.getNumVisits();

                    if (date.getMonth() == parking.getDate().getMonth()) {
                        numVisits += 1;
                        if(numVisits > 3) {
                            calculateHoursAndCost();
                        }
                        else {
                            calculateHours();
                        }
                    }
                }
                Parking newParking = new Parking(buildingCode, numHours, carPlateNum, suitNum, date, numVisits, parkingCost);
                parkingViewModel.insert(newParking);
                openDisplayParkingActivity();
                usersFound = true;
            }
        }
        if (!usersFound){
            Toast.makeText(this, "Incorrect Car Plate Number ! Try again.", Toast.LENGTH_LONG).show();
        }

    }

    private void openDisplayParkingActivity() {
        Intent displayParkingIntent = new Intent(this, DisplayParking.class);
        displayParkingIntent.putExtra("EXTRA_EMAIL", extraEmail);
        startActivity(displayParkingIntent);
    }
}
