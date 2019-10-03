package com.example.qrace;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import static android.Manifest.permission_group.CAMERA;

public class scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int REQUEST_CAMERA=1;
    private ZXingScannerView scannerView;
    private static int camid= Camera.CameraInfo.CAMERA_FACING_BACK;
    private boolean verified=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView=new ZXingScannerView(this);
        setContentView(scannerView);
        int currentApiVersion= Build.VERSION.SDK_INT;
        if(currentApiVersion>=Build.VERSION_CODES.M)
        {
            if(checkPermission())
            {
            }
            else
            {
                requestPermission();
            }
        }

    }
    private boolean checkPermission(){
        return (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED);
    }
    private void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA);
    }
    @Override
    public void onResume(){
        super.onResume();
        int currentApiVersion= android.os.Build.VERSION.SDK_INT;
        if(currentApiVersion>=android.os.Build.VERSION_CODES.M){
            if(checkPermission()){
                startScanning();
            }
            else{
                requestPermission();
            }
        }
        else{
            startScanning();
        }
    }
    private void startScanning(){
        if(scannerView==null){
            scannerView=new ZXingScannerView(this);
            setContentView(scannerView);
        }
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        scannerView.stopCamera();
    }
    @Override
    public void onRequestPermissionsResult(int requestcode,String permissions[],int[] grantresults) {
        switch (requestcode) {
            case REQUEST_CAMERA:
                if (grantresults.length > 0) {
                    boolean cameraAccepted = grantresults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted) {
                        Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                showMessageOkCancel("YOU SHOULD GIVE ACCESS TO BOTH PERMISSIONS", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            requestPermissions(new String[]{CAMERA}, REQUEST_CAMERA);
                                        }
                                    }
                                });

                                return;
                            }
                        }
                    }

                }
                break;

        }
    }
    private void showMessageOkCancel(String message,DialogInterface.OnClickListener okListener){
        new android.support.v7.app.AlertDialog.Builder(scanner.this)
                .setMessage(message)
                .setPositiveButton("ok",okListener)
                .setNegativeButton("cancel",null)
                .create()
                .show();
    }
    @Override
    public void handleResult(Result result){
        String myResult=result.getText();
        String identifier=myResult.substring(0,3);
        if(identifier.equals("<c>")) {
            int a=myResult.indexOf(":");
            if (a>=0) {
                startActivity(new Intent(scanner.this, contact_found.class).putExtra("result", myResult));

            }
            else {
                startActivity(new Intent(scanner.this,otherformat_found.class).putExtra("result",myResult));
            }

        }

        else if (identifier.equals("<e>")){
            startActivity(new Intent(scanner.this,email_found.class).putExtra("result",myResult));
        }
        else{
            startActivity(new Intent(scanner.this,otherformat_found.class).putExtra("result",myResult));
        }
    }

}
