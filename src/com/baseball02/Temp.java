package com.baseball02;

import java.util.Random;

public class Temp {

	public static void main(String[] args) {
		Random r = new Random();
		double ba = 0.499;

		double hitProbability = ba;
		double sbProbability = (1 - ba) / 3 - 0.05;
		double outProbability = 0.100;

		double d = Double.parseDouble(String.format("%.3f",r.nextDouble()));
		int temp = r.nextInt(4) + 1;

		System.out.println("hit " + hitProbability);
		System.out.println("sb " + sbProbability);
		System.out.println("random " + d);
		System.out.println(temp);
		
		double ss = 0.134;
		System.out.println(String.valueOf(ss).length());

	}

}
