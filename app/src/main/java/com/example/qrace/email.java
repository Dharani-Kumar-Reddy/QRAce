package com.example.qrace;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class email extends AppCompatActivity {
    private EditText email;
    private ImageView qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        email = findViewById(R.id.nameText);
        qr=findViewById(R.id.qr);
        File file = new File(this.getFilesDir(), "my_email.txt");
        if (file.exists()) {
            int length = (int) file.length();
            byte[] bytes = new byte[length];
            try {
                FileInputStream in = new FileInputStream(file);
                in.read(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String final_string = new String(bytes);
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(final_string, BarcodeFormat.QR_CODE, 800, 800);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                qr.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
            email.setText(final_string.substring(3));


        }
    }
    public void back_home(View v) {
        startActivity(new Intent(email.this, MainMenu.class));
        finish();
    }
    public void generate(View v) {
        email = findViewById(R.id.nameText);
        qr=findViewById(R.id.qr);
        email.onEditorAction(EditorInfo.IME_ACTION_DONE);
        File file = new File(this.getFilesDir(), "my_email.txt");
        if (file.exists()) {
            int length = (int) file.length();
            byte[] bytes = new byte[length];
            try {
                FileInputStream in = new FileInputStream(file);
                in.read(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String final_string = new String(bytes);
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(final_string, BarcodeFormat.QR_CODE, 1000, 1000);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                qr.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }


        }
        else {
            Toast.makeText(this,"PLEASE SAVE DETAILS TO GET QR",Toast.LENGTH_LONG).show();
        }
    }
    public void save(View v){
        String identifier="<e>";
        email= findViewById(R.id.nameText);
        String final_string = identifier.concat(email.getText().toString().trim());
        File file = new File(this.getFilesDir(), "my_email.txt");
        if (!final_string.matches("<e>")) {
            try {
                FileOutputStream stream = new FileOutputStream(file);
                stream.write(final_string.getBytes());
                stream.close();
                Toast.makeText(this,"E-MAIL is saved",Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                e.printStackTrace();
            }



        } else {
            Toast.makeText(getApplicationContext(), "PLEASE ENTER DETAILS", Toast.LENGTH_LONG).show();
        }
    }
}
