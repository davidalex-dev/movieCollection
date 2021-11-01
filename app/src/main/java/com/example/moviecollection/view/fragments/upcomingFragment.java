package com.example.moviecollection.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviecollection.R;
import com.example.moviecollection.adapter.UpcomingAdapter;
import com.example.moviecollection.helper.ItemClickSupport;
import com.example.moviecollection.model.Upcoming;
import com.example.moviecollection.viewmodel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link upcomingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class upcomingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public upcomingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment upcomingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static upcomingFragment newInstance(String param1, String param2) {
        upcomingFragment fragment = new upcomingFragment();
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

    private RecyclerView rv_upcoming;
    private MovieViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);

        rv_upcoming = view.findViewById(R.id.rv_upcoming_fragment);
        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        viewModel.getUpcoming();
        viewModel.getResultUpcoming().observe(getActivity(), showUpcoming);



        return view;
    }

    private Observer<Upcoming> showUpcoming = new Observer<Upcoming>() {
        @Override
        public void onChanged(Upcoming upcoming) {
            rv_upcoming.setLayoutManager(new LinearLayoutManager(getActivity()));
            UpcomingAdapter adapter = new UpcomingAdapter(getActivity());
            adapter.setListUpcoming(upcoming.getResults());
            rv_upcoming.setAdapter(adapter);

            ItemClickSupport.addTo(rv_upcoming).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                    return false;
                }
            });

            ItemClickSupport.addTo(rv_upcoming).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("movieId", String.valueOf(upcoming.getResults().get(position).getId()));
                    Navigation.findNavController(v).navigate(R.id.action_upcomingFragment_to_movieDetailsFragment, bundle);
                }
            });
        }
    };
}