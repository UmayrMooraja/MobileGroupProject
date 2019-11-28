package com.tc.parkingproject.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.tc.parkingproject.model.Parking;

import java.util.List;

/**
 * Group Project created by user
 * student ID: 991522432
 * on 2019-11-14
 */

@Dao
public interface ParkingDao {

    @Insert
    void insert(Parking parking);

    @Query("SELECT * FROM parking_table ORDER BY buildingCode ASC")
    LiveData<List<Parking>> getAllParking();

}
