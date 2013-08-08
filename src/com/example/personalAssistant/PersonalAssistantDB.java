package com.example.personalAssistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersonalAssistantDB extends SQLiteOpenHelper {
    public static final String T_SPAM_BLOCKER = "SpamBlockerText";
    public static final String C_ID = "_id";
    public static final String C_SPAM_TEXT = "spamText";

    public PersonalAssistantDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ T_SPAM_BLOCKER + "("
                                  + C_ID + " INTEGER PRIMARY KEY, "
                                  + C_SPAM_TEXT + " TEXT)");

        //initialize table
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_SPAM_TEXT, "");
        db.insert(T_SPAM_BLOCKER, null,contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          db.execSQL("DROP TABLE IF EXISTS "+ T_SPAM_BLOCKER +"");
          onCreate(db);
    }
}
