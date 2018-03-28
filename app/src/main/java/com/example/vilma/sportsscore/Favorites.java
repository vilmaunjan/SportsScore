package com.example.vilma.sportsscore;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by Vilma on 3/27/2018.
 */

public class Favorites {

    public Favorites() {
        super();
    }

    public void saveFavorites(Context context, List<Team> favorites){
        SharedPreferences prefs;
        SharedPreferences.Editor editor;

        prefs = context.getSharedPreferences("FAV_TEAMS", Context.MODE_PRIVATE);
        editor = prefs.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString("fav_teams", jsonFavorites);
        editor.commit();

    }

    public void addFavorite(Context context, Team team){
        ArrayList<Team> favorites = getFavorites(context);
        if(favorites == null){
            favorites = new ArrayList<Team>();
        }
        favorites.add(team);
        saveFavorites(context,favorites);
    }

    public void removeFavorite(Context context, Team team){
        ArrayList<Team> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(team);
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<Team> getFavorites(Context context){

        SharedPreferences prefs;
        //ArrayList<Team> favorites;
        List<Team> favorites;

        prefs = context.getSharedPreferences("FAV_TEAMS",
                Context.MODE_PRIVATE);


        if (prefs.contains("fav_teams")) {
            String jsonFavorites = prefs.getString("fav_teams", null);

            Gson gson = new Gson();
            Team[] favoriteItems = gson.fromJson(jsonFavorites, Team[].class);

            //favorites = new ArrayList<Team>();
            //favorites.addAll(Arrays.asList(favoriteItems));
            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<Team>(favorites);
        } else
            return null;

        return (ArrayList<Team>) favorites;
    }
}