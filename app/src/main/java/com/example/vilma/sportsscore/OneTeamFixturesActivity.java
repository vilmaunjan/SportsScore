package com.example.vilma.sportsscore;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class OneTeamFixturesActivity extends AppCompatActivity {

    private static final String LOG_TAG = OneTeamFixturesActivity.class.getSimpleName();

    //For list view
    private ListView listTeamFixtures;
    private FixturesAdapter fixturesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_team_fixtures);
        initUI();
    }

    private void initUI() {
        listTeamFixtures = (ListView) findViewById(R.id.listTeamFixtures);
        fixturesAdapter = new FixturesAdapter(this, R.layout.fixtures_custom_layout);
        listTeamFixtures.setAdapter(fixturesAdapter);
        loadTeamFixtures();
    }

    public void loadTeamFixtures() {
        //For checking the network state and empty search field case
        ConnectivityManager connMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        Log.i(LOG_TAG,"1. Made it to loadCompetitions");

        if (networkInfo != null && networkInfo.isConnected()) {
            Log.i(LOG_TAG,"2. Made it to the if statement in load competitions");
            new FetchTeamFixtures(fixturesAdapter, listTeamFixtures,this).execute();
        } else {
            Log.i(LOG_TAG,"2. DID NOT ENTER THE IFFF!!!");
        }
    }
}
