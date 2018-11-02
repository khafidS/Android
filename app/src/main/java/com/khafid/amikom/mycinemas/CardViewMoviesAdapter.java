package com.khafid.amikom.mycinemas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class CardViewMoviesAdapter extends RecyclerView.Adapter<CardViewMoviesAdapter.CardViewHolder> {

    private ArrayList<Movies> filems;
    private Context context;

    public CardViewMoviesAdapter(Context context){this.context = context;}
    public ArrayList<Movies> getFilems() {
        return filems;
    }

    public void setFilems(ArrayList<Movies> filems) {
        this.filems = filems;
    }
    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movies, viewGroup, false);
        CardViewHolder viewHolder = new CardViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int i) {
        final Movies f = getFilems().get(i);

        Glide.with(context)
                .load(f.getImgPoster())
                .override(350, 350)
                .into(cardViewHolder.imgPoster);

        cardViewHolder.txtTitle.setText(f.getTxtTitle());
        String overview = f.getTxtOverview();
        if(overview.length() >= 60){
            overview = f.getTxtOverview().substring(0, 60) + "... ";
        }
        cardViewHolder.txtOverview.setText(overview);
        cardViewHolder.txtRelease.setText(f.getReleaseDate());

        cardViewHolder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMovies.class);
                intent.putExtra("F", f);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return getFilems().size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPoster;
        TextView txtTitle, txtOverview, txtRelease;
        Button btnDetail;

        public CardViewHolder(View view) {
            super(view);
            imgPoster = (ImageView)itemView.findViewById(R.id.imgPoster);
            txtTitle = (TextView)itemView.findViewById(R.id.txtTitle);
            txtOverview = (TextView)itemView.findViewById(R.id.txtOverview);
            btnDetail = (Button)itemView.findViewById(R.id.btnDetail);
            txtRelease = (TextView)itemView.findViewById(R.id.txtRelease);
        }
    }

}
