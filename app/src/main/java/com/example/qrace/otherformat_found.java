package com.example.qrace;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class otherformat_found extends AppCompatActivity {
    private TextView other_format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otherformat_found);
        other_format = findViewById(R.id.editText2);
        String s = (String) getIntent().getExtras().get("result");
        other_format.setText(s);

    }
    public void search(View v){
       try {
           String escapedQuery = URLEncoder.encode((String) getIntent().getExtras().get("result"), "UTF-8");
           Uri uri = Uri.parse("http://www.google.com/#q=" + escapedQuery);
           Intent intent = new Intent(Intent.ACTION_VIEW, uri);
           startActivity(intent);
       }
       catch (UnsupportedEncodingException e){
           e.printStackTrace();
       }
    }
}
