package com.baseball02.model;

public class BoardCount {
	private int points;
	private int pitchings;
	private int threeOuts;
	private int hits;

	public BoardCount(int points, int pitchings, int threeOuts, int hits) {
		this.points = points;
		this.pitchings = pitchings;
		this.threeOuts = threeOuts;
		this.hits = hits;
	}

	public int getPoints() {
		return points;
	}

	public int getPitchings() {
		return pitchings;
	}

	public int getThreeOuts() {
		return threeOuts;
	}

	public int getHits() {
		return hits;
	}

}
