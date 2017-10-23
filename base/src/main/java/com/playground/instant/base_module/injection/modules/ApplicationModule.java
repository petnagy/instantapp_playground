package com.playground.instant.base_module.injection.modules;

import android.app.Application;
import android.content.Context;

import com.playground.instant.base_module.injection.ApplicationContext;
import com.playground.instant.base_module.network.GitHubEndpoint;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by petnagy on 2017. 05. 24..
 */
@Module
public class ApplicationModule {

    private static final String GITHUB_BASE_URL = "https://api.github.com/";

    @Provides
    @ApplicationContext
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(GITHUB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    GitHubEndpoint provideGitHubEndpoint(Retrofit retrofit) {
        return retrofit.create(GitHubEndpoint.class);
    }

}
