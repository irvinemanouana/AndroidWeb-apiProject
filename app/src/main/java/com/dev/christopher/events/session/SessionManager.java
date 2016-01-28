package com.dev.christopher.events.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Christopher on 28/01/2016.
 */
public class SessionManager {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;

    private int PREF_MODE =0;
    private static final String PREF_NAME = "session";
    private static final String value_username="username";
    private static final String value_name="name";
    private static final String value_firstname="firstname";
    private static final String value_email="email";
    private static final String value_login="exist";

    public SessionManager(Context context) {

        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME,PREF_MODE);
        this.editor = pref.edit();
    }

    public void CreateUserSession(String username,String name,String firstname,String email){
        editor.putBoolean(value_login,true);
        editor.putString(value_username, username);
        editor.putString(value_name, name);
        editor.putString(value_firstname, firstname);
        editor.putString(value_email, email);
        editor.commit();
    }

    public HashMap<String,String> storeUser(){
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put(value_username, pref.getString(value_username, null));
        hashMap.put(value_email, pref.getString(value_email, null));
        hashMap.put(value_name, pref.getString(value_name, null));
        hashMap.put(value_firstname, pref.getString(value_firstname, null));
        return hashMap;
    }
    public void DestroyUserSession(){
        editor.clear();
        editor.commit();
    }
    public boolean isLoggedIn(){
        return pref.getBoolean(value_login, false);
    }
    public void checkLogin(){
        // Check login status

        if(pref.getBoolean(value_login,false)==true){
            Log.d("session","true");
        }else {
            Log.d("session","false");
        }

    }


}
