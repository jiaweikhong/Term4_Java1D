package com.example.khong.term4_java1d;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class ChooseBlock extends AppCompatActivity {

    private CardView blk55Card;
    private CardView blk57Card;
    private CardView blk59Card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_block);

        blk55Card = findViewById(R.id.blk55Card);
        blk57Card = findViewById(R.id.blk57Card);
        blk59Card = findViewById(R.id.blk59Card);

        blk55Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: store value 55 into user's firebase?
                Toast.makeText(ChooseBlock.this, "You have chosen Block 55", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ChooseBlock.this, MainActivity.class);   // Go to main activity
                startActivity(intent);
            }
        });

        blk57Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: store value 57 into user's firebase?
                Toast.makeText(ChooseBlock.this, "You have chosen Block 57", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ChooseBlock.this, MainActivity.class);   // Go to main activity
                startActivity(intent);
            }
        });

        blk59Card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: store value 59 into user's firebase?
                Toast.makeText(ChooseBlock.this, "You have chosen Block 59", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ChooseBlock.this, MainActivity.class);   // Go to main activity
                startActivity(intent);
            }
        });
    }
}
