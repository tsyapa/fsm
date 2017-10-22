package com.example.vitaliytsyapa.fsm.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.vitaliytsyapa.fsm.FSM;
import com.example.vitaliytsyapa.fsm.R;
import com.example.vitaliytsyapa.fsm.models.State;
import org.json.JSONException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG=this.getClass().getSimpleName();
    private final int LOCK_ID=1;
    private final int LOCK_X2_ID=2;
    private final int UNLOCK_ID=3;
    private final int UNLOCK_X2_ID=4;
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
            if (savedInstanceState!=null) {   //restoring state if lost after activity restart
                State lastState=(State) savedInstanceState.getParcelable("lastState");
                fsm.setCurrentState(lastState);
                changeIndicator();
            }
            else
                changeIndicator();
        }catch (IOException e){
            showDialog(this, "Error", "Cannot read configuration file");
            Log.e(TAG, Log.getStackTraceString(e));
        }catch (JSONException e){
            showDialog(this, "Error", "Incorrect data in configuration file");
            Log.e(TAG,Log.getStackTraceString(e));
        }catch (Exception e){
            showDialog(this, "Error", "Something went wrong. Try again");
            Log.e(TAG,Log.getStackTraceString(e));
        }
    }
    //initializing ui elements
    private void init(){
        tvState=(TextView) findViewById(R.id.textView);
        btnLock=(Button) findViewById(R.id.buttonLock);
        btnLockX2=(Button) findViewById(R.id.buttonLockX2);
        btnUnlock=(Button) findViewById(R.id.buttonUnlock);
        btnUnlockX2=(Button) findViewById(R.id.buttonUnlockX2);
    }
    //setting listeners
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
                fsm.changeState(LOCK_ID);
                changeIndicator();
                break;
            case R.id.buttonLockX2:
                fsm.changeState(LOCK_X2_ID);
                changeIndicator();
                break;
            case R.id.buttonUnlock:
                fsm.changeState(UNLOCK_ID);
                changeIndicator();
                break;
            case R.id.buttonUnlockX2:
                fsm.changeState(UNLOCK_X2_ID);
                changeIndicator();
                break;
        }
    }

    private void changeIndicator(){
        State currentState=fsm.getCurrentState();
        if(currentState.getIsAlarmArmed())
            tvState.setBackgroundResource(R.color.red);
        else
            tvState.setBackgroundResource(R.color.green);
        tvState.setText(currentState.getName());
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("lastState", fsm.getCurrentState());
    }
}