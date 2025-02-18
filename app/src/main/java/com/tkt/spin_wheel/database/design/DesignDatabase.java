package com.tkt.spin_wheel.database.design;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {DesignModel.class}, version = 1)
public abstract class DesignDatabase extends RoomDatabase {
    public abstract DesignDAO designDAO();

    private static DesignDatabase instance;

    public static synchronized DesignDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            DesignDatabase.class, "design_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}