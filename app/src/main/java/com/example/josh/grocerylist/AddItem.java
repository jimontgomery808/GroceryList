package com.example.josh.grocerylist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class AddItem extends AppCompatActivity
{
    private Button addItemBtn;
    private EditText itemName;
    private EditText itemPrice;
    private EditText itemQuantity;
    private Button eachButton;
    private Button perPoundButton;
    private String name;
    private String price;
    private String unit_measure;
    private String quantity;
    private DecimalFormat precision = new DecimalFormat("0.00");
    private boolean eachClicked = false;
    private boolean poundClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        addItemBtn = (Button) findViewById(R.id.addToList);
        itemName = (EditText) findViewById(R.id.nameInput);
        itemPrice = (EditText) findViewById(R.id.priceInput);
        eachButton = (Button) findViewById(R.id.eachButton);
        perPoundButton = (Button) findViewById(R.id.poundButton);
        //unitOfMeasure = (Spinner) findViewById(R.id.unit_of_measure);
        itemQuantity = (EditText) findViewById(R.id.quantity);
        itemQuantity.setVisibility(View.INVISIBLE);

        eachButton.setBackgroundColor(Color.parseColor("lightgrey"));
        perPoundButton.setBackgroundColor(Color.parseColor("lightgrey"));

        eachButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                eachButton.setBackgroundColor(Color.parseColor("blue"));
                eachButton.setTextColor(Color.parseColor("white"));
                perPoundButton.setBackgroundColor(Color.parseColor("lightgray"));
                perPoundButton.setTextColor(Color.parseColor("black"));
                itemQuantity.setText(null);
                itemQuantity.setVisibility(View.VISIBLE);
                itemQuantity.setInputType(InputType.TYPE_CLASS_NUMBER);
                itemQuantity.setHint("quantity");
                eachClicked = true;
                poundClicked = false;
            }
        });

        perPoundButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                perPoundButton.setBackgroundColor(Color.parseColor("blue"));
                perPoundButton.setTextColor(Color.parseColor("white"));
                eachButton.setTextColor(Color.parseColor("black"));
                eachButton.setBackgroundColor(Color.parseColor("lightgray"));
                itemQuantity.setText(null);
                itemQuantity.setVisibility(View.VISIBLE);
                int maxLengthofEditText = 4;
                itemQuantity.setInputType(InputType.TYPE_CLASS_NUMBER| InputType.TYPE_NUMBER_FLAG_DECIMAL);
                itemQuantity.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLengthofEditText)});
                itemQuantity.setHint("weight (lbs)");
                poundClicked = true;
                eachClicked = false;
            }
        });

        itemPrice.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(5,2)});

        View.OnClickListener returnToList = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(itemName.getText().toString().matches("")| itemPrice.getText().toString().matches("") | itemQuantity.getText().toString().matches(""))
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Please fill out all required data";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else
                {
                    String name = (String) itemName.getText().toString();
                    String price = (String) itemPrice.getText().toString();
                    String unit_measure ="";
                    String quantity = (String) itemQuantity.getText().toString();

                    if(eachClicked)
                    {
                        unit_measure = "each";
                    }
                    else if(poundClicked)
                    {
                        unit_measure = "per pound";
                    }

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("NAME", name);
                    resultIntent.putExtra("PRICE", price);
                    resultIntent.putExtra("UNIT_OF_MEASURE", unit_measure);
                    resultIntent.putExtra("QUANTITY", quantity);

                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            }
        };

        addItemBtn.setOnClickListener(returnToList);
    }
}
