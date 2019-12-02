package com.baseball02.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.baseball02.model.Hitter;
import com.baseball02.model.TeamData;
import com.baseball02.util.DataInputUtil;

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

	@Override
	public void selectMenu(TeamData td1, TeamData td2, Input input, Out printData, Play play, int playerNumber)
			throws IOException {
		TeamData t1 = td1;
		TeamData t2 = td2;

		Input in = input;
		Out out = printData;
		Play py = play;

		int total = playerNumber;

		while (true) {

			out.selectMessage();

			String userInput = DataInputUtil.getInput();

			// 메뉴 종료조건
			if (userInput.equals("0")) {
				out.closeMessage();
				break;
			}

			switch (userInput) {

			case "1":
				System.out.println("메뉴선택 " + userInput + "번");
				System.out.print("1번팀의 정보를입력하세요> ");
				t1 = in.input(total);

				System.out.print("2번팀의 정보를입력하세요> ");
				t2 = in.input(total);

				if (t1 != null && t2 != null) {
					System.out.println("팀 데이터 입력이 완료되었습니다.");
					System.out.println();
					break;

				}

			case "2":
				System.out.println("메뉴선택 " + userInput + "번");
				out.printTeamData(t1, total);
				out.printTeamData(t2, total);
				break;

			case "3":
				System.out.println("메뉴선택 " + userInput + "번");
				System.out.println(t1.getTeamName() + " VS " + t2.getTeamName() + "의 경기를 시작합니다.");
				System.out.println();
				py.setData(t1, t2, total);
				break;

			default:
				out.errorMessage();
				break;
			}

		}
	}

}
