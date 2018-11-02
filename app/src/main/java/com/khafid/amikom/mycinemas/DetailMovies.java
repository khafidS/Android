package com.khafid.amikom.mycinemas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;



public class DetailMovies extends AppCompatActivity {
    private TextView txtTitle;
    private TextView txtOverview;
    private TextView txtRelease;
    private ImageView imgPoster;
    private TextView txtRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_movie);

        Movies f = (Movies) getIntent().getSerializableExtra("F");
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText(f.getTxtTitle());
        txtOverview = (TextView) findViewById(R.id.txtSynopsisContent);
        txtOverview.setText(f.getTxtOverview());
        txtRelease = (TextView) findViewById(R.id.txtDate);
        txtRelease.setText(" "+f.getReleaseDate());
        imgPoster = (ImageView) findViewById(R.id.ivPoster);
        Glide.with(getApplicationContext())
                .load(f.getImgPoster())
                .override(350, 350)
                .into(imgPoster);
        txtRating = (TextView) findViewById(R.id.txtRating);
        txtRating.setText(" "+f.getRating());
    }
}