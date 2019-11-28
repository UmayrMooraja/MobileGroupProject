package com.tc.parkingproject.db;

import android.app.Application;
import android.os.AsyncTask;

import com.tc.parkingproject.model.Parking;
import com.tc.parkingproject.model.User;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * ParkingProject created by trubattommy
 * student ID : 991_526_630
 * on 2019-11-09
 */
public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;


    public UserRepository(Application application){
        UserDB db = UserDB.getINSTANCE(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insert(User user){
        new insertAsyncTask(userDao).execute(user);

    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao asyncTaskDuo;

        insertAsyncTask(UserDao userDao){
            asyncTaskDuo = userDao;
        }


        @Override
        protected Void doInBackground(User... users) {

            asyncTaskDuo.insert(users[0]);
            return null;

        }
    }

    public void deleteUserByEmail(String email){
        new  deleteUserByEmailAsyncTask(userDao).execute(email);
    }

    private static class deleteUserByEmailAsyncTask extends AsyncTask<String, Void, Void>{

        private UserDao asyncTaskDao;

        deleteUserByEmailAsyncTask(UserDao dao){
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(String... strings) {
            asyncTaskDao.deleteUserByEmail(strings[0]);

            return null;


        }
    }

    public void updateUser(User user) {
        new updateAsyncTask(userDao).execute(user);
    }
    private static class updateAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao asyncTaskDao;
        updateAsyncTask(UserDao userDao){
            asyncTaskDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            asyncTaskDao.update(users[0]);
            return null;
        }
    }

    public void getUserByEmail(String email){
        new getUserByEmailAsyncTask(userDao).execute(email);
    }
    private static class getUserByEmailAsyncTask extends AsyncTask<String, Void, Void>{

        private UserDao asyncTaskDao;
        getUserByEmailAsyncTask(UserDao dao){
               asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(String... strings) {
            asyncTaskDao.getUserByEmail(strings[0]);

            return null;

        }
    }
}

