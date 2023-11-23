package com.example.cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryBaseAdapter extends BaseAdapter {
    ArrayList<History> purchaseList;

    Context activityContext;

    public HistoryBaseAdapter(ArrayList<History> purchaseList, Context activityContext) {
        this.purchaseList = purchaseList;
        this.activityContext = activityContext;
    }

    @Override
    public int getCount() {
        return purchaseList.size();
    }

    @Override
    public Object getItem(int position) {
        return purchaseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View historyRowView = LayoutInflater.from(activityContext).inflate(R.layout.item_list_row,viewGroup,false);
        TextView hName = historyRowView.findViewById(R.id.itemName_row);
        TextView hQuantity = historyRowView.findViewById(R.id.itemQuantity_row);
        TextView hPrice = historyRowView.findViewById(R.id.itemPrice_row);

        hName.setText(purchaseList.get(i).getProductName());
        hQuantity.setText(String.valueOf(purchaseList.get(i).getQuantity()));
        hPrice.setText(String.valueOf(purchaseList.get(i).getProductPrice()));

        return historyRowView;
    }
}
