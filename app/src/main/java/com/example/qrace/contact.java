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

public class contact extends AppCompatActivity {
    private EditText name;
    private EditText phone;
    private ImageView qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        name = findViewById(R.id.nameText);
        phone = findViewById(R.id.editText);
        qr = findViewById(R.id.qr);
        File file = new File(this.getFilesDir(), "my_contact.txt");
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
            String original=final_string.substring(3);
            String text[]=original.split(":");
            name.setText(text[0]);
            phone.setText(text[1]);


        }

    }

    public void back_home(View v) {
        startActivity(new Intent(contact.this, MainMenu.class));
        finish();
    }

    public void generate(View v) {
        name = findViewById(R.id.nameText);
        phone = findViewById(R.id.editText);
        qr = findViewById(R.id.qr);
        phone.onEditorAction(EditorInfo.IME_ACTION_DONE);
        File file = new File(this.getFilesDir(), "my_contact.txt");
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


        }
        else {
            Toast.makeText(this,"PLEASE SAVE DETAILS TO GET QR",Toast.LENGTH_LONG).show();
        }
    }
    public void save(View v){
        String identifier="<c>";
        String noid=":";
        name = findViewById(R.id.nameText);
        phone = findViewById(R.id.editText);
        String name_string = identifier.concat(name.getText().toString().trim());
        String phone_string = noid.concat(phone.getText().toString().trim());
        String final_string = name_string.concat(phone_string);
        File file = new File(this.getFilesDir(), "my_contact.txt");
        if (!final_string.matches("<c>:")&&!phone_string.matches(":")&&!name_string.matches("<c>")) {
            try {
                FileOutputStream stream = new FileOutputStream(file);
                stream.write(final_string.getBytes());
                stream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }



        } else {
            Toast.makeText(getApplicationContext(), "PLEASE ENTER DETAILS", Toast.LENGTH_LONG).show();
        }
    }
}
