package com.example.testdaggerhilt.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.testdaggerhilt.model.BaseModel;
import com.example.testdaggerhilt.model.Movie;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface AppDao {
    @Query("SELECT * FROM movie")
    Single<List<Movie>> getAll();
    @Insert
    void insert(List<Movie> movie);

}
