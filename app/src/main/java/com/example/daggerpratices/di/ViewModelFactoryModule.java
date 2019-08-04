package com.example.daggerpratices.di;

import androidx.lifecycle.ViewModelProvider;
import com.example.daggerpratices.viewmodels.ViewModelProviderFactory;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);


}
