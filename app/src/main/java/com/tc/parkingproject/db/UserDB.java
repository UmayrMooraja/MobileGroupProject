package com.tc.parkingproject.db;

import android.content.Context;

import com.tc.parkingproject.model.Parking;
import com.tc.parkingproject.model.User;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * ParkingProject created by trubattommy
 * student ID : 991_526_630
 * on 2019-11-09
 */
@Database(entities = {User.class, Parking.class}, version = 10)
@TypeConverters(DateConverter.class)

public abstract class UserDB extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract ParkingDao parkingDao();

    private static volatile UserDB INSTANCE;

    public static UserDB getINSTANCE(final Context context) {
        if(INSTANCE == null){
            synchronized (UserDB.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDB.class, "parking_room_database")
                        .fallbackToDestructiveMigration()
                        .build();

            }
        }
        return INSTANCE;

    }
}
