package com.example.testdaggerhilt.db;

import android.app.Application;

import com.example.testdaggerhilt.MainActivity;
import com.example.testdaggerhilt.MyApplication;
import com.example.testdaggerhilt.api.APIService;
import com.example.testdaggerhilt.viewmodel.MainViewModel;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = APIService.class)
public interface AppComponent {
//    MainViewModel getMainViewModel();

    void inject(MainActivity mainActivity);

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

}
