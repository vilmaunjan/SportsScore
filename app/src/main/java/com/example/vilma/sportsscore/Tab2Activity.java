package com.example.vilma.sportsscore;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Vilma on 3/18/2018.
 */

public class Tab2Activity extends Fragment {

    private EditText txtSearchInput;
    private TextView lblTeamName, lblNickname, lblMV;
    private Button btnSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2, container, false);

        /*txtSearchInput = (EditText) rootView.findViewById(R.id.searchTeam);
        lblTeamName = (TextView) rootView.findViewById(R.id.txtTeamName);
        lblNickname = (TextView) rootView.findViewById(R.id.txtNickname);
        lblMV = (TextView) rootView.findViewById(R.id.txtMV);
        btnSearch = (Button) rootView.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchTeams(v);
            }
        });
        */

        return rootView;
    }


    public void searchTeams(View view) {
        String queryString = txtSearchInput.getText().toString();

        //For hiding the keyboard when the search buttton is clicked
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        //For checking the network state and empty search field case
        ConnectivityManager connMgr = (ConnectivityManager) getActivity()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() && queryString.length() != 0) {
            ///new FetchTeam(lblTeamName, lblNickname, lblMV).execute(queryString);
            lblNickname.setText("");
            lblNickname.setText("");
            lblTeamName.setText("Loading...");

        } else {
            if (queryString.length() == 0) {
                lblNickname.setText("");
                lblTeamName.setText("Please enter a search term");
                lblMV.setText("");
            } else {
                lblNickname.setText("");
                lblTeamName.setText("Please enter your network your network connection and try again");
                lblMV.setText("");
            }
        }
    }

}
