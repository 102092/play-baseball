package com.baseball02.service;

import java.util.Random;

import com.baseball02.model.Board;
import com.baseball02.model.BoardCount;
import com.baseball02.model.Score;
import com.baseball02.model.TeamData;
import com.baseball02.util.DataInputUtil;

public class Play implements PlayAction {

	private int skip = 0;

	@Override
	public void setData(TeamData t1, TeamData t2, int playerNumber) {

		String AteamName = t1.getTeamName();
		String BteamName = t2.getTeamName();

		int AteamPoints = 0;
		int BteamPoints = 0;

		BoardCount t1Counts = new BoardCount(0, 0, 0, 0);
		BoardCount t2Counts = new BoardCount(0, 0, 0, 0);

		Board board = new Board(t1, t2, t1Counts, t2Counts);

		boolean flag = false;

		// 회차
		int count = 1;

		while (true) {

			if (flag == false) {
				System.out.println("========== " + count + "초 " + AteamName + " 공격 " + "==========");
				System.out.println();
				board = play(board, playerNumber, flag, count);
				AteamPoints += board.getT1Counts().getPoints();
				flag = true;
			}

			if (flag == true) {
				System.out.println("========== " + count + "말 " + BteamName + " 공격 " + "==========");
				System.out.println();
				board = play(board, playerNumber, flag, count);
				BteamPoints += board.getT2Counts().getPoints();
				flag = false;
				count++;
			}

			if (count == 7)
				break;

		}

		endGame(AteamName, BteamName, AteamPoints, BteamPoints);

	}

	@Override
	public Board play(Board board, int playerNumber, boolean flag, int count) {

		String[] playerNames;
		double[] playerBAs;

		if (flag == false) {
			// 초
			playerNames = board.getT1().getPlayerName();
			playerBAs = board.getT1().getPlayerBA();

			board = doBatting(playerNames, playerBAs, playerNumber, flag, count, board);
		} else {
			// 말

			playerNames = board.getT2().getPlayerName();
			playerBAs = board.getT2().getPlayerBA();

			board = doBatting(playerNames, playerBAs, playerNumber, flag, count, board);
		}

		return board;

	}

	@Override
	public Board doBatting(String[] playerNames, double[] playerBAs, int playerNumber, boolean flag, int count,
			Board board) {

		// 6회말 종료조건
		earlyStop(count, flag, board);

		Out print = new Out();

		int strike = 0;
		int ball = 0;
		int out = 0;
		int hit = 0;

		int pitchings, threeOuts, hits, points;

		if (flag == false) {
			pitchings = board.getT1Counts().getPitchings();
			threeOuts = board.getT1Counts().getThreeOuts();
			hits = board.getT1Counts().getHits();
			points = 0;

		} else {
			pitchings = board.getT1Counts().getPitchings();
			threeOuts = board.getT1Counts().getThreeOuts();
			hits = board.getT1Counts().getHits();
			points = 0;

		}

		int nextPlayer = 0;

		while (out != 3) {

			if (nextPlayer >= playerNumber) {
				nextPlayer = 0;
			}

			String playerName = playerNames[nextPlayer];
			double playerBA = playerBAs[nextPlayer];

			if (flag == false) {
				board.setT1Counts(new BoardCount(pitchings, threeOuts, hits, points));
			} else {
				board.setT2Counts(new BoardCount(pitchings, threeOuts, hits, points));
			}

			print.printBoard(board, new Score(strike, ball, out, hit), count, flag);

			System.out.println(nextPlayer + 1 + "번 " + playerName);

			int random = rollDice(playerBA);

			switch (random) {

			case 1:
				// strike
				strike++;
				pitchings++;

				System.out.println("스트라이크!");
				print.printSBO(strike, ball, threeOuts);
				break;

			case 2:
				// ball
				ball++;
				pitchings++;
				System.out.println("볼!");
				print.printSBO(strike, ball, threeOuts);
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
					print.printSBO(strike, ball, threeOuts);
					break;

				} else {
					out++;
					pitchings++;
					System.out.println("아웃!");
					strike = 0;
					ball = 0;
					print.printSBO(strike, ball, threeOuts);
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
				print.printSBO(strike, ball, threeOuts);
				break;

			}

			if (strike == 3) {
				threeOuts++;
				out++;

				if (out == 3) {
					System.out.println("3 스트라이크 아웃!");
					strike = 0;
					ball = 0;
					print.printSBO(strike, ball, threeOuts);
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
				points++;
				System.out.println("득점하였습니다.");
				System.out.println("현재 점수: " + points);
				System.out.println();
				hit = 3;
			}

			if (skip == 0) {
				System.out.println("" + "다음 투구 보기(enter) or 스킵하고 X회말 후 투구보기(숫자+enter)");
				String userInput = DataInputUtil.getInput();

				if (userInput.equals("")) {
					continue;
				} else if ((Integer.parseInt(userInput) > 0 && Integer.parseInt(userInput) < 7
						&& Integer.parseInt(userInput) >= count)) {
					skip = Integer.parseInt(userInput);
				}
			}

		}
		if (flag == true) {
			skip--;
		}
		if (skip < 0) {
			skip = 0;
		}

		return board;
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

	@Override
	public Board earlyStop(int count, boolean flag, Board board) {

		if (count == 6 && flag == true && board.getT2Counts().getPoints() > board.getT1Counts().getPoints()) {
			return board;
		}
		return board;
	}

}
