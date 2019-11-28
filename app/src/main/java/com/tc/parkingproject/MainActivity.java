package com.tc.parkingproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.tc.parkingproject.model.User;
import com.tc.parkingproject.viewModel.UserViewModel;

import java.util.List;

import static com.tc.parkingproject.SignUp.EXTRA_REPLY;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtEmail;
    EditText edtPassword;
    Button btnSignIn;
    TextView txtSignUp;
    Switch swtRemember;
    TextView txtForgetPassword;
    String email = "";
    String password = "";


    UserViewModel userViewModel;

    public static final int SIGN_UP_REQUEST_CODE = 1;
    public static final int RESET_PASSWORD_REQUEST_CODE = 1;
    public  static final String USER_PREF = "com.tc.parkingproject.userpref";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userViewModel = new UserViewModel(getApplication());

        userViewModel.getAllUsers().observe(MainActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                //task when the data changes
                for (User user : users){
                    Log.e("SignInActivity", user.toString());
                }
            }
        });
        referWidgets();

        this.getRememberedData();
    }

    void referWidgets(){
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);

        txtSignUp = findViewById(R.id.tvSignUp);
        txtSignUp.setOnClickListener(this);

        swtRemember = findViewById(R.id.swtRememberMe);
        txtForgetPassword = findViewById(R.id.tvForgotPassword);
        txtForgetPassword.setOnClickListener(this);
        //tvUpdateInfo.findViewById(R.id.tvUpdate);
        //tvUpdateInfo.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignIn:
                //signin operation
                this.signIn();
                break;
            case R.id.tvSignUp:
                //sign up operation
                this.signUp();
                break;
            case R.id.tvForgotPassword:
                //forget password operation
                this.resetPassword();
                break;


        }

    }


    void signIn(){
        email = edtEmail.getText().toString();
        password = edtPassword.getText().toString();

        if(this.authenticateUser(email, password)){
            if(swtRemember.isChecked()){
                this.rememberData();
            }
            else
                {
                this.forgetData();
            }



            //login successful
            Toast.makeText(this, "Login successful",Toast.LENGTH_LONG).show();



            this.openMainActivity();
        }
        else
        {
            //login unsuccessful
            Toast.makeText(this, "Incorrect Email/Password ! Try again.",Toast.LENGTH_LONG).show();
        }
    }



    private boolean authenticateUser(String email, String password) {
        List<User> allUsers = userViewModel.getAllUsers().getValue();

        for(User user: allUsers){
            if (user.getEmail().equals(email) && user.getPassword().equals(password)){
                return true;
            }
        }

        return false;


    }

    void openMainActivity(){
        Intent mainIntent = new Intent(MainActivity.this, ParkingLot.class);
        mainIntent.putExtra("EMAIL_EXTRA", edtEmail.getText().toString());
        mainIntent.putExtra("EXTRA_EMAIL", email);
        startActivity(mainIntent);
    }

    void signUp(){
        Intent signUpIntent = new Intent(MainActivity.this, SignUp.class);
        startActivityForResult(signUpIntent, SIGN_UP_REQUEST_CODE);
    }

    private void rememberData(){
        SharedPreferences sp = getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);

        sp.edit().putString(EMAIL, edtEmail.getText().toString()).commit();
        sp.edit().putString(PASSWORD, edtPassword.getText().toString()).commit();
    }

    private void getRememberedData(){
        SharedPreferences sp = getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);

        edtEmail.setText(sp.getString(EMAIL, ""));
        edtPassword.setText(sp.getString(PASSWORD, ""));
    }

    private void forgetData(){

        SharedPreferences sp = getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);

        sp.edit().clear().commit();

    }

    void resetPassword(){
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_add_edit, null);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Reset Password")
                .setMessage("Please reset the password")
                .setView(dialogView)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText edt_email = dialogView.findViewById(R.id.edt_email);
                        EditText edt_password = dialogView.findViewById(R.id.edt_password);

                        String edtEmail = edt_email.getText().toString();
                        String edtPassword = edt_password.getText().toString();

                        List<User> allUsers = userViewModel.getAllUsers().getValue();

                        for(User user: allUsers){
                            if (user.getEmail().equals(edtEmail)) {

                                user.setPassword(edtPassword);
                                userViewModel.updateUser(user);
                            }
                            Log.e("ResetPassword", user.toString());
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();

        alertDialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_UP_REQUEST_CODE){
            if(resultCode == RESULT_OK){

                User newUser = (User) data.getSerializableExtra("com.tc.parkingproject.REPLY");
                Log.e("SIGN_IN_ACTIVITY", newUser.toString());

                //insert new user account detail into database
                userViewModel.insert(newUser);
            }
        }
    }


}
