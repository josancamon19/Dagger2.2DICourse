package com.example.a1cdmdagger2.di.main;

import androidx.lifecycle.ViewModel;

import com.example.a1cdmdagger2.di.ViewModelKey;
import com.example.a1cdmdagger2.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindMainViewModel(ProfileViewModel viewModel);
}
