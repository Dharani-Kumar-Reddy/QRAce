package com.example.qrace;

import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class contact_found extends AppCompatActivity {
    private EditText name;
    private EditText no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_found);
        name=findViewById(R.id.nameText);
        no=findViewById(R.id.editText);
        String original=((String)getIntent().getExtras().get("result")).substring(3);
        String text[]=original.split(":");
        name.setText(text[0]);
        no.setText(text[1]);

    }
    public void back_home(View v) {
        startActivity(new Intent(contact_found.this, MainMenu.class));
        finish();
    }
    public void save_contact(View v){
        name=findViewById(R.id.nameText);
        no=findViewById(R.id.editText);
        String original=((String)getIntent().getExtras().get("result")).substring(3);
        String text[]=original.split(":");
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME,text[0]);
         intent.putExtra(ContactsContract.Intents.Insert.PHONE,text[1]);
        startActivity(intent);

    }
    public void call(View v){
        String phone =no.getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);

    }
    public void message(View v){
        String number = no.getText().toString();  // The number on which you want to send SMS
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));

    }

}
