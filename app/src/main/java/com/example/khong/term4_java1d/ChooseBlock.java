package com.example.khong.term4_java1d;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class ChooseBlock extends AppCompatActivity {

    // Firebase
    private FirebaseAuth auth;
    private DatabaseReference userDatabase;
    private String user_uuid;

    private CardView blk55Card;
    private CardView blk57Card;
    private CardView blk59Card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_block);

        // Firebase Authentication
        auth = FirebaseAuth.getInstance();
        userDatabase = FirebaseDatabase.getInstance().getReference().child("users");

        user_uuid = auth.getCurrentUser().getUid();


        blk55Card = findViewById(R.id.blk55Card);
        blk57Card = findViewById(R.id.blk57Card);
        blk59Card = findViewById(R.id.blk59Card);

        blk55Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setUserBlock(55);
                writeNewUserBlockChoice("block_55");
                Intent intent = new Intent(ChooseBlock.this, MainActivity.class);   // Go to main activity
                startActivity(intent);
            }
        });

        blk57Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setUserBlock(57);
                writeNewUserBlockChoice("block_57");
                Intent intent = new Intent(ChooseBlock.this, MainActivity.class);   // Go to main activity
                startActivity(intent);
            }
        });

        blk59Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setUserBlock(59);
                writeNewUserBlockChoice("block_59");
                Intent intent = new Intent(ChooseBlock.this, MainActivity.class);   // Go to main activity
                startActivity(intent);
            }
        });
    }

    private void writeNewUserBlockChoice(String block_choice) {
        userDatabase.child(user_uuid).child("block_choice").setValue(block_choice);
    }

    /*
    protected void setUserBlock(int block) {
        switch(block) {
            case 55:
                    subscribeTopic("block_55");
                    unsubscribeTopic("block_57");
                    unsubscribeTopic("block_59");
                break;
            case 57:
                    unsubscribeTopic("block_55");
                    subscribeTopic("block_57");
                    unsubscribeTopic("block_59");
                break;
            case 59:
                    unsubscribeTopic("block_55");
                    unsubscribeTopic("block_57");
                    subscribeTopic("block_59");
                break;
        }
        Toast.makeText(ChooseBlock.this, "You have chosen: Block "+block, Toast.LENGTH_SHORT).show();
    }
    */

}
