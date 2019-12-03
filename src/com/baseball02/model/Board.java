package com.baseball02.model;

public class Board {
	private TeamData t1;
	private TeamData t2;

	private BoardCount t1Counts;
	private BoardCount t2Counts;

	public Board(TeamData t1, TeamData t2, BoardCount t1Counts, BoardCount t2Counts) {
		this.t1 = t1;
		this.t2 = t2;
		this.t1Counts = t1Counts;
		this.t2Counts = t2Counts;
	}

	public TeamData getT1() {
		return t1;
	}

	public TeamData getT2() {
		return t2;
	}

	public BoardCount getT1Counts() {
		return t1Counts;
	}

	public BoardCount getT2Counts() {
		return t2Counts;
	}

	public void setT1Counts(BoardCount t1Counts) {
		this.t1Counts = t1Counts;
	}

	public void setT2Counts(BoardCount t2Counts) {
		this.t2Counts = t2Counts;
	}

}
