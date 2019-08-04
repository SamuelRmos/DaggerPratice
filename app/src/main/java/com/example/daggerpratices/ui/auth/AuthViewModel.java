package com.example.daggerpratices.ui.auth;

import android.util.Log;
import androidx.lifecycle.*;
import com.example.daggerpratices.models.User;
import com.example.daggerpratices.network.auth.AuthApi;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;

public class AuthViewModel  extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;
    private MediatorLiveData<User> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi){
        this.authApi = authApi;
        Log.d(TAG,"AuthViewModel: viewmodel is working...");
        }

        public void authenticateWithId(int userId){
        final LiveData<User> source = LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)
                .subscribeOn(Schedulers.io())
        );

        authUser.addSource(source, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                authUser.setValue(user);
                authUser.removeSource(source);
                }
            });
        }

    public LiveData<User> observeUser(){
            return authUser;
        }

    }

