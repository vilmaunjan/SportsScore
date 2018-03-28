package com.example.vilma.sportsscore;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vilma on 3/27/2018.
 */

public class FavTeamAdapter extends ArrayAdapter<Team> {

    Context context;
    List<Team> teams;
    Favorites f;


    public FavTeamAdapter(@NonNull Context context, List<Team> teams) {
        super(context, R.layout.favorite_custom_layout, teams);
        this.context = context;
        this.teams = teams;
        f = new Favorites();
    }

    private class FavTeamHolder{
        TextView lblTeamname;
        ImageView favImage;
    }

    public void add(Team object) {
        super.add(object);
        teams.add(object);
        notifyDataSetChanged();
    }

    @Override
    public void remove(Team team) {
        super.remove(team);
        teams.remove(team);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        super.clear();
        teams.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return teams.size();
    }

    @Nullable
    @Override
    public Team getItem(int position) {
        return teams.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        FavTeamHolder teamHolder=null;
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.favorite_custom_layout, null);
            teamHolder = new FavTeamHolder();
            teamHolder.lblTeamname = (TextView) row.findViewById(R.id.lblFavName);
            row.setTag(teamHolder);
            //teamHolder.favImage = (ImageView) row.findViewById(R.id.fav);
        }else{
            teamHolder = (FavTeamHolder) row.getTag();
        }
        Team team = (Team) this.getItem(position);
        teamHolder.lblTeamname.setText(team.getTeamName());

        return row;
    }

    /*Checks whether a particular product exists in SharedPreferences*/
    public boolean checkFavoriteItem(Team checkProduct) {
        boolean check = false;
        List<Team> favorites = f.getFavorites(context);
        if (favorites != null) {
            for (Team t : favorites) {
                if (t.equals(checkProduct)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }




}