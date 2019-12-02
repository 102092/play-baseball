package com.baseball02;

import java.io.IOException;

import com.baseball02.model.TeamData;
import com.baseball02.service.Input;
import com.baseball02.service.Play;
import com.baseball02.service.Out;

public class DoGame {

	final static int TOTAL = 2;

	public static void main(String[] args) throws IOException {

		// 팀 데이터 셋
		TeamData t1 = new TeamData();
		TeamData t2 = new TeamData();

		// Action 셋
		Input in = new Input();
		Out out = new Out();
		Play py = new Play();

		// Selcet Menu
		in.selectMenu(t1, t2, in, out, py, TOTAL);

	}

}