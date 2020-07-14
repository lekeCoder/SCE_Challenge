package com.sce.challenge;

import android.util.Log;

public class Utils {

    public static void printLog(String tag, String message, String level){
        Log.e("PRINT START: ","========================================================================");
        if(level.equals("e")){
            Log.e(tag,message);
        }
        else if(level.equals("d")){
            Log.d(tag,message);
        }
        else {
            Log.i(tag,message);
        }
        Log.e("PRINT END: ","___________________________________________________________________________");
    }
}
