package com.dev.christopher.events.Persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.StringTokenizer;

/**
 * Created by Christopher on 16/01/2016.
 */
public class DatabasePersistence extends SQLiteOpenHelper{

    private static final int database_version = 1;
    private static final String Datebase_name = "UserBase";
    private static final String table_user = "User";

    private static final String id_column = "id";
    private static final String name_column = "name";
    private static final String firstname_column ="firstname";
    private static final String username_column ="username";
    private static final String email_column = "email";

    private static final String Create_Table_User = "CREATE TABLE "+table_user+
            "("+id_column+" INTEGER PRIMARY KEY AUTOINCREMENT, "+name_column+" TEXT, "+firstname_column+" TEXT, "+username_column+
            " TEXT, "+email_column+" TEXT"+")";


    public DatabasePersistence(Context context) {
        super(context, Datebase_name, null,database_version );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Table_User);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
