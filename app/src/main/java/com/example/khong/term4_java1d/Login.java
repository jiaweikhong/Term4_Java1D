package com.example.khong.term4_java1d;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    Button textViewLogin;
    Button textViewRegister;
    EditText editTextUsername;
    EditText editTextUserPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUserName);
        editTextUserPassword = findViewById(R.id.editTextUserPassword);

        //TODO 1: Login button to main activity
        textViewLogin = findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editTextUsername.getText().toString();
                String password = editTextUserPassword.getText().toString();
                // TODO 1.1 for Tim: check whether the information is correct
                Log.i("Logcat", userName);
                Log.i("Logcat", password);
                if (userName.equals("i") && password.equals("i")) {
                    // TODO 1.2 for Pengfei: link this to the new intent -- activity main
                    Intent intentToMain = new Intent(Login.this, MainActivity.class);
                    startActivity(intentToMain);
                } else {
                    Toast.makeText(Login.this, "Your account have not been created",Toast.LENGTH_LONG).show();
                }
            }
        });

        // TODO 2: register Button to register
        textViewRegister = findViewById(R.id.textViewRegister);
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editTextUsername.getText().toString();
                String password = editTextUserPassword.getText().toString();
                // TODO 2.1 for Pengfei; link this to register

                Intent intentToMain = new Intent(Login.this, Register.class);
                startActivity(intentToMain);


            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu_top_bar);
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


}
