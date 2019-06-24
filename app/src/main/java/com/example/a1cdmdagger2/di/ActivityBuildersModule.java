package com.example.a1cdmdagger2.di;

import com.example.a1cdmdagger2.di.auth.AuthModule;
import com.example.a1cdmdagger2.di.auth.AuthViewModelsModule;
import com.example.a1cdmdagger2.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class} // Only Auth Activity will be able to use AuthViewModel
    )
    abstract AuthActivity contributeAuthActivity();

    // All the activity declarations


}
