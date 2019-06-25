package com.example.a1cdmdagger2;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.lifecycle.Observer;

import com.example.a1cdmdagger2.models.User;
import com.example.a1cdmdagger2.ui.auth.AuthActivity;
import com.example.a1cdmdagger2.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    @Inject
    public SesionManager sesionManager;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        subscribeObservers();
    }

    public void subscribeObservers() {
        sesionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case LOADING:
                            break;
                        case AUTHENTICATED:
                            Timber.d("User AUTHENTICATED:%s", userAuthResource.data.getEmail());
                            break;
                        case ERROR:
                            break;
                        case NOT_AUTHENTICATED:
                            navLoginScreen();
                            break;
                    }
                }
            }
        });
    }
    private void navLoginScreen(){
        Intent intent =new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
