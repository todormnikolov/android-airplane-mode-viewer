package com.example.todor.airplanemodeviewer;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AirplaneModeBroadcastReceiver.ViewListener {
    private AirplaneModeBroadcastReceiver broadcastReceiver;

    private static boolean isAirplaneModeOn;
    private static final String AIRPLANE_MODE_MESSAGE_PREFIX = "Airplane mode is ";
    private static final String AIRPLANE_MODE_MESSAGE_ON = "on";
    private static final String AIRPLANE_MODE_MESSAGE_OFF = "off";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        initReceiver();
    }

    private void setupView() {
        setContentView(R.layout.activity_main);
        setAirplaneModeListener();
        isAirplaneModeOn = SystemGlobalUtils.checkAirplaneModeIsOn(this);
        changeMessage();
    }

    private void setAirplaneModeListener() {
        broadcastReceiver = new AirplaneModeBroadcastReceiver();
        broadcastReceiver.setViewListener(this);
    }

    private void initReceiver() {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private void changeMessage() {
        TextView txtMessage = findViewById(R.id.txt_message);
        txtMessage.setText(AIRPLANE_MODE_MESSAGE_PREFIX.concat(
                isAirplaneModeOn ? AIRPLANE_MODE_MESSAGE_ON : AIRPLANE_MODE_MESSAGE_OFF));
    }

    @Override
    public void onAirplaneModeChanged() {
        isAirplaneModeOn = !isAirplaneModeOn;
        changeMessage();
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}
