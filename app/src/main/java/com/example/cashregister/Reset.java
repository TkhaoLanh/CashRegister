package com.example.cashregister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Reset extends AppCompatActivity {

    EditText newQtyText;
    Button OkBtn, CancelBtn;
    ListView itemListView;
    ArrayList<Item> itemArrayList;
    ItemBaseAdapter itemBaseAdapter;

    Item selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        newQtyText = findViewById(R.id.newQty_Text);
        OkBtn = findViewById(R.id.ok_btn);
        CancelBtn = findViewById(R.id.cancel_button);
        itemListView = findViewById(R.id.itemLst);

        itemArrayList = ((MyApp)getApplication()).getItemList();

        //create an adapter
        itemBaseAdapter = new ItemBaseAdapter(itemArrayList,this);

        itemListView.setAdapter(itemBaseAdapter);
        //set item click listener to display product name on textView
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //get selected item
                selectedItem = itemArrayList.get(position);

            }
        });

        //set clicklister on each button
        //OK button
        OkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userQuantityStr = newQtyText.getText().toString();
                if(validate()){
                    int userQuantity = Integer.parseInt(userQuantityStr);
                    //get current quantity
                    int currentQuantity = selectedItem.getQuantity();
                    int newQty = userQuantity + currentQuantity;
                    //update selected item's quantity
                    selectedItem.setQuantity(newQty);
                    //update the adapter
                    itemBaseAdapter.notifyDataSetChanged();

                    //show alert to thank you user
                    String msg = selectedItem.getProductName() +" is updated successfully";
                    showAlert(msg);
                    //clear editText
                    newQtyText.setText("");
                }
            }
        });


        //cancel button
        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reset newQuantity EditText
                newQtyText.setText("");
                //Return to Manager Panel
                Intent manager = new Intent(Reset.this, ManagerPanel.class);
                startActivity(manager);
            }
        });

    }

    //validate function
    //validate function
    private boolean validate() {
        String quantity = newQtyText.getText().toString();
        //if quantity is empty
        if (quantity.isEmpty()) {
            Toast.makeText(this, "Please input new quantity!!!", Toast.LENGTH_LONG).show();
            return false;
        }
        //check if user doesn't select item
        if (selectedItem == null) {
            Toast.makeText(this, "Please select an item!!!", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    //Alert function
    private void showAlert(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg);
        builder.create();

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}