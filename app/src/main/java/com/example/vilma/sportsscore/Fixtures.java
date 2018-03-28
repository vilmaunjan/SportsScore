package com.example.vilma.sportsscore;

/**
 * Created by Vilma on 3/26/2018.
 */

public class Fixtures {
    String date, status, matchday, homeTeam, awayTeam, homeGoals, awaysGoals, homeWin, draw, awayWin;

    public Fixtures(String date, String status, String matchday, String homeTeam, String awayTeam, String homeGoals, String awaysGoals, String homeWin, String draw, String awayWin) {
        this.date = date;
        this.status = status;
        this.matchday = matchday;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = homeGoals;
        this.awaysGoals = awaysGoals;
        this.homeWin = homeWin;
        this.draw = draw;
        this.awayWin = awayWin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMatchday() {
        return matchday;
    }

    public void setMatchday(String matchday) {
        this.matchday = matchday;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(String homeGoals) {
        this.homeGoals = homeGoals;
    }

    public String getAwaysGoals() {
        return awaysGoals;
    }

    public void setAwaysGoals(String awaysGoals) {
        this.awaysGoals = awaysGoals;
    }

    public String getHomeWin() {
        return homeWin;
    }

    public void setHomeWin(String homeWin) {
        this.homeWin = homeWin;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public String getAwayWin() {
        return awayWin;
    }

    public void setAwayWin(String awayWin) {
        this.awayWin = awayWin;
    }
}
