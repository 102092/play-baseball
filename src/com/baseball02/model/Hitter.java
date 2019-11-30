package com.baseball02.model;

public class Hitter {
	private final String playerName;
	private final double playerBA;

	public Hitter(String playerName, double playerBA) {
		this.playerName = playerName;
		this.playerBA = playerBA;
	}

	public String getPlayerName() {
		return playerName;
	}

	public double getPlayerBA() {
		return playerBA;
	}

}
