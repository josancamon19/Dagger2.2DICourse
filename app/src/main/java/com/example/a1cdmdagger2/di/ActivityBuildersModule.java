package com.example.a1cdmdagger2.di;

import com.example.a1cdmdagger2.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();

    // All the activity declarations


}
