package com.baseball01;

public class Score {

    private int strike;
    private int ball;
    private int hit;
    private int out;

    public Score(int strike, int ball, int hit, int out) {
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