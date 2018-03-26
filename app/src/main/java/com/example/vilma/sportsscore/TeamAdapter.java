package com.example.vilma.sportsscore;

/**
 * Created by Vilma on 3/25/2018.
 */

import android.content.Context;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public TeamAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void add(Team object) {
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
        TeamHolder teamHolder;

        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.teams_custom_layout, parent, false);
            teamHolder = new TeamHolder();
            teamHolder.lblTeamname = (TextView) row.findViewById(R.id.lblName);
            teamHolder.lblSquadMV = (TextView) row.findViewById(R.id.lblMV);
            row.setTag(teamHolder);
        }else{
            teamHolder = (TeamHolder) row.getTag();
        }
        Team team = (Team) this.getItem(position);
        teamHolder.lblTeamname.setText(team.getTeamName());
        teamHolder.lblSquadMV.setText("Squad Market Value: "+team.getSquadMV());
        return row;
    }

    static class TeamHolder{
        TextView lblTeamname, lblSquadMV;
    }
}