package com.baseball02;

import java.io.IOException;

import com.baseball02.model.TeamData;
import com.baseball02.service.Input;
import com.baseball02.service.Play;
import com.baseball02.service.PrintData;
import com.baseball02.util.DataInputUtil;

public class DoGame {

	static TeamData t1 = new TeamData();
	static TeamData t2 = new TeamData();
	final static int TOTAL = 2;

	public static void main(String[] args) throws IOException {

		Input in = new Input();
		PrintData out = new PrintData();
		Play py = new Play();

		// Selcet Menu
		while (true) {

			out.selectMessage();

			String input = DataInputUtil.getInput();

			if (input.equals("0")) {
				out.closeMessage();
				break;
			}

			switch (input) {

			case "1":
				System.out.println("메뉴선택 " + input + "번");
				System.out.print("1번팀의 정보를입력하세요> ");
				t1 = in.input(TOTAL);

				System.out.print("2번팀의 정보를입력하세요> ");
				t2 = in.input(TOTAL);

				if (t1 != null && t2 != null) {
					System.out.println("팀 데이터 입력이 완료되었습니다.");
					System.out.println();
					break;

				}

			case "2":
				System.out.println("메뉴선택 " + input + "번");
				out.printTeamData(t1, TOTAL);
				out.printTeamData(t2, TOTAL);
				break;

			case "3":
				System.out.println("메뉴선택 " + input + "번");
				System.out.println(t1.getTeamName() + " VS " + t2.getTeamName() + "의 경기를 시작합니다.");
				System.out.println();
				py.set(t1, t2, TOTAL);
				break;

			default:
				out.errorMessage();
				break;
			}

		}

	}

}