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
import android.widget.ListView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Vilma on 3/18/2018.
 */

public class Tab3Activity extends Fragment {

    private static final String LOG_TAG = Tab3Activity.class.getSimpleName();
    private ListView listFixtures;
    private FixturesAdapter fixturesAdapter;
    String competitionId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3, container, false);

        String competitionId;
        SharedPreferences prefs = getActivity().getSharedPreferences("MyPref1",MODE_PRIVATE);
        competitionId = prefs.getString("competitionID",null);

        listFixtures = (ListView) rootView.findViewById(R.id.listFixtures);
        fixturesAdapter = new FixturesAdapter(rootView.getContext(), R.layout.fixtures_custom_layout);
        listFixtures.setAdapter(fixturesAdapter);
        loadFixtures(competitionId);

        return rootView;
    }

    public void loadFixtures(String competitionId) {
        //For checking the network state and empty search field case
        ConnectivityManager connMgr = (ConnectivityManager) getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        Log.i(LOG_TAG,"1. Made it to loadFixtures");

        if (networkInfo != null && networkInfo.isConnected()) {
            Log.i(LOG_TAG,"2. Made it to the if statement in load league table");
            new FetchFixtures(fixturesAdapter, listFixtures).execute(competitionId);
        } else {
            Log.i(LOG_TAG,"2. DID NOT ENTER THE IF!!!");
        }
    }
}
