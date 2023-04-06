package com.example.pr55.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pr55.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataSource {
    private static List<User> users = new ArrayList<>();
    private static MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();

    static {
        users.add(new User("user1", "password1", "", "", ""));
        users.add(new User("user2", "password2", "", "", ""));
        users.add(new User("user3", "password3", "", "", ""));
        usersLiveData.setValue(users);
    }

    public int getId(User user) {
        return users.indexOf(user);
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public LiveData<User> getUserById(int id) {
        MutableLiveData<User> userLiveData = new MutableLiveData<>();
        for (User user : users) {
            if (getId(user) == id) {
                userLiveData.setValue(user);
                break;
            }
        }
        return userLiveData;
    }

    public void createUser(User user) {
        users.add(user);
    }

    public void updateUser(User user) {
        int index = users.indexOf(user);
        if (index != -1) {
            users.set(index, user);
        }
    }

    public void deleteUser(User user) {
        users.remove(user);
    }

    public LiveData<List<User>> getAllUsers() {
        return usersLiveData;
    }
}
