package com.example.daggerpratices.ui.auth;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.example.daggerpratices.models.User;
import com.example.daggerpratices.network.auth.AuthApi;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;

public class AuthViewModel  extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi){
        this.authApi = authApi;
        Log.d(TAG,"AuthViewModel: viewmodel is working...");

        authApi.getUser(1)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        Log.d(TAG,"onNext: " + user.getUsername());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"onError: ", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        }

    }

