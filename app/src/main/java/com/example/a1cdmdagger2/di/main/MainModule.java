package com.example.a1cdmdagger2.di.main;

import com.example.a1cdmdagger2.network.main.MainApi;
import com.example.a1cdmdagger2.ui.main.posts.PostsRecyclerAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @MainScope
    @Provides
    static PostsRecyclerAdapter providePostsRecyclerAdapter(){
        return  new PostsRecyclerAdapter();
    }

   @MainScope
    @Provides
    static MainApi provideMainApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }
}
