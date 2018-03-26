package com.example.vilma.sportsscore;

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

    static String getAPIInfo(String queryString, String url){
        //"http://api.football-data.org/v1/competitions/
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String JSONString = null;

        try {
            //Build up your query URI limiting results to 10 items and printed books
            Uri builtUri = Uri.parse("http://api.football-data.org/v1/competitions/").buildUpon()
                    .appendQueryParameter("season", queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .build();

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
            Log.d(LOG_TAG, "the competitions JSON: "+JSONString);

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

    static String getTeamInfo(String queryString){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String teamJSONString = null;
        String FOOTBALL_BASE_URL = "http://api.football-data.org/v1/competitions/"+queryString+"/teams";

        try {
            //Build up your query URI limiting results to 10 items and printed books
            Uri builtUri = Uri.parse(FOOTBALL_BASE_URL).buildUpon()
                    //.appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .build();

            URL requestURL = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("X-Auth-Token", API_TOKEN);
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

    static String getLeagueTable(String queryString){

        String leagueJSONString = null;
        String URL = "http://api.football-data.org/v1/competitions/"+queryString+"/leagueTable";

        Uri builtUri = Uri.parse(URL).buildUpon()
                .appendQueryParameter("season", queryString)
                .appendQueryParameter(MAX_RESULTS, "10")
                .build();
        leagueJSONString = connectAPI(builtUri);
        return leagueJSONString;
    }

    static String getFixtures(String queryString){
        String fixturesJSONString = null;
        String URL = "http://api.football-data.org/v1/competitions/"+queryString+"/fixtures";
        Uri builtUri = Uri.parse("http://api.football-data.org/v1/competitions/").buildUpon()
                .appendQueryParameter("season", queryString)
                .appendQueryParameter(MAX_RESULTS, "10")
                .build();
        fixturesJSONString = connectAPI(builtUri);
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
            Log.d(LOG_TAG, "the competitions JSON: "+JSONString);

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

    static void connectToAPIwithOKHTTP() {
        //okhttp
        OkHttpClient client;
        Request request;

        client = new OkHttpClient().newBuilder()
                .authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        //updateRestult(response.headers().toString());
                        //String credential = Credentials.basic("user", "passwrd");
                        //if(credential.equals(response.request().header("Authorization")))
                        //    return null;
                        return response.request().newBuilder()
                                .header("X-Auth-Token", "01238bb38ddc413fb98fbfb568d9f561")
                                .build();
                    }
                }).build();

        request = new Request.Builder().url("http://api.football-data.org/v1/competitions/").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(LOG_TAG, e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(LOG_TAG, response.body().string());
            }
        });



    }

}
