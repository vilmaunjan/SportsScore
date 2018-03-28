package com.example.vilma.sportsscore;

/**
 * Created by Vilma on 3/25/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class TeamAdapter  extends ArrayAdapter {

    Context context;
    List list = new ArrayList();
    Favorites f;

    public TeamAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public void add(Team object) {
        super.add(object);
        list.add(object);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        super.clear();
        list.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        TeamHolder teamHolder;

        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.teams_custom_layout, parent, false);
            teamHolder = new TeamHolder();
            teamHolder.lblTeamname = (TextView) row.findViewById(R.id.lblName);
            row.setTag(teamHolder);
            teamHolder.favImage = (ImageView) row.findViewById(R.id.fav);
        }else{
            teamHolder = (TeamHolder) row.getTag();
        }
        final Team team = (Team) this.getItem(position);
        teamHolder.lblTeamname.setText(team.getTeamName());
        //final String teamURL = team.getTeamId();

        //if(checkFavItem(team)){
            teamHolder.favImage.setImageResource(R.drawable.ic_fav);
            teamHolder.favImage.setTag("yes");
      /*  }else{
          teamHolder.favImage.setImageResource(R.drawable.ic_unfav);
            teamHolder.favImage.setTag("no");
        }*/

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teamURL;
                teamURL = team.getTeamId();
                SharedPreferences prefs = context.getSharedPreferences("MyPref1",MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("teamURL", teamURL);
                editor.commit();
                Intent intentBundle = new Intent(context,OneTeamFixturesActivity.class);
                context.startActivity(intentBundle);
            }
        });

        row.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
              /*  ImageView favBtn = (ImageView) v.findViewById(R.id.fav);
                String tag = favBtn.getTag().toString();
                if (tag.equalsIgnoreCase("no")) {
                    f.addFavorite(context, team);
                    Toast.makeText(context, "Added team to favorites",
                            Toast.LENGTH_SHORT).show();

                    favBtn.setTag("yes");
                    favBtn.setImageResource(R.drawable.ic_fav);
                } else {
                    f.removeFavorite(context, team);
                    favBtn.setTag("no");
                    favBtn.setImageResource(R.drawable.ic_unfav);
                    Toast.makeText(context,
                            "Team removed from favorites",
                            Toast.LENGTH_SHORT).show();
                }*/
                return false;
            }
        });
        return row;
    }

    //to check whether list item exists in shared preferences
    public boolean checkFavItem(Team team) {
        boolean check = false;
        List<Team> favorites = f.getFavorites(context);
        if (favorites != null) {
            for (Team teams : favorites) {
                if (teams.equals(team)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }


    static class TeamHolder{
        TextView lblTeamname;
        ImageView favImage;
    }
}