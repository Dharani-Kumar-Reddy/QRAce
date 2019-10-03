package com.example.qrace;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class profile extends AppCompatActivity {
    private EditText editText;
    private Button generatebutton;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        generatebutton=findViewById(R.id.button13);
        editText=findViewById(R.id.editText5);
        imageView=findViewById(R.id.qr);

        generatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string=editText.getText().toString().trim();
                if(!string.matches("")){
                    MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
                    try{
                        BitMatrix bitMatrix=multiFormatWriter.encode(string, BarcodeFormat.QR_CODE,800,800);
                        BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
                        Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
                        imageView.setImageBitmap(bitmap);
                    }
                    catch(WriterException e){
                        e.printStackTrace();
                    }
                    editText.onEditorAction(EditorInfo.IME_ACTION_DONE);

                }
                else{
                  Toast.makeText(getApplicationContext(),"PLEASE ENTER TEXT",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void goto_contact(View v){

        startActivity(new Intent(profile.this,contact.class));
        finish();
    }
    public void back_home(View v){

        startActivity(new Intent(profile.this,MainMenu.class));
    }
    public void goto_email(View v){
        startActivity(new Intent(profile.this,email.class));
        finish();
    }

}
