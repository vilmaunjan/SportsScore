package com.example.vilma.sportsscore;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Vilma on 3/21/2018.
 */

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    //Base URI for the football scores API
    private static final String FOOTBALL_BASE_URL = "http://api.football-data.org/v1/competitions/398/teams";
    private static final String QUERY_PARAM = "q"; // Parameter for the search string
    private static final String MAX_RESULTS = "maxResults"; //Parameter that limits search results
    private static final String PRINT_TYPE = "printType"; //Parameter to filter by print type

    static String getTeamInfo(String queryString){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String teamJSONString = null;

        try {
            //Build up your query URI limiting results to 10 items and printed books
            Uri builtUri = Uri.parse(FOOTBALL_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "teams")
                    .build();

            URL requestURL = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            Log.d(LOG_TAG, "im connected ");

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
                Log.d(LOG_TAG, "Buffer empty ");
                //Stream was empty. No point in parsing.
                return null;
            }
            teamJSONString = buffer.toString();
            Log.d(LOG_TAG, ""+teamJSONString);

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
            Log.d(LOG_TAG, "getTeamInfo: ");
            return teamJSONString;
        }
    }
}
