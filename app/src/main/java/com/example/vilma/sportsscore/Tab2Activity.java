package com.example.vilma.sportsscore;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Vilma on 3/18/2018.
 */

public class Tab2Activity extends Fragment {

    private static final String LOG_TAG = Tab2Activity.class.getSimpleName();
    private ListView listLeagueTable;
    private LeagueTableAdapter leagueAdapter;
    String competitionId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);

        String competitionId;
        SharedPreferences prefs = getActivity().getSharedPreferences("MyPref1",MODE_PRIVATE);
        competitionId = prefs.getString("competitionID",null);

        listLeagueTable = (ListView) rootView.findViewById(R.id.listLeague);
        leagueAdapter = new LeagueTableAdapter(rootView.getContext(), R.layout.league_custom_layout);
        listLeagueTable.setAdapter(leagueAdapter);
        loadLeagueTable(competitionId);

        return rootView;
    }


    public void loadLeagueTable(String competitionId) {
        //For checking the network state and empty search field case
        ConnectivityManager connMgr = (ConnectivityManager) getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        Log.i(LOG_TAG,"1. Made it to loadLeagueTable");

        if (networkInfo != null && networkInfo.isConnected()) {
            Log.i(LOG_TAG,"2. Made it to the if statement in load league table");
            new FetchLeagueTable(leagueAdapter, listLeagueTable).execute(competitionId);
        } else {
            Log.i(LOG_TAG,"2. DID NOT ENTER THE IF");
        }
    }

}
