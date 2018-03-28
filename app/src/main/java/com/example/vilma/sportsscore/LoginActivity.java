package com.example.vilma.sportsscore;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    //For list view
    private ListView listCompetition;
    private CompetitionAdapter competitionAdapter;

    //For spinner
    private String queryString;
    private Spinner seasonSpinner;
    ArrayAdapter<CharSequence> adapterSeason;

    String competitionId="400";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
    }

    private void initUI() {
        listCompetition = (ListView) findViewById(R.id.competitionsList);
        competitionAdapter = new CompetitionAdapter(this, R.layout.competition_custom_layout);
        listCompetition.setAdapter(competitionAdapter);
        loadCompetitions();
        listCompetition.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Competition data =  (Competition) parent.getItemAtPosition(position);
                competitionId = data.getId();

                SharedPreferences prefs = LoginActivity.this.getSharedPreferences("MyPref1",MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("competitionID", competitionId);
                editor.commit();

                Intent intentBundle = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intentBundle);
            }
        });


        //Used for season spinner
        adapterSeason = ArrayAdapter.createFromResource(this, R.array.season_array, android.R.layout.simple_spinner_item);
        adapterSeason.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Get season
        seasonSpinner = (Spinner) findViewById(R.id.seasonSpinner);
        seasonSpinner.setAdapter(adapterSeason);
        seasonSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                queryString = parent.getSelectedItem().toString();
                competitionAdapter.clear();
                competitionAdapter.notifyDataSetChanged();
                loadCompetitions();
                Toast.makeText(getBaseContext(),"Loading "+queryString+" competitions...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

    }

    public void loadCompetitions() {
        //queryString = competitionSpinner.getText().toString();

        //For checking the network state and empty search field case
        ConnectivityManager connMgr = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        Log.i(LOG_TAG,"1. Made it to loadCompetitions");

        if (networkInfo != null && networkInfo.isConnected()) {
            Log.i(LOG_TAG,"2. Made it to the if statement in load competitions");
            new FetchCompetitions(competitionAdapter, listCompetition).execute(queryString);
        } else {
            Log.i(LOG_TAG,"2. DID NOT ENTER THE IFFF!!!");
        }
    }

}
