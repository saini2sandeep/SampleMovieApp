package com.example.samplemovieapp.view.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.samplemovieapp.database.entity.EntityMovie;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeepsaini on 29,May,2019
 */
public class MoviesListResponse implements Parcelable {

    public MoviesListResponse() {
        this.movieList = new ArrayList<>();
    }

    @SerializedName("pages")
    private long currentPage;

    @SerializedName("total_pages")
    private long totalPages;

    @SerializedName("total_results")
    private long totalResults;

    @SerializedName("results")
    private List<EntityMovie> movieList;


    protected MoviesListResponse(Parcel in) {
        currentPage = in.readLong();
        totalPages = in.readLong();
        totalResults = in.readLong();
        movieList = in.createTypedArrayList(EntityMovie.CREATOR);
    }

    public static final Creator<MoviesListResponse> CREATOR = new Creator<MoviesListResponse>() {
        @Override
        public MoviesListResponse createFromParcel(Parcel in) {
            return new MoviesListResponse(in);
        }

        @Override
        public MoviesListResponse[] newArray(int size) {
            return new MoviesListResponse[size];
        }
    };

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public List<EntityMovie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<EntityMovie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(currentPage);
        dest.writeLong(totalPages);
        dest.writeLong(totalResults);
        dest.writeTypedList(movieList);
    }
}
