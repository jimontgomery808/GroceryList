package com.example.josh.grocerylist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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

        View.OnClickListener clickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(List.this, AddItem.class);
                startActivityForResult(intent, 2);
            }
        };

        add.setOnClickListener(clickListener);
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

            runTotal.setText("$" + Double.toString(total));
        }
    }
}
