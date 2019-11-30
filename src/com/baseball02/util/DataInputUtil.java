package com.baseball02.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataInputUtil {

	public static String getInput() throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String input = bf.readLine();
		return input;
	}
	
}
