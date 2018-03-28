package com.example.vilma.sportsscore;

/**
 * Created by Vilma on 3/24/2018.
 */

public class Competition {

    String id, caption, league, year, numTeams, numGames;

    public Competition(String id, String caption, String league, String year, String numTeams, String numGames) {
        this.setId(id);
        this.setCaption(caption);
        this.setLeague(league);
        this.setYear(year);
        this.setNumTeams(numTeams);
        this.setNumGames(numGames);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNumTeams() {
        return numTeams;
    }

    public void setNumTeams(String numTeams) {
        this.numTeams = numTeams;
    }

    public String getNumGames() {
        return numGames;
    }

    public void setNumGames(String numDays) {
        this.numGames = numDays;
    }

}
