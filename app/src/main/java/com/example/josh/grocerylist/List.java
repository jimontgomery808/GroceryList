package com.example.josh.grocerylist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.josh.grocerylist.R.id.runningTotal;

public class List extends AppCompatActivity
{
    private double total = 0.00;
    private ListView listView;
    private TextView runTotal;
    private ArrayList<GroceryItem> shoppingCart = new ArrayList<>();
    private ArrayAdapter<GroceryItem> adapter;
    private final String LIST_KEY = "listKey";
    private final int ADD_NEW = 1;
    private final int EDIT = 2;
    private FloatingActionButton newItem;
    private DecimalFormat precision = new DecimalFormat("#.00");



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.list);
        runTotal = (TextView) findViewById(runningTotal);
        newItem = (FloatingActionButton) findViewById(R.id.fab);

        // set custom GroceryItem adapter
        adapter = new GroceryListAdapter(this, R.layout.adapter_view, shoppingCart);
        // set the adapter to the list view
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
                adb.setMessage("Are you sure you want to delete " + item + " from the list?");

                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // Remove grocery list item selected
                        deleteItem(index);
                        adapter.notifyDataSetChanged();
                    }
                });
                adb.show();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(List.this, AddItem.class);

                String editName          = shoppingCart.get(position).getName();
                String editCost          = Double.toString(shoppingCart.get(position).getCost());
                String editUnitOfMeasure = shoppingCart.get(position).getUnitOfMeasure();
                String editQuantity      = Double.toString(shoppingCart.get(position).getQuanity());

                intent.putExtra("NAME", editName);
                intent.putExtra("PRICE", editCost);
                intent.putExtra("UNIT_OF_MEASURE", editUnitOfMeasure);
                intent.putExtra("QUANTITY", editQuantity);
                intent.putExtra("POSITION", position);

                startActivityForResult(intent, EDIT);

            }
        });

        // Set click listener for add button
        View.OnClickListener addPushed = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(List.this, AddItem.class);
                startActivityForResult(intent, ADD_NEW);
            }
        };
        newItem.setOnClickListener(addPushed);

        // When screen orientation changes, this will re-initialize shopping cart
        if(savedInstanceState != null)
        {
            shoppingCart = savedInstanceState.getParcelableArrayList(LIST_KEY);
            adapter = new GroceryListAdapter(this, R.layout.adapter_view, shoppingCart);
            listView.setAdapter(adapter);
            updateTotalOnConfigChange();
        }

    }

    // When screen orientation changes, this will save shopping cart
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(LIST_KEY, shoppingCart);
    }

    // GroceryItem added from AddItem Activity will be inserted into shopping cart
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(data != null)
        {
            String name = data.getStringExtra("NAME");
            String price = data.getStringExtra("PRICE");
            String unit_of_measure = data.getStringExtra("UNIT_OF_MEASURE");
            String quantity = data.getStringExtra("QUANTITY");
            GroceryItem groceryItem = new GroceryItem(name, Double.parseDouble(price), Double.parseDouble(quantity), unit_of_measure);

            switch (requestCode)
            {

                case ADD_NEW: addItem(groceryItem);
                    break;

                case EDIT: String position = data.getStringExtra("POSITION");
                    break;

            }
        }

    }

    // Updates running total
    private void updateTotal(Double tot)
    {
        total += tot;
        String listTotal = precision.format(total);

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
    private void deleteItem(int index)
    {
        double priceSubtracted = (shoppingCart.get(index).getCost() * shoppingCart.get(index).getQuanity()) * -1;
        shoppingCart.remove(index);
        adapter.notifyDataSetChanged();
        updateTotal(priceSubtracted);
    }

    // Add item to list
    private void addItem(GroceryItem item)
    {
        Double itemTotal = (item.getCost() * item.getQuanity());
        shoppingCart.add(item);
        adapter.notifyDataSetChanged();
        updateTotal(itemTotal);
    }

    private void editItem(int position, GroceryItem item)
    {
        shoppingCart.set(position, item);
        updateTotalOnConfigChange();
    }

    // Re-initalizes all shopping cart values after screen orientation
    private void updateTotalOnConfigChange()
    {
        Double cost = 0.00;

        if(shoppingCart.isEmpty())
        {
            updateTotal(0.00);
        }
        else
        {
            for(GroceryItem item: shoppingCart)
            {
                cost += (item.getCost() * item.getQuanity());
            }

            updateTotal(cost);
        }
    }

}
