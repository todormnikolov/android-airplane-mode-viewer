package com.example.todor.airplanemodeviewer;

import android.content.Context;
import android.provider.Settings;

public class SystemGlobalUtils {

    public static boolean checkAirplaneModeIsOn(Context context) {
        return Settings.Global.getInt(
                context.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
    }
}
