package com.khafid.amikom.mycinemas;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Movies>>{
    private RecyclerView recyclerView;
    private ArrayList<Movies> list;
    private Button btnMoveActivity;

    private String URL = "https://api.themoviedb.org/3/movie/550?api_key=41a816cb914839a36a6d3cff932830b5";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.mainRecycler);
        recyclerView.setHasFixedSize(true);
        getSupportLoaderManager().initLoader(0, null, (LoaderManager.LoaderCallbacks<ArrayList<Movies>>) this).forceLoad();

    }

    private void showRecyclerCardView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CardViewMoviesAdapter CardViewMoviesAdapter = new CardViewMoviesAdapter(this);
        CardViewMoviesAdapter.setFilems(list);
        recyclerView.setAdapter(CardViewMoviesAdapter);
    }

    @Override
    public Loader<ArrayList<Movies>> onCreateLoader(int i, Bundle bundle) {
        return new MoviesAsync(this, URL);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Movies>> loader, ArrayList<Movies> filems) {
        this.list = filems;
        Log.d("LIST : ", String.valueOf(this.list.size()));
        if (this.list != null) {
            showRecyclerCardView();
        } else {
            setContentView(R.layout.detail_movie);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Movies>> loader) {
        this.list = null;
    }

    public void Pindah(View view) {
        Intent intent = new Intent(MainActivity.this, DetailMovies.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.bahasa:
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
