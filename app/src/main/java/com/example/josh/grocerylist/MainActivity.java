package com.example.josh.grocerylist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private Button createList;
    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createList = (Button)findViewById(R.id.createList);
        test = (TextView) findViewById(R.id.textView);

        View.OnClickListener createListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                test.setText("Pushed");
            }
        };

        createList.setOnClickListener(createListener);
    }
}
