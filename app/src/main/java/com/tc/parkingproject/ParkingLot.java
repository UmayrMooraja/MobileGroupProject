package com.tc.parkingproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tc.parkingproject.model.User;
import com.tc.parkingproject.viewModel.UserViewModel;

import java.util.List;


public class ParkingLot extends AppCompatActivity implements View.OnClickListener {

    Button btnAddParking;
    Button btnViewReceiptList;
    Button btnSearchParking;
    Button btnUpdateUser;
    Button btnAppManual;
    Button btnConsumerSupport;

    User userA;
    String currentEmail;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_lot);
        referWidgets();

        userViewModel = new UserViewModel(getApplication());

        currentEmail = getIntent().getStringExtra("EXTRA_EMAIL");

        userViewModel.getAllUsers().observe(ParkingLot.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                //task when the data changes
                for (User user : users){
                    if (user.getEmail().equals(currentEmail)){
                        userA = user;

                    }

                    Log.e("SignInActivity", user.toString());
                }
            }
        });
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
            case R.id.btnUpdateUser:
                updateInfo();
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
        Intent parkingIntent = new Intent(this, NearbyparkingActivity.class);
        startActivity(parkingIntent);

    }

    private void openParkingAppManualActivity() {

        //tring url = ;

        Intent manual=new Intent(Intent.ACTION_VIEW, Uri.parse("file:///android_asset/html/app_manual.html"));
        startActivity(manual);

    }

    private void openCustomerSupportActivity() {
        Intent supportIntent = new Intent(this, SupportActivity.class);
        startActivity(supportIntent);

    }

    void updateInfo(){
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_update_info, null);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Reset Password")
                .setMessage("Please reset the password")
                .setView(dialogView)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        EditText edt_name = dialogView.findViewById(R.id.edt_name);
                        EditText edt_password = dialogView.findViewById(R.id.edt_password);
                        EditText edt_phoneNumber = dialogView.findViewById(R.id.edt_phoneNumber);
                        EditText edt_licensePlate = dialogView.findViewById(R.id.edt_licensePlate);
                        EditText edt_cardNumber = dialogView.findViewById(R.id.edt_cardNumber);
                        EditText edt_expirationDate = dialogView.findViewById(R.id.edt_expirationData);
                        EditText edt_cardName = dialogView.findViewById(R.id.edt_cardName);
                        EditText edt_cvvNumber = dialogView.findViewById(R.id.edt_cvvNumber);


                        String edtName = edt_name.getText().toString();
                        String edtPassword = edt_password.getText().toString();
                        String edtPhoneNumber = edt_phoneNumber.getText().toString();
                        String edtLicensePlate = edt_licensePlate.getText().toString();
                        String edtCardNumber = edt_cardNumber.getText().toString();
                        String edtExpirationDate = edt_expirationDate.getText().toString();
                        String edtCardName = edt_cardName.getText().toString();
                        String edtCvvNumber = edt_cvvNumber.getText().toString();

                        userA.setFirstName(edtName);
                        userA.setPassword(edtPassword);
                        userA.setPhoneNumber(edtPhoneNumber);
                        userA.setLicensePlate(edtLicensePlate);
                        userA.setCardNumber(edtCardNumber);
                        userA.setExpiryDate(edtExpirationDate);
                        userA.setCardName(edtCardName);
                        userA.setCvvNumber(edtCvvNumber);
                        userViewModel.updateUser(userA);


                        Log.e("Updated information", userA.toString());

                    }
                })
                .setNegativeButton("Cancel", null)
                .create();

        alertDialog.show();
    }

}
