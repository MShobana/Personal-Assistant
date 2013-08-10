package com.example.personalAssistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.example.personalAssistant.PersonalAssistantDB.*;

public class PersonalAssistantDatastore {
    //TODO extract these constants to strings.xml
    public static final String DATABASE_NAME = "personal_assistant.db" ;
    public static final String DEFAULT_SPAM_TEXT = "good morning" ;
    public static final int DB_VERSION = 1;
    private final SQLiteDatabase db;

    public PersonalAssistantDatastore(Context context) {
        PersonalAssistantDB personalAssistantDB = new PersonalAssistantDB(context, DATABASE_NAME, null, DB_VERSION);
        db = personalAssistantDB.getWritableDatabase();
    }

    //TODO MAke this return all spam text and use for loop in caller
    public String readSpamTextFromDb()
    {
        String query = "select spamText from SpamBlockerText";
        Cursor cursor =  db.rawQuery(query, null) ;
        if (cursor.moveToFirst())
        {
            String spam  = cursor.getString(0);
            return spam;
        }
        return DEFAULT_SPAM_TEXT ;


    }


    public void update(String spamText) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(C_SPAM_TEXT,spamText);
        db.insert(T_SPAM_BLOCKER,null,contentValues)   ;
    }
}
