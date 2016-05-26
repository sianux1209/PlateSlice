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
			System.out.println("������ ã�� �� �����ϴ�.");
			System.out.println("�Ϲ� ��� >> \"java -jar �������� �Է�����\"");
			System.out.println("�� ��� >> \"java -jar �������� �Է����� -a\"");
			System.out.println("�ִϸ��̼� ��� >> \"java -jar �������� �Է����� -t\"");
			return;
		}

		int T = scan.nextInt(); // test case ��
		for (t = 1; t <= T; t++) {

			begin = System.currentTimeMillis();

			ArrayList<Plate> plate = new ArrayList<Plate>();

			// �Է�
			plateQtt = scan.nextInt();
			backPlateWitch = scan.nextInt();

			for (int i = 0; i < plateQtt; i++) {
				plate.add(new Plate(scan.nextInt(), scan.nextInt()));
			}
			// ó��
			solve = new Solve(plate);
			int testCaseSize = solve.running();
			end = System.currentTimeMillis();

			// ���
			System.out.println(
					"Testcase " + t + " >> ������ ���� : " + testCaseSize + ", �ɸ� �ð� : " + (end - begin) / 1000.0 + "��");

			if (args.length == 2) {
				if (args[1].equals("-a")) {
					solve.view.printView();
					System.out.println();
				} else if (!args[1].equals("-t")) {
					solve.view.drawPlate();
				}
			} else
				solve.view.drawPlate();

		} // ���� ����
		
		System.out.println("�Ϲ� ��� >> \"java -jar �������� �Է�����\"");
		System.out.println("�� ��� >> \"java -jar �������� �Է����� -a\"");
		System.out.println("�ִϸ��̼� ��� >> \"java -jar �������� �Է����� -t\"");

	}
}
