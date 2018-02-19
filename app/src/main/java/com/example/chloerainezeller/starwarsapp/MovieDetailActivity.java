package com.example.chloerainezeller.starwarsapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private Context mContext;
    private TextView mTitleView;
    private ImageView mThumbnailView;
    private TextView mDescriptionView;
    private Button submitButton;
    private boolean alreadySeen;
    private boolean wantToSee;
    private boolean doNotLike;
    //private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mContext = this;
        submitButton = findViewById(R.id.submit_button);

        String title = this.getIntent().getExtras().getString("title");

        String posterUrl = this.getIntent().getExtras().getString("url");
        System.out.println("MOVIE DETAIL ACTIVITY: POSTER URL FOUND IN INTENT FROM MAIN ACTIVITY:");
        System.out.println(posterUrl);
        String description = this.getIntent().getExtras().getString("description");

        setTitle(title);

        mTitleView = findViewById(R.id.movie_detail_title);

        mThumbnailView = findViewById(R.id.movie_detail_thumbnail);

        mDescriptionView = findViewById(R.id.movie_detail_description);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // construct intent
                Intent radioButtonIntent = new Intent();
                // put three boolean values into intent
                radioButtonIntent.putExtra("radio_button_already_seen", alreadySeen);
                radioButtonIntent.putExtra("radio_button_want_to_see", wantToSee);
                radioButtonIntent.putExtra("radio_button_do_not_like", doNotLike);
                // send back to main activity
                setResult(RESULT_OK, radioButtonIntent);

                finish();
            }
        });

        // load the information to the view
        mTitleView.setText(title);
        Picasso.with(mContext).load(posterUrl).into(mThumbnailView);
        mDescriptionView.setText(description);
    }

    public void alreadySeen(View view) {
        alreadySeen = ((RadioButton) view).isChecked();
    }
    public void wantToSee(View view) {
        wantToSee = ((RadioButton) view).isChecked();
    }
    public void doNotLike(View view) {
        doNotLike = ((RadioButton) view).isChecked();
    }
}
