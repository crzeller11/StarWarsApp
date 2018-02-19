package com.example.chloerainezeller.starwarsapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


// FIXME: Make sure that the changes to activity movie detail are interpolated in the code
public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Context mContext;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        // data to display
        final ArrayList<Movie> movieList = Movie.getMoviesFromFile("movies.json",
                this);

        MovieAdapter adapter = new MovieAdapter(this, movieList);

        mListView = findViewById(R.id.movie_list_view);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // allows each row to be clickable, when the row is clicked, the intent is created
            // and sent
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie selectedMovie = movieList.get(position);

                resultTextView = view.findViewById(R.id.movie_list_has_seen);

                // create intent package, information we need for detail page
                Intent detailIntent = new Intent(mContext, MovieDetailActivity.class);
                detailIntent.putExtra("title", selectedMovie.title);
                detailIntent.putExtra("url", selectedMovie.posterImage);
                detailIntent.putExtra("description", selectedMovie.description);

                launchActivity(detailIntent);

            }

        });

    }

    private void launchActivity(Intent intent) {
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                boolean already_seen_button = data.getBooleanExtra("radio_button_already_seen", false);
                boolean want_to_see_button = data.getBooleanExtra("radio_button_want_to_see", false);
                boolean do_not_like_button = data.getBooleanExtra("radio_button_do_not_like", false);

                if (already_seen_button) {
                    resultTextView.setText("Already seen");
                }
                else if (want_to_see_button) {
                    resultTextView.setText("Want to see");
                }
                else if (do_not_like_button) {
                    resultTextView.setText("Do not like");
                }
            }
        }
    }
}
