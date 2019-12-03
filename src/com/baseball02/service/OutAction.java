package com.baseball02.service;

import com.baseball02.model.Board;
import com.baseball02.model.Score;
import com.baseball02.model.TeamData;

public interface OutAction {
	
	void printSBO(int strike, int ball, int out);

	void selectMessage();

	void closeMessage();

	void errorMessage();

	void printTeamData(TeamData td, int playerNumber);

	void printBoard(Board board, Score score, int count, boolean flag);
}
