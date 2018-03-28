package com.example.vilma.sportsscore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class FavTeamsActivity extends AppCompatActivity {

    ListView favoriteList;
    Favorites f;
    List<Team> favorites;
    FavTeamAdapter favTeamAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_teams);

        f = new Favorites();
        favorites = f.getFavorites(FavTeamsActivity.this);

        if(favorites == null){
            showAlert("Remove favorite", "Team is being removed from list");
        }else{
           if(favorites.size() == 0){
               showAlert("Remove favorite", "Team is being removed from list");
           }

           favoriteList = (ListView) findViewById(R.id.favTeamList);
           if(favorites != null){
               favTeamAdapter = new FavTeamAdapter(FavTeamsActivity.this,favorites);
               favoriteList.setAdapter(favTeamAdapter);
               for(Team l : favorites){
                   favTeamAdapter.add(l);
               }

               favoriteList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                   @Override
                   public boolean onItemLongClick(
                           AdapterView<?> parent, View view,
                           int position, long id) {

                       ImageView button = (ImageView) view.findViewById(R.id.heart);

                       String tag = button.getTag().toString();
                       if (tag.equalsIgnoreCase("no")) {
                           f.addFavorite(FavTeamsActivity.this,
                                   favorites.get(position));
                           Toast.makeText(FavTeamsActivity.this, "Team was added",
                                           Toast.LENGTH_SHORT).show();

                           button.setTag("yes");
                           button.setImageResource(R.drawable.ic_fav);
                       } else {
                           f.removeFavorite(FavTeamsActivity.this,favorites.get(position));
                           button.setTag("no");
                           button.setImageResource(R.drawable.ic_unfav);
                           favTeamAdapter.remove(favorites.get(position));
                           Toast.makeText(FavTeamsActivity.this,"Team was removed", Toast.LENGTH_SHORT).show();
                       }
                       return true;
                   }
               });
           }
        }
    }

    public void showAlert(String title, String message) {
        if (FavTeamsActivity.this != null && !FavTeamsActivity.this.isFinishing()) {
            AlertDialog alertDialog = new AlertDialog.Builder(FavTeamsActivity.this)
                    .create();
            alertDialog.setTitle(title);
            alertDialog.setMessage(message);
            alertDialog.setCancelable(false);

            // setting OK Button
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            // activity.finish();
                            getFragmentManager().popBackStackImmediate();
                        }
                    });
            alertDialog.show();
        }
    }
}
