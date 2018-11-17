package com.example.khong.term4_java1d;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    String blockNumber = "";
    EditText editTextUserNameRegister;
    EditText editTextUserPasswordRegister;
    TextView textViewRegister1;
    String userName;
    String userPassword;

    String sharedPrefFile = "com.example.andriod.mainsharedprefs";
    SharedPreferences mPreferences;

    // TODO 1: get Blocknumber
    void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton55:
                if (checked) {blockNumber = "55";}
            case R.id.radioButton57:
                if (checked) {blockNumber = "57";}
            case R.id.radioButton59:
                if (checked) {blockNumber = "59";}
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //TODO 3; shared Preferences
        mPreferences = getSharedPreferences(sharedPrefFile,
                MODE_PRIVATE);

        // TODO 2: Register button
        editTextUserNameRegister = findViewById(R.id.editTextUserNameRegister);
        editTextUserPasswordRegister = findViewById(R.id.editTextUserPasswordRegister);
        textViewRegister1 = findViewById(R.id.Register);
        textViewRegister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 2.1 for Tim: adding the information to the fire base
                userName = editTextUserNameRegister.getText().toString();
                userPassword = editTextUserPasswordRegister.getText().toString();
                // blockNumber;

                //TODO 2.2 for Pengfei: save the information in shared preference
                //done

                //TODO 2.3 for Pengfei: create another intent and move to main activity.
                Toast.makeText(Register.this, "Your account has been created, Welcome User!",Toast.LENGTH_LONG).show();
                Intent intentToMain = new Intent(Register.this, MainActivity.class);
                startActivity(intentToMain);
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencessEditor = mPreferences.edit();
        preferencessEditor.putString("username", editTextUserNameRegister.getText().toString());
        preferencessEditor.putString("userPassword", editTextUserPasswordRegister.getText().toString());
        preferencessEditor.putString("blockNumber", blockNumber);
        preferencessEditor.apply();
    }
}
