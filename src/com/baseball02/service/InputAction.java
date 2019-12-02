package com.baseball02.service;

import java.io.IOException;

import com.baseball02.model.TeamData;

public interface InputAction {

	TeamData input(int playerNumber) throws IOException;
	void selectMenu(TeamData t1, TeamData t2, Input in, Out out, Play py, int playerNumber) throws IOException;
}
