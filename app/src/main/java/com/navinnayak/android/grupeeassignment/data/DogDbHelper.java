package com.navinnayak.android.grupeeassignment.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.navinnayak.android.grupeeassignment.data.DogContract.DogEntry;

public class DogDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pets.db";
    private static final int DATABASE_VERSION = 1;

    public DogDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_DOGS_TABLE = "CREATE TABLE " + DogEntry.TABLE_NAME + "("
                + DogEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DogEntry.COLUMN_DOG_BREED + " TEXT, "
                + DogEntry.COLUMN_TIME_STAMP + " TEXT );";
        db.execSQL(SQL_CREATE_DOGS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DogEntry.TABLE_NAME);
    }
}