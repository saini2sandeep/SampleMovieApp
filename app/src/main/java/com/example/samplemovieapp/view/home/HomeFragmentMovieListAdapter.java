package com.example.samplemovieapp.view.home;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.samplemovieapp.R;
import com.example.samplemovieapp.database.entity.EntityMovie;
import com.example.samplemovieapp.databinding.ItemMovieBinding;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sandeepsaini on 30,May,2019
 */
public class HomeFragmentMovieListAdapter extends RecyclerView.Adapter<HomeFragmentMovieListAdapter.MovieItemViewHolder> {

    public interface MovieClickListener {
        void onMovieClick(EntityMovie movie);

        void onLikeButtonClick(EntityMovie movie, boolean isFavorite);
    }

    private List<EntityMovie> movieList = new ArrayList<>();
    private MovieClickListener movieClickListener;

    // ItemId, Position
    private Map<Long, Integer> positionsMap;

    public HomeFragmentMovieListAdapter() {
        positionsMap = new LinkedHashMap<>();
    }

    public void addData(List<EntityMovie> movieList) {
        this.movieList.addAll(movieList);
        notifyDataSetChanged();
    }

    public void clearList(boolean shouldClear) {
        if (!movieList.isEmpty() && shouldClear)
            movieList.clear();
    }

    public void setMovieClickListener(MovieClickListener movieClickListener) {
        this.movieClickListener = movieClickListener;
    }

    @NonNull
    @Override
    public MovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        ItemMovieBinding movieItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()), R.layout.item_movie, viewGroup, false);
        return new MovieItemViewHolder(movieItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieItemViewHolder movieItemViewHolder, int i) {

        movieItemViewHolder.bindData(movieList.get(i));
        positionsMap.put(movieList.get(i).getId(), i);

    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public class MovieItemViewHolder extends RecyclerView.ViewHolder {

        ItemMovieBinding movieItemBinding;

        public MovieItemViewHolder(@NonNull ItemMovieBinding movieItemBinding) {
            super(movieItemBinding.getRoot());
            this.movieItemBinding = movieItemBinding;
        }

        public void bindData(EntityMovie movie) {

            movieItemBinding.setMovie(movie);
            movieItemBinding.setMovieClickListener(movieClickListener);

            if (movie.isFavorite()) {
                movieItemBinding.favoriteButtonCb.setChecked(true);
            } else {
                movieItemBinding.favoriteButtonCb.setChecked(false);
            }


            movieItemBinding.favoriteButtonCb.setOnCheckedChangeListener((buttonView, isChecked) -> {

                if (buttonView.isPressed()) {

                    if (isChecked) {
                        movieClickListener.onLikeButtonClick(movie, true);
                    } else {
                        movieClickListener.onLikeButtonClick(movie, false);
                    }
                }
            });
        }
    }

    // To show like in real time
    public void refreshItem(EntityMovie entityMovie) {
        if (!positionsMap.isEmpty()) {
            Integer position = positionsMap.get(entityMovie.getId());
            if (position != null) {
                movieList.get(position).setFavorite(entityMovie.isFavorite());
                notifyItemChanged(position);
            }
        }
    }
}
