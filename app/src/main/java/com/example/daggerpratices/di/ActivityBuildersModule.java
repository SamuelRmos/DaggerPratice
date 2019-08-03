package com.example.daggerpratices.di;

import com.example.daggerpratices.di.auth.AuthViewModelsModule;
import com.example.daggerpratices.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {



    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class}
    )
    abstract AuthActivity contributeAuthActivity();


}
