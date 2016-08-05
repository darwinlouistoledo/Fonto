package me.darwinlouistoledo.fonto;

import android.util.Log;

/**
 * Created by darwinlouistoledo on 8/5/16.
 */
class Logger {
    static final String TAG = "FONTO";
    public static void writeI(String msg){
        Log.i(TAG, "Info: "+msg);
    }

    public static void writeE(String msg){
        Log.e(TAG, "Error: "+msg);
    }
}
