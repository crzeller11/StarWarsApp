package com.example.chloerainezeller.starwarsapp;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    // instance variables
    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private LayoutInflater mInflater;


    // constructor
    public MovieAdapter(Context mContext, ArrayList<Movie> mMovieList) {

        // initialize instance variables
        this.mContext = mContext;
        this.mMovieList = mMovieList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    // methods, 4 that we must override

    // gives you the number of movies in the json file
    @Override
    public int getCount() {
        return mMovieList.size();
    }

    // returns the movie at a given index
    @Override
    public Object getItem(int position) {
        return mMovieList.get(position);
    }

    // returns the row id associated with the specific position in the list
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // make
        ViewHolder holder;

        if (convertView == null) {
            // inflate
            convertView = mInflater.inflate(R.layout.list_item_movie, parent, false);

            // add views to holder
            holder = new ViewHolder();

            holder.titleTextView = convertView.findViewById(R.id.movie_list_title);
            holder.descriptionTextView = convertView.findViewById(R.id.movie_list_description);
            holder.mainCharacterTextView = convertView.findViewById(R.id.movie_list_main_characters);
            holder.hasSeenTextView = convertView.findViewById(R.id.movie_list_has_seen);
            holder.thumbnailImageView = convertView.findViewById(R.id.movie_list_thumbnail);

            // add the holder to the view for future use
            convertView.setTag(holder);

        }
        else {
            holder = (ViewHolder) convertView.getTag();
            TextView titleTextView = holder.titleTextView;
            TextView descriptionTextView = holder.descriptionTextView;
            TextView mainCharacterTextView = holder.mainCharacterTextView;
            TextView hasSeenTextView = holder.hasSeenTextView;
            ImageView thumbnailImageView = holder.thumbnailImageView;

            Movie movie = (Movie) getItem(position);

            // title text view
            titleTextView.setText(movie.title);
            titleTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
            titleTextView.setTextSize(20);

            // description text view
            descriptionTextView.setText(movie.description);
            descriptionTextView.setTextSize(9);

            // main characters text view
            mainCharacterTextView.setText(movie.mainCharacters);
            mainCharacterTextView.setTextSize(14);

            // FIXME: do I do this in the adapter? Where is this supposed to go?
            hasSeenTextView.setTextSize(11);
            hasSeenTextView.setTextColor(ContextCompat.getColor(mContext, R.color.black));

            Picasso.with(mContext).load(movie.posterImage).into(thumbnailImageView);
        }



        return convertView;

    }

    // FIXME: Make sure that the radio button display is included here, not sure if these are ALL the components of the ViewHolder
    private static class ViewHolder {
      public TextView titleTextView;
      public TextView descriptionTextView;
      public TextView mainCharacterTextView;
      public TextView hasSeenTextView;
      public ImageView thumbnailImageView;
    }


}
