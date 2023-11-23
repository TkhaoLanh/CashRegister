package com.example.cashregister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ListView itemListView;
    TextView itemNameTextView, itemQuantityTextView, totalTextView;

    Button oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn, zeroBtn, clearBtn, buyBtn, managerBtn;
    ArrayList<Item> itemArrayList;
    ArrayList<History> purchaseArrayList;
    ItemBaseAdapter itemBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemListView = findViewById(R.id.itemList);

        itemNameTextView = findViewById(R.id.product_type);
        itemQuantityTextView = findViewById(R.id.quantity);
        totalTextView = findViewById(R.id.total_amount);

        // Initialize buttons and set their click listeners
        oneBtn = findViewById(R.id.oneBTN);
        twoBtn = findViewById(R.id.twoBTN);
        threeBtn = findViewById(R.id.threeBTN);
        fourBtn = findViewById(R.id.fourBTN);
        fiveBtn = findViewById(R.id.fiveBTN);
        sixBtn = findViewById(R.id.sixBTN);
        sevenBtn = findViewById(R.id.sevenBTN);
        eightBtn = findViewById(R.id.eightBTN);
        nineBtn = findViewById(R.id.nineBTN);
        zeroBtn = findViewById(R.id.zeroBTN);
        clearBtn = findViewById(R.id.clearBTN);
        buyBtn = findViewById(R.id.buyBTN);
        managerBtn = findViewById(R.id.manager_btn);

        // Set click listeners for all buttons
        oneBtn.setOnClickListener(this);
        twoBtn.setOnClickListener(this);
        threeBtn.setOnClickListener(this);
        fourBtn.setOnClickListener(this);
        fiveBtn.setOnClickListener(this);
        sixBtn.setOnClickListener(this);
        sevenBtn.setOnClickListener(this);
        eightBtn.setOnClickListener(this);
        nineBtn.setOnClickListener(this);
        zeroBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        buyBtn.setOnClickListener(this);
        managerBtn.setOnClickListener(this);

        itemArrayList = ((MyApp)getApplication()).getItemList();
        purchaseArrayList = ((MyApp)getApplication()).getPurchaseList();

        //create an adapter
       itemBaseAdapter = new ItemBaseAdapter(itemArrayList,this);

        itemListView.setAdapter(itemBaseAdapter);

        //set item click listener to display product name on textView
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemName = itemArrayList.get(position).getProductName();
                itemNameTextView.setText(selectedItemName);

            }
        });
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText){
            case "C":
                itemNameTextView.setText("");
                itemQuantityTextView.setText("");
                totalTextView.setText("");
                break;

            case "1": case "2": case "3": case "4": case "5":
            case "6": case "7": case "8": case "9": case "0":
                String currentQuantity = itemQuantityTextView.getText().toString();
                currentQuantity += buttonText;
                itemQuantityTextView.setText(currentQuantity);
                break;

            case "BUY":
                if(validate()){
                    purchase();
                }
                break;
            case "MANAGER":
                Log.d("ManagerButton", "Manager Button Clicked");
                Intent intent = new Intent(MainActivity.this, ManagerPanel.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    //validate function
    private boolean validate(){
        String name = itemNameTextView.getText().toString();
        String quantity = itemQuantityTextView.getText().toString();
        if(name.isEmpty() || quantity.isEmpty()){
            Toast.makeText(this,"All fields are required!!!",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    //calculate total
    private void purchase(){
        String selectedItem = itemNameTextView.getText().toString();
        int selectedQuantity = Integer.parseInt(itemQuantityTextView.getText().toString());

        //find the selected item in the list
        for(Item item : itemArrayList){
            if(item.getProductName().equals(selectedItem)){
                int stockQuantity = item.getQuantity();
                //check quantity in stock
                if(stockQuantity < selectedQuantity){
                    //show error message
                    Toast.makeText(this,"No enough quantiy in the stock!!!", Toast.LENGTH_LONG).show();
                    itemQuantityTextView.setText("");
                    return;
                }
                if(selectedQuantity <= 0){
                    //show error message
                    Toast.makeText(this,"Please enter quantity greater than 0!!!", Toast.LENGTH_LONG).show();
                    itemQuantityTextView.setText("");
                    return;
                }
                //get price from list
               double price = item.getProductPrice();
                //calculate total
                double total = price * selectedQuantity;
                // Format the total with two decimal places
                DecimalFormat decimalFormat = new DecimalFormat("#.00");
                String formattedTotal = decimalFormat.format(total);

                //display total
                totalTextView.setText(String.format("%s", formattedTotal));

                //update UI
                itemNameTextView.setText("");
                itemQuantityTextView.setText("");
                totalTextView.setText("");

                //update quantity in stock
                int newQuantity = item.getQuantity() - selectedQuantity;
                item.setQuantity(newQuantity);

                //update the adapter
                itemBaseAdapter.notifyDataSetChanged();

                //show alert to thank you user
                String msg = "Thank You for your purchase!" +"\n Your purchase is "+ selectedQuantity +" "+ selectedItem +" for $"+formattedTotal;
                showAlert(msg);

                //format date time
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                //add a new purchase in History
                History newPurchase = new History(selectedItem,Double.parseDouble(formattedTotal),selectedQuantity, dtf.format(now));
                purchaseArrayList.add(newPurchase);

            }
        }
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