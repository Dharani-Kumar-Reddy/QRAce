package com.example.qrace;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

    }
    public void profile_act(View v){
        startActivity(new Intent(MainMenu.this,profile.class));
        finish();
    }
    public void scan_act(View v){
      startActivity(new Intent(MainMenu.this,scanner.class));
      finish();
    }



}
