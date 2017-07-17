package com.example.josh.grocerylist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Josh on 7/17/2017.
 */

public class GroceryListAdapter extends ArrayAdapter<GroceryItem>
{
    private static final String TAG = "GroceryItemAdapter";
    private Context mContext;
    int mResource;

    public GroceryListAdapter(Context context, int resource, ArrayList<GroceryItem> items)
    {
        super(context, resource, items);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        String name  = getItem(position).getName();
        double price = getItem(position).getCost();
        int quantity = getItem(position).getQuanity();

        GroceryItem gItem = new GroceryItem(name, price, quantity);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView txtViewName = (TextView) convertView.findViewById(R.id.itemName);
        TextView txtViewPrice = (TextView) convertView.findViewById(R.id.itemPrice);
        TextView txtViewQuantity = (TextView) convertView.findViewById(R.id.itemQuantity);
        TextView txtViewTotal = (TextView) convertView.findViewById(R.id.totalPrice);

        txtViewName.setText(gItem.getName());
        txtViewPrice.setText(Double.toString(gItem.getCost()));
        txtViewQuantity.setText(Integer.toString(gItem.getQuanity()));
        txtViewTotal.setText(Double.toString((gItem.getCost()) * gItem.getQuanity()));

        return convertView;

    }
}
