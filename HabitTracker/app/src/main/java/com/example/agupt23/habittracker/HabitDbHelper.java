package com.example.agupt23.habittracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.agupt23.habittracker.HabitContract.HabitEntry;

/**
 * Created by agupt23 on 2/27/17.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "habittracker.db";

    public static final int DATABASE_VERSION = 1;

    public HabitDbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_HABIT_TABLE_QUERY = "CREATE TABLE " + HabitEntry.TABLE_NAME + " (" +
                HabitEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HabitEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                HabitEntry.COLUMN_DESCRIPTION + " TEXT, " +
                HabitEntry.COLUMN_DAY_OCCURRENCE + " INTEGER NOT NULL DEFAULT 1, " +
                HabitEntry.COLUMN_WEEK_FREQUENCY + " INTEFER NOT NULL DEFAULT 1);";

        db.execSQL(CREATE_HABIT_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
