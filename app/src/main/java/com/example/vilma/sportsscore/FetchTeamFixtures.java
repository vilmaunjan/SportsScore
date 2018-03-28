package com.example.vilma.sportsscore;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Vilma on 3/27/2018.
 */

public class FetchTeamFixtures extends AsyncTask<String, Void, String> {
    private static final String LOG_TAG = FetchFixtures.class.getSimpleName();
    FixturesAdapter fixturesAdapter;
    ListView fixturesList;
    Context context;

    public FetchTeamFixtures(FixturesAdapter fixturesAdapter, ListView fixturesList, Context context) {
        this.fixturesAdapter = fixturesAdapter;
        this.fixturesList = fixturesList;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getTeamFixtures(context);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject jsonObject = new JSONObject(s);

            if(jsonObject.has("fixtures")){
            JSONArray fixturesArray = jsonObject.getJSONArray("fixtures");

                for (int i = fixturesArray.length()-1; i >= 0; i--) {
                    JSONObject fixturesObj = fixturesArray.getJSONObject(i);
                    String date = "TBA";
                    String status = "TBA";
                    String matchday = "TBA";
                    String homeTeamName = "TBA";
                    String awayTeamName = "TBA";

                    JSONObject resultObj = fixturesObj.getJSONObject("result");
                    String goalsHomeTeam = "TBA";
                    String goalsAwayTeam = "TBA";

                    String homeWin = "-";
                    String draw = "-";
                    String awayWin = "-";

                    try {
                        date = fixturesObj.getString("date");
                        status = fixturesObj.getString("status");
                        matchday = fixturesObj.getString("matchday");
                        homeTeamName = fixturesObj.getString("homeTeamName");
                        awayTeamName = fixturesObj.getString("awayTeamName");
                        goalsHomeTeam = resultObj.getString("goalsHomeTeam");
                        goalsAwayTeam = resultObj.getString("goalsAwayTeam");
                        if(goalsHomeTeam.equals("null")){
                            goalsHomeTeam = "TBA";
                        }
                        if(goalsAwayTeam.equals("null")){
                            goalsAwayTeam = "TBA";
                        }

                        if(fixturesObj.has("odds") && !fixturesObj.isNull("odds")) {
                            JSONObject oddsObj = fixturesObj.getJSONObject("odds");
                            homeWin = oddsObj.getString("homeWin");
                            draw = oddsObj.getString("draw");
                            awayWin = oddsObj.getString("awayWin");
                        }

                        Fixtures fixtures = new Fixtures(date, status, matchday, homeTeamName,
                            awayTeamName, goalsHomeTeam, goalsAwayTeam, homeWin, draw, awayWin);
                        fixturesAdapter.add(fixtures);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
