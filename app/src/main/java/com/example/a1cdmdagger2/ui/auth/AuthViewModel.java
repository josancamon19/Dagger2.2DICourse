package com.example.a1cdmdagger2.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.a1cdmdagger2.models.User;
import com.example.a1cdmdagger2.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class AuthViewModel extends ViewModel {

    private final AuthApi authApi;
    private MediatorLiveData<User> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
        Timber.d("AuthViewModel Constructor Working");

    }

    public void authenticateWithId(int id) {
        final LiveData<User> source = LiveDataReactiveStreams.fromPublisher(authApi.getUser(id).subscribeOn(Schedulers.io()));
        authUser.addSource(source, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });
    }

    public LiveData<User> getUser() {
        return authUser;
    }
}
