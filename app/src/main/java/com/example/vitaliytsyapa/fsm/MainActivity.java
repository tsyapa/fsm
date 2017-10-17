package com.example.vitaliytsyapa.fsm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView state=(TextView) findViewById(R.id.textView);
        Button btnLock=(Button) findViewById(R.id.buttonLock);
        btnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state.setBackgroundColor(getResources().getColor(R.color.red));
            }
        });
    }
}
