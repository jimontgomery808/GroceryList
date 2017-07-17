package com.example.josh.grocerylist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

import java.text.DecimalFormat;

public class AddItem extends AppCompatActivity
{
    private Button addItemBtn;
    private EditText itemName;
    private EditText itemPrice;
    private NumberPicker numberPicker;
    private Spinner measureSpinner;
    private DecimalFormat precision = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        addItemBtn = (Button) findViewById(R.id.addToList);
        itemName = (EditText) findViewById(R.id.nameInput);
        itemPrice = (EditText) findViewById(R.id.priceInput);
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        measureSpinner = (Spinner) findViewById(R.id.measure_spinner);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        //numberPicker.setWrapSelectorWheel(false);

        ArrayAdapter<CharSequence> meausureAdapter = ArrayAdapter.createFromResource(this, R.array.measure,android.R.layout.simple_spinner_item);

        meausureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        measureSpinner.setAdapter(meausureAdapter);

        itemPrice.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(5,2)});

        View.OnClickListener returnToList = new View.OnClickListener()
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

        addItemBtn.setOnClickListener(returnToList);

        View.OnClickListener startBarcodeScannerActivity = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AddItem.this, BarcodeScanner.class);
                startActivity(intent);
            }
        };


    }
}
