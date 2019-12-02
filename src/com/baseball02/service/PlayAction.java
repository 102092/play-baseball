package com.baseball02.service;

import com.baseball02.model.Score;
import com.baseball02.model.TeamData;

public interface PlayAction {
	
	void setData(TeamData t1, TeamData t2, int playerNumber);
	int play(String teamName, String[] playerNames, double[] playerBAs, int playerNumber);
	int doBatting(String[] playerNames, double[] playerBAs, Score score, int playerNumber);
	int rollDice(double ba);
	void endGame(String ateamName, String bteamName, int ateamPoints, int bteamPoints);
}
