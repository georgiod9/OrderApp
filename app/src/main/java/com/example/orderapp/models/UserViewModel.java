package com.example.orderapp.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class UserViewModel extends ViewModel {
    private MutableLiveData<List<UserModel>> users;
    public LiveData<List<UserModel>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<List<UserModel>>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.
    }
}
