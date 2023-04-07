package com.example.pr55.domain.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pr55.data.repository.UserRepository;
import com.example.pr55.domain.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends ViewModel {
    private final UserRepository userRepository;

    private MutableLiveData<List<User>> usersLiveData;

    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
        usersLiveData = new MutableLiveData<>();
    }

    public LiveData<List<User>> getUsersLiveData() {
        return usersLiveData;
    }

    public void addUser(User user) {
        userRepository.createUser(user, new UserRepository.Callback<User>() {
            @Override
            public void onSuccess(User addedUser) {
                List<User> users = usersLiveData.getValue();
                if (users == null) {
                    users = new ArrayList<>();
                }
                users.add(addedUser);
                usersLiveData.setValue(users);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }

    public void updateUser(User user) {
        userRepository.updateUser(user, new UserRepository.Callback<User>() {
            @Override
            public void onSuccess(User updatedUser) {
                List<User> users = usersLiveData.getValue();
                if (users != null) {
                    int index = users.indexOf(updatedUser);
                    if (index != -1) {
                        users.set(index, updatedUser);
                        usersLiveData.setValue(users);
                    }
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }

    public void deleteUser(User user) {
        userRepository.deleteUser(user, new UserRepository.Callback<User>() {
            @Override
            public void onSuccess(User data) {

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

    }
}
