package com.example.josh.grocerylist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Josh on 7/17/2017.
 */

public class GroceryListAdapter extends ArrayAdapter<GroceryItem>
{
    private static final String TAG = "GroceryItemAdapter";
    private Context mContext;
    int mResource;
    private DecimalFormat precision = new DecimalFormat("#.00");

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
        double quantity = getItem(position).getQuanity();
        String uOfMeasure = getItem(position).getUnitOfMeasure();

        GroceryItem gItem = new GroceryItem(name, price, quantity, uOfMeasure);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView txtViewName = (TextView) convertView.findViewById(R.id.itemName);
        TextView txtViewPrice = (TextView) convertView.findViewById(R.id.itemPrice);
        TextView txtViewQuantity = (TextView) convertView.findViewById(R.id.itemQuantity);
        TextView txtViewTotal = (TextView) convertView.findViewById(R.id.totalPrice);

        txtViewName.setText(gItem.getName());
        txtViewPrice.setText("$" + Double.toString(gItem.getCost()) + " " + gItem.getUnitOfMeasure());

        String measure = "";

        if(gItem.getQuanity() == 1)
        {
            if(gItem.getUnitOfMeasure().equals("each"))
            {
                measure =("quantity: 1");
            }
            else if(gItem.getUnitOfMeasure().equals("per pound"))
            {
                measure = ("1 pound");
            }
        }
        else
        {
            if(gItem.getUnitOfMeasure().equals("each"))
            {
                measure = ("quantity: " + gItem.getQuanity());
            }
            else if(gItem.getUnitOfMeasure().equals("per pound"))
            {
                measure = (gItem.getQuanity() + " pounds");
            }
        }

        txtViewQuantity.setText(measure);
        txtViewTotal.setText("$" + precision.format(gItem.getCost() * gItem.getQuanity()));

        return convertView;

    }

}

