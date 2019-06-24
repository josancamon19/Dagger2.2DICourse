package com.example.a1cdmdagger2.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.a1cdmdagger2.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.a1cdmdagger2.util.Constants.BASE_URL;

@Module
public class AppModule {
    // This is not abstract cause we are not using @Contributes
    // here goes application level dependencies
    // retrofit glide instances for example, things wont change through the app lifetime

    // @Provides is used for declare a dependency, static is required!!

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions(){
        return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background);
    }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions){
        return Glide.with(application).setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static Drawable getAppLogo(Application application){
        return ContextCompat.getDrawable(application, R.drawable.logo);
    }
}
