package com.example.josh.grocerylist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddItem extends AppCompatActivity
{
    private Button addItemBtn;
    private TextView itemName;
    private TextView itemPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        addItemBtn = (Button) findViewById(R.id.addToList);
        itemName = (TextView) findViewById(R.id.nameInput);
        itemPrice = (TextView) findViewById(R.id.priceInput);

        View.OnClickListener clickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name = (String) itemName.getText().toString();
                String price = (String) itemPrice.getText().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("NAME", name);
                resultIntent.putExtra("PRICE", price);

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        };

        addItemBtn.setOnClickListener(clickListener);
    }
}
