package com.baseball02.service;

import java.util.Random;

import com.baseball02.model.Board;
import com.baseball02.model.BoardCount;
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

//		int AteamPoints = 0;
//		int BteamPoints = 0;

		BoardCount t1Counts = new BoardCount(0, 0, 0, 0);
		BoardCount t2Counts = new BoardCount(0, 0, 0, 0);

		Board board = new Board(AteamName, BteamName, AplayerNames, BplayerNames, null, t1Counts, t2Counts);

		boolean flag = false;

		// 회차
		int count = 1;

		while (true) {

			if (flag == false) {
				System.out.println(count + "초 " + AteamName + " 공격 ");
				System.out.println();
				board.setT1Counts(play(AteamName, AplayerNames, AplayerBAs, playerNumber, board.getT1Counts()));
				flag = true;

				System.out.println("t1점수: " + board.getT1Counts().getPoints());
				System.out.println("t1투구: " + board.getT1Counts().getPitchings());
				System.out.println("t1삼진: " + board.getT1Counts().getThreeOuts());
				System.out.println("t1안타: " + board.getT1Counts().getHits());
			}

			if (flag == true) {
				System.out.println(count + "말 " + BteamName + " 공격 ");
				System.out.println();
				board.setT2Counts(play(BteamName, BplayerNames, BplayerBAs, playerNumber, board.getT2Counts()));
				flag = false;
				count++;

				System.out.println("t2점수: " + board.getT2Counts().getPoints());
				System.out.println("t2투구: " + board.getT2Counts().getPitchings());
				System.out.println("t2삼진: " + board.getT2Counts().getThreeOuts());
				System.out.println("t2안타: " + board.getT2Counts().getHits());

			}

			if (count == 3)
				break;

		}
//		endGame(AteamName, BteamName, AteamPoints, BteamPoints);

	}

	@Override
	public BoardCount play(String teamName, String[] playerNames, double[] playerBAs, int playerNumber,
			BoardCount boardCount) {

		Score score = new Score(0, 0, 0, 0, 0);
		BoardCount bc = doBatting(playerNames, playerBAs, score, playerNumber, boardCount);
		return bc;

	}

	@Override
	public BoardCount doBatting(String[] playerNames, double[] playerBAs, Score score, int playerNumber,
			BoardCount boardCount) {
		int strike = score.getStrike();
		int ball = score.getBall();
		int out = score.getOut();
		int hit = score.getHit();

		int point = boardCount.getPoints();
		int pitchings = boardCount.getPitchings();
		int threeOuts = boardCount.getThreeOuts();
		int hits = boardCount.getHits();

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
				pitchings++;
				System.out.println("스트라이크!");
				System.out.println(strike + "S " + ball + "B " + out + "O");
				System.out.println();
				break;

			case 2:
				// ball
				ball++;
				pitchings++;
				System.out.println("볼!");
				System.out.println(strike + "S " + ball + "B " + out + "O");
				System.out.println();
				break;

			case 3:
				// out
				if (out < 2) {
					out++;
					pitchings++;
					System.out.println("아웃! 다음 타자가 타석에 입장했습니다");
					strike = 0;
					ball = 0;
					nextPlayer++;
					System.out.println(strike + "S " + ball + "B " + out + "O");
					System.out.println();
					break;

				} else {
					out++;
					pitchings++;
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
				pitchings++;
				hits++;
				System.out.println("안타! 다음 타자가 타석에 입장했습니다");
				strike = 0;
				ball = 0;
				nextPlayer++;
				System.out.println(strike + "S " + ball + "B " + out + "O");
				System.out.println();
				break;

			}

			if (strike == 3) {
				threeOuts++;
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

		BoardCount bc = new BoardCount(point, pitchings, threeOuts, hits);

		// return point;
		return bc;
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
