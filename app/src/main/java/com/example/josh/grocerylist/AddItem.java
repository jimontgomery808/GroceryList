package com.example.josh.grocerylist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DecimalFormat;

public class AddItem extends AppCompatActivity
{
    private Button addItemBtn;
    private EditText itemName;
    private EditText itemPrice;
    private Spinner unitOfMeasure;
    private EditText itemQuantity;
    private String name;
    private String price;
    private String unit_measure;
    private String quantity;
    private DecimalFormat precision = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        addItemBtn = (Button) findViewById(R.id.addToList);
        itemName = (EditText) findViewById(R.id.nameInput);
        itemPrice = (EditText) findViewById(R.id.priceInput);
        unitOfMeasure = (Spinner) findViewById(R.id.unit_of_measure);
        itemQuantity = (EditText) findViewById(R.id.quantity);
        itemQuantity.setVisibility(View.INVISIBLE);

        unitOfMeasure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(unitOfMeasure.getSelectedItem().toString().equals("each"))
                {
                    itemQuantity.setText(null);
                    itemQuantity.setVisibility(View.VISIBLE);
                    itemQuantity.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
                else if(unitOfMeasure.getSelectedItem().toString().equals("per pound"))
                {
                    itemQuantity.setText(null);
                    itemQuantity.setVisibility(View.VISIBLE);
                    int maxLengthofEditText = 3;
                    itemQuantity.setInputType(InputType.TYPE_CLASS_NUMBER| InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    itemQuantity.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLengthofEditText)});
                }
                else if(unitOfMeasure.getSelectedItem().toString().equals("select unit of measure"))
                {
                    itemQuantity.setText(null);
                    itemQuantity.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        ArrayAdapter<CharSequence> meausureAdapter = ArrayAdapter.createFromResource(this, R.array.measure, R.layout.my_spinner_textview);

        meausureAdapter.setDropDownViewResource(R.layout.my_spinner_textview);
        unitOfMeasure.setAdapter(meausureAdapter);

        itemPrice.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(5,2)});

        View.OnClickListener returnToList = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name = (String) itemName.getText().toString();
                String price = (String) itemPrice.getText().toString();
                String unit_measure = unitOfMeasure.getSelectedItem().toString();
                String quantity = (String) itemQuantity.getText().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("NAME", name);
                resultIntent.putExtra("PRICE", price);
                resultIntent.putExtra("UNIT_OF_MEASURE", unit_measure);
                resultIntent.putExtra("QUANTITY", quantity);

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
