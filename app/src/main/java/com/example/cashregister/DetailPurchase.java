package com.example.cashregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailPurchase extends AppCompatActivity {
    TextView productNameTextView, totalPriceTextView, dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_purchase);

        // Find TextViews in your detail layout
        productNameTextView = findViewById(R.id.nameText);
        totalPriceTextView = findViewById(R.id.priceText);
        dateTextView = findViewById(R.id.date);

        // Retrieve the selected history item from intent extras
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("Selected purchase")) {
            History selectedHistory = (History) intent.getSerializableExtra("Selected purchase");

            // Display details of the selected purchase
            if (selectedHistory != null) {
                productNameTextView.setText("Product: "+selectedHistory.getProductName());
                totalPriceTextView.setText("Total Price: $"+String.valueOf(selectedHistory.getProductPrice()));
                dateTextView.setText("Purchased Date: "+selectedHistory.getDate());
            }
        }

    }
}