package com.example.vilma.sportsscore;

/**
 * Created by Vilma on 3/18/2018.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


public class Tab1Activity extends Fragment {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private ListView listTeams;
    private TeamAdapter teamAdapter;
    String competitionId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View rootView = inflater.inflate(R.layout.tab1, container, false);

        String competitionId;
        SharedPreferences prefs = getActivity().getSharedPreferences("MyPref1",MODE_PRIVATE);
        competitionId = prefs.getString("competitionID",null);

        Log.i(LOG_TAG,"SEE ID AT TAB1(SUCCESSS!!!!): COmpetition id!!!!!: "+competitionId);

        listTeams = (ListView) rootView.findViewById(R.id.listTeams);
        teamAdapter = new TeamAdapter(rootView.getContext(), R.layout.teams_custom_layout);
        listTeams.setAdapter(teamAdapter);
        loadTeams(competitionId);
        listTeams.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Team data =  (Team) parent.getItemAtPosition(position);
            String teamId = data.getTeamId();
            Toast.makeText(getActivity(), "data id: "+teamId,Toast.LENGTH_LONG).show();
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