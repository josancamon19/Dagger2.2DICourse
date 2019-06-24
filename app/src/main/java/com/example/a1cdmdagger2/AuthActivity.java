package com.example.a1cdmdagger2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {
    @Inject
    Drawable logo;
    @Inject
    RequestManager glideRequestManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        setLogo();
    }

    private void setLogo() {
        glideRequestManager.load(logo).into((ImageView) findViewById(R.id.login_logo));
    }
}
