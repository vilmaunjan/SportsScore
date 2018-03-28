package com.example.vilma.sportsscore;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Vilma on 3/24/2018.
 */

public class FetchCompetitions extends AsyncTask<String, Void, String> {

    private static final String LOG_TAG = FetchCompetitions.class.getSimpleName();
    CompetitionAdapter competitionAdapter;
    ListView competitionList;

    public FetchCompetitions( CompetitionAdapter competitionAdapter, ListView list) {
        competitionList = list;
        this.competitionAdapter = competitionAdapter;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getCompetitionsInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONArray competitionsArray = new JSONArray(s);

            for (int i = 0; i < competitionsArray.length(); i++) {
                JSONObject competitionObj = competitionsArray.getJSONObject(i);
                String id = null;
                String caption = null;
                String league = null;
                String year = null;
                String numTeams = null;
                String numGames = null;

                try {
                    JSONObject linksObj = competitionObj.getJSONObject("_links");
                    JSONObject idLink = linksObj.getJSONObject("self");
                    //id = idLink.getString("href");
                    id = competitionObj.getString("id");
                    caption = competitionObj.getString("caption");
                    league = competitionObj.getString("league");
                    year = competitionObj.getString("year");
                    numTeams = competitionObj.getString("numberOfTeams");
                    numGames = competitionObj.getString("numberOfGames");
                    Competition competition = new Competition(id, caption, league, year, numTeams, numGames);
                    //competitionAdapter.clear();
                    competitionAdapter.add(competition);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
