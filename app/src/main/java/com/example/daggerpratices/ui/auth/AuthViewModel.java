package com.example.daggerpratices.ui.auth;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.example.daggerpratices.network.auth.AuthApi;

import javax.inject.Inject;

public class AuthViewModel  extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi){
        this.authApi = authApi;
        Log.d(TAG,"AuthViewModel: viewmodel is working...");

        if (this.authApi == null) {
            Log.d(TAG, "AuthViewModel: auth api is NULL");
        }
        else {
            Log.d(TAG, "AuthViewModel: auth api NOT NULL");
        }

    }
}
