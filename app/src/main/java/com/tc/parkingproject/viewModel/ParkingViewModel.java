package com.tc.parkingproject.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tc.parkingproject.db.ParkingRepository;
import com.tc.parkingproject.model.Parking;

import java.util.List;

/**
 * Group Project created by user
 * student ID: 991522432
 * on 2019-11-14
 */
public class ParkingViewModel extends AndroidViewModel {
    private LiveData<List<Parking>> allParking;
    private ParkingRepository parkingRepository;


    public ParkingViewModel(@NonNull Application application) {
        super(application);

        parkingRepository = new ParkingRepository(application);
        allParking = parkingRepository.getAllParking();
    }

    public LiveData<List<Parking>> getAllParking() {
        return allParking;
    }

    public void insert(Parking parking) {
        parkingRepository.insert(parking); }
}
