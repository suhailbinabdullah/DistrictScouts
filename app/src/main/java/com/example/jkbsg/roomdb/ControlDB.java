package com.example.jkbsg.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.jkbsg.roomdb.tables.NewsFeed;
import com.example.jkbsg.utils.AppConstants;

@Database(entities = {NewsFeed.class}, exportSchema = false, version = 1)
public abstract class ControlDB extends RoomDatabase {
    private static ControlDB instance;

    public static synchronized ControlDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ControlDB.class,
                    AppConstants.KEY_DATABASE_NAME).fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract ControlDAO getControlDao();
}
