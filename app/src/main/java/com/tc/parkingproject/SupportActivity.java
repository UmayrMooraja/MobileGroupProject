package com.tc.parkingproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class SupportActivity extends AppCompatActivity implements View.OnClickListener {


    Button btnCall;
    Button btnEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        btnCall = findViewById(R.id.btnCall);
        btnCall.setOnClickListener(this);

        btnEmail = findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCall:
                this.makeCall();
                break;
            case R.id.btnEmail:
                this.sendEmail();
                break;
        }
    }

    private void makeCall(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);

        callIntent.setData(Uri.parse("tel:1234567890"));

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            return;
        }

        startActivity(callIntent);

    }

    //send email using Intent Service
    private void sendEmail(){
        //prebuilt intent for email
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL,
                new String[]{"random.gmail.com"});

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Support Request");

        emailIntent.putExtra(Intent.EXTRA_TEXT,
                "Support Request");

        emailIntent.setType("message/rfc822");

        startActivity(Intent.createChooser(emailIntent, "Please select email client"));
    }
}
