package com.company;

/**
 * Created by ryankielty on 12/20/16.
 */
public class FootballDefenseGameStatistics {
    String firstName;
    String lastName;
    String team;
    String teamPlayed;
    int totalTackles;
    Double sacks;
    int interceptions;
    int forcedFumbles;

    public FootballDefenseGameStatistics(String firstName, String lastName, String team, String teamPlayed, int totalTackles, Double sacks, int interceptions, int forcedFumbles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
        this.teamPlayed = teamPlayed;
        this.totalTackles = totalTackles;
        this.sacks = sacks;
        this.interceptions = interceptions;
        this.forcedFumbles = forcedFumbles;
    }

    public FootballDefenseGameStatistics(String playerLastName) {
        this.lastName = playerLastName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeamVersus() {
        return teamPlayed;
    }

    public void setTeamVersus(String teamVersus) {
        this.teamPlayed = teamVersus;
    }

    public int getTotalTackles() {
        return totalTackles;
    }

    public void setTotalTackles(int totalTackles) {
        this.totalTackles = totalTackles;
    }

    public Double getSacks() {
        return sacks;
    }

    public void setSacks(Double sacks) {
        this.sacks = sacks;
    }

    public int getInterceptions() {
        return interceptions;
    }

    public void setInterceptions(int interceptions) {
        this.interceptions = interceptions;
    }

    public int getForcedFumbles() {
        return forcedFumbles;
    }

    public void setForcedFumbles(int forcedFumbles) {
        this.forcedFumbles = forcedFumbles;
    }

}
