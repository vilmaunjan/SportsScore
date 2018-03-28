package com.example.vilma.sportsscore;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Vilma on 3/21/2018.
 */

public class FetchTeam extends AsyncTask<String, Void, String> {

    private static final String LOG_TAG = FetchTeam.class.getSimpleName();
    TeamAdapter teamsAdapter;
    ListView teamsList;

    public FetchTeam(TeamAdapter teamsAdapter, ListView teamsList) {
        this.teamsAdapter = teamsAdapter;
        this.teamsList = teamsList;
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getTeamInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject jsonObject = new JSONObject(s);

            JSONArray teamsArray = jsonObject.getJSONArray("teams");
            Log.d(LOG_TAG, "teamsarray: "+teamsArray);

            for (int i = 0; i < teamsArray.length(); i++) {
                JSONObject teamObj = teamsArray.getJSONObject(i);
                String id = null;
                String fixtures = null;
                String players = null;
                String name = null;
                String squadMarketValue = null;

                try {
                    JSONObject linksObj = teamObj.getJSONObject("_links");
                    JSONObject idLink = linksObj.getJSONObject("self");
                    id = idLink.getString("href");
                    JSONObject fixturesLink = linksObj.getJSONObject("fixtures");
                    fixtures = fixturesLink.getString("href");
                    JSONObject playersLink = linksObj.getJSONObject("players");
                    players = playersLink.getString("href");

                    name = teamObj.getString("name");
                    squadMarketValue = teamObj.getString("squadMarketValue");
                    Team team = new Team(id,fixtures,players, name, squadMarketValue);
                    teamsAdapter.add(team);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}