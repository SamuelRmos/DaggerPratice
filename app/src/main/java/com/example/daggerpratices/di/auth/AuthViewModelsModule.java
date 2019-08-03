package com.example.daggerpratices.di.auth;

import androidx.lifecycle.ViewModel;
import com.example.daggerpratices.di.ViewModelKey;
import com.example.daggerpratices.ui.auth.AuthViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);

}
