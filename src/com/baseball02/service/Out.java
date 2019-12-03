package com.baseball02.service;

import com.baseball02.model.Board;
import com.baseball02.model.Score;
import com.baseball02.model.TeamData;

public class Out implements OutAction {

	static int[] t1Points = { 0, 0, 0, 0, 0, 0 };
	static int[] t2Points = { 0, 0, 0, 0, 0, 0 };

	@Override
	public void printSBO(int strike, int ball, int out) {
		System.out.println(strike + "S " + ball + "B " + out + "O");
		System.out.println();

	}

	@Override
	public void selectMessage() {
		System.out.println("신나는 야구시합");
		System.out.println("1. 데이터 입력");
		System.out.println("2. 데이터 출력");
		System.out.println("3. 시합시작");
		System.out.println("0. 종료");

	}

	@Override
	public void closeMessage() {
		System.out.println("==================");
		System.out.println("프로그램을 종료합니다");
		System.out.println("==================");

	}

	@Override
	public void errorMessage() {
		System.out.println("잘못된 메뉴 선택입니다");
		System.out.println();

	}

	@Override
	public void printTeamData(TeamData td, int playerNumber) {

		System.out.println(td.getTeamName() + " 팀 정보");
		String[] playerNames = td.getPlayerName();
		double[] playerBAs = td.getPlayerBA();
		for (int i = 0; i < playerNumber; i++) {
			System.out.println(i + 1 + "번 " + playerNames[i] + ", " + playerBAs[i]);
		}
		System.out.println();

	}

	@Override
	public void printBoard(Board b, Score s, int c, boolean flag) {

		int t1Sum = 0;
		int t2Sum = 0;

		if (flag == false) {
			t1Points[c - 1] = b.getT1Counts().getPoints();

		} else {
			t2Points[c - 1] = b.getT2Counts().getPoints();
		}

		for (int i = 0; i < t1Points.length; i++) {
			t1Sum += t1Points[i];
		}

		for (int i = 0; i < t2Points.length; i++) {
			t2Sum += t2Points[i];
		}

		System.out.println("+--------------------------------+");
		System.out.println("| \t 1 2 3 4 5 6 | TOT");
		System.out.print("| " + b.getT1().getTeamName() + "\t ");
		for (int i = 0; i < t1Points.length; i++) {
			System.out.print(t1Points[i] + " ");
		}
		System.out.println("|  " + t1Sum);

		System.out.print("| " + b.getT2().getTeamName() + "\t ");
		for (int i = 0; i < t2Points.length; i++) {
			System.out.print(t2Points[i] + " ");
		}
		System.out.println("|  " + t2Sum);

		System.out.println("|  ");
		System.out.println("|  " + b.getT1().getTeamName() + "\t\t\t" + b.getT2().getTeamName());

		for (int i = 0; i < b.getT1().getPlayerName().length; i++) {

			if (i != 3 && i != 4 && i != 5) {
				System.out.println("| " + (i + 1) + ". " + b.getT1().getPlayerName()[i] + "\t\t\t" + (i + 1) + ". "
						+ b.getT2().getPlayerName()[i]);

			} else if (i == 3) {
				System.out.println("| " + (i + 1) + ". " + b.getT1().getPlayerName()[i] + "       S " + s.getStrike()
						+ "\t" + +(i + 1) + ". " + b.getT2().getPlayerName()[i]);
			} else if (i == 4) {
				System.out.println("| " + (i + 1) + ". " + b.getT1().getPlayerName()[i] + "       B " + s.getBall()
						+ "\t" + +(i + 1) + ". " + b.getT2().getPlayerName()[i]);
			} else if (i == 5) {
				System.out.println("| " + (i + 1) + ". " + b.getT1().getPlayerName()[i] + "       O " + s.getOut()
						+ "\t" + +(i + 1) + ". " + b.getT2().getPlayerName()[i]);
			}

		}

		System.out.println("|");
		System.out.println("|");
		System.out
				.println("| 투구: " + b.getT1Counts().getPitchings() + "\t\t" + "투구: " + b.getT2Counts().getPitchings());
		System.out.println(
				"| 삼진: " + b.getT1Counts().getThreeOuts() + "\t\t\t" + "삼진: " + b.getT2Counts().getThreeOuts());
		System.out.println("| 안타: " + b.getT1Counts().getHits() + "\t\t" + "안타: " + b.getT2Counts().getHits());
		System.out.println("+--------------------------------+");
		System.out.println();

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
