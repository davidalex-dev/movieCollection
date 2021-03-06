package com.example.moviecollection.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviecollection.model.Movies;
import com.example.moviecollection.model.NowPlaying;
import com.example.moviecollection.model.Upcoming;
import com.example.moviecollection.repositories.MovieRepository;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository repository;

    public MovieViewModel(@NonNull Application application){
        super(application);
        repository = MovieRepository.getInstance();
    }

    //==Begin of viewmodel get movie by id==//
    private MutableLiveData<Movies> resultGetMovieById = new MutableLiveData<>();
    public void getMovieById(String movieId){
        resultGetMovieById = repository.getMovieData(movieId);
    }

    public LiveData<Movies> getResultGetMovieById(){
        return resultGetMovieById;
    }

    //==End of viewmodel get movie by id==//

    //==Begin of viewmodel get now playing

    private MutableLiveData<NowPlaying> resultGetNowPlaying = new MutableLiveData<>();
    public void getNowPlaying(){
        resultGetNowPlaying = repository.getNowPlayingData();
    }
    public LiveData<NowPlaying> getResultNowPlaying(){
        return resultGetNowPlaying;
    }

    //==End of viewmodel get now playing

    //==Begin of viewmodel get upcoming

    private MutableLiveData<Upcoming> resultGetUpcoming = new MutableLiveData<>();
    public void getUpcoming(){
        resultGetUpcoming = repository.getUpcomingData();
    }
    public LiveData<Upcoming> getResultUpcoming(){
        return resultGetUpcoming;
    }

    //==End of viewmodel get upcoming


}
