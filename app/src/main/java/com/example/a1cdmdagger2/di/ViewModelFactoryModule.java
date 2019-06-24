package com.example.a1cdmdagger2.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.a1cdmdagger2.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory providerFactory);

}
