package com.example.agupt23.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agupt23.habittracker.HabitContract.HabitEntry;

public class MainActivity extends AppCompatActivity {

    HabitDbHelper habitDbHelper;
    Button insertDummyButton;
    Button refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        habitDbHelper = new HabitDbHelper(this);

        //UI has been added for troubleshooting purposes
        insertDummyButton = (Button) findViewById(R.id.insert_dummy_data_button);
        insertDummyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertValuesInDb();
            }
        });

        refreshButton = (Button) findViewById(R.id.refresh);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDatabaseInfo();
            }
        });

    }

    public void insertValuesInDb() {

        SQLiteDatabase dbWriteInstance = habitDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HabitEntry.COLUMN_NAME,getString(R.string.description));
        contentValues.put(HabitEntry.COLUMN_DAY_OCCURRENCE,HabitEntry.DAY_OCCURRENCE_MORNING);
        contentValues.put(HabitEntry.COLUMN_WEEK_FREQUENCY,2);

        long newRowId = dbWriteInstance.insert(HabitEntry.TABLE_NAME,null,contentValues);
        if (newRowId != -1) {
            Toast.makeText(this, R.string.insert_success,Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.insert_failure,Toast.LENGTH_SHORT).show();
        }
    }

    public void displayDatabaseInfo() {

        SQLiteDatabase dbReadInstance = habitDbHelper.getReadableDatabase();
        String[] projection = {
                HabitEntry.COLUMN_ID,
                HabitEntry.COLUMN_DESCRIPTION,
                HabitEntry.COLUMN_NAME,
                HabitEntry.COLUMN_DAY_OCCURRENCE,
                HabitEntry.COLUMN_WEEK_FREQUENCY
        };

        Cursor habitCursor = dbReadInstance.query(HabitEntry.TABLE_NAME, projection, null, null, null, null, null);

        TextView mainTextView = (TextView) findViewById(R.id.main_textview);

        try {
            mainTextView.setText("The table contains " + habitCursor.getCount() + " rows \n");

            mainTextView.append(HabitEntry.COLUMN_ID + " - " +
                    HabitEntry.COLUMN_NAME + " - " +
                    HabitEntry.COLUMN_DESCRIPTION + " - " +
                    HabitEntry.COLUMN_DAY_OCCURRENCE + " - " +
                    HabitEntry.COLUMN_WEEK_FREQUENCY);

            //figure out the index of each column
            int idColumnIndex = habitCursor.getColumnIndex(HabitEntry.COLUMN_ID);
            int nameColumnIndex = habitCursor.getColumnIndex(HabitEntry.COLUMN_NAME);
            int descColumnIndex = habitCursor.getColumnIndex(HabitEntry.COLUMN_DESCRIPTION);
            int dayColumnIndex = habitCursor.getColumnIndex(HabitEntry.COLUMN_DAY_OCCURRENCE);
            int weekColumnIndex = habitCursor.getColumnIndex(HabitEntry.COLUMN_WEEK_FREQUENCY);

            while (habitCursor.moveToNext()) {
                //get values
                int id = habitCursor.getInt(idColumnIndex);
                String name = habitCursor.getString(nameColumnIndex);
                String desc = habitCursor.getString(descColumnIndex);
                int dayFreq = habitCursor.getInt(dayColumnIndex);
                int weekFreq = habitCursor.getInt(weekColumnIndex);

                mainTextView.append("\n" + id + " - " +
                        name + " - " +
                        desc + " - " +
                        dayFreq + " - " +
                        weekFreq);
            }
        } finally {
            habitCursor.close();
        }
    }

    public Cursor read() {
        SQLiteDatabase dbReadInstance = habitDbHelper.getReadableDatabase();
        String[] projection = {
                HabitEntry.COLUMN_ID,
                HabitEntry.COLUMN_DESCRIPTION,
                HabitEntry.COLUMN_NAME,
                HabitEntry.COLUMN_DAY_OCCURRENCE,
                HabitEntry.COLUMN_WEEK_FREQUENCY
        };

        Cursor habitCursor = dbReadInstance.query(HabitEntry.TABLE_NAME, projection, null, null, null, null, null);
        return habitCursor;
    }
}
