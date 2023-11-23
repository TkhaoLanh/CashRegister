package com.example.cashregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoryList extends AppCompatActivity {

    ListView historyListView;

    ArrayList<History> purchaseArrayList;

    HistoryBaseAdapter historyBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        historyListView = findViewById(R.id.historyListView);

        purchaseArrayList = ((MyApp)getApplication()).getPurchaseList();
        //create an adapter
        historyBaseAdapter = new HistoryBaseAdapter(purchaseArrayList,this);

        historyListView.setAdapter(historyBaseAdapter);

        //set on click listener
        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                History selectedHistory = purchaseArrayList.get(position);
                //create new Intent to navigate Detail Purchase
                Intent detailIntent = new Intent(HistoryList.this, DetailPurchase.class);
                //pass the selected purchase to DetailPurchase
                detailIntent.putExtra("Selected purchase", (Serializable) selectedHistory);
                //start activity
                startActivity(detailIntent);
            }
        });
    }
}