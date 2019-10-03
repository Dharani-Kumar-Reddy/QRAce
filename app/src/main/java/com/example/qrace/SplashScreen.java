package com.example.qrace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Thread(){
            public void run(){
                try {
                    sleep(2000);
                    Log.i("info","Hi man in sleep");
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(SplashScreen.this,MainMenu.class));
                    finish();
                }
            }
        }.start();
        Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();
    }
}
