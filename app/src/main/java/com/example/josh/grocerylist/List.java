package com.example.josh.grocerylist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.josh.grocerylist.R.id.runningTotal;

public class List extends AppCompatActivity
{
    private double total = 0.00;
    private Button add;
    private ListView listView;
    private TextView runTotal;
    private ArrayList<GroceryItem> shoppingCart = new ArrayList<>();
    private ArrayAdapter<GroceryItem> adapter;

    private DecimalFormat precision = new DecimalFormat("#.##");
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        add = (Button) findViewById(R.id.addBtn);
        listView = (ListView) findViewById(R.id.list);
        runTotal = (TextView) findViewById(runningTotal);

        adapter = new GroceryListAdapter(this, R.layout.adapter_view, shoppingCart);
        listView.setAdapter(adapter);

        // ITEM DELETED FROM LIST
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           final int index, long arg3)
            {
                String item = shoppingCart.get(index).getName();
                AlertDialog.Builder adb=new AlertDialog.Builder(List.this);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + item + "?");
                final int positionToRemove = index;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        total -= deleteItem(index);
                        updateTotal(total);

                        adapter.notifyDataSetChanged();
                    }
                });
                adb.show();
                return true;
            }
        });



        View.OnClickListener addPushed = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(List.this, AddItem.class);
                startActivityForResult(intent, 2);
            }
        };
        add.setOnClickListener(addPushed);
    }

    // ITEM ADDED TO LIST
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2)
        {
            String name = data.getStringExtra("NAME");
            String price = data.getStringExtra("PRICE");
            String unit_of_measure = data.getStringExtra("UNIT_OF_MEASURE");
            String quantity = data.getStringExtra("QUANTITY");

            double itemPrice = Double.parseDouble(price);
            total += itemPrice * Double.parseDouble(quantity);

            GroceryItem groceryItem = new GroceryItem(name, itemPrice, Double.parseDouble(quantity), unit_of_measure);
            addItem(groceryItem);

        }
    }

    // Updates running total
    private void updateTotal(Double tot)
    {
        String listTotal = precision.format(tot);

        if(adapter.isEmpty())
        {
            shoppingCart.clear();
            total = 0.00;
            runTotal.setText("$0.00");
        }
        else
        {
            runTotal.setText("$" + listTotal);
        }
    }

    // Remove item from list
    private double deleteItem(int index)
    {
        double subtract = shoppingCart.get(index).getCost();
        shoppingCart.remove(index);
        adapter.notifyDataSetChanged();
        updateTotal(total);

        return subtract;
    }

    // Add item to list
    private void addItem(GroceryItem item)
    {
        shoppingCart.add(item);
       // listArray.add(item.toString());
        adapter.notifyDataSetChanged();
        updateTotal(total);
    }

}
