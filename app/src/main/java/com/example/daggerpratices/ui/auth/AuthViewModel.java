package com.example.daggerpratices.ui.auth;

import android.util.Log;
import androidx.lifecycle.*;
import com.example.daggerpratices.SessionManager;
import com.example.daggerpratices.models.User;
import com.example.daggerpratices.network.auth.AuthApi;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;

public class AuthViewModel  extends ViewModel {

    private static final String TAG = "AuthViewModel";

    private final AuthApi authApi;
    private SessionManager sessionManager;

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager){
        this.authApi = authApi;
        this.sessionManager = sessionManager;

        Log.d(TAG,"AuthViewModel: viewmodel is working...");
        }

        public void authenticateWithId(int userId){
            Log.d(TAG,"autheticateWithId: attempting to login.");
            sessionManager.athenticateWihtId(queryUserId(userId));

        }

        private LiveData<AuthResource<User>> queryUserId(int userId){

        return LiveDataReactiveStreams.fromPublisher(

                authApi.getUser(userId)

                        //instead of calling onError (error happens)

                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                User errorUser = new User();
                                errorUser.setId(-1);
                                return errorUser;
                            }
                        })

                        //wrap User object in AuthResource

                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Exception {
                                if (user.getId() == -1){
                                    return  AuthResource.error("Could not authenticate", (User)null);
                                }
                                return AuthResource.authenticated(user);
                            }
                        })
                        .subscribeOn(Schedulers.io()));
        }

        public LiveData<AuthResource<User>> observeAuthState(){
         return sessionManager.getAuthUser();
        }

    }

