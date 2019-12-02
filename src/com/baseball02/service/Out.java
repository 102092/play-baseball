package com.baseball02.service;

import com.baseball02.model.Board;
import com.baseball02.model.Score;
import com.baseball02.model.TeamData;

public class Out implements OutAction {

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
	public void printBoard() {
		
		System.out.println("------------------------------");
		System.out.println("|"+"/t"+"1 2 3 4 5 6  | TOT"+"/t"+"|");
		System.out.println("|"+"Mouse  "+"0 0 0 0 0 0  | 0"+"/t+"+"|");
		System.out.println("|"+"Cat    "+"0 0 0 0 0 0  | 0"+"/t+"+"|");
		System.out.println("|"+"/t/t/t/t/t"+"|");
		System.out.println("|"+"Mouse"+"/t/t/t"+"Cats"+"|");
		
		
		
	}

}
