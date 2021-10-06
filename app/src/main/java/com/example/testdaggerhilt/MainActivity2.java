package com.example.testdaggerhilt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.example.testdaggerhilt.viewmodel.MainViewModel;

public class MainActivity2 extends AppCompatActivity {
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        initViewModel();
        viewModel.getData();
    }

    private void initViewModel() {
        viewModel.getFilmLiveDataLocal().observe(this,movieList -> {
            Toast.makeText(this, movieList.size()+"", Toast.LENGTH_SHORT).show();
        });
    }
}