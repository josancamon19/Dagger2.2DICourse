package com.example.a1cdmdagger2.ui.auth;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import timber.log.Timber;

public class AuthViewModel extends ViewModel {

    @Inject
    public AuthViewModel() {
        Timber.d("AuthViewModel Constructor Working");
    }
}
