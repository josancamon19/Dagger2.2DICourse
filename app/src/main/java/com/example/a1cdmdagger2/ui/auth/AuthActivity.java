package com.example.a1cdmdagger2.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.example.a1cdmdagger2.R;
import com.example.a1cdmdagger2.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProviderFactory providerFactory;
    private AuthViewModel viewModel;

    @Inject
    Drawable logo;
    @Inject
    RequestManager glideRequestManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);
        setLogo();
    }

    private void setLogo() {
        glideRequestManager.load(logo).into((ImageView) findViewById(R.id.login_logo));
    }
}
