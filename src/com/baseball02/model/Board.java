package com.baseball02.model;

public class Board {
	private String t1Name;
	private String t2Name;

	private String[] t1Player;
	private String[] t2Player;

	private Score score;

	private BoardCount t1Counts;
	private BoardCount t2Counts;

	public Board(String t1Name, String t2Name, String[] t1Player, String[] t2Player, Score score, BoardCount t1Counts,
			BoardCount t2Counts) {
		this.t1Name = t1Name;
		this.t2Name = t2Name;
		this.t1Player = t1Player;
		this.t2Player = t2Player;
		this.score = score;
		this.t1Counts = t1Counts;
		this.t2Counts = t2Counts;
	}

	public String getT1Name() {
		return t1Name;
	}

	public String getT2Name() {
		return t2Name;
	}

	public String[] getT1Player() {
		return t1Player;
	}

	public String[] getT2Player() {
		return t2Player;
	}

	public Score getScore() {
		return score;
	}

	public BoardCount getT1Counts() {
		return t1Counts;
	}

	public BoardCount getT2Counts() {
		return t2Counts;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public void setT1Counts(BoardCount t1Counts) {
		this.t1Counts = t1Counts;
	}

	public void setT2Counts(BoardCount t2Counts) {
		this.t2Counts = t2Counts;
	}

}
