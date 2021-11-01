package com.example.moviecollection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviecollection.R;
import com.example.moviecollection.helper.Const;
import com.example.moviecollection.model.Movies;

import java.util.List;

public class ProductionCompanyAdapter extends RecyclerView.Adapter<ProductionCompanyAdapter.CardViewViewHolder>{
    private Context context;
    private List<Movies.ProductionCompanies> listProductionCompanies;
    private List<Movies.ProductionCompanies> getListProductionCompanies(){
        return listProductionCompanies;
    }
    public void setListProductionCompanies(List<Movies.ProductionCompanies> listProductionCompanies){
        this.listProductionCompanies = listProductionCompanies;
    }
    public ProductionCompanyAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ProductionCompanyAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_production_company, parent, false);
        return new ProductionCompanyAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductionCompanyAdapter.CardViewViewHolder holder, int position) {
        final Movies.ProductionCompanies company = getListProductionCompanies().get(position);
        holder.name = company.getName();
        holder.logo_path = company.getLogo_path();
        Glide.with(context).load(Const.IMG_URL + holder.logo_path).into(holder.img_logo);

        holder.img_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), holder.name, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return getListProductionCompanies().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView img_logo;
        String name, logo_path;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            img_logo = itemView.findViewById(R.id.img_production_company_logo);

        }
    }
}
