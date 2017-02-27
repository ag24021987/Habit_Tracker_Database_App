package com.example.agupt23.habittracker;

import android.provider.BaseColumns;

/**
 * Created by agupt23 on 2/26/17.
 */

public class HabitContract {

    private HabitContract() {
        //do nothing
    }

    public static final class HabitEntry implements BaseColumns {

        public static final String COLUMN_ID = BaseColumns._ID;

        public static final String TABLE_NAME = "habit";

        public static final String COLUMN_NAME = "habit_name";

        public static final String COLUMN_DESCRIPTION = "habit_description";

        public static final String COLUMN_WEEK_FREQUENCY = "habit_week_frequency";

        public static final String COLUMN_DAY_OCCURRENCE = "habit_day_occurrence";

        //DAY OCCURRENCE variables

        public static final int DAY_OCCURRENCE_MORNING = 1;

        public static final int DAY_OCCURRENCE_AFTERNOON = 2;

        public static final int DAY_OCCURRENCE_NIGHT = 3;

    }
}
