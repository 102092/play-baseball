package com.baseball02.service;

import java.io.IOException;

import com.baseball02.model.TeamData;

public interface InputAction {

	TeamData input(int playerNumber) throws IOException;
}
