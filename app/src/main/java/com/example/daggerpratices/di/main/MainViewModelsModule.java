package com.example.daggerpratices.di.main;

import androidx.lifecycle.ViewModel;
import com.example.daggerpratices.di.ViewModelKey;
import com.example.daggerpratices.ui.main.posts.PostsViewModel;
import com.example.daggerpratices.ui.main.profile.ProfileViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModule(ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    public abstract ViewModel bindPostsViewModule(PostsViewModel viewModel);
}
