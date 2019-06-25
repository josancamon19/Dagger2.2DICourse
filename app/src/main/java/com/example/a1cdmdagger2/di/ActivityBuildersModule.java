package com.example.a1cdmdagger2.di;

import com.example.a1cdmdagger2.di.auth.AuthModule;
import com.example.a1cdmdagger2.di.auth.AuthViewModelsModule;
import com.example.a1cdmdagger2.ui.auth.AuthActivity;
import com.example.a1cdmdagger2.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class} // Only Auth Activity will be able to use AuthViewModel
    )
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    // All the activity declarations


}
