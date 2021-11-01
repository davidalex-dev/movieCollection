package com.example.moviecollection.view.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviecollection.R;
import com.example.moviecollection.adapter.ProductionCompanyAdapter;
import com.example.moviecollection.helper.Const;
import com.example.moviecollection.model.Movies;
import com.example.moviecollection.view.activities.MainMenuActivity;
import com.example.moviecollection.viewmodel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailsFragment newInstance(String param1, String param2) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextView lbl_movie_title, lbl_movie_release_date, lbl_movie_runtime,
            lbl_movie_summary, lbl_movie_tagline, lbl_movie_genres, lbl_movie_vote,
            lbl_movie_popularity, lbl_movie_production_companies;
    private ImageView imageView_movie_poster, imageView_movie_backdrop,
            imageView_movie_vote, imageView_movie_popularity;
    private MovieViewModel viewModel;
    private RecyclerView rv_production_companies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_details, container, false);

        lbl_movie_title = view.findViewById(R.id.lbl_movie_title_movie_details_fragment);
        lbl_movie_release_date = view.findViewById(R.id.lbl_movie_release_date_movie_details_fragment);
        lbl_movie_runtime = view.findViewById(R.id.lbl_movie_runtime_movie_details_fragment);
        lbl_movie_summary = view.findViewById(R.id.lbl_movie_summary_movie_details_fragment);
        lbl_movie_tagline = view.findViewById(R.id.lbl_movie_tagline_movie_details_fragment);
        lbl_movie_genres = view.findViewById(R.id.lbl_movie_genres_movie_details_fragment);
        lbl_movie_vote = view.findViewById(R.id.lbl_movie_vote_movie_details_fragment);
        lbl_movie_popularity = view.findViewById(R.id.lbl_movie_popularity_movie_details_fragment);
        lbl_movie_production_companies = view.findViewById(R.id.lbl_production_companies_movie_details_fragment);
        imageView_movie_poster = view.findViewById(R.id.imageView_movie_poster_movie_details_fragment);
        imageView_movie_backdrop = view.findViewById(R.id.imageView_movie_backdrop_movie_details_fragment);
        imageView_movie_vote = view.findViewById(R.id.imageView_movie_vote_movie_details_fragment);
        imageView_movie_popularity = view.findViewById(R.id.imageView_movie_popularity_movie_details_fragment);
        rv_production_companies = view.findViewById(R.id.rv_production_companies);

        String movieId = getArguments().getString("movieId");

        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        viewModel.getMovieById(movieId);
        viewModel.getResultGetMovieById().observe(getActivity(), showResultMovie);

        return view;



    }

    private Observer<Movies> showResultMovie = new Observer<Movies>() {

        @Override
        public void onChanged(Movies movies) {


            //get data
            String title = movies.getTitle();
            String summary = movies.getOverview();
            String runtime = String.valueOf(movies.getRuntime());
            String img_path = movies.getPoster_path().toString();
            String backdrop_path = movies.getBackdrop_path();
            String release_date = movies.getRelease_date();
            String tagline = movies.getTagline();
            String genre = "";
            String vote_count = String.valueOf(movies.getVote_count());
            String vote_average = String.valueOf(movies.getVote_average());
            String popularity = String.valueOf(movies.getPopularity());
            String vote = vote_average + " (from " + vote_count + " votes)";

            LinearLayoutManager company_lm
                    = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);

            rv_production_companies.setLayoutManager(company_lm);
            ProductionCompanyAdapter adapter = new ProductionCompanyAdapter(getActivity());
            adapter.setListProductionCompanies(movies.getProduction_companies());
            rv_production_companies.setAdapter(adapter);

            for (int i = 0; i < movies.getGenres().size(); i++){
                if(i == movies.getGenres().size() - 1){
                    genre += movies.getGenres().get(i).getName();
                }else{
                    genre += movies.getGenres().get(i).getName()+", ";
                }
            }

            //set data
            lbl_movie_title.setText(title);
            lbl_movie_summary.setText(summary);
            lbl_movie_runtime.setText(runtime + " minutes");
            lbl_movie_release_date.setText(release_date);
            lbl_movie_tagline.setText(tagline);
            lbl_movie_genres.setText(genre);
            lbl_movie_vote.setText(vote);
            lbl_movie_popularity.setText(popularity);
            Glide.with(getActivity()).load(Const.IMG_URL + img_path).into(imageView_movie_poster);
            Glide.with(getActivity()).load(Const.IMG_URL + backdrop_path).into(imageView_movie_backdrop);

            //set visibility
            lbl_movie_title.setVisibility(View.VISIBLE);
            lbl_movie_summary.setVisibility(View.VISIBLE);
            lbl_movie_runtime.setVisibility(View.VISIBLE);
            lbl_movie_release_date.setVisibility(View.VISIBLE);
            lbl_movie_genres.setVisibility(View.VISIBLE);
            lbl_movie_vote.setVisibility(View.VISIBLE);
            lbl_movie_popularity.setVisibility(View.VISIBLE);
            lbl_movie_production_companies.setVisibility(View.VISIBLE);
            imageView_movie_poster.setVisibility(View.VISIBLE);
            imageView_movie_backdrop.setVisibility(View.VISIBLE);
            imageView_movie_popularity.setVisibility(View.VISIBLE);
            imageView_movie_vote.setVisibility(View.VISIBLE);
            rv_production_companies.setVisibility(View.VISIBLE);

        }
    };

}