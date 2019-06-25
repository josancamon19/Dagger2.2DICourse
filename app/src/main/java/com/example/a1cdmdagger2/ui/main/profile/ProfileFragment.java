package com.example.a1cdmdagger2.ui.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.a1cdmdagger2.R;
import com.example.a1cdmdagger2.models.User;
import com.example.a1cdmdagger2.ui.auth.AuthResource;
import com.example.a1cdmdagger2.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    @Inject
    ViewModelProviderFactory providerFactory;
    private ProfileViewModel viewModel;

    private TextView tvEmail, tvUsername, tvWebsite;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvEmail = view.findViewById(R.id.email);
        tvUsername = view.findViewById(R.id.username);
        tvWebsite = view.findViewById(R.id.website);

        viewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel.class);
        subscribeObservers();

        return view;
    }

    private void subscribeObservers() {
        viewModel.getAuthUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case ERROR:
                            setErrorDetails(userAuthResource.message);
                            break;
                        case AUTHENTICATED:
                            setUserDetails(userAuthResource.data);
                            break;
                    }
                }
            }
        });
    }

    private void setErrorDetails(String message) {
        tvEmail.setText(message);
        tvUsername.setText("Error");
        tvWebsite.setText("Error");
    }

    private void setUserDetails(User data) {
        tvEmail.setText(data.getEmail());
        tvUsername.setText(data.getUsername());
        tvWebsite.setText(data.getWebsite());
    }


}
