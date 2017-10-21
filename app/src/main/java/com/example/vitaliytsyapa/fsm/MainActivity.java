package com.example.vitaliytsyapa.fsm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG=this.getClass().getSimpleName();
    private Button btnLock, btnLockX2, btnUnlock, btnUnlockX2;
    private TextView tvState;
    private FSM fsm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setListeners();
        try{
            fsm=new FSM(this);
            changeIndicator();
        }catch (IOException e){
            showDialog(this, "Error", "Cannot read configuration file");
            Log.e(TAG, Log.getStackTraceString(e));
        }catch (JSONException e){
            showDialog(this, "Error", "Incorrect data in configuration file");
            Log.e(TAG,Log.getStackTraceString(e));
        }
        catch (Exception e){
            showDialog(this, "Error", "Something went wrong. Try again");
            Log.e(TAG,Log.getStackTraceString(e));
        }
    }

    private void init(){
        tvState=(TextView) findViewById(R.id.textView);
        btnLock=(Button) findViewById(R.id.buttonLock);
        btnLockX2=(Button) findViewById(R.id.buttonLockX2);
        btnUnlock=(Button) findViewById(R.id.buttonUnlock);
        btnUnlockX2=(Button) findViewById(R.id.buttonUnlockX2);
    }

    private void setListeners() {
        btnLock.setOnClickListener(this);
        btnLockX2.setOnClickListener(this);
        btnUnlock.setOnClickListener(this);
        btnUnlockX2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLock:
                fsm.changeState("lock");
                changeIndicator();
                break;
            case R.id.buttonLockX2:
                fsm.changeState("lockX2");
                changeIndicator();
                break;
            case R.id.buttonUnlock:
                fsm.changeState("unlock");
                changeIndicator();
                break;
            case R.id.buttonUnlockX2:
                fsm.changeState("unlockX2");
                changeIndicator();
                break;
        }
    }

    private void changeIndicator(){
        String currentState=fsm.getCurrentState();
        if(currentState.toLowerCase().contains("alarmarmed"))
            tvState.setBackgroundColor(getResources().getColor(R.color.red));
        else
            tvState.setBackgroundColor(getResources().getColor(R.color.green));
        tvState.setText(fsm.getCurrentState());
    }

    private void showDialog(final Activity activity, String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                activity.finish();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}