package com.quinn.mylibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016-09-12.
 */
public class PreferencesUtil {

    private SharedPreferences preferences;

    public PreferencesUtil(Context context) {
        preferences = context.getSharedPreferences("PreferencesUtil", context.MODE_PRIVATE);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key, String def) {
        return preferences.getString(key, def);
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key, int def) {
        return preferences.getInt(key, def);
    }

    public void putBoolean(String key, boolean flag) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, flag);
        editor.commit();
    }

    public boolean getBoolean(String key, boolean def) {
        return preferences.getBoolean(key, def);
    }
}
