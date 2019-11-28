package com.tc.parkingproject.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.tc.parkingproject.model.Parking;

import java.util.List;

/**
 * Group Project created by user
 * student ID: 991522432
 * on 2019-11-21
 */
public class ParkingRepository {

    private ParkingDao parkingDao;
    private LiveData<List<Parking>> allParking;

    public ParkingRepository(Application application) {
        UserDB db = UserDB.getINSTANCE(application);
        parkingDao = db.parkingDao();
        allParking = parkingDao.getAllParking();

    }

    public LiveData<List<Parking>> getAllParking() {
        return allParking;
    }

    public void insert (Parking parking) {
        new insertAsyncTask(parkingDao).execute(parking);
    }
    private static class insertAsyncTask extends AsyncTask<Parking, Void, Void> {
        private ParkingDao asyncTaskDao;

        insertAsyncTask(ParkingDao parkingDao) {
            asyncTaskDao = parkingDao;
        }
        @Override
        protected Void doInBackground(Parking... parkings) {
            asyncTaskDao.insert(parkings[0]);
            return null;
        }
    }

}
