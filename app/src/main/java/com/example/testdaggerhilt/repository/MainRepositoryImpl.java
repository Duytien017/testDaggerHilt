package com.example.testdaggerhilt.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.testdaggerhilt.api.ClientInterface;
import com.example.testdaggerhilt.db.AppDao;
import com.example.testdaggerhilt.db.AppDatabase;
import com.example.testdaggerhilt.model.Movie;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainRepositoryImpl {
    AppDao appDao;

    AppDatabase appDatabase;
    private ClientInterface client;
    private static volatile MainRepositoryImpl mInstance;

    public MainRepositoryImpl(ClientInterface client, Context context) {
        this.client = client;
        appDatabase = AppDatabase.getInstance(context);
    }

    @Inject
    public MainRepositoryImpl(AppDao appDao) {
        this.appDao = appDao;
    }

    public static MainRepositoryImpl getInstance(ClientInterface client, Context context) {
                    mInstance = new MainRepositoryImpl(client,context.getApplicationContext());
        return mInstance;
    }

    @SuppressLint("CheckResult")
    public void getAllFilm(int page, MutableLiveData<List<Movie>> liveData) {
        client.getFilm(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieList -> {
                    liveData.postValue(movieList.getData());
                    addDataToDatabase(movieList.getData());
                },throwable -> {
                    Log.e("TIEN",throwable.getMessage());
                });

    }
    private void addDataToDatabase(List<Movie> movie) {
        Log.e("TIENSIZE",movie.size()+"");
        appDao.insert(movie);
    }
    public Single<List<Movie>> getAll(){
        return appDatabase.getAppDao().getAll();
//        return appDao.getAll();
    }
}
