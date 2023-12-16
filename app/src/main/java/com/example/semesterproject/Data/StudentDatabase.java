package com.example.semesterproject.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.migration.Migration;

@Database(entities = {Student.class}, version = 4, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {
    public abstract StudentDao studentDao();

    private static volatile StudentDatabase INSTANCE;

    public static StudentDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (StudentDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    StudentDatabase.class, "student_database")
                            .fallbackToDestructiveMigration()  // This will recreate the database if no Migration object matches the current version
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
