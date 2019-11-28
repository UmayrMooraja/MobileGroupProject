package com.tc.parkingproject.db;

import com.tc.parkingproject.model.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * ParkingProject created by trubattommy
 * student ID : 991_526_630
 * on 2019-11-09
 */
@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user_table WHERE email = :email")
    void deleteUserByEmail(String email);

    @Query("SELECT * FROM user_table ORDER BY email ASC")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM user_table WHERE email = :email")
    LiveData<User> getUserByEmail(String email);

}