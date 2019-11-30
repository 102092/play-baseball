package com.baseball02.model;

public class TeamData {
    private String teamName;
    private String[] playerName;
    private double[] playerBA;
    
    public TeamData() {

    }

    public TeamData(String teamName, String[] playerName, double[] playerBA) {
        this.teamName = teamName;
        this.playerName = playerName;
        this.playerBA = playerBA;
    }

    public String getTeamName() {
        return teamName;
    }

    public String[] getPlayerName() {
        return playerName;
    }

    public double[] getPlayerBA() {
        return playerBA;
    }

}