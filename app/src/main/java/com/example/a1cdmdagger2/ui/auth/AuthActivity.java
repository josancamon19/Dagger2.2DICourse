package com.example.a1cdmdagger2.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

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
    private ProgressBar pb;

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
        pb = findViewById(R.id.progress_bar);
        findViewById(R.id.login_button).setOnClickListener(this);

        setupViewModel();
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);
        viewModel.getUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case LOADING:
                            changePb(true);
                            break;
                        case AUTHENTICATED:
                            changePb(false);
                            Timber.d("User AUTHENTICATED:%s", userAuthResource.data.getEmail());
                            break;
                        case ERROR:
                            changePb(false);
                            Timber.e(userAuthResource.message);
                            break;
                        case NOT_AUTHENTICATED:
                            changePb(false);
                            break;
                    }
                }
            }
        });
    }

    private void changePb(boolean pbVisibility) {
        if (pbVisibility) {
            pb.setVisibility(View.VISIBLE);
        } else {
            pb.setVisibility(View.GONE);
        }
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
        if (TextUtils.isEmpty(etUserId.getText().toString())) {
            return;
        }
        viewModel.authenticateWithId(Integer.parseInt(etUserId.getText().toString()));
    }
}
