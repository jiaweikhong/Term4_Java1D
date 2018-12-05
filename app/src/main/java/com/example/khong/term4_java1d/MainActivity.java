package com.example.khong.term4_java1d;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Firebase
    private static final int RC_SIGN_IN = 123;
    private FirebaseAuth auth;
    private DatabaseReference userDatabase;
    private String userUuid;

    private String userBlockChoice;

    private TextView lblWelcome;
    private String user_displayName;

    private CardView gotoWashers;
    private CardView gotoDryers;
    private CustomShineButton washersNotifyAllImgBtn;
    private CustomShineButton dryersNotifyAllImgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

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

    /*
     * Setup MainActivity here
     */
    public void setupApp() {


        setContentView(R.layout.activity_main);

        // Navigation to other machine list

        gotoWashers = findViewById(R.id.goToWashers);
        gotoDryers = findViewById(R.id.goToDryers);


        washersNotifyAllImgBtn = findViewById(R.id.washersNotifyAllImgBtn);
        dryersNotifyAllImgBtn = findViewById(R.id.dryersNotifyAllImgBtn);

        // Firebase

        userDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        userUuid = auth.getCurrentUser().getUid();

        DatabaseReference userBlockChoiceRef = userDatabase.child(userUuid).child("block_choice");
        ValueEventListener blockChoiceListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    Intent intent = new Intent(MainActivity.this, ChooseBlock.class);
                    startActivity(intent);
                } else {
                    userBlockChoice = dataSnapshot.getValue().toString();
                    // Welcome Text
                    lblWelcome = findViewById(R.id.lblWelcome);
                    user_displayName = auth.getCurrentUser().getDisplayName();
                    String welcomeText = userBlockChoice.replace("_", " ") + " welcomes " + user_displayName;
                    welcomeText = capitalizeFirstLetter(welcomeText);
                    lblWelcome.setText(welcomeText);
                    setupBlockDatabase();

                    washersNotifyAllImgBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            washersNotifyAllImgBtn.washerOnClickFunction(userBlockChoice);
                        }
                    });


                    dryersNotifyAllImgBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dryersNotifyAllImgBtn.dryerOnClickFunction(userBlockChoice);
                        }
                    });

                    gotoDryers.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Intent intent = new Intent(MainActivity.this, Dryer.class);
                            Intent intent = new Intent(MainActivity.this, DryerView.class);
                            intent.putExtra("block_choice", userBlockChoice);
                            startActivity(intent);
                        }
                    });

                    gotoWashers.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, WasherView.class);
                            intent.putExtra("block_choice", userBlockChoice);
                            startActivity(intent);
                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast toast_error = Toast.makeText(getApplicationContext(), "Database Error: " + databaseError.toString(), Toast.LENGTH_SHORT);
                toast_error.show();
            }
        };
        userBlockChoiceRef.addValueEventListener(blockChoiceListener);

    }

    public void setupBlockDatabase() {
        DatabaseReference blockDatabase = FirebaseDatabase.getInstance().getReference().child(userBlockChoice);
        ValueEventListener blockDatabaseListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                populateMachinesCount(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast toast_error = Toast.makeText(getApplicationContext(), "Database Error: " + databaseError.toString(), Toast.LENGTH_SHORT);
                toast_error.show();
            }
        };
        blockDatabase.addValueEventListener(blockDatabaseListener);
    }

    public void populateMachinesCount(DataSnapshot dataSnapshot) {
        TextView washersCount = findViewById(R.id.noOfWashers);
        TextView dryersCount = findViewById(R.id.noOfDryers);

        DataSnapshot washersData = dataSnapshot.child("washers");
        DataSnapshot dryersData = dataSnapshot.child("dryers");

        int washersCountNo = 0;
        int dryersCountNo = 0;

        long totalWashers = washersData.getChildrenCount();
        long totalDryers = dryersData.getChildrenCount();

        long unixTime = System.currentTimeMillis() / 1000L;

        for (DataSnapshot i : washersData.getChildren()) {
            String timecode = i.child("startTime").getValue().toString();
            int parsedTimecode = Integer.parseInt(timecode);
            if (unixTime - parsedTimecode > 2700) {
                washersCountNo++;
            }
        }

        for (DataSnapshot j : dryersData.getChildren()) {
            String timecode = j.child("startTime").getValue().toString();
            int parsedTimecode = Integer.parseInt(timecode);
            if (unixTime - parsedTimecode > 2700) {
                dryersCountNo++;
            }
        }


        if (washersCountNo > 0) {
            washersNotifyAllImgBtn.NotifyStatus = false;
        } else {
            washersNotifyAllImgBtn.NotifyStatus = true;
        }
        washersNotifyAllImgBtn.NotifyState = findViewById(R.id.washerNotifyState);

        if (dryersCountNo > 0) {
            dryersNotifyAllImgBtn.NotifyStatus = false;
        } else {
            dryersNotifyAllImgBtn.NotifyStatus = true;
        }
        dryersNotifyAllImgBtn.NotifyState = findViewById(R.id.dryerNotifyState);


        // set button state from Firebase

        DatabaseReference userBlockChoiceRef = userDatabase.child(userUuid).child("subscriptions");
        ValueEventListener blockChoiceListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int washerNEnabled = 0;
                int dryerNEnabled = 0;

                for (DataSnapshot subscription : dataSnapshot.getChildren()) {
                    String subTopicName = subscription.getKey();
                    String subBlockName = subTopicName.substring(0,2);
                    String correctComparison = userBlockChoice.substring(6,8);

                    String subStatus = subscription.getValue().toString();
                    if (subBlockName.equals(correctComparison)) {
                        if (subTopicName.substring(3,4).equals("d")) {
                            if (subStatus.equals("true")) {
                                dryerNEnabled++;
                            }
                        }
                        if (subTopicName.substring(3,4).equals("w")) {
                            if (subStatus.equals("true")) {
                                washerNEnabled++;
                            }
                        }

                    }

                }

                Log.e("MainActivity", Integer.toString(washerNEnabled));
                Log.e("MainActivity", Integer.toString(dryerNEnabled));

                washersNotifyAllImgBtn.setUnavailable();
                dryersNotifyAllImgBtn.setUnavailable();

                if (washerNEnabled>11) {
                    washersNotifyAllImgBtn.setChecked(true);
                    washersNotifyAllImgBtn.washerOnClickFunction(userBlockChoice);
                }

                if (dryerNEnabled>8) {
                    dryersNotifyAllImgBtn.setChecked(true);
                    dryersNotifyAllImgBtn.dryerOnClickFunction(userBlockChoice);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        userBlockChoiceRef.addValueEventListener(blockChoiceListener);

        washersCount.setText(Integer.toString(washersCountNo) + "/" + Long.toString(totalWashers));
        dryersCount.setText(Integer.toString(dryersCountNo) + "/" + Long.toString(totalDryers));

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

        if (id == R.id.Statistics) {
            Intent intent = new Intent (MainActivity.this, UserUsage.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }
}
