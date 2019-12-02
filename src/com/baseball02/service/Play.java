package com.baseball02.service;

import java.util.Random;

import com.baseball02.model.Score;
import com.baseball02.model.TeamData;

public class Play implements PlayAction {

	@Override
	public void setData(TeamData t1, TeamData t2, int playerNumber) {

		String AteamName = t1.getTeamName();
		String BteamName = t2.getTeamName();

		String[] AplayerNames = t1.getPlayerName();
		String[] BplayerNames = t2.getPlayerName();

		double[] AplayerBAs = t1.getPlayerBA();
		double[] BplayerBAs = t2.getPlayerBA();

		int AteamPoints = 0;
		int BteamPoints = 0;

		boolean flag = false;

		// 회차
		int count = 1;

		while (true) {

			if (flag == false) {
				System.out.println(count + "초 " + AteamName + " 공격 ");
				System.out.println();
				AteamPoints += play(AteamName, AplayerNames, AplayerBAs, playerNumber);
				flag = true;
			}

			if (flag == true) {
				System.out.println(count + "말 " + BteamName + " 공격 ");
				System.out.println();
				BteamPoints += play(BteamName, BplayerNames, BplayerBAs, playerNumber);
				flag = false;
				count++;
			}

			if (count == 2)
				break;

		}
		endGame(AteamName, BteamName, AteamPoints, BteamPoints);

	}

	@Override
	public int play(String teamName, String[] playerNames, double[] playerBAs, int playerNumber) {

		Score score = new Score(0, 0, 0, 0, 0);
		int points = doBatting(playerNames, playerBAs, score, playerNumber);
		return points;

	}

	@Override
	public int doBatting(String[] playerNames, double[] playerBAs, Score score, int playerNumber) {
		int strike = score.getStrike();
		int ball = score.getBall();
		int out = score.getOut();
		int hit = score.getHit();
		int point = score.getPoint();

		int nextPlayer = 0;

		while (out != 3) {

			if (nextPlayer >= playerNumber) {
				nextPlayer = 0;
			}

			String playerName = playerNames[nextPlayer];
			double playerBA = playerBAs[nextPlayer];

			System.out.println(nextPlayer + 1 + "번 " + playerName);

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

			if (strike == 3) {
				out++;

				if (out == 3) {
					System.out.println("3 스트라이크 아웃!");
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

		}

		return point;
	}

	@Override
	public int rollDice(double ba) {

		Random r = new Random();

		double hitProbability = ba; // 0.499
		double sbProbability = (1 - ba) / 2 - 0.05; // 0.200
		double outProbability = 0.100;
		boolean isGet = false;
		int result = 0;

		while (isGet == false) {

			int temp = r.nextInt(4) + 1;

			switch (temp) {

			case 1:
				if (sbProbability >= Double.parseDouble(String.format("%.3f", r.nextDouble()))) {
					result = 1;
					isGet = true;
					break;
				}
				break;

			case 2:
				if (sbProbability >= Double.parseDouble(String.format("%.3f", r.nextDouble()))) {
					result = 2;
					isGet = true;
					break;
				}
				break;

			case 3:
				if (outProbability >= Double.parseDouble(String.format("%.3f", r.nextDouble()))) {
					result = 3;
					isGet = true;
					break;
				}
				break;

			case 4:
				if (hitProbability >= Double.parseDouble(String.format("%.3f", r.nextDouble()))) {
					result = 4;
					isGet = true;
					break;
				}
				break;

			}
		}
		return result;
	}

	@Override
	public void endGame(String ateamName, String bteamName, int ateamPoints, int bteamPoints) {

		System.out.println("경기 종료");
		System.out.println();
		System.out.println(ateamName + " VS " + bteamName);

		if (ateamPoints == bteamPoints) {
			System.out.println(ateamPoints + " : " + bteamPoints);
			System.out.println("무승부! ");
		} else {
			System.out.println(ateamPoints + " : " + bteamPoints);
		}
		System.out.println("Thank You!");
		System.out.println();
	}

}
