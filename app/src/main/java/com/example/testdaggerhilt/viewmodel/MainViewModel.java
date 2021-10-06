package com.example.testdaggerhilt.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.testdaggerhilt.api.ClientInterface;
import com.example.testdaggerhilt.model.BaseModel;
import com.example.testdaggerhilt.model.Movie;
import com.example.testdaggerhilt.repository.MainRepositoryImpl;
import com.example.testdaggerhilt.util.DisposableManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@HiltViewModel
public class MainViewModel extends AndroidViewModel {
    MainRepositoryImpl repository;
    @Inject
    ClientInterface client;

    MutableLiveData<List<Movie>> listLiveData = new MutableLiveData<>();
    MutableLiveData<List<Movie>> listLiveDataLocal = new MutableLiveData<>();

    @Inject
    public MainViewModel(@NonNull Application application, MainRepositoryImpl repository) {
        super(application);
        this.repository = repository;
    }

    @SuppressLint("CheckResult")
    public void getData(){
        repository = MainRepositoryImpl.getInstance(client,getApplication());
        repository = new MainRepositoryImpl(client,getApplication());
        repository.getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieList -> {
                    listLiveDataLocal.postValue(movieList);
                },throwable -> {
                    Log.e("TIENLOCAL",throwable.getMessage());
                });
    }

    public LiveData<List<Movie>> getFilmLiveData() {
        return listLiveData;
    }
    public LiveData<List<Movie>> getFilmLiveDataLocal() {
        return listLiveDataLocal;
    }

    public void getDataServer(int page) {
        repository = MainRepositoryImpl.getInstance(client,getApplication());
        repository = new MainRepositoryImpl(client,getApplication());
        repository.getAllFilm(page,listLiveData);
    }
}
