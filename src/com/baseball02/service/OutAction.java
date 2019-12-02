package com.baseball02.service;

import com.baseball02.model.TeamData;

public interface OutAction {

	void selectMessage();
	void closeMessage();
	void errorMessage();
	void printTeamData(TeamData td, int playerNumber);
	void printBoard();
}
