package com.example.daggerpratices.ui.auth;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import com.bumptech.glide.RequestManager;
import com.example.daggerpratices.R;
import com.example.daggerpratices.models.User;
import com.example.daggerpratices.ui.main.MainActivity;
import com.example.daggerpratices.viewmodels.ViewModelProviderFactory;
import dagger.android.support.DaggerAppCompatActivity;

import javax.inject.Inject;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AuthActivity";
    private AuthViewModel viewModel;
    EditText userId;
    private ProgressBar progressBar;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        viewModel = ViewModelProviders.of(this, providerFactory). get(AuthViewModel.class);
        userId = findViewById(R.id.user_id_input);
        progressBar = findViewById(R.id.progress_bar);

        findViewById(R.id.login_button).setOnClickListener(this);

        setLogo();
        subscribeObserves();
    }

    private void subscribeObserves(){
        viewModel.observeAuthState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null){
                    switch (userAuthResource.status){
                        case LOADING:{
                            showProgressBar(true);
                            break;
                        }
                        case AUTHENTICATED:{
                            showProgressBar(false);
                            Log.d(TAG,"onChaged: LOGIN SUCCESS: " + userAuthResource.data.getEmail());
                            onLoginSucess();
                            break;
                        }
                        case ERROR:{
                            showProgressBar(false);
                            Toast.makeText(AuthActivity.this, userAuthResource.message
                                 + "\n Did you enter a number between 1 and 10?", Toast.LENGTH_LONG).show();
                            break;
                        }
                        case NOT_AUTHENTICATED:{
                            showProgressBar(false);
                            break;
                        }
                    }
                }
            }
        });

    }

    private void onLoginSucess(){

        Log.d(TAG, "onLoginSuccess: login successful!");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    private  void showProgressBar(boolean isVisible){
        if(isVisible){
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void setLogo(){
        requestManager
                .load(logo)
                .into((ImageView)findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button: {
                attemptLogin();
                break;
            }

        }
    }

    private void attemptLogin() {
        if (TextUtils.isEmpty(userId.getText().toString())){
            return;
        }
        viewModel.authenticateWithId(Integer.parseInt(userId.getText().toString()));
    }
}
