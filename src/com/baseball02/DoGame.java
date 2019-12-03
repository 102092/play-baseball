package com.baseball02;

import java.io.IOException;

import com.baseball02.model.TeamData;
import com.baseball02.service.Input;
import com.baseball02.service.Play;
import com.baseball02.service.Out;

public class DoGame {

	final static int TOTAL = 9;

	public static void main(String[] args) throws IOException {

		String tn1 = "마우스";
		String[] pn1 = { "지수", "호리", "통닭", "골드", "피자", "쭈꾸", "수리", "노트", "구파" };
		double[] pba1 = { 0.234, 0.332, 0.221, 0.300, 0.198, 0.287, 0.119, 0.223, 0.154 };

		String tn2 = "고양이";
		String[] pn2 = { "민수", "재후", "고일", "고이", "고삼", "고사", "고오", "고육", "고칠" };
		double[] pba2 = { 0.301, 0.198, 0.341, 0.412, 0.200, 0.108, 0.209, 0.308, 0.018 };

		// 팀 데이터 셋
		TeamData t1 = new TeamData(tn1, pn1, pba1);
		TeamData t2 = new TeamData(tn2, pn2, pba2);

		// Action 셋
		Input in = new Input();
		Out out = new Out();
		Play py = new Play();

		// Selcet Menu
		in.selectMenu(t1, t2, in, out, py, TOTAL);

	}

}