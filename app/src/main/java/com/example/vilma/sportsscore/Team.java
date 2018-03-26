package com.example.vilma.sportsscore;

/**
 * Created by Vilma on 3/25/2018.
 */

public class Team {

    String teamId, teamFixtures, teamPlayers, teamName, squadMV;

    public Team(String teamId, String teamFixtures, String teamPlayers, String teamName, String squadMV) {
        this.teamId = teamId;
        this.teamFixtures = teamFixtures;
        this.teamPlayers = teamPlayers;
        this.teamName = teamName;
        this.squadMV = squadMV;
    }


    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamFixtures() {
        return teamFixtures;
    }

    public void setTeamFixtures(String teamFixtures) {
        this.teamFixtures = teamFixtures;
    }

    public String getTeamPlayers() {
        return teamPlayers;
    }

    public void setTeamPlayers(String teamPlayers) {
        this.teamPlayers = teamPlayers;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getSquadMV() {
        return squadMV;
    }

    public void setSquadMV(String squadMV) {
        this.squadMV = squadMV;
    }


}
