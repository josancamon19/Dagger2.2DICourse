package com.example.a1cdmdagger2.ui.auth;

import androidx.lifecycle.ViewModel;

import com.example.a1cdmdagger2.network.auth.AuthApi;

import javax.inject.Inject;

import timber.log.Timber;

public class AuthViewModel extends ViewModel {
    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi) {
        this.authApi = authApi;
        Timber.d("AuthViewModel Constructor Working");
        if(authApi!= null) {
            Timber.d("Auth API NOT NULL.");
        }
    }
}
