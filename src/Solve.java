import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

class Solve {

	private static final Comparator<Plate> PlateComparator = (plate1, plate2) -> plate2.getLongerLength()
			- plate1.getLongerLength(); // �������� Comparator

	ArrayList<Plate> plate;
	Cursor cursor;
	View view;

	// Ŀ�� ����
	Solve(ArrayList<Plate> plate) {

		//int maxSum = plate.stream().mapToInt(p->p.getLongerLength()).sum();

		cursor = new Cursor(Main.backPlateWitch);
		this.plate = plate;
		view = new View(cursor);

	}

	public int running() {
		// TODO �˰��� ���� �޼ҵ�

		sortDescending(); // �������� ����
		checkOverWitch(); // ������ ū ö�� ������ ȸ���ϰ� ����Ʈ �Ǿ����� �̵��ϰ� ö�� �Է�

		// ö���� ���� �߸������� �ݺ�
		while (!plate.isEmpty()) {
			if (cursorCheck()) { // Ŀ���� ���� ��ġ�� �� �� �ִ� ö���� �ִ��� �˻�
				slicePlate(); // �Է� ������ ö���� �ִ��� �˻��ϰ� ������ ö�� �Է�

			}
			// �Է��� ö���� ������ Ŀ�� �̵�
			else
				cursor.moveY();

		} // ö���� ���� �߸�.
		
		return cursor.getMaxHeight();

	}

	boolean cursorCheck() {
		// TODO Ŀ���� ��ġ�� ö���� �Էµ� �� �ִ��� �˻�
		// Ŀ�� üũ�� �� ��ȿ ���� ���� ū�� ���ؼ� ��ȿ���� �� ������ ��ȿ ���� ����

		int availableWidth = cursor.getAvailableWidth();

		Optional<Plate> check = plate.stream()
				.filter(plate -> plate.getWidth() <= availableWidth || plate.getHeight() <= availableWidth)
				.findFirst();
		
		return check.isPresent();

	}

	private void slicePlate() {
		// TODO �Է� ������ ö���� �˻��ϰ� ������ ö�� �Է�

		Optional<Plate> op_slicePlate = Optional.empty();
		Plate slicePlate = null;
		
		// �Է��� �� �ִ� ö�� ã��
		while (true) {

			// ù��° �˻� ����
			op_slicePlate = plate.stream()
					.filter(plate -> plate.getHeight() <= cursor.getAvailableHeight())
					.filter(plate -> plate.getWidth() <= cursor.getAvailableWidth())
					.findFirst();

			// ���� ã���� break
			if (op_slicePlate.isPresent()) {
				slicePlate = op_slicePlate.get();

				break;
			} // ù��° �˻� ����
				// ���� ������ ��, ���̸� �ٲ㼭 ã�ƺ��� ������ �ش� ö�� ȸ�� �� break
			else {
				op_slicePlate = plate.stream()
						.filter(plate -> plate.getHeight() <= cursor.getAvailableWidth())
						.filter(plate -> plate.getWidth() <= cursor.getAvailableHeight())
						.findFirst();

				if (op_slicePlate.isPresent()) {
					slicePlate = op_slicePlate.get();
					slicePlate.rotate();
					break;

				}
				// �Է��� �� �ִ� ö���� ������ �ִ� ���� ����
			} // ȸ�� �� ��˻� ����

		} // �Է��� �� �ִ� ö�� ã�� ����

		// ���ǿ� ö�� �Է� �� ����Ʈ���� ö�� ����
		
		//Scanner s = new Scanner(System.in);
		//s.next();
		/*try{
			Thread.sleep(1000);
			
		}catch(Exception ee){}*/
		
		//System.out.println("\n��ȿ�� : " + cursor.getAvailableWidth() + ", " + "��ȿ���� : " + cursor.getAvailableHeight());
		//System.out.println("�ԷµǴ� ö���� �� = " + slicePlate.getWidth() + ", �ԷµǴ� ö���� ���� : " + slicePlate.getHeight());

		cursor.inputPlate(view.plate_view, slicePlate.getWidth(), slicePlate.getHeight());

		if(Main.argsCheck.equals("-t"))
			view.drawPlate(Main.sleepTime);
		
		//view.drawPlate();
		plate.remove(slicePlate);

		// System.out.println(slicePlate);
		//System.out.println("���� Ŀ�� ��ġ : " + cursor.getLocation());
		//System.out.println("��ȿ �� : " + cursor.getAvailableWidth() + " ��ȿ ���� : " + cursor.getAvailableHeight());

		//view.printView();

	}
	

	// ö�� �� �Ǵ� ���̰� �� ������� �������� ����
	private void sortDescending() {

		plate = (ArrayList<Plate>) this.plate.stream()
											 .sorted(PlateComparator)
											 .collect(toList());

	}

	private void checkOverWitch() {
		// TODO Auto-generated method stub

		int moveIndex = 0;

		for (int i = 0; i < plate.size(); i++) {

			// ���� ��� ȸ���ϰ� ����Ʈ �������� �̵�
			if (plate.get(i).getWidth() > Main.backPlateWitch) {
				plate.get(i).rotate();

				plate.add(moveIndex++, plate.get(i));
				plate.remove(i--); // ������ �ε����� �ٽ� �˻��ϵ��� �ϱ� ���Ͽ� i--
				continue;
			}
			// ö���� ���� ȸ����Ű�� �� �� ū �͵� ���� �Է�
			else {
				if (plate.get(i).getLongerLength() <= Main.backPlateWitch) {

					Plate temp;
					for (int j = 0; j < moveIndex; j++) {
						temp = plate.get(j);
						int height = temp.getHeight();
						int width = temp.getWidth();
						cursor.inputPlate(view.plate_view, width, height);
						plate.remove(temp);
						
						if(Main.argsCheck.equals("-t"))
							view.drawPlate(Main.sleepTime);

					}
					return;
				}

			}

		}

	}

}
