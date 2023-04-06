package com.example.pr55.data;

import androidx.lifecycle.LiveData;

import com.example.pr55.domain.User;

import java.util.List;

public class UserRepository {
    private final UserDataSource userDataSource;

    public UserRepository(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    public LiveData<User> getUserById(int userId) {
        return userDataSource.getUserById(userId);
    }

    public void createUser(User user, Callback<User> callback) {
        userDataSource.createUser(user);
    }

    public void updateUser(User user, Callback<User> callback) {
        userDataSource.updateUser(user);
    }

    public void deleteUser(User user, Callback<User> callback) {
        userDataSource.deleteUser(user);
    }

    public LiveData<List<User>> getAllUsers() {
        return userDataSource.getAllUsers();
    }

    public interface Callback<T> {
        void onSuccess(T data);
        void onError(Throwable throwable);
    }

}
