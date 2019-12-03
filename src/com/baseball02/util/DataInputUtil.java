package com.baseball02.util;

import java.util.Scanner;

public class DataInputUtil {

	public static String getInput() {
//		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//		String input = bf.readLine();
//		return input;
		
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		return input;
	}
	
}
