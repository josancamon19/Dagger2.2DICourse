package com.example.a1cdmdagger2.di.auth;

import androidx.lifecycle.ViewModel;

import com.example.a1cdmdagger2.di.ViewModelKey;
import com.example.a1cdmdagger2.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    /* If there is another view model "MainViewModel", just create the same
     * with the different name*/

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
