package com.example.personalAssistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.example.personalAssistant.PersonalAssistantDB.*;

public class PersonalAssistantDatastore {
    private final SQLiteDatabase db;

    public PersonalAssistantDatastore(Context context) {
        String database_name = context.getString(R.string.database_name);
        int db_version= Integer.parseInt(context.getString(R.string.database_name));
        PersonalAssistantDB personalAssistantDB = new PersonalAssistantDB(context, database_name, null, db_version);

        db = personalAssistantDB.getWritableDatabase();
    }

    public void update(String spamText) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(C_SPAM_TEXT,spamText);
        db.update(T_SPAM_BLOCKER,contentValues,null,null);
    }
}
