package com.tc.parkingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.tc.parkingproject.adapters.ParkingAdapter;
import com.tc.parkingproject.model.Parking;
import com.tc.parkingproject.model.User;
import com.tc.parkingproject.viewModel.ParkingViewModel;
import com.tc.parkingproject.viewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class ParkingList extends AppCompatActivity {

    ListView lvParking;
    ArrayAdapter parkingAdapter;
    ArrayList<Parking> parkingArrayList;

    ParkingViewModel parkingViewModel;
    UserViewModel userViewModel;

    String extraEmail;

    String buildingCode;
    String numHours;
    String plateNum;
    String suitNum;
    String date;
    String cost;

    String carPlateNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_list);


        parkingArrayList = new ArrayList<Parking>();

        lvParking = findViewById(R.id.lvParking);
        parkingAdapter = new ParkingAdapter(ParkingList.this, parkingArrayList);
        lvParking.setAdapter(parkingAdapter);

        extraEmail = this.getIntent().getStringExtra("EXTRA_EMAIL");

        userViewModel = new UserViewModel(getApplication());
        userViewModel.getAllUsers().observe(ParkingList.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User user : users) {
                    if(user.getEmail().equals(extraEmail)) {
                        carPlateNum = user.getLicensePlate();
                    }
                }
            }
        });

        parkingViewModel = new ParkingViewModel(getApplication());
        parkingViewModel.getAllParking().observe(ParkingList.this, new Observer<List<Parking>>() {
            @Override
            public void onChanged(List<Parking> parkings) {
                parkingArrayList.clear();
                for (Parking parking : parkings) {
                    if (parking.getPlateNum().equals(carPlateNum)){
                        Log.e("ParkingList", parking.toString());
                        parkingArrayList.add(parking);

                    }
                }
                parkingAdapter.notifyDataSetChanged();
            }
        });

        this.listViewListener();

    }

    private void listViewListener() {
        lvParking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = parkingArrayList.get(position).getPlateNum();
                Toast.makeText(ParkingList.this, title, Toast.LENGTH_LONG).show();

                setInfo(position);

                openDisplayParkingActivity();
            }
        });
    }


    void setInfo(final int position) {
        buildingCode = parkingArrayList.get(position).getBuildingCode();
        numHours = parkingArrayList.get(position).getNumHours();
        plateNum = parkingArrayList.get(position).getPlateNum();
        suitNum = parkingArrayList.get(position).getSuitNum();
        date = parkingArrayList.get(position).getDate().toString();
        cost = String.valueOf(parkingArrayList.get(position).getCost());
    }


    void openDisplayParkingActivity() {
        Intent displayParkingIntent = new Intent(this, DisplayFromList.class);
        displayParkingIntent.putExtra("EXTRA_EMAIL", extraEmail);
        displayParkingIntent.putExtra("EXTRA_BUILDING_CODE", buildingCode);
        displayParkingIntent.putExtra("EXTRA_NUM_HOURS", numHours);
        displayParkingIntent.putExtra("EXTRA_PLATE_NUM", carPlateNum);
        displayParkingIntent.putExtra("EXTRA_SUIT_NUM", suitNum);
        displayParkingIntent.putExtra("EXTRA_DATE", date);
        displayParkingIntent.putExtra("EXTRA_COST", cost);
        startActivity(displayParkingIntent);
    }
}
