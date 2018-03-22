package com.example.vilma.sportsscore;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Vilma on 3/21/2018.
 */

public class FetchTeam extends AsyncTask<String, Void, String> {

    private TextView mNameText;
    private TextView mNicknameText;
    private TextView mMVText;
    private ImageView mLogo;
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    public FetchTeam(TextView nameText, TextView nicknameText, TextView MVText, ImageView logo){
        this.mNameText = nameText;
        this.mNicknameText = nicknameText;
        this.mMVText = MVText;
        this.mLogo = logo;
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
                JSONObject team = teamsArray.getJSONObject(i);
                String name = null;
                String shortname = null;
                String squadMarketValue = null;
                String logo = null;


                try {
                    name = team.getString("name");
                    shortname = team.getString("shortName");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //If items exist,  update textviews and returns
                if (name != null) {
                    mNameText.setText(name);
                    mNicknameText.setText(shortname);
                    mMVText.setText(squadMarketValue);
                    return;
                }
            }

            mNameText.setText("Nope results found");
        }catch (Exception e){
            mNameText.setText("No results found");
            mNicknameText.setText("");
            e.printStackTrace();
        }
    }
}
