package com.example.todor.airplanemodeviewer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AirplaneModeBroadcastReceiver extends BroadcastReceiver {
    private ViewListener viewListener;

    public AirplaneModeBroadcastReceiver() {
        this.viewListener = null;
    }

    public void setViewListener(ViewListener viewListener) {
        this.viewListener = viewListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        viewListener.onAirplaneModeChanged();
    }

    public interface ViewListener {
        void onAirplaneModeChanged();
    }
}
