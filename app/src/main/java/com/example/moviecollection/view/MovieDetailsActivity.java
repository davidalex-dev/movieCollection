package com.example.moviecollection.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviecollection.R;
import com.example.moviecollection.helper.Const;
import com.example.moviecollection.model.Movies;
import com.example.moviecollection.viewmodel.MovieViewModel;

public class MovieDetailsActivity extends AppCompatActivity {

    private MovieViewModel viewModel;
    private TextView lbl_text, lbl_movie_name, lbl_now_loading, lbl_movie_summary, lbl_movie_runtime, lbl_movie_release_date;
    private String movie_id = "";
    private ImageView image_poster;
    private static final String TAG = "MovieDetailsActivity";
    private Toolbar toolbar_movie_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();
        movie_id = intent.getStringExtra("movie_id");

        lbl_text = findViewById(R.id.lbl_movie_details);
        lbl_movie_name = findViewById(R.id.lbl_movie_name);
        lbl_now_loading = findViewById(R.id.lbl_now_loading);
        lbl_movie_summary = findViewById(R.id.lbl_movie_summary);
        lbl_movie_runtime = findViewById(R.id.lbl_movie_runtime);
        lbl_movie_release_date = findViewById(R.id.lbl_movie_release_date);
        toolbar_movie_details = findViewById(R.id.toolbar_movie_details);
        image_poster = findViewById(R.id.image_poster);
        viewModel = new ViewModelProvider(MovieDetailsActivity.this).get(MovieViewModel.class);
        lbl_text.setText(movie_id);
        viewModel.getMovieById(movie_id);
        viewModel.getResultGetMovieById().observe(MovieDetailsActivity.this, showResultMovie);

        toolbar_movie_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
                //get data
                String title = movies.getTitle();
                String summary = movies.getOverview();
                String runtime = String.valueOf(movies.getRuntime());
                String img_path = movies.getPoster_path().toString();
                String release_date = movies.getRelease_date();

                //set data
                lbl_movie_name.setText(title);
                lbl_movie_summary.setText(summary);
                lbl_movie_runtime.setText(runtime + " minutes");
                lbl_movie_release_date.setText("Released on " + release_date);
                Glide.with(MovieDetailsActivity.this).load(Const.IMG_URL + img_path).into(image_poster);

                //set visibility
                lbl_movie_name.setVisibility(View.VISIBLE);
                lbl_movie_summary.setVisibility(View.VISIBLE);
                lbl_movie_runtime.setVisibility(View.VISIBLE);
                lbl_movie_release_date.setVisibility(View.VISIBLE);
                image_poster.setVisibility(View.VISIBLE);
                lbl_now_loading.setVisibility(View.GONE);
            }

    };
}