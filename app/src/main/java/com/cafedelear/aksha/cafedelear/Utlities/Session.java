package com.cafedelear.aksha.cafedelear.Utlities;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.SwitchCompat;
import android.widget.Toast;

import com.kyleduo.switchbutton.SwitchButton;

public class Session {

     private static Session session;
     Context mContext;
     private SharedPreferences sharedPreferences;
     private SharedPreferences.Editor editor;
     String IsLogin;
     private SwitchCompat switchCompat;

    private static String PREF_NAME = "CafeDelear";
    private String DELEAR_ID = "delear_id";
    private String DELEAR_NAME = "delear_name";
    private String DELEAR_EMAIL = "delear_email";
    private String DELEAR_PROFILE = "profile";
    private String is_visible = "ON";
    private String Is_visible = "OFF";

    public Session(Context context){
        mContext = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public synchronized Session getInstace (Context context) {

        if (session == null){
            session = new Session(context.getApplicationContext());
        }
        return session;
    }

    public void setIsLogin(Boolean isLogin) {
        editor.putBoolean(IsLogin,isLogin);
        editor.commit();
    }

    public Boolean getIsLogin() {
        return sharedPreferences.getBoolean(IsLogin,false);
    }

    public String getDELEAR_ID() {
        return sharedPreferences.getString(DELEAR_ID,"delear_id");
    }

    public void setDELEAR_ID(String delear_id) {
        editor.putString(DELEAR_ID,delear_id);
        editor.commit();
    }

    public String getDELEAR_EMAIL() {
        return sharedPreferences.getString(DELEAR_EMAIL,"delear_email");
    }

    public void setDELEAR_EMAIL(String delear_email) {
        editor.putString(DELEAR_EMAIL,delear_email);
        editor.commit();
    }

    public String getDELEAR_NAME() {
        return sharedPreferences.getString(DELEAR_NAME,"delear_name");
    }

    public void setDELEAR_NAME(String delear_name) {
        editor.putString(DELEAR_NAME,delear_name);
        editor.commit();
    }

    public String getDELEAR_PROFILE() {
        return sharedPreferences.getString(DELEAR_PROFILE,"profile");
    }

    public void setDELEAR_PROFILE(String profile) {
        editor.putString(DELEAR_PROFILE,profile);
        editor.commit();
    }





    /*public void removeKey(String DELEAR_ID,String DELEAR_EMAIL){

        if (editor != null){
            editor.remove(DELEAR_ID);
            editor.remove(DELEAR_EMAIL);
            editor.commit();
        }
    }*/



    public void clearAll(){
        if (editor != null){
            editor.clear().commit();
        }
        else {
            Toast.makeText(mContext, "Not Login User", Toast.LENGTH_SHORT).show();
        }

    }


}
