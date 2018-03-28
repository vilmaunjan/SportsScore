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

public class FixturesAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public FixturesAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void add(Fixtures object) {
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
        FixturesAdapter.FixturesHolder fixturesHolder;

        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.fixtures_custom_layout, parent, false);
            fixturesHolder = new FixturesAdapter.FixturesHolder();
            fixturesHolder.lblDate = (TextView) row.findViewById(R.id.lblDate);
            fixturesHolder.lblStatus = (TextView) row.findViewById(R.id.lblStatus);
            fixturesHolder.lblHomeTeam = (TextView) row.findViewById(R.id.lblHomeTeam);
            fixturesHolder.lblAwayTeam = (TextView) row.findViewById(R.id.lblAwayTeam);
            fixturesHolder.lblHomeGoals = (TextView) row.findViewById(R.id.lblHomeGoals);
            fixturesHolder.lblAwaysGoals = (TextView) row.findViewById(R.id.lblAwayGoals);
            fixturesHolder.lblHomeWin = (TextView) row.findViewById(R.id.lblHomeWin);
            fixturesHolder.lblDraw = (TextView) row.findViewById(R.id.lblDraw);
            fixturesHolder.lblAwayWin = (TextView) row.findViewById(R.id.lblAwayWin);
            row.setTag(fixturesHolder);
        }else{
            fixturesHolder = (FixturesAdapter.FixturesHolder) row.getTag();
        }
        Fixtures fixtures = (Fixtures) this.getItem(position);
        fixturesHolder.lblDate.setText(fixtures.getDate());
        fixturesHolder.lblStatus.setText("Status: "+fixtures.getStatus());
        fixturesHolder.lblHomeTeam.setText(fixtures.getHomeTeam());
        fixturesHolder.lblAwayTeam.setText(fixtures.getAwayTeam());
        fixturesHolder.lblHomeGoals.setText(fixtures.getHomeGoals());
        fixturesHolder.lblAwaysGoals.setText(fixtures.getAwaysGoals());
        fixturesHolder.lblHomeWin.setText("Home win: "+fixtures.getHomeWin());
        fixturesHolder.lblDraw.setText("Draw: "+fixtures.getDraw());
        fixturesHolder.lblAwayWin.setText("Away win: "+fixtures.getAwayWin());
        return row;
    }

    static class FixturesHolder{
        TextView lblDate, lblStatus, lblHomeTeam, lblAwayTeam, lblHomeGoals, lblAwaysGoals, lblHomeWin, lblDraw, lblAwayWin;

    }
}
