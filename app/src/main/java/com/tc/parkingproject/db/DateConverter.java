package com.tc.parkingproject.db;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * ParkingProject created by user
 * student ID: 991522432
 * on 2019-11-26
 */
public class DateConverter {
    @TypeConverter
    public static Date toDate(Long dateLong) {
        return dateLong == null ? null : new Date(dateLong);
    }

    @TypeConverter
    public static Long fromDate(Date date) {
        return date == null ? null : date.getTime();

    }
}
