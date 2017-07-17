package com.example.josh.grocerylist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

;

public class BarcodeScanner extends AppCompatActivity
{
    private Button scanBtn;
    private ImageView barcodeImage;
    private TextView txtView;
    private Bitmap myBitmap;
    private Frame frame;
    private SparseArray<Barcode> barcodes;
    private BarcodeDetector detector;
    private Barcode thisCode;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);

        txtView = (TextView) findViewById(R.id.txtContent);
        scanBtn = (Button) findViewById(R.id.button);
        scanBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        barcodeImage = (ImageView) findViewById(R.id.imgview);
        myBitmap = BitmapFactory.decodeResource(
                getApplicationContext().getResources(),
                R.drawable.puppy);
        barcodeImage.setImageBitmap(myBitmap);

        detector = new BarcodeDetector.Builder(getApplicationContext()).setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE).build();

        if(!detector.isOperational())
        {
            txtView.setText("Could not set up the detector!");
            return;
        }

        frame = new Frame.Builder().setBitmap(myBitmap).build();
        barcodes = detector.detect(frame);

        thisCode = barcodes.valueAt(0);
        txtView.setText(thisCode.rawValue);
    }
}
