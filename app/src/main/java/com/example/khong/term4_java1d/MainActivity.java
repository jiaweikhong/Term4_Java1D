package com.example.khong.term4_java1d;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Firebase
    private static final int RC_SIGN_IN = 123;
    private FirebaseAuth auth;

    private TextView lblWelcome;

    private CardView gotoWashers;
    private CardView gotoDryers;
    private CustomShineButton washersNotifAllImgBtn;
    private CustomShineButton dryersNotifAllImgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Firebase Authentication
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            // signed in
            setupApp();
        } else {
            // not signed in
            createSignInIntent();
        }

    }

    public void setupApp() {

        lblWelcome = findViewById(R.id.lblWelcome);
        String user_displayName = auth.getCurrentUser().getDisplayName();
        lblWelcome.setText("Welcome, "+user_displayName);

        Random randomNumber = new Random();
        // get next next boolean value

        washersNotifAllImgBtn = findViewById(R.id.washersNotifAllImgBtn);
        washersNotifAllImgBtn.NotifStatus = randomNumber.nextBoolean();//set to random, it should be firebase
        washersNotifAllImgBtn.NotifState = findViewById(R.id.washerNotifState);

        dryersNotifAllImgBtn = findViewById(R.id.dryersNotifAllImgBtn);
        dryersNotifAllImgBtn.NotifStatus = randomNumber.nextBoolean();
        dryersNotifAllImgBtn.NotifState = findViewById(R.id.dryerNotifState);

        gotoWashers = findViewById(R.id.goToWashers);
        gotoWashers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Washer.class);
                startActivity(intent);
            }
        });

        gotoDryers = findViewById(R.id.goToDryers);
        gotoDryers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Dryer.class);
                startActivity(intent);

            }
        });

        washersNotifAllImgBtn.setUnavailable();
        dryersNotifAllImgBtn.setUnavailable();

        washersNotifAllImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                washersNotifAllImgBtn.WasherOnClickFunction();
            }
        });


        dryersNotifAllImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dryersNotifAllImgBtn.DryerOnClickFunction();

            }
        });

    }

    public void createSignInIntent() {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                //new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false)
                        .setLogo(R.drawable.ic_assets_washingmachinedark)
                        .setTosAndPrivacyPolicyUrls(
                                "https://termsfeed.com/blog/sample-terms-of-service-template/",
                                "https://www.freeprivacypolicy.com/blog/sample-privacy-policy-template/")
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in

                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();

                Toast toast_success = Toast.makeText(getApplicationContext(), "Logged in: " + user.getDisplayName(), Toast.LENGTH_SHORT);
                toast_success.show();

                setupApp();

            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                Toast toast = Toast.makeText(getApplicationContext(), "! Error: " + response.getError().getErrorCode(), Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_top_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Logout) {
            Intent intent = new Intent(MainActivity.this, LogoutActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        }

        if (id == R.id.ChooseBlock) {
            Intent intent = new Intent(MainActivity.this, ChooseBlock.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
