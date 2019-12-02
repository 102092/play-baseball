package com.baseball02.model;

public class Score {

	private int strike;
	private int ball;
	private int hit;
	private int out;
	private int point;

	public Score(int strike, int ball, int hit, int out, int point) {
		this.strike = strike;
		this.ball = ball;
		this.hit = hit;
		this.out = out;
		this.point = point;
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

	public int getPoint() {
		return point;
	}

}
