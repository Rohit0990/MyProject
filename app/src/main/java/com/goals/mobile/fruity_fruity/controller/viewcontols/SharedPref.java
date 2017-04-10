package com.goals.mobile.fruity_fruity.controller.viewcontols;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.goals.mobile.fruity_fruity.helper.Fruity_Constant;




public class SharedPref {

    public static void setString(Context act, String var_name, String var_value) {
        SharedPreferences pref = act.getSharedPreferences(Fruity_Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(var_name, var_value);
        edit.commit();
    }

    public static String getString(Context act, String var_name) {
        SharedPreferences pref = act.getSharedPreferences(Fruity_Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        return pref.getString(var_name, "");
    }

    public static void setInt(Activity act, String var_name, int var_value) {
        SharedPreferences pref = act.getSharedPreferences(Fruity_Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt(var_name, var_value);
        edit.commit();
    }

    public static int getInt(Context act, String var_name) {
        SharedPreferences pref = act.getSharedPreferences(Fruity_Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        return pref.getInt(var_name, 0);
    }

    public static void clear(Activity act) {
        SharedPreferences pref = act.getSharedPreferences(Fruity_Constant.PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.clear();
        edit.commit();
    }





}