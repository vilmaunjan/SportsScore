package com.example.vilma.sportsscore;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vilma on 3/26/2018.
 */

public class LeagueTableAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public LeagueTableAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void add(LeagueTable object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public void clear() {
        super.clear();
        list.clear();

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        LeagueTableAdapter.LeagueTableHolder leagueHolder;

        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.league_custom_layout, parent, false);
            leagueHolder = new LeagueTableAdapter.LeagueTableHolder();
            leagueHolder.lblPosition = (TextView) row.findViewById(R.id.lblPosition);
            leagueHolder.lblTeamName = (TextView) row.findViewById(R.id.lblTname);
            leagueHolder.lblGamesPlayed = (TextView) row.findViewById(R.id.lblNumPlayedGames);
            leagueHolder.lblGoals = (TextView) row.findViewById(R.id.lblGoals);
            leagueHolder.lblPoints = (TextView) row.findViewById(R.id.lblPoints);
            leagueHolder.lblWins = (TextView) row.findViewById(R.id.lblWins);
            leagueHolder.lblDraws = (TextView) row.findViewById(R.id.lblDraws);
            leagueHolder.lblLosses = (TextView) row.findViewById(R.id.lblLosses);
            row.setTag(leagueHolder);
        }else{
            leagueHolder = (LeagueTableAdapter.LeagueTableHolder) row.getTag();
        }
        LeagueTable l = (LeagueTable) this.getItem(position);
        leagueHolder.lblPosition.setText(l.getPosition());
        leagueHolder.lblTeamName.setText(l.getTeamName());
        leagueHolder.lblGamesPlayed.setText("Games played: "+l.getGamesPlayed());
        leagueHolder.lblGoals.setText("Goals: "+l.getGoals());
        leagueHolder.lblPoints.setText("Points: "+l.getPoints());
        leagueHolder.lblWins.setText("Wins: "+l.getWins());
        leagueHolder.lblDraws.setText("Draws: "+l.getDraws());
        leagueHolder.lblLosses.setText("Losses: "+l.getLosses());
        return row;
    }

    static class LeagueTableHolder{
        TextView lblPosition, lblTeamName, lblGamesPlayed, lblGoals, lblPoints, lblWins, lblDraws, lblLosses;
    }
}
