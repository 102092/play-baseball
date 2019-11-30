package com.baseball02.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.baseball02.model.Hitter;
import com.baseball02.model.TeamData;

public class Input implements InputAction {

	@Override
	public TeamData input(int playerNumber) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] playerNames = new String[playerNumber];
		double[] playerBAs = new double[playerNumber];
		String input;

		// Input TeamName
		String teamName = bf.readLine();

		// TeamName Null Check
		if (teamName.isEmpty() == false) {

			// Input Player Information
			for (int i = 1; i <= playerNumber; i++) {
				System.out.print(i + "번 타자의 정보를입력하세요> ");

				try {
					input = bf.readLine();
					String[] temp = input.split(",");
					String playerName = temp[0];
					double playerBA = Double.parseDouble(temp[1].trim());
					int leng = String.valueOf(playerBA).length();

					// Player Data Check
					if (playerName.isEmpty() == false && leng >= 1 && leng <= 5) {
						Hitter player = new Hitter(playerName, playerBA);
						playerNames[i - 1] = player.getPlayerName();
						playerBAs[i - 1] = player.getPlayerBA();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		TeamData td = new TeamData(teamName, playerNames, playerBAs);
		return td;

	}

}
