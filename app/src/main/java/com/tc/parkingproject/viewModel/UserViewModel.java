package com.tc.parkingproject.viewModel;

import android.app.Application;

import com.tc.parkingproject.db.UserRepository;
import com.tc.parkingproject.model.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * ParkingProject created by trubattommy
 * student ID : 991_526_630
 * on 2019-11-09
 */
public class UserViewModel extends AndroidViewModel {
    private LiveData<List<User>> allUsers;
    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
    }

    public void insert(User user){
        userRepository.insert(user);
    }

    public void updateUser(User user){ userRepository.updateUser(user);}

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }

    public void getUserByEmail(String email){
        userRepository.getUserByEmail(email);
    }




}
