package com.example.testdaggerhilt.api;

import android.app.Application;
import android.content.Context;

import com.example.testdaggerhilt.db.AppDao;
import com.example.testdaggerhilt.db.AppDatabase;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
@Module
@InstallIn(SingletonComponent.class)
public class APIService {
    private static Retrofit retrofit;

    @Singleton
    @Provides
    public AppDatabase getAppDatabase(Context context){
        return AppDatabase.getInstance(context);
    }

    @Singleton
    @Provides
    public AppDao getAppDao(AppDatabase appDatabase){
        return appDatabase.getAppDao();
    }

    @Singleton
    @Provides
    public static ClientInterface getClient(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().
                    baseUrl("http://training-movie.bsp.vn:82").
                    addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                    addConverterFactory(GsonConverterFactory.create()).
                    build();
        }

        return retrofit.create(ClientInterface.class);
    }

}

