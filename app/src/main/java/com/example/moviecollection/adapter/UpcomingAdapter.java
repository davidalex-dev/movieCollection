package com.example.moviecollection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviecollection.R;
import com.example.moviecollection.helper.Const;
import com.example.moviecollection.model.Upcoming;

import java.util.List;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.CardViewViewHolder>{

    private Context context;
    private List<Upcoming.Results> listUpcoming;
    private List<Upcoming.Results> getListUpcoming(){
        return listUpcoming;
    }
    public void setListUpcoming(List<Upcoming.Results> listUpcoming){
        this.listUpcoming = listUpcoming;
    }
    public UpcomingAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public UpcomingAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_movie, parent, false);
        return new UpcomingAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingAdapter.CardViewViewHolder holder, int position) {
        final Upcoming.Results results = getListUpcoming().get(position);
        holder.lbl_title.setText(results.getTitle());
        holder.lbl_overview.setText(results.getOverview());
        holder.lbl_release_date.setText(results.getRelease_date());
        Glide.with(context).load(Const.IMG_URL + results.getPoster_path()).into(holder.img_poster);
    }

    @Override
    public int getItemCount() {
        return getListUpcoming().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView img_poster;
        TextView lbl_title, lbl_overview, lbl_release_date;
        CardView cv;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            img_poster = itemView.findViewById(R.id.img_poster_card_movie);
            lbl_title = itemView.findViewById(R.id.lbl_title_card_movie);
            lbl_overview = itemView.findViewById(R.id.lbl_overview_card_movie);
            lbl_release_date = itemView.findViewById(R.id.lbl_releasedate_card_movie);
            cv = itemView.findViewById(R.id.cv_card_movie);
        }
    }
}
