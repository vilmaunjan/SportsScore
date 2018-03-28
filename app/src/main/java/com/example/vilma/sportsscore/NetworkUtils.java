package com.example.vilma.sportsscore;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Vilma on 3/21/2018.
 */

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    //Base URI for the football scores API
    private static final String FOOTBALL_BASE_URL = "http://api.football-data.org/v1/competitions/398/teams";
    private static final String QUERY_PARAM = "q"; // Parameter for the search string
    private static final String MAX_RESULTS = "maxResults"; //Parameter that limits search results
    private static final String API_TOKEN = "01238bb38ddc413fb98fbfb568d9f561";

    static String getCompetitionsInfo(String queryString){

        String competitionJSONString = null;
        String URL = "http://api.football-data.org/v1/competitions/";

        Uri builtUri = Uri.parse(URL).buildUpon()
                .appendQueryParameter("season", queryString)
                .build();
        competitionJSONString = connectAPI(builtUri);
        return competitionJSONString;
    }

    static String getTeamInfo(String queryString){

        String teamJSONString = null;
        String URL = "http://api.football-data.org/v1/competitions/"+queryString+"/teams";

        Uri builtUri = Uri.parse(URL).buildUpon()
                .appendQueryParameter("season", queryString)
                //.appendQueryParameter(MAX_RESULTS, "10")
                .build();
        teamJSONString = connectAPI(builtUri);
        return teamJSONString;
    }

    static String getLeagueTable(String queryString){

        String leagueJSONString = null;
        String URL = "http://api.football-data.org/v1/competitions/"+queryString+"/leagueTable";

        Uri builtUri = Uri.parse(URL).buildUpon()
                .appendQueryParameter("season", queryString)
                //.appendQueryParameter(MAX_RESULTS, "10")
                .build();
        leagueJSONString = connectAPI(builtUri);
        Log.d(LOG_TAG, "LEAGUE TABLE json: "+leagueJSONString);
        return leagueJSONString;
    }

    static String getFixtures(String queryString){
        String fixturesJSONString = null;
        String URL = "http://api.football-data.org/v1/competitions/"+queryString+"/fixtures";
        Uri builtUri = Uri.parse(URL).buildUpon().build();
        fixturesJSONString = connectAPI(builtUri);
        return fixturesJSONString;
    }

    static String getTeamFixtures(Context context){
        String fixturesJSONString = null;

        String teamId;
        SharedPreferences prefs = context.getSharedPreferences("MyPref1",MODE_PRIVATE);
        teamId = prefs.getString("teamURL",null);
        String URL = teamId;
        Uri builtUri = Uri.parse(URL+"/fixtures").buildUpon().build();
        fixturesJSONString = connectAPI(builtUri);
        Log.d(LOG_TAG, "HEREEEEEEEEEEEEEEEEEEEEEEEE getting the team fixtures JSON!!!!!: "+fixturesJSONString);
        return fixturesJSONString;
    }


    static String connectAPI(Uri builtUri){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String JSONString = null;

        try {
            URL requestURL = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("X-Auth-Token", API_TOKEN);
            urlConnection.connect();

            //Read the response using an inputStream and a StringBuffer, then convert it to a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if(inputStream == null){
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = reader.readLine()) != null){
                buffer.append(line +"\n");
            }
            if(buffer.length() == 0){
                Log.d(LOG_TAG, "Buffer empty "); //Stream was empty. No point in parsing.
                return null;
            }
            JSONString = buffer.toString();
            //Log.d(LOG_TAG, "the competitions JSON: "+JSONString);

        } catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(reader != null){
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            Log.d(LOG_TAG, "getApiInfo: "+ JSONString);
            return JSONString;
        }
    }
}
