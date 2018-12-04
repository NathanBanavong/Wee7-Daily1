package com.example.consultants.week7_daily1;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class PrefManager {

    public static final String TAG = PrefManager.class.getSimpleName()+"_TAG";
    public static final String PrefKey = "MySharedPref";

    Context context;

    public PrefManager(Context context) {
        this.context = context;
    }

    public void saveLoginDetails(String email, String password) {
        Log.d(TAG, "saveLoginDetails: ");
        SharedPreferences sharedPreferences = context.getSharedPreferences(PrefKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(email, password);
        editor.commit();
    }

    public boolean checkLogin(String email, String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PrefKey, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(email) && sharedPreferences.getString(email, "password").equals(password)){
            return true;
        }
        else return false;
    }

}
