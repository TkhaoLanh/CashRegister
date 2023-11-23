package com.example.cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemBaseAdapter extends BaseAdapter {

    ArrayList<Item> itemArrayList;

    Context activityContext;

    public ItemBaseAdapter(ArrayList<Item> itemArrayList, Context activityContext) {
        this.itemArrayList = itemArrayList;
        this.activityContext = activityContext;
    }

    @Override
    public int getCount() {
        return itemArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemArrayList.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View itemRowView = LayoutInflater.from(activityContext).inflate(R.layout.item_list_row,viewGroup,false);
        TextView iName =itemRowView.findViewById(R.id.itemName_row);
        TextView iQuantity = itemRowView.findViewById(R.id.itemQuantity_row);
        TextView iPrice = itemRowView.findViewById(R.id.itemPrice_row);

        iName.setText(itemArrayList.get(i).getProductName());
        iQuantity.setText(String.valueOf(itemArrayList.get(i).getQuantity()));
        iPrice.setText(String.valueOf(itemArrayList.get(i).getProductPrice()));
        return itemRowView;
    }
}
