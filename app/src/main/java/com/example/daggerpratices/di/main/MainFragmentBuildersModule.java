package com.example.daggerpratices.di.main;

import com.example.daggerpratices.ui.main.posts.PostsFragment;
import com.example.daggerpratices.ui.main.profile.ProfileFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostsFragment contributePostsFragment();
}
