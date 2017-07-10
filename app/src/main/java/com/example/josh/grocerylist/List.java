package com.example.josh.grocerylist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class List extends AppCompatActivity
{
    private Button add;
    private ListView listView;
    private ArrayList<String> listArray = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        add = (Button) findViewById(R.id.addBtn);
        listView = (ListView) findViewById(R.id.list);

        adapter = new ArrayAdapter<String>(this, R.layout.activity_list, listArray);
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

            GroceryItem groceryItem = new GroceryItem(name, price);

            listArray.add(groceryItem.toString());
        }
    }
}
