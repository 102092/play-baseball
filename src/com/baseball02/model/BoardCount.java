package com.baseball02.model;

public class BoardCount {
	private int pitchings;
	private int threeOuts;
	private int hits;
	private int points;

	public BoardCount(int pitchings, int threeOuts, int hits, int points) {
		this.pitchings = pitchings;
		this.threeOuts = threeOuts;
		this.hits = hits;
		this.points = points;
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

	public int getPoints() {
		return points;
	}

}
