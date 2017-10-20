package com.example.vitaliytsyapa.fsm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLock, btnLockX2, btnUnlock, btnUnlockX2;
    private TextView tvState;
    private FSM fsm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setListeners();
        fsm=new FSM(this);
        changeIndicator();
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
                break;
            case R.id.buttonLockX2:
                fsm.changeState("lockX2");
                break;
            case R.id.buttonUnlock:
                fsm.changeState("unlock");
                break;
            case R.id.buttonUnlockX2:
                fsm.changeState("unlockX2");
                break;
        }
        changeIndicator();
    }

    private void changeIndicator(){
        String currentState=fsm.getCurrentState();
        if(currentState.contains("Armed"))
            tvState.setBackgroundColor(getResources().getColor(R.color.red));
        else
            tvState.setBackgroundColor(getResources().getColor(R.color.green));
        tvState.setText(fsm.getCurrentState());
    }

}
