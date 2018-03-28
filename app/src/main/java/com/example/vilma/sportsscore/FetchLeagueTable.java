package com.example.vilma.sportsscore;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import static java.util.Objects.isNull;

/**
 * Created by Vilma on 3/26/2018.
 */

public class FetchLeagueTable extends AsyncTask<String, Void, String> {
    private static final String LOG_TAG = FetchLeagueTable.class.getSimpleName();
    LeagueTableAdapter leagueTableAdapter;
    ListView leagueTableList;

    public FetchLeagueTable(LeagueTableAdapter leagueTableAdapter, ListView list) {
        leagueTableList = list;
        this.leagueTableAdapter = leagueTableAdapter;
    }

    @Override
    protected String doInBackground(String... strings) {
        Log.i(LOG_TAG,"At do in background league table");
        return NetworkUtils.getLeagueTable(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject jsonObject = new JSONObject(s);
            Log.d(LOG_TAG, "1. League table obj: "+jsonObject);

            if(jsonObject.has("standing")){
                JSONArray standingArray = jsonObject.getJSONArray("standing");
                Log.d(LOG_TAG, "standingarray: "+standingArray);

                for (int i = 0; i < standingArray.length(); i++) {
                    JSONObject teamObj = standingArray.getJSONObject(i);
                    String position = null;
                    String teamName = null;
                    String playedGames = null;
                    String points = null;
                    String goals = null;
                    String wins = null;
                    String draws = null;
                    String losses = null;

                    try {
                        position = teamObj.getString("position");
                        Log.d(LOG_TAG, "position: "+position);
                        teamName = teamObj.getString("teamName");
                        Log.d(LOG_TAG, "teamName: "+teamName);
                        playedGames = teamObj.getString("playedGames");
                        goals = teamObj.getString("goals");
                        points = teamObj.getString("points");
                        wins = teamObj.getString("wins");
                        draws = teamObj.getString("draws");
                        losses = teamObj.getString("losses");

                        LeagueTable t = new LeagueTable(position, teamName, playedGames, goals, points, wins, draws, losses);
                        leagueTableAdapter.add(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
