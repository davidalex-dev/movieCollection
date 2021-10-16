package com.example.moviecollection.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviecollection.R;
import com.example.moviecollection.helper.Const;
import com.example.moviecollection.model.Movies;
import com.example.moviecollection.viewmodel.MovieViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel viewModel;
    private Button btn_hit_main;
    private TextView txt_show;
    private TextInputLayout til_movie_id;
    private ImageView img_poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(MainActivity.this).get(MovieViewModel.class);

        btn_hit_main = findViewById(R.id.btn_hit_main);
        txt_show = findViewById(R.id.txt_show);
        til_movie_id = findViewById(R.id.til_movie_id);
        img_poster = findViewById(R.id.img_poster);

        btn_hit_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movieId = til_movie_id.getEditText().getText().toString().trim();
                if(movieId.isEmpty()){
                    til_movie_id.setError("Please fill the movie ID field!");
                }else{
                    til_movie_id.setError(null);
                    viewModel.getMovieById(movieId);
                    viewModel.getResultGetMovieById().observe(MainActivity.this, showResultMovie);
                }

            }
        });

    }

    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            if(movies == null){
                txt_show.setVisibility(View.VISIBLE);
                txt_show.setText("Movie ID is not available in MovieDB");
            }else{
                txt_show.setVisibility(View.VISIBLE);
                String title = movies.getTitle();
                String img_path = movies.getPoster_path().toString();
                Glide.with(MainActivity.this).load(Const.IMG_URL + img_path).into(img_poster);
                txt_show.setText(title);
            }

        }
    };

}