package com.example.personalAssistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.example.personalAssistant.PersonalAssistantDB.*;

public class PersonalAssistantDatastore {
    public static final String DATABASE_NAME = "personal_assistant.db" ;
    public static final int DB_VERSION = 1;
    private final SQLiteDatabase db;

    public PersonalAssistantDatastore(Context context) {
        PersonalAssistantDB personalAssistantDB = new PersonalAssistantDB(context, DATABASE_NAME, null, DB_VERSION);
        db = personalAssistantDB.getWritableDatabase();
    }

    public void update(String spamText) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(C_SPAM_TEXT,spamText);
        db.update(T_SPAM_BLOCKER,contentValues,null,null);
    }
}
