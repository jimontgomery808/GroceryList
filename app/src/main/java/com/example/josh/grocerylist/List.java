package com.example.josh.grocerylist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class List extends AppCompatActivity
{
    private double total = 0.00;
    private Button add;
    private ListView listView;
    private TextView runTotal;
    private ArrayList<GroceryItem> shoppingCart = new ArrayList<>();
    private ArrayList<String> listArray = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private GroceryItem tempItem;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        add = (Button) findViewById(R.id.addBtn);
        listView = (ListView) findViewById(R.id.list);
        runTotal = (TextView) findViewById(R.id.runningTotal);

        adapter = new ArrayAdapter<String>(this, R.layout.single_item, listArray);
        listView.setAdapter(adapter);

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
                        total -= Double.parseDouble(shoppingCart.get(index).getCost());
                        updateTotal(Double.toString(total));
                        listArray.remove(index);
                        shoppingCart.remove(index);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2)
        {
            String name = data.getStringExtra("NAME");
            String price = data.getStringExtra("PRICE");
            double runningTotal = Double.parseDouble(price);
            total += runningTotal;

            Log.v("HERE", name + ", " + price);

            GroceryItem groceryItem = new GroceryItem(name, price);
            shoppingCart.add(groceryItem);

            listArray.add(groceryItem.toString());
            adapter.notifyDataSetChanged();

            updateTotal("$" + Double.toString(total));
        }
    }

    private void updateTotal(String total)
    {
        runTotal.setText(total);
    }

}
