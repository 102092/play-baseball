package com.baseball02.model;

public class Score {

	private int strike;
	private int ball;
	private int out;
	private int hit;

	public Score(int strike, int ball, int out, int hit) {
		this.strike = strike;
		this.ball = ball;
		this.hit = hit;
		this.out = out;
	}

	public int getStrike() {
		return strike;
	}

	public int getBall() {
		return ball;
	}

	public int getHit() {
		return hit;
	}

	public int getOut() {
		return out;
	}

}
