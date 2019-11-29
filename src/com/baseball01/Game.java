package com.baseball01;

import java.util.Random;

public class Game {

	public Score setGame() {

		System.out.println("신나는 야구 게임!");
		System.out.println("첫 번째 타자가 타석에 입장했습니다.");
		System.out.println("========================================");
		System.out.println();
		Score s = new Score(0, 0, 0, 0);

		return s;
	}

	public int startGame(Score s) {

		Random r = new Random();

		int strike = s.getStrike();
		int ball = s.getBall();
		int out = s.getOut();
		int hit = s.getHit();

		while (out != 3) {

			if (strike == 3) {
				out++;

				if (out == 3) {
					System.out.println("아웃!");
					strike = 0;
					ball = 0;
					System.out.println(strike + "S " + ball + "B " + out + "O");
					System.out.println();
				} else {
					System.out.println("3 스트라이크!");
					System.out.println("다음 타자가 타석에 입장했습니다");
					System.out.println();
					strike = 0;
					ball = 0;
				}
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
				if (out < 2) {
					out++;
					System.out.println("아웃! 다음 타자가 타석에 입장했습니다");
					strike = 0;
					ball = 0;
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
				System.out.println(strike + "S " + ball + "B " + out + "O");
				System.out.println();
				break;

			}

		}
		return hit;
	}

	public void endGame(int hits) {
		System.out.println("========================================");
		System.out.println("최종 안타수: " + hits);
		System.out.println("GAME OVER");

	}

	public static void main(String[] args) {

		Game g = new Game();
		Score s = g.setGame();
		int hits = g.startGame(s);
		g.endGame(hits);

	}

}
