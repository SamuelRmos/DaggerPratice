package com.example.daggerpratices.di;

import com.example.daggerpratices.di.auth.AuthModule;
import com.example.daggerpratices.di.auth.AuthViewModelsModule;
import com.example.daggerpratices.ui.auth.AuthActivity;
import com.example.daggerpratices.ui.main.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class,
                       AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

}
