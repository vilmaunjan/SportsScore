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

public class CompetitionAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public CompetitionAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void add(Competition object) {
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
        CompetitionHolder competitionHolder;

        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.competition_custom_layout, parent, false);
            competitionHolder = new CompetitionHolder();
            competitionHolder.lblCaption = (TextView) row.findViewById(R.id.lblCaption);
            competitionHolder.lblLeague = (TextView) row.findViewById(R.id.lblLeague);
            competitionHolder.lblYear = (TextView) row.findViewById(R.id.lblYear);
            competitionHolder.lblNumTeams = (TextView) row.findViewById(R.id.lblNumTeams);
            competitionHolder.lblNumGames = (TextView) row.findViewById(R.id.lblNumGames);
            row.setTag(competitionHolder);
        }else{
            competitionHolder = (CompetitionHolder) row.getTag();
        }
        Competition competition = (Competition) this.getItem(position);
        competitionHolder.lblCaption.setText(competition.getCaption());
        competitionHolder.lblLeague.setText("League: "+competition.getLeague());
        competitionHolder.lblYear.setText("Year: "+competition.getYear());
        competitionHolder.lblNumTeams.setText("Number of Teams: "+competition.getNumTeams());
        competitionHolder.lblNumGames.setText("Number of Games: "+competition.getNumGames());
        return row;
    }

    static class CompetitionHolder{
        TextView lblCaption, lblLeague, lblYear, lblNumTeams, lblNumGames;
    }
}
