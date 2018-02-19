package com.example.chloerainezeller.starwarsapp;


import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class Movie {

    // instance variables
    public String title;
    public String episodeNumber;
    public String mainCharacters;
    public String description;
    public String posterImage;
    public String url;



    // methods
    // static method to read movie json file in, and load into Movie class
    public static ArrayList<Movie> getMoviesFromFile(String filename, Context context) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        // try to read from the JSON, get info using tags, construct movie object, add object to
        // arrayList, return arraylist
        try {
            String jsonString = loadJsonFromAsset("movies.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray movies = json.getJSONArray("movies");

            // for loop to go through all movies in the movies array

            for (int i = 0; i < movies.length(); i++) {
                Movie movie = new Movie();
                movie.title = movies.getJSONObject(i).getString("title");
                movie.episodeNumber = movies.getJSONObject(i).getString("episode_number");
                String temp = movies.getJSONObject(i).getString("main_characters");
                temp = Movie.getMainCharacters(temp);
                movie.mainCharacters = temp;
                movie.description = movies.getJSONObject(i).getString("description");
                movie.posterImage = movies.getJSONObject(i).getString("poster");
                movie.url = movies.getJSONObject(i).getString("url");

                // add movie object to arrayList
                movieList.add(movie);
            }
        }

        catch (JSONException e) {
            e.printStackTrace();
        }

        // return the completed arrayList
        return movieList;
    }
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }

        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;

        }

        return json;
    }

    // small method to parse only first three characters from the main characters list
    private static String getMainCharacters(String temp) {

        String characters[]= temp.split(",");
        String firstCharacter = characters[0].replace("\"", "")
                .replace("[", "").replace("]","");
        String secondCharacter = characters[1].replace("\"", "")
                .replace("[", "").replace("]","");;
        String thirdCharacter = characters[2].replace("\"", "")
                .replace("[", "").replace("]","");;
        String concatenation = firstCharacter + ", " + secondCharacter + ", " + thirdCharacter;
        return concatenation;
    }
}
