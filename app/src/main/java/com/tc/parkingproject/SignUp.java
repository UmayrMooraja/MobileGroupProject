package com.tc.parkingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.VectorEnabledTintResources;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tc.parkingproject.model.User;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText edtNameSU;
    EditText edtEmailSU;
    EditText edtPhoneNumberSU;
    EditText edtPasswordSU;
    EditText edtPlateNumberSU;
    EditText edtCardNumberSU;
    EditText edtExpiryDateSU;
    EditText edtCardNameSU;
    EditText edtCVVNumberSU;

    Button btnSignUp;

    public static final String EXTRA_REPLY = "com.tc.parkingproject.REPLY";

    String Name;
    String Email;
    String Password;
    String PhoneNumber;
    String licensePlate;
    String CardNumber;
    String CardExpiry;
    String CardName;
    String CardCVV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        referWidgets();
    }




    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnSignUp:
                if (this.validateData()) {
                    this.createUserAndReply();
                }
                break;
        }

    }


    private void referWidgets(){
        edtNameSU = findViewById(R.id.edtNameSU);
        edtEmailSU = findViewById(R.id.edtEmailSU);
        edtPhoneNumberSU = findViewById(R.id.edtPhoneNumberSU);
        edtPasswordSU = findViewById(R.id.edtPasswordSU);
        edtPlateNumberSU = findViewById(R.id.edtPlateNumberSU);
        edtCardNumberSU = findViewById(R.id.edtCardNumberSU);
        edtCardNameSU = findViewById(R.id.edtCardNameSU);
        edtExpiryDateSU = findViewById(R.id.edtCardExpirySU);
        edtCVVNumberSU = findViewById(R.id.edtCvvNumberSU);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);

    }

    private boolean validateData(){
        boolean allValidations = true;

        if (edtNameSU.getText().toString().isEmpty()){
            edtNameSU.setError("You must enter your name");
            allValidations = false;
        }

        if(edtPasswordSU.getText().toString().isEmpty()){
            edtPasswordSU.setError("Please enter your password");
            allValidations = false;
        }

        if (edtEmailSU.getText().toString().isEmpty()){
            edtEmailSU.setError("You must enter your Email");
            allValidations = false;
        }

        if (edtPasswordSU.getText().toString().isEmpty()){
            edtPasswordSU.setError("Please enter password");
            allValidations = false;
        }

        if (edtPhoneNumberSU.getText().toString().isEmpty()){
            edtPhoneNumberSU.setError("You must provide phone number");
            allValidations = false;
        }

        if (edtPlateNumberSU.getText().toString().isEmpty()){
            edtPlateNumberSU.setError("You must enter your plate number");
            allValidations = false;
        }

        if (edtCardNumberSU.getText().toString().isEmpty()){
            edtCardNumberSU.setError("You must enter your card number");
            allValidations = false;
        }

        if (edtExpiryDateSU.getText().toString().isEmpty()){
            edtExpiryDateSU.setError("You must enter your expiry date");
            allValidations = false;
        }

        if (edtCardNameSU.getText().toString().isEmpty()){
            edtCardNameSU.setError("You must enter your card name");
            allValidations = false;
        }

        if (edtCVVNumberSU.getText().toString().isEmpty()){
            edtCVVNumberSU.setError("You must enter your cvv number");
            allValidations = false;
        }

        return allValidations;
    }

    private void createUserAndReply(){
        Name = edtNameSU.getText().toString();
        Email = edtEmailSU.getText().toString();
        Password = edtPasswordSU.getText().toString();
        PhoneNumber = edtPhoneNumberSU.getText().toString();
        licensePlate = edtPlateNumberSU.getText().toString();
        CardNumber = edtCardNumberSU.getText().toString();
        CardExpiry = edtExpiryDateSU.getText().toString();
        CardName = edtCardNameSU.getText().toString();
        CardCVV = edtCVVNumberSU.getText().toString();




        User newUser = new User(Name, PhoneNumber,  Email, Password, licensePlate, CardNumber, CardExpiry, CardName, CardCVV);
        Log.e("SignUpActivity", newUser.toString());

        //reply to previous intent
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, newUser);
        setResult(RESULT_OK, replyIntent);
        finish();
    }

}
