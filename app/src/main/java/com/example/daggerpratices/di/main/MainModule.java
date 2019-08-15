package com.example.daggerpratices.di.main;

import com.example.daggerpratices.network.main.MainApi;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module33
public class MainModule {

    @Provides
    static MainApi provideMainApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }
}
