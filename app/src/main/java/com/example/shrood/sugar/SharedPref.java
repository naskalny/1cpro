package com.example.shrood.sugar;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by shrood on 02.09.2018.
 * Сохраняет состояние темы
 */

public class SharedPref {
    SharedPreferences mySharedPref;
    public SharedPref(Context context) {
        mySharedPref = context.getSharedPreferences("filename", Context.MODE_PRIVATE);
    }
    public void setNightModStats (Boolean state){
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean("NightMode", state);
        editor.commit();
    }
    public boolean loadNightModeState() {
        Boolean state = mySharedPref.getBoolean("NightMode", false);
        return state;
    }
    public void setAmoledMode (Boolean amoledMode){
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean("AmoledMode", amoledMode);
        editor.commit();
    }
    public boolean loadAmoledMode() {
        Boolean amoledMode = mySharedPref.getBoolean("AmoledMode", false);
        return amoledMode;
    }
}
