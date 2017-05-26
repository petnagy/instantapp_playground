package com.playground.instant.instantapp_playground.injection.modules;

import android.app.Application;
import android.content.Context;

import com.playground.instant.instantapp_playground.injection.ApplicationContext;
import com.playground.instant.instantapp_playground.network.GitHubEndpoint;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by petnagy on 2017. 05. 24..
 */
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    GitHubEndpoint provideGitHubEndpoint(Retrofit retrofit) {
        return retrofit.create(GitHubEndpoint.class);
    }

}
