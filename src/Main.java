import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static Scanner scan;
	static int plateQtt;
	static int backPlateWitch;
	static int t;
	static final int sleepTime = 500;
	static String argsCheck = "";

	public static void main(String[] args) {
		long begin, end;
		Solve solve = null;

		if (args.length == 2)
			argsCheck = args[1];

		try {
			// scan = new Scanner(new File("check.txt"));
			scan = new Scanner(new File(args[0]));
		} catch (NullPointerException | FileNotFoundException | ArrayIndexOutOfBoundsException e) {
			// System.out.println(fe.getMessage());
			System.out.println("파일을 찾을 수 없습니다.");
			System.out.println("일반 출력 >> \"java -jar 실행파일 입력파일\"");
			System.out.println("상세 출력 >> \"java -jar 실행파일 입력파일 -a\"");
			System.out.println("애니메이션 출력 >> \"java -jar 실행파일 입력파일 -t\"");
			return;
		}

		int T = scan.nextInt(); // test case 수
		for (t = 1; t <= T; t++) {

			begin = System.currentTimeMillis();

			ArrayList<Plate> plate = new ArrayList<Plate>();

			// 입력
			plateQtt = scan.nextInt();
			backPlateWitch = scan.nextInt();

			for (int i = 0; i < plateQtt; i++) {
				plate.add(new Plate(scan.nextInt(), scan.nextInt()));
			}
			// 처리
			solve = new Solve(plate);
			int testCaseSize = solve.running();
			end = System.currentTimeMillis();

			// 출력
			System.out.println(
					"Testcase " + t + " >> 후판의 높이 : " + testCaseSize + ", 걸린 시간 : " + (end - begin) / 1000.0 + "초");

			if (args.length == 2) {
				if (args[1].equals("-a")) {
					solve.view.printView();
					System.out.println();
				} else if (!args[1].equals("-t")) {
					solve.view.drawPlate();
				}
			} else
				solve.view.drawPlate();

		} // 메인 종료
		
		System.out.println("일반 출력 >> \"java -jar 실행파일 입력파일\"");
		System.out.println("상세 출력 >> \"java -jar 실행파일 입력파일 -a\"");
		System.out.println("애니메이션 출력 >> \"java -jar 실행파일 입력파일 -t\"");

	}
}
