package com.tkt.spin_wheel.database.icon;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {IconModel.class}, version = 1)
public abstract class IconDatabase extends RoomDatabase {
    public abstract IconDAO iconDAO();

    private static IconDatabase instance;

    public static synchronized IconDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            IconDatabase.class, "icon_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}