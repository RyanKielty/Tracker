package com.company;

/**
 * Created by ryankielty on 12/20/16.
 */
public class FootballDefenseSeasonStatistics {
    int uniformNumber;
    String lastName;
    String team;
    int totalTackles;
    Double sacks;
    int interceptions;

    public FootballDefenseSeasonStatistics(int uniformNumber, String lastName, String team, int totalTackles, Double sacks, int interceptions) {
        this.uniformNumber = uniformNumber;
        this.lastName = lastName;
        this.team = team;
        this.totalTackles = totalTackles;
        this.sacks = sacks;
        this.interceptions = interceptions;
    }

    public int getUniformNumber() {
        return uniformNumber;
    }

    public void setUniformNumber(int uniformNumber) {
        this.uniformNumber = uniformNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
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
}
