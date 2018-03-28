package com.example.vilma.sportsscore;

/**
 * Created by Vilma on 3/18/2018.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class Tab1Activity extends Fragment {

    private static final String LOG_TAG = Tab1Activity.class.getSimpleName();
    private ListView listTeams;
    private TeamAdapter teamAdapter;
    List<Team> team;
    //String competitionId;
    Favorites f;
    Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View rootView = inflater.inflate(R.layout.tab1, container, false);

        //For favorites
        activity = getActivity();
        f = new Favorites();

        String competitionId;
        SharedPreferences prefs = getActivity().getSharedPreferences("MyPref1",MODE_PRIVATE);
        competitionId = prefs.getString("competitionID",null);

        listTeams = (ListView) rootView.findViewById(R.id.listTeams);
        teamAdapter = new TeamAdapter(rootView.getContext(), R.layout.teams_custom_layout);
        listTeams.setAdapter(teamAdapter);
        loadTeams(competitionId);
        listTeams.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
          /*      Team data = (Team) parent.getItemAtPosition(position);
                ImageView btn = (ImageView) view.findViewById(R.id.fav);

                String tag = btn.getTag().toString();
                if (tag.equalsIgnoreCase("no")) {
                    f.addFavorite(activity, team.get(position)); //add current to the list
                    Toast.makeText(activity, "Added clicked team to favorites", Toast.LENGTH_LONG).show();
                    btn.setTag("yes");
                    btn.setImageResource(R.drawable.ic_fav);
                } else {
                    f.removeFavorite(activity, team.get(position));
                    btn.setTag("no");
                    btn.setImageResource(R.drawable.ic_unfav);
                    Toast.makeText(activity, "Removed clicked team to favorites", Toast.LENGTH_LONG).show();
                }
                //String teamId = data.getTeamId();
                //Toast.makeText(getActivity(), "data id: "+teamId,Toast.LENGTH_LONG).show();
            */    //}
                return true;
            }
        });
        return rootView;
    }

    public void loadTeams(String competitionId) {

        //For checking the network state and empty search field case
        ConnectivityManager connMgr = (ConnectivityManager) getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        Log.i(LOG_TAG,"1. Made it to loadCompetitions");

        if (networkInfo != null && networkInfo.isConnected()) {
            Log.i(LOG_TAG,"2. Made it to the if statement in load competitions");
            new FetchTeam(teamAdapter, listTeams).execute(competitionId);
        } else {
            Log.i(LOG_TAG,"2. DID NOT ENTER THE IFFF!!!");
        }
    }
}