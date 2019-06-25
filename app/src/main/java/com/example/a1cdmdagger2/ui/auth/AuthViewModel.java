package com.example.a1cdmdagger2.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.example.a1cdmdagger2.SessionManager;
import com.example.a1cdmdagger2.models.User;
import com.example.a1cdmdagger2.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class AuthViewModel extends ViewModel {

    private final AuthApi authApi;
    private final SessionManager sessionManager;

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager) {
        this.authApi = authApi;
        this.sessionManager = sessionManager;
        Timber.d("AuthViewModel Constructor");

    }

    public void authenticateWithId(int id) {
        sessionManager.authenticateWithId(queryUserId(id));
    }

    private LiveData<AuthResource<User>> queryUserId(int id) {
        return LiveDataReactiveStreams
                .fromPublisher(authApi
                        .getUser(id)
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                Timber.e(throwable);
                                User user = new User();
                                user.setId(-1);
                                return user;
                            }
                        })
                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Exception {
                                if (user.getId() == -1) {
                                    return AuthResource.error("Could not authenticate", null);
                                }
                                return AuthResource.authenticated(user);
                            }
                        })
                        .subscribeOn(Schedulers.io()));
    }

    public LiveData<AuthResource<User>> observeAuthState() {
        return sessionManager.getAuthUser();
    }
}
