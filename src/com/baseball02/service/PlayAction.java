package com.baseball02.service;

import com.baseball02.model.Board;
import com.baseball02.model.TeamData;

public interface PlayAction {

	void setData(TeamData t1, TeamData t2, int playerNumber);

	Board play(Board board, int playerNumber, boolean flag, int count);

	Board doBatting(String[] playerNames, double[] playerBAs, int playerNumber, boolean flag, int count, Board board);

	int rollDice(double ba);

	Board earlyStop(int count, boolean flag, Board board);

}
