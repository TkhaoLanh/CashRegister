package com.example.cashregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerPanel extends AppCompatActivity {

    Button history_btn, reset_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_panel);

        history_btn = findViewById(R.id.history_btn);
        reset_btn = findViewById(R.id.reset_btn);

        //set clickListener for each button
        history_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent history = new Intent(ManagerPanel.this, HistoryList.class);
                startActivity(history);
            }
        });

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reset = new Intent(ManagerPanel.this, Reset.class);
                startActivity(reset);
            }
        });
    }
}