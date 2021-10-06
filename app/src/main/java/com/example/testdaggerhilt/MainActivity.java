package com.example.testdaggerhilt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.testdaggerhilt.adapter.ListFilmAdapter;
import com.example.testdaggerhilt.db.AppComponent;
import com.example.testdaggerhilt.db.AppDatabase;
import com.example.testdaggerhilt.db.DaggerAppComponent;
import com.example.testdaggerhilt.model.Movie;
import com.example.testdaggerhilt.viewmodel.MainViewModel;

import java.util.List;
import java.util.logging.Handler;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
     MainViewModel viewModel;
     RecyclerView recyclerView;
     ListFilmAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControls();
        initViewModel();

        AppComponent appComponent = DaggerAppComponent.builder().application(getApplication()).build();
        appComponent.inject(this);
        initEvent();
    }

    private void initEvent() {
        Button button = findViewById(R.id.buttonMove);
        button.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,MainActivity2.class));
        });
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getFilmLiveData().observe(this, movieList -> {
            if (movieList!=null){
                adapter.setMovieList(movieList,MainActivity.this);
                adapter.notifyDataSetChanged();
            }else {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }

        });
        viewModel.getDataServer(1);
    }

    private void initControls() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListFilmAdapter();
        recyclerView.setAdapter(adapter);
    }
}