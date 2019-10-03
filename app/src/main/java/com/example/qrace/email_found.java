package com.example.qrace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class email_found extends AppCompatActivity {
    private EditText emailtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_found);
        emailtext=findViewById(R.id.nameText);
        String text=(String)getIntent().getExtras().get("result");
        String email=text.substring(3);
        emailtext.setText(email);
    }
    public void back_home(View v) {
        startActivity(new Intent(email_found.this, MainMenu.class));
        finish();
    }
    public void compose(View v){
        String email=emailtext.getText().toString();
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        email_found.this.startActivity(intent);    }
}
