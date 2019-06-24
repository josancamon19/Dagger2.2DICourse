package com.example.a1cdmdagger2.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.example.a1cdmdagger2.R;
import com.example.a1cdmdagger2.models.User;
import com.example.a1cdmdagger2.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import timber.log.Timber;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {

    private EditText etUserId;

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
        setLogo();

        etUserId = findViewById(R.id.user_id_input);
        findViewById(R.id.login_button).setOnClickListener(this);

        setupViewModel();
    }

    private void setupViewModel(){
        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);
        viewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                 if (user != null){
                     Timber.d("User authenticated %s", user.getEmail());
                 }
            }
        });
    }

    private void setLogo() {
        glideRequestManager.load(logo).into((ImageView) findViewById(R.id.login_logo));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.login_button) {
            attemptLogin();
        }
    }

    private void attemptLogin() {
        if (TextUtils.isEmpty(etUserId.getText().toString())){
            return;
        }
        viewModel.authenticateWithId(Integer.parseInt(etUserId.getText().toString()));
    }
}
