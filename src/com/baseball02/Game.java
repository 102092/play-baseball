package com.baseball02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.baseball02.Util;

public class Game {

	static TeamData t1 = new TeamData();
	static TeamData t2 = new TeamData();
	final static int playerNumber = 9;

	public static void selectMessage() {
		System.out.println("신나는 야구시합");
		System.out.println("1. 데이터 입력");
		System.out.println("2. 데이터 출력");
		System.out.println("3. 종료");

	}

	public TeamData inputData(String teamName) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] playerNames = new String[playerNumber];
		double[] playerBAs = new double[playerNumber];

		for (int i = 1; i <= playerNumber; i++) {
			System.out.print(i + "번 타자의 정보를입력하세요> ");
			String data = bf.readLine();
			String[] temp = data.split(",");
			String playerName = temp[0];
			double playerBA = Double.parseDouble(temp[1].trim());

			playerNames[i - 1] = playerName;
			playerBAs[i - 1] = playerBA;
		}
		TeamData td = new TeamData(teamName, playerNames, playerBAs);

		return td;
	}

	public void printTeamData(TeamData td) {
		System.out.println(td.getTeamName() + " 팀 정보");
		String[] playerNames = td.getPlayerName();
		double[] playerBAs = td.getPlayerBA();
		for (int i = 0; i < playerNumber; i++) {
			System.out.println(playerNames[i] + ", " + playerBAs[i]);
		}

	}

	public static void main(String[] args) throws IOException {

		Game g = new Game();

		while (true) {

			selectMessage();
			String input = Util.getInput();
			if (input.equals("3")) {
				System.out.println("==================");
				System.out.println("프로그램을 종료합니다");
				System.out.println("==================");
				break;
			}

			switch (input) {
			case "1":
				System.out.println("메뉴선택 (1 - 2) " + input);
				System.out.print("1번팀의 정보를입력하세요> ");
				String fTeam = Util.getInput();
				t1 = g.inputData(fTeam);

				System.out.println();

				System.out.print("2번팀의 정보를입력하세요> ");
				String sTeam = Util.getInput();
				t2 = g.inputData(sTeam);

				System.out.println("팀 데이터 입력이 완료되었습니다.");
				System.out.println();
				break;

			case "2":
				System.out.println("메뉴선택 (1 - 2) " + input);
				g.printTeamData(t1);
				System.out.println();
				g.printTeamData(t2);
				System.out.println();
				break;

			default:
				System.out.println("잘못된 메뉴 선택입니다");
				System.out.println();
				break;
			}

		}

	}
}