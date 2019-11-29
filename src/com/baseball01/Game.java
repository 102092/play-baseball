package com.baseball01;

import java.util.Random;

public class Game {

	static int strike = 0;
	static int ball = 0;
	static int hit = 0;
	static int out = 0;

	public void setGame() {

		System.out.println("신나는 야구 게임!");
		System.out.println("첫 번째 타자가 타석에 입장했습니다.");
		System.out.println();

	}

	public void startGame() {
		Random r = new Random();

		while (out != 3) {

			if (strike == 3) {
				out++;
				System.out.println("3 스트라이크!");
				System.out.println("다음 타자가 타석에 입장했습니다");
				System.out.println();
				strike = 0;
				ball = 0;
			}

			if (ball == 4) {
				hit++;
				System.out.println("4 볼!");
				System.out.println("다음 타자가 타석에 입장했습니다");
				System.out.println();
				strike = 0;
				ball = 0;
			}

			int random = r.nextInt(4) + 1; // 1~4

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
				out++;
				System.out.println("아웃! 다음 타자가 타석에 입장했습니다");
				strike = 0;
				ball = 0;
				System.out.println(strike + "S " + ball + "B " + out + "O");
				System.out.println();
				break;

			case 4:
				// hit
				hit++;
				System.out.println("안타! 다음 타자가 타석에 입장했습니다");
				strike = 0;
				ball = 0;
				System.out.println(strike + "S " + ball + "B " + out + "O");
				System.out.println();
				break;

			}

		}
	}

	public void endGame() {
		System.out.println("최종 안타수: " + hit);
		System.out.println("GAME OVER");

	}

	public static void main(String[] args) {

		Game g = new Game();
		g.setGame();
		g.startGame();
		g.endGame();

	}

}
