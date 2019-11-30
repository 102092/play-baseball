package com.baseball02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import com.baseball02.Util;

public class Game {

	static TeamData t1 = new TeamData();
	static TeamData t2 = new TeamData();
	final static int playerNumber = 2;

	public static void selectMessage() {
		System.out.println("신나는 야구시합");
		System.out.println("1. 데이터 입력");
		System.out.println("2. 데이터 출력");
		System.out.println("3. 시합시작");
		System.out.println("0. 종료");

	}

	private TeamData inputData(String teamName) throws IOException {
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

	private void printTeamData(TeamData td) {
		System.out.println(td.getTeamName() + " 팀 정보");
		String[] playerNames = td.getPlayerName();
		double[] playerBAs = td.getPlayerBA();
		for (int i = 0; i < playerNumber; i++) {
			System.out.println(i + 1 + "번 " + playerNames[i] + ", " + playerBAs[i]);
		}

	}

	public static void main(String[] args) throws IOException {

		Game g = new Game();

		while (true) {

			selectMessage();
			String input = Util.getInput();
			if (input.equals("0")) {
				System.out.println("==================");
				System.out.println("프로그램을 종료합니다");
				System.out.println("==================");
				break;
			}

			switch (input) {
			case "1":
				System.out.println("메뉴선택 " + input + "번");
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
				System.out.println("메뉴선택 " + input + "번");
				g.printTeamData(t1);
				System.out.println();
				g.printTeamData(t2);
				System.out.println();
				break;

			case "3":
				System.out.println("메뉴선택 " + input + "번");
				System.out.println(t1.getTeamName() + " VS " + t2.getTeamName() + "의 경기를 시작합니다.");
				g.start(t1, t2);

				break;

			default:
				System.out.println("잘못된 메뉴 선택입니다");
				System.out.println();
				break;
			}

		}

	}

	private void start(TeamData t1, TeamData t2) {

		String AteamName = t1.getTeamName();
		String BteamName = t2.getTeamName();

		String[] AplayerNames = t1.getPlayerName();
		String[] BplayerNames = t2.getPlayerName();

		double[] AplayerBAs = t1.getPlayerBA();
		double[] BplayerBAs = t2.getPlayerBA();

		int AteamPoints = 0;
		int BteamPoints = 0;

		boolean flag = false;
		int count = 1;

		// 6회 게임 반복 총 12번 게임하
		while (true) {

			if (flag == false) {
				System.out.println(count + "초 " + AteamName + " 공격 ");
				AteamPoints += play(AteamName, AplayerNames, AplayerBAs);
				flag = true;
			}

			if (flag == true) {
				System.out.println(count + "말 " + BteamName + " 공격 ");
				BteamPoints += play(BteamName, BplayerNames, BplayerBAs);
				flag = false;
				count++;
			}

			if (count == 2)
				break;

		}
		endGame(AteamName, BteamName, AteamPoints, BteamPoints);

	}

	private int play(String teamName, String[] playerNames, double[] playerBAs) {

		Score score = new Score(0, 0, 0, 0, 0);
		int points = doBatting(playerNames, playerBAs, score);
		return points;

	}

	private int doBatting(String[] playerNames, double[] playerBAs, Score score) {

		int strike = score.getStrike();
		int ball = score.getBall();
		int out = score.getOut();
		int hit = score.getHit();
		int point = score.getPoint();

		int nextPlayer = 0;

		while (out != 3) {

			
			if (nextPlayer == playerNumber) {
				nextPlayer = 0;
			}

			String playerName = playerNames[nextPlayer];
			double playerBA = playerBAs[nextPlayer];

			System.out.println(nextPlayer + 1 + "번 " + playerName);

			if (strike == 3) {
				out++;

				if (out == 3) {
					System.out.println("아웃!");
					strike = 0;
					ball = 0;
					System.out.println(strike + "S " + ball + "B " + out + "O");
					System.out.println();
					break;
				} else {
					System.out.println("3 스트라이크!");
					System.out.println("다음 타자가 타석에 입장했습니다");
					System.out.println();
					nextPlayer++;
					strike = 0;
					ball = 0;
				}
			}

			if (ball == 4) {
				hit++;
				System.out.println("4 볼!");
				System.out.println("다음 타자가 타석에 입장했습니다");
				System.out.println();
				nextPlayer++;
				strike = 0;
				ball = 0;
			}

			if (hit == 4) {
				point++;
				System.out.println("득점하였습니다.");
				System.out.println("현재 점수: " + point);
				System.out.println();
				hit = 3;
			}

			int random = rollDice(playerBA);

			switch (random) {

			case 1:
				// strike
				strike++;
				System.out.println("스트라이크!");
				System.out.println(strike + "S " + ball + "B " + out + "O");
				System.out.println();
				break;

			case 2:
				// ball
				ball++;
				System.out.println("볼!");
				System.out.println(strike + "S " + ball + "B " + out + "O");
				System.out.println();
				break;

			case 3:
				// out
				if (out < 2) {
					out++;
					System.out.println("아웃! 다음 타자가 타석에 입장했습니다");
					strike = 0;
					ball = 0;
					nextPlayer++;
					System.out.println(strike + "S " + ball + "B " + out + "O");
					System.out.println();
					break;

				} else {
					out++;
					System.out.println("아웃!");
					strike = 0;
					ball = 0;
					System.out.println(strike + "S " + ball + "B " + out + "O");
					System.out.println();
					break;

				}

			case 4:
				// hit
				hit++;
				System.out.println("안타! 다음 타자가 타석에 입장했습니다");
				strike = 0;
				ball = 0;
				nextPlayer++;
				System.out.println(strike + "S " + ball + "B " + out + "O");
				System.out.println();
				break;

			}

		}
		return point;
	}

	private int rollDice(double ba) {
		Random r = new Random();

		double hitProbability = ba;
		double sbProbability = (1 - ba) / 3 - 0.05;
		double outProbability = 0.100;

		int result = 0;

		while (true) {

			if (hitProbability >= Double.parseDouble(String.format("%.3f", r.nextDouble()))) {
				result = 4;
				break;
			}
			if (sbProbability >= Double.parseDouble(String.format("%.3f", r.nextDouble()))) {
				result = 1;
				break;
			}
			if (sbProbability >= Double.parseDouble(String.format("%.3f", r.nextDouble()))) {
				result = 2;
				break;

			}
			if (outProbability >= Double.parseDouble(String.format("%.3f", r.nextDouble()))) {
				result = 3;
				break;

			}

		}

		return result;
	}

	private void endGame(String ateamName, String bteamName, int ateamPoints, int bteamPoints) {

		System.out.println("경기 종료");
		System.out.println(ateamName + " VS " + bteamName);

		if (ateamPoints == bteamPoints) {
			System.out.println(ateamPoints + " : " + bteamPoints);
			System.out.println("무승부! ");
			System.out.println();
		} else {
			System.out.println(ateamPoints + " : " + bteamPoints);
			System.out.println();
		}

	}

}