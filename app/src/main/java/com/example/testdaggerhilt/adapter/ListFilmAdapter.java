package com.example.testdaggerhilt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testdaggerhilt.R;
import com.example.testdaggerhilt.databinding.ItemFilmBinding;
import com.example.testdaggerhilt.model.Movie;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListFilmAdapter extends RecyclerView.Adapter<ListFilmAdapter.ViewHolder> {
    List<Movie> movieList;
    Context conText;
    ItemFilmBinding binding;

    public void setMovieList(List<Movie> movieList, Context conText) {
        this.movieList = movieList;
        this.conText = conText;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(conText);
        binding = ItemFilmBinding.inflate(inflater);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.binding.tvTitleFilm.setText(movieList.get(position).getCategory());
        holder.binding.tvSummary.setText(movieList.get(position).getDescription());
        Glide.with(conText).load(movieList.get(position).getImage()).into(holder.binding.ivAvatar);

    }

    @Override
    public int getItemCount() {
        if (movieList != null) {
            return movieList.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemFilmBinding binding;

        public ViewHolder(@NonNull @NotNull ItemFilmBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
