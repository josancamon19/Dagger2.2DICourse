package com.example.a1cdmdagger2.ui.main.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.a1cdmdagger2.SessionManager;
import com.example.a1cdmdagger2.models.User;
import com.example.a1cdmdagger2.ui.auth.AuthResource;

import javax.inject.Inject;

import timber.log.Timber;

public class ProfileViewModel extends ViewModel {

    private SessionManager sessionManager;

    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        Timber.d("Profile View Model Constructor.");
    }

    public LiveData<AuthResource<User>> getAuthUser() {
        return sessionManager.getAuthUser();
    }
}
